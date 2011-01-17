/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.Serializable;
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
    
    private Player otherPlayer;

    /** Creates a new instance of PlayerManagedBean */
    public PlayerManagedBean() {
        FacesContext context = FacesContext.getCurrentInstance();
        current = (Player) context.getExternalContext().getSessionMap().get(RegisteredUserManagedBean.USER_SESSION_KEY);
    }

    public List<Match> getMatches() {
        Query q = em.createQuery("SELECT m FROM Player p JOIN p.matches m");
        return q.getResultList();
    }

    public String applyTournament(Tournament tr)
    {
        selectedTournament = tr;
        return "player:createTeam";
    }

    public String createTeamAndJoin(){
        Team team = null;
        if(selectedTournament.getType().equals(Tournament.MENS_SINGLES) ||
                selectedTournament.getType().equals(Tournament.WOMENS_SINGLES)){
            try {
                team = new SinglesTeam(current);
            } catch (Exception ex) {
                Logger.getLogger(PlayerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            try {
                team = new DoublesTeam();
                team.join(current);
                team.join(otherPlayer);
            } catch (Exception ex) {
                Logger.getLogger(PlayerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(team != null){
            try {
                TournamentJoinRequest tjr = new TournamentJoinRequest(selectedTournament, team);
                Manager tManager = selectedTournament.getManager();
                tManager.addTournamentJoinRequest(tjr);
                utx.begin();
                em.persist(team);
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

    public String joinWithExistingTeam(Team team){
        if(team != null)
        {
            try {
                TournamentJoinRequest tjr = new TournamentJoinRequest(selectedTournament, team);
                Manager tManager = selectedTournament.getManager();
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

    public List<Team> getExistingTeams(){
        String query = "select t from DoublesTeam t left join fetch t.players join t.players tp "
                + "WHERE tp.id = :pid";
        if(selectedTournament.getType().equals(Tournament.MENS_SINGLES) ||
                selectedTournament.getType().equals(Tournament.WOMENS_SINGLES))
            query = "select t from SinglesTeam t left join fetch t.players join t.players tp "
                + "WHERE tp.id = :pid";
        Query q = em.createQuery(query);
        q.setParameter("pid", current.getId());
        return q.getResultList();
    }

    public Player getCurrentPlayer(){
        return current;
    }

    public List<Player> getOtherPlayers(){
        Query q = em.createQuery("from Player");
        List<Player> players = q.getResultList();
        players.remove(current);
        return players;
    }

    public boolean isDoubles(){
        String type = selectedTournament.getType();
        return (type.equals(Tournament.MENS_DOUBLES) || type.equals(Tournament.WOMENS_DOUBLES)
                || type.equals(Tournament.MIXED_DOUBLES));
    }

    public void setOtherPlayer(Player otherPlayer) {
        this.otherPlayer = otherPlayer;
    }

    public Player getOtherPlayer() {
        return otherPlayer;
    }
    
}
