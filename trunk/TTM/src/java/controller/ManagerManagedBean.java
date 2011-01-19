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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import model.Court;
import model.Manager;
import model.MembershipRequest;
import model.Player;
import model.Referee;
import model.RegisteredUser;
import model.Team;
import model.Tournament;
import model.TournamentJoinRequest;
import model.Umpire;

@Named(value = "managerManagedBean")
@SessionScoped
public class ManagerManagedBean implements Serializable {

    @PersistenceContext
    private EntityManager em;
    @Resource
    private UserTransaction utx;
    private Manager manager;
    private RegisteredUser selectedUser;
    private String courtName;
    private int currentPage = 1;
    private int pageInterval = 10;
    private int pageCount = 0;

    /** Creates a new instance of ManagerManagedBean */
    public ManagerManagedBean() {
        FacesContext context = FacesContext.getCurrentInstance();
        manager = (Manager) context.getExternalContext().getSessionMap().get(RegisteredUserManagedBean.USER_SESSION_KEY);
    }

    public void createTournament() {
    }

    public List<Tournament> getTournaments() {
        Query q = em.createQuery("SELECT distinct t FROM Manager m JOIN m.tournaments t "
                + "where m.id = :mid");
        q.setParameter("mid", manager.getId());
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

    public void acceptMembershipRequest(MembershipRequest req) {
        try {
            RegisteredUser user = manager.verifyMembership(req);
            utx.begin();
            em.remove(em.merge(req));
            em.persist(user);
            utx.commit();
            countPages();
        } catch (Exception ex) {
            Logger.getLogger(ManagerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Operation Failed"));
        }
    }

    public void denyMembershipRequest(MembershipRequest req) {
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

    public void removeUser(RegisteredUser user) {
        try {
            utx.begin();
            em.remove(em.merge(user));
            utx.commit();
            countPages();
        } catch (Exception ex) {
            Logger.getLogger(ManagerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Operation Failed"));
        }
    }

    public String editUser() {
        try {
            utx.begin();
            em.merge(selectedUser);
            utx.commit();
        } catch (Exception ex) {
            Logger.getLogger(ManagerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Operation Failed"));
        }
        return "manager:index";
    }

    private void countPages() {
        int userCount;
        userCount = ((Number) em.createQuery("select count(*) from RegisteredUser").getSingleResult()).intValue();
        pageCount = userCount / pageInterval;
        if (userCount % pageInterval != 0) {
            pageCount++;
        }
        if (pageCount < currentPage) {
            currentPage = pageCount;
        }
    }

    public List<RegisteredUser> getUsers() {
        Query q = null;
        if (pageCount == 0) {
            countPages();
        }
        q = em.createQuery("from RegisteredUser");
        q.setFirstResult((currentPage - 1) * pageInterval);
        q.setMaxResults(pageInterval);
        return q.getResultList();
    }

    public boolean isNextPageDisplayable() {
        if (pageCount == 0) {
            countPages();
        }
        return currentPage < pageCount;
    }

    public boolean isPreviousPageDisplayable() {
        return currentPage > 1;
    }

    public String nextPage() {
        currentPage++;
        return "manager:index";
    }

    public String previousPage() {
        currentPage--;
        return "manager:index";
    }

    public String typeOf(RegisteredUser ru) {
        if (ru instanceof Manager) {
            return "Manager";
        } else if (ru instanceof Referee) {
            return "Referee";
        } else if (ru instanceof Umpire) {
            return "Umpire";
        } else if (ru instanceof Player) {
            return "Player";
        } else {
            return "Registered User";
        }
    }

    public List<Court> getCourts() {
        Query q = em.createQuery("from Court");
        return q.getResultList();
    }

    public void removeCourt(Court court) {
        try {
            utx.begin();
            em.remove(em.merge(court));
            utx.commit();
        } catch (Exception ex) {
            Logger.getLogger(ManagerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Operation Failed"));
        }
    }

    public void addCourt() {
        Court newCourt = new Court(courtName);
        try {
            utx.begin();
            em.persist(newCourt);
            utx.commit();
        } catch (Exception ex) {
            Logger.getLogger(ManagerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Operation Failed"));
        }
    }

    public String getCourtName() {
        return courtName;
    }

    public void setCourtName(String courtName) {
        this.courtName = courtName;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public RegisteredUser getSelectedUser() {
        return selectedUser;
    }

    public String setSelectedUser(RegisteredUser selectedUser) {
        this.selectedUser = selectedUser;
        return "manager:editUser";
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentFetchIndex) {
        this.currentPage = currentFetchIndex;
    }

    public int getPageInterval() {
        return pageInterval;
    }

    public void setPageInterval(int pageInterval) {
        this.pageInterval = pageInterval;
    }

    public int getPAgeCount() {
        return pageCount;
    }

    public void setPageCount(int userCount) {
        this.pageCount = userCount;
    }
}
