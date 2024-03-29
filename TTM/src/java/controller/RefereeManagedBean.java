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
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import model.Match;
import model.Referee;
import model.Tournament;

@Named(value = "refereeManagedBean")
@SessionScoped
public class RefereeManagedBean implements Serializable {

    @PersistenceContext(unitName = "TTMPU")
    private EntityManager em;
    @Resource
    private UserTransaction utx;
    private Referee referee;
    private Match selectedMatch;

    /** Creates a new instance of RefereeManagedBean */
    public RefereeManagedBean() {
        FacesContext context = FacesContext.getCurrentInstance();
        referee = (Referee) context.getExternalContext().getSessionMap().get(RegisteredUserManagedBean.USER_SESSION_KEY);
    }

    public List<Tournament> getTournaments() {
        Query q = em.createQuery("SELECT distinct r FROM Referee r LEFT JOIN FETCH r.tournaments m"
                + " where r.id = :rid");
        q.setParameter("rid", referee.getId());
        referee = (Referee) q.getSingleResult();
        return referee.getTournaments();
    }

    public List<Match> getMatches() {
        Query q = em.createQuery("SELECT distinct m FROM tmatch m LEFT JOIN FETCH m.teams "
                + "join m.referees r "
                + "WHERE r.id = :rid");
        q.setParameter("rid", referee.getId());
        return q.getResultList();
    }

    public String linkMatch(Match match) {
        selectedMatch = match;
        return "referee:editReport";
    }

    public String editReport() {
        try {
            utx.begin();
            em.merge(selectedMatch);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(RefereeManagedBean.class.getName()).log(Level.SEVERE, e.getMessage());
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
        }
        return "referee:index";
    }

    public Match getSelectedMatch() {
        return selectedMatch;
    }

    public void setSelectedMatch(Match selectedMatch) {
        this.selectedMatch = selectedMatch;
    }
}
