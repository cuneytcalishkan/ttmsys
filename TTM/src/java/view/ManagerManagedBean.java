/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import model.Manager;
import model.MembershipRequest;
import model.RegisteredUser;
import model.Team;
import model.Tournament;
import model.TournamentJoinRequest;

@Named(value = "managerManagedBean")
@SessionScoped
public class ManagerManagedBean implements Serializable {

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
        manager.setTournaments(q.getResultList());
        return manager.getTournaments();
    }

    public List<MembershipRequest> getMembershipRequests() {
        Query q = em.createQuery("SELECT mr from MembershipRequest mr");
        return q.getResultList();
    }

    public List<TournamentJoinRequest> getTournamentJoinRequests() {
        String query = "SELECT r FROM Manager m JOIN m.tournamentRequests r";
        Query q = em.createQuery(query);
        manager.setTournamentRequests(q.getResultList());

        for (TournamentJoinRequest tjr : manager.getTournamentRequests()) {
            query = "SELECT team FROM Team AS team LEFT JOIN FETCH team.players WHERE team.id = :tid";
            q = em.createQuery(query);
            q.setParameter("tid", tjr.getTeam().getId());
            tjr.setTeam((Team) q.getSingleResult());
        }

        return manager.getTournamentRequests();
    }

    public void removeTournament(Tournament t) {
        try {
            utx.begin();
            em.remove(em.merge(t));
            em.merge(manager);
            utx.commit();
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            System.out.println(e);
        }
    }

    public void denyTournamentJoinRequest(TournamentJoinRequest req) {
        try {
            Query q = em.createQuery("SELECT t FROM Manager m JOIN m.tournaments t");
            manager.setTournaments(q.getResultList());
            manager.verifyTournamentRequest(req, false);
            utx.begin();
            em.remove(em.merge(req));
            em.merge(manager);
            utx.commit();
        } catch (Exception ex) {
            Logger.getLogger(ManagerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Operation Failed"));
        }

    }

    public void acceptTournamentJoinRequest(TournamentJoinRequest req) {
        try {
            Query q = em.createQuery("SELECT t FROM Manager m JOIN m.tournaments t");
            manager.setTournaments(q.getResultList());
            Team t = req.getTeam();
            Tournament to = req.getTournament();
            t.setTournament(to);
            manager.verifyTournamentRequest(req, true);
            utx.begin();
            em.merge(to);
            em.merge(t);
            em.remove(em.merge(req));
            em.merge(manager);
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

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
