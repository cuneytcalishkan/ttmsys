/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import model.DoublesTeam;
import model.Manager;
import model.Match;
import model.Player;
import model.SinglesTeam;
import model.Team;
import model.Tournament;
import model.TournamentJoinRequest;

@Named(value = "playerManagedBean")
@SessionScoped
public class PlayerManagedBean implements Serializable {

    @PersistenceContext
    private EntityManager em;
    @Resource
    private UserTransaction utx;
    private Player current;
    private Tournament selectedTournament;

    /** Creates a new instance of PlayerManagedBean */
    public PlayerManagedBean() {
        FacesContext context = FacesContext.getCurrentInstance();
        current = (Player) context.getExternalContext().getSessionMap().get(RegisteredUserManagedBean.USER_SESSION_KEY);
    }

    public List<Match> getMatches() {
        /*Query q = em.createQuery("SELECT teams FROM Player as player JOIN player.teams as teams WHERE player.id = :pid");
        q.setParameter("pid", current.getId());*/
        List<Team> teams = current.getTeams();//q.getResultList();
        System.out.println(teams.size());
        List<Match> result = new ArrayList<Match>();
        for (Team team : teams) {
            Query q = em.createQuery("SELECT tm FROM Team t join t.matches tm WHERE t.id = :tid");
            q.setParameter("tid", team.getId());
            result.addAll(q.getResultList());
        }
        return result;
    }

    public String applyTournament(Tournament tr) {
        selectedTournament = tr;
        if (!isDoubles()) {
            Query q = em.createQuery("FROM SinglesTeam tm join tm.players p WHERE p.id = :pid");
            //        + "from SinglesTeam t where t.players.id = :pid");
            q.setParameter("pid", current.getId());
            Team team = (Team) q.getResultList().get(0);
            if(team != null)
                joinWithExistingTeam(team);
            //TODO message
            return "player:index";
        } else {
            return "player:createTeam";
        }
    }

    public String createTeamAndJoin(Player player2) {
        Team team = null;

        try {
            team = new DoublesTeam();
            team.join(current);
            team.join(player2);
        } catch (Exception ex) {
            Logger.getLogger(PlayerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (team != null) {
            try {
                utx.begin();
                em.persist(team);
                TournamentJoinRequest tjr = new TournamentJoinRequest(selectedTournament, team);
                em.persist(tjr);
                Query q = em.createQuery("select man from Manager AS man left join fetch man.tournamentRequests tr"
                        + " where man.id = :mid");
                q.setParameter("mid", selectedTournament.getManager().getId());
                Manager tManager = (Manager) q.getSingleResult();
                tManager.addTournamentJoinRequest(tjr);

                em.merge(tManager);
                utx.commit();
                return "player:index";
            } catch (Exception ex) {
                Logger.getLogger(PlayerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "player:createTeam";
    }

    public String joinWithExistingTeam(Team team) {
        if (team != null) {
            try {
                TournamentJoinRequest tjr = new TournamentJoinRequest(selectedTournament, team);
                Manager tManager = selectedTournament.getManager();
                Query q = em.createQuery("select man from Manager AS man left join fetch man.tournamentRequests where man.id = :mid");
                q.setParameter("mid", tManager.getId());
                tManager = (Manager) q.getSingleResult();
                tManager.addTournamentJoinRequest(tjr);
                utx.begin();
                em.persist(tjr);
                em.merge(tManager);
                utx.commit();
                return "player:index";
            } catch (Exception ex) {
                Logger.getLogger(PlayerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "player:createTeam";
    }

    public List<Team> getExistingTeams() {
        String query = "select t from DoublesTeam t left join fetch t.players tp "
                + "WHERE tp.id = :pid";
        Query q = em.createQuery(query);
        q.setParameter("pid", current.getId());
        return q.getResultList();
    }

    public Player getCurrentPlayer() {
        return current;
    }

    public List<Player> getOtherPlayers() {
        Query q = em.createQuery("from Player p WHERE p.id != :pid");
        q.setParameter("pid", current.getId());
        List<Player> players = q.getResultList();
        return players;
    }

    public boolean isDoubles() {
        String type = selectedTournament.getType();
        return (type.equals(Tournament.MENS_DOUBLES) || type.equals(Tournament.WOMENS_DOUBLES)
                || type.equals(Tournament.MIXED_DOUBLES));
    }
}
