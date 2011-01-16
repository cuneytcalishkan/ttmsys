/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.Date;
import javax.annotation.Resource;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import model.Draw;
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

    public String createTournament() {
        try {
            Tournament t = new Tournament(name, type, startDate, endDate, prize);
            utx.begin();
            utx.commit();
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
        }
        return "";
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
