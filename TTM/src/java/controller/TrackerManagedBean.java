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
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
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
        String query = "SELECT DISTINCT team FROM " + current.getClass().getSimpleName()
                + " AS user JOIN user.trackList AS team WHERE user.id = :pid";
        Query q = em.createQuery(query);
        q.setParameter("pid", current.getId());
        current.setTrackList(q.getResultList());
        return current.getTrackList();
    }

    public List<Team> getTrackList() {
        String query = "SELECT DISTINCT team FROM Team AS team WHERE team NOT IN "
                + "(SELECT DISTINCT ut  FROM " + current.getClass().getSimpleName() + " AS user "
                + "JOIN user.trackList AS ut WHERE user.id = :uid )";
        Query q = em.createQuery(query);
        q.setParameter("uid", current.getId());
        List<Team> result = q.getResultList();
        return result;
    }

    public void removeTeam(Team t) {
        current.removeFromTrackList(t);
        try {
            utx.begin();
            em.merge(current);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(TrackerManagedBean.class.getName()).log(Level.SEVERE, e.getMessage());
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
        }
    }

    public void trackTeam(Team t) {
        current.addToTrackList(t);
        try {
            utx.begin();
            em.merge(current);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(TrackerManagedBean.class.getName()).log(Level.SEVERE, e.getMessage());
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
        }
    }
}
