/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import model.RegisteredUser;
import model.Team;

@Named(value = "trackerManagedBean")
@SessionScoped
public class TrackerManagedBean implements Serializable {

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

    public List<Team> getUserTrackList() {
        String query = "SELECT team FROM " + current.getClass().getName() + " AS man JOIN man.trackList AS team";
        Query q = em.createQuery(query);
        current.setTrackList(q.getResultList());
        for (Team team : current.getTrackList()) {
            query = "SELECT player FROM Team AS team JOIN team.players as player";
            q = em.createQuery(query);
            team.setPlayers(q.getResultList());
        }
        return current.getTrackList();
    }

    public List<Team> getTrackList() {
        String query = "SELECT team FROM Team AS team WHERE team NOT IN (SELECT t FROM " + current.getClass().getName() + " AS user JOIN user.trackList t)";
        Query q = em.createQuery(query);
        List<Team> result = q.getResultList();
        for (Team team : result) {
            query = "SELECT player FROM Team AS team JOIN team.players as player";
            q = em.createQuery(query);
            team.setPlayers(q.getResultList());
        }
        return result;
    }

    public void trackPlayer(ActionEvent event) {
        Team p = (Team) event.getComponent().getAttributes().get("player");
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
