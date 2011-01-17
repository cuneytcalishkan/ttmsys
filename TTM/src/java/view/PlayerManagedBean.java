/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import model.Match;
import model.Player;
import model.Team;
import model.Tournament;

@Named(value = "playerManagedBean")
@SessionScoped
public class PlayerManagedBean implements Serializable {

    @PersistenceContext
    private EntityManager em;
    @Resource
    private UserTransaction utx;
    private Player current;
    private Tournament selectedTournament;
    
    private Team existingTeam;
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
        return "player:index";
    }

    public String joinWithExistingTeam(){
        return "player:index";
    }

    public List<Team> getExistingTeams(){
        Query q = em.createQuery("select t from Team t left join fetch t.players join t.players tp "
                + "WHERE tp.id = :pid AND t.teamType = :type");
        q.setParameter("pid", current.getId());
        q.setParameter("type", selectedTournament.getType());
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

    public void setExistingTeam(Team existingTeam) {
        this.existingTeam = existingTeam;
    }

    public void setOtherPlayer(Player otherPlayer) {
        this.otherPlayer = otherPlayer;
    }

    public Team getExistingTeam() {
        return existingTeam;
    }

    public Player getOtherPlayer() {
        return otherPlayer;
    }
    
}
