/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import model.DoublesTeam;
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
        Query q = em.createQuery("Select distinct m FROM tmatch m "
                + "LEFT JOIN FETCH m.teams "
                + "join m.teams t join t.players p where p.id = :pid");
        q.setParameter("pid", current.getId());
        return q.getResultList();
    }

    public String applyTournament(Tournament tr) {
        // Request Check
        Query q = em.createQuery("select tjr FROM TournamentJoinRequest tjr"
                + " join tjr.tournament tour join tjr.team team join team.players p"
                + " where tour.id = :tid AND p.id = :pid");
        q.setParameter("tid", tr.getId());
        q.setParameter("pid", current.getId());
        if(!q.getResultList().isEmpty()){
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("You have already sent a request for this Tournament."));
            return "player:index";
        }
        // Tournament Check
        q = em.createQuery("select tour FROM Tournament tour"
                + " join tour.teams team join team.players p"
                + " WHERE tour.id = :tid AND p.id = :pid");
        q.setParameter("tid", tr.getId());
        q.setParameter("pid", current.getId());
        if(!q.getResultList().isEmpty()){
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("You are already participating this Tournament."));
            return "player:index";
        }
        selectedTournament = tr;
        if (!isDoubles()) {
            try {
                Team team = new SinglesTeam(current);
                team = new SinglesTeam(current);
                utx.begin();
                em.persist(team);
                TournamentJoinRequest tjr = new TournamentJoinRequest(selectedTournament, team);
                tjr.setManager(selectedTournament.getManager());
                em.persist(tjr);                
                utx.commit();
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Your request to join tournament is sent to the Manager."));
            } catch (Exception ex) {
                Logger.getLogger(PlayerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
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
                tjr.setManager(selectedTournament.getManager());
                em.persist(tjr);
                utx.commit();
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Your request to join tournament is sent to the Manager."));
                return "player:index";
            } catch (Exception ex) {
                Logger.getLogger(PlayerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "player:createTeam";
    }

    public Player getCurrentPlayer() {
        return current;
    }

    public List<Player> getOtherPlayers() {
        Query q = em.createQuery("select distinct p from Player p "
                + "WHERE p.id != :pid AND p not in "
                + "(select distinct pl from Player pl "
                + "join pl.teams tm join tm.tournament tr "
                + "where tr.id = :tid) AND p not in "
                + "(select distinct pla from TournamentJoinRequest tjr "
                + "join tjr.team tea join tea.players pla)");
        q.setParameter("pid", current.getId());
        q.setParameter("tid", selectedTournament.getId());
        List<Player> players = q.getResultList();
        return players;
    }

    public List<Tournament> getTournaments() {
        Query q = em.createQuery("select distinct t from Tournament t join t.teams team join team.players p where p.id = :pid ");
        q.setParameter("pid", current.getId());
        return q.getResultList();
    }

    public boolean isDoubles() {
        String type = selectedTournament.getType();
        return (type.equals(Tournament.MENS_DOUBLES) || type.equals(Tournament.WOMENS_DOUBLES)
                || type.equals(Tournament.MIXED_DOUBLES));
    }
}
