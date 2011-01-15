/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Match;
import model.Referee;
import model.Tournament;

/**
 *
 * @author Natan
 */
@ManagedBean
@SessionScoped
public class RefereeManagedBean {

    @PersistenceContext
    private EntityManager em;
    private Referee referee;

    /** Creates a new instance of RefereeManagedBean */
    public RefereeManagedBean() {
        FacesContext fc = FacesContext.getCurrentInstance();
        referee = (Referee) fc.getExternalContext().getSessionMap().get(RegisteredUserManagedBean.USER_SESSION_KEY);
    }

    public List<Tournament> getTournaments(){
        return referee.getTournaments();
    }

    public List<Match> getMatches(){
        return new ArrayList<Match>();
        /*Query q = em.createQuery("from tmatch m where :referee in m.referees");
        q.setParameter("referee", referee);
        return q.getResultList();*/
    }

}
