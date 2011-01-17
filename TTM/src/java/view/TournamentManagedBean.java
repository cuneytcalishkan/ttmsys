/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import model.Manager;
import model.Tournament;

@Named(value = "tournamentManagedBean")
@SessionScoped
public class TournamentManagedBean implements Serializable {

    private Date startDate;
    private Date endDate;
    private String type;
    private String name;
    private double prize;
    private String report;
    @PersistenceContext
    private EntityManager em;
    @Resource
    private UserTransaction utx;
    private Tournament current;

    /** Creates a new instance of TournamentManagedBean */
    public TournamentManagedBean() {
    }

    public String createTournament() {
        FacesContext context = FacesContext.getCurrentInstance();
        Manager manager = (Manager) context.getExternalContext().getSessionMap().get(RegisteredUserManagedBean.USER_SESSION_KEY);
        Tournament t = new Tournament(name, type, startDate, endDate, prize);
        t.setReport(report);
        t.setManager(manager);
        try {
            utx.begin();
            em.persist(t);
            em.persist(em.merge(manager));
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(TournamentManagedBean.class.getName()).log(Level.SEVERE, null, e);
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
        }
        return "manager:index";
    }

    public void linkTournament(ActionEvent event) {
        current = (Tournament) event.getComponent().getAttributes().get("tournament");
    }

    public String editTournament() {
        try {
            utx.begin();
            em.merge(current);
            utx.commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return "manager:index";
    }

    public Tournament getCurrent() {
        return current;
    }

    public void setCurrent(Tournament current) {
        this.current = current;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrize() {
        return prize;
    }

    public void setPrize(double prize) {
        this.prize = prize;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
