/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Match;
import model.Referee;
import model.Tournament;

/**
 *
 * @author Natan
 */
@Named(value="refereeManagedBean")
@Dependent
public class RefereeManagedBean {

    @PersistenceContext(unitName = "TTMPU")
    private EntityManager em;
    private Referee referee;

    /** Creates a new instance of RefereeManagedBean */
    public RefereeManagedBean() {
        FacesContext context = FacesContext.getCurrentInstance();
        referee = (Referee) context.getExternalContext().getSessionMap().get(RegisteredUserManagedBean.USER_SESSION_KEY);
    }
    
    public List<Tournament> getTournaments(){
        Query q = em.createQuery("SELECT m FROM Referee r JOIN r.tournaments m WHERE r.id = :uid");
        q.setParameter("uid", referee.getId());
        return q.getResultList();
    }

    public List<Match> getMatches(){
        Query q = em.createQuery("SELECT m FROM Referee r JOIN r.matches m WHERE r.id = :uid");
        q.setParameter("uid", referee.getId());
        return q.getResultList();
    }
}
