/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Match;
import model.Player;
import model.Tournament;
import model.Umpire;

@ManagedBean
@SessionScoped
public class UmpireManagedBean {

    @PersistenceContext(unitName = "TTMPU")
    private EntityManager em;
    private Umpire current;

    /** Creates a new instance of UmpireManagedBean */
    public UmpireManagedBean() {
        FacesContext context = FacesContext.getCurrentInstance();
        current = (Umpire) context.getExternalContext().getSessionMap().get(RegisteredUserManagedBean.USER_SESSION_KEY);
    }

    public List<Match> getMatches() {
        Query q = em.createQuery("SELECT m FROM Umpire u JOIN u.matches m WHERE u.id = :uid");
        q.setParameter("uid", current.getId());
        return q.getResultList();
    }

    public List<Tournament> getTournaments() {
        Query q = em.createQuery("SELECT t FROM Umpire u JOIN u.tournaments t WHERE u.id = :uid");
        q.setParameter("uid", current.getId());
        return q.getResultList();
    }

    public List<Player> getTrackList() {
        Query q = em.createQuery("SELECT t FROM Umpire u JOIN u.trackList t WHERE u.id = :uid");
        q.setParameter("uid", current.getId());
        return q.getResultList();
    }
}
