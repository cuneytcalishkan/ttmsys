/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Match;
import model.Team;
import model.Tournament;

@Named(value = "teamManagedBean")
@SessionScoped
public class TeamManagedBean implements Serializable{

    @PersistenceContext
    private EntityManager em;
    private Team current;

    /** Creates a new instance of TeamManagedBean */
    public TeamManagedBean() {
    }

    public String linkTeam(Team t) {
        current = t;
        return "teamInfo";
    }

    public List<Match> getMatches() {
        Query q = em.createQuery("SELECT t FROM Team AS t LEFT JOIN FETCH t.matches AS tm WHERE t.id = :tid");
        q.setParameter("tid", current.getId());
        current = (Team) q.getSingleResult();
        return current.getMatches();
    }

    public Tournament getTournaments() {
        return current.getTournament();
    }
}
