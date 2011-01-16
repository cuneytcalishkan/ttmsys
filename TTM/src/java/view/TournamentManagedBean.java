/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import model.Manager;
import model.Tournament;

@Named(value = "tournamentManagedBean")
@RequestScoped
public class TournamentManagedBean {

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

    /** Creates a new instance of TournamentManagedBean */
    public TournamentManagedBean() {
    }

    public void createTournament(ActionEvent event) {
        Manager manager = (Manager) event.getComponent().getAttributes().get("manager");
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
    }

    public String newTournament() {
        return "manager:index";
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
