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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import model.Draw;
import model.Match;
import model.MatchStatistics;
import model.Team;
import model.Tournament;
import model.Umpire;

@ManagedBean
@SessionScoped
public class UmpireManagedBean implements Serializable {

    @PersistenceContext(unitName = "TTMPU")
    private EntityManager em;
    @Resource
    private UserTransaction utx;
    private Umpire current;
    private Match selectedMatch;

    /** Creates a new instance of UmpireManagedBean */
    public UmpireManagedBean() {
        FacesContext context = FacesContext.getCurrentInstance();
        current = (Umpire) context.getExternalContext().getSessionMap().get(RegisteredUserManagedBean.USER_SESSION_KEY);
    }

    public List<Match> getMatches() {
        Query q = em.createQuery("SELECT distinct m FROM tmatch m "
                + "left JOIN fetch m.teams"
                + " JOIN m.umpires u"
                + " WHERE u.id = :uid");
        q.setParameter("uid", current.getId());
        return q.getResultList();
    }

    public List<Tournament> getTournaments() {
        Query q = em.createQuery("SELECT distinct t FROM Umpire u JOIN u.tournaments t");
        current.setTournaments(q.getResultList());
        return current.getTournaments();
    }

    public String linkStatistics(Match match) {
        selectedMatch = match;
        return "umpire:editStatistics";
    }

    public String editStatistics() {
        try {
            utx.begin();
            em.persist(em.merge(selectedMatch.getStatistics()));
            em.persist(em.merge(selectedMatch));
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(UmpireManagedBean.class.getName()).log(Level.SEVERE, e.getMessage());
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
        }
        return "umpire:index";
    }

    public boolean canAssignWinner(Tournament t, Team tm) {
        return !tm.equals(t.getDraw().getWinnerTeam());
    }

    public String selectWinner(Team winner, Tournament t) {
        Draw d = t.getDraw();
        d.setWinnerTeam(winner);
        try {
            utx.begin();
            em.merge(d);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(UmpireManagedBean.class.getName()).log(Level.SEVERE, e.getMessage());
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
        }
        return "umpire:matchDetails";
    }

    public MatchStatistics getStatistics() {
        return selectedMatch.getStatistics();
    }

    public Match getSelectedMatch() {
        return selectedMatch;
    }

    public void setSelectedMatch(Match selectedMatch) {
        this.selectedMatch = selectedMatch;
    }
}
