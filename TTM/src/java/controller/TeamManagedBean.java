/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
public class TeamManagedBean implements Serializable {

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
        Query q = em.createQuery("SELECT distinct tm FROM Team AS t JOIN t.matches AS tm LEFT JOIN FETCH tm.teams WHERE t.id = :tid");
        q.setParameter("tid", current.getId());
        current.setMatches(q.getResultList());
        return current.getMatches();
    }

    public Tournament getTournaments() {
        return current.getTournament();
    }
}
