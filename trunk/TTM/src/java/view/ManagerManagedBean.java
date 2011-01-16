/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import model.Manager;
import model.MembershipRequest;
import model.Player;
import model.RegisteredUser;
import model.Tournament;
import model.TournamentJoinRequest;

@Named(value = "managerManagedBean")
@Dependent
public class ManagerManagedBean {

    @PersistenceContext
    private EntityManager em;
    @Resource
    private UserTransaction utx;
    private Manager manager;

    /** Creates a new instance of ManagerManagedBean */
    public ManagerManagedBean() {
        FacesContext context = FacesContext.getCurrentInstance();
        manager = (Manager) context.getExternalContext().getSessionMap().get(RegisteredUserManagedBean.USER_SESSION_KEY);
    }

    public void createTournament() {
    }

    public List<Tournament> getTournaments() {
        Query q = em.createQuery("SELECT t FROM Manager m JOIN m.tournaments t");
        return q.getResultList();
    }

    public List<MembershipRequest> getMembershipRequests() {
        Query q = em.createQuery("from MembershipRequest");
        return q.getResultList();
    }

    public List<TournamentJoinRequest> getTournamentJoinRequests() {
        Query q = em.createQuery("SELECT r FROM Manager m JOIN m.tournamentRequests r");
        return q.getResultList();
    }

    public void denyTournamentJoinRequest(ActionEvent event) {
        try {
            TournamentJoinRequest req = (TournamentJoinRequest) event.getComponent().getAttributes().get("tournamentrequest");
            Query q = em.createQuery("SELECT t FROM Manager m JOIN m.tournaments t");
            manager.setTournaments(q.getResultList());
            manager.verifyTournamentRequest(req, false);
            utx.begin();
            em.remove(em.merge(req));
            em.persist(em.merge(manager));
            utx.commit();
        } catch (Exception ex) {
            Logger.getLogger(ManagerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Operation Failed"));
        }

    }

    public void acceptTournamentJoinRequest(ActionEvent event) {
        try {
            TournamentJoinRequest req = (TournamentJoinRequest) event.getComponent().getAttributes().get("tournamentrequest");
            Query q = em.createQuery("SELECT t FROM Manager m JOIN m.tournaments t");
            manager.setTournaments(q.getResultList());
            manager.verifyTournamentRequest(req, true);
            utx.begin();
            em.remove(em.merge(req));
            em.persist(em.merge(manager));
            utx.commit();
        } catch (Exception ex) {
            Logger.getLogger(ManagerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Operation Failed"));
        }

    }

    public void acceptMembershipRequest(ActionEvent event) {
        try {
            MembershipRequest req = (MembershipRequest) event.getComponent().getAttributes().get("request");
            RegisteredUser user = manager.verifyMembership(req);
            utx.begin();
            em.remove(em.merge(req));
            em.persist(user);
            utx.commit();
        } catch (Exception ex) {
            Logger.getLogger(ManagerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Operation Failed"));
        }
    }

    public void denyMembershipRequest(ActionEvent event) {
        MembershipRequest req = (MembershipRequest) event.getComponent().getAttributes().get("request");
        try {
            utx.begin();
            em.remove(em.merge(req));
            utx.commit();
        } catch (Exception ex) {
            Logger.getLogger(ManagerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Operation Failed"));
        }
    }

    public void removePlayer(ActionEvent event) {
        Player p = (Player) event.getComponent().getAttributes().get("player");
        manager.removeFromTrackList(p);
        try {
            utx.begin();
            em.persist(em.merge(manager));
            utx.commit();
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
        }
    }

    public List<Player> getTrackList() {
        Query q = em.createQuery("SELECT t FROM Manager p JOIN p.trackList t");
        manager.setTrackList(q.getResultList());
        return manager.getTrackList();
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
