/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.List;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import model.Player;
import model.RegisteredUser;

@Named(value = "trackerManagedBean")
@Dependent
public class TrackerManagedBean {

    @PersistenceContext
    private EntityManager em;
    @Resource
    private UserTransaction utx;
    private RegisteredUser current;

    /** Creates a new instance of TrackerManagedBean */
    public TrackerManagedBean() {
        FacesContext context = FacesContext.getCurrentInstance();
        current = (RegisteredUser) context.getExternalContext().getSessionMap().get(RegisteredUserManagedBean.USER_SESSION_KEY);
    }

    public List<Player> getTrackList() {
        Query q = em.createQuery("SELECT pl FROM Player pl WHERE pl NOT IN (SELECT t FROM " + current.getClass().getName() + " p JOIN p.trackList t)");
        return q.getResultList();
    }

    public void trackPlayer(ActionEvent event) {
        Player p = (Player) event.getComponent().getAttributes().get("player");
        current.addToTrackList(p);
        try {
            utx.begin();
            em.persist(em.merge(current));
            utx.commit();
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
        }
    }
}
