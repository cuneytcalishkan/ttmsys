/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.Temporal;
import model.DTO.TournamentDTO;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

/**
 *
 * @author CUNEYT
 */
@Entity
public class Tournament implements TournamentDTO, Serializable {

    @Id
    @Column(nullable = false)
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDate;
    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endDate;
    @Column(nullable = false)
    private double prize;
    @OneToMany(mappedBy = "tournament")
    private List<Team> teams;
    @OneToMany(mappedBy = "tournament")
    private List<Match> matches;
    @ManyToMany(targetEntity = Umpire.class)
    private List<Umpire> umpires;
    @ManyToMany(targetEntity = Referee.class)
    private List<Referee> referees;
    @ManyToMany(targetEntity = Court.class)
    private List<Court> courts;
    @ManyToOne
    private Manager manager;
    private String report;
    @Transient
    public static String MENS_SINGLES = "Men's Singles";
    @Transient
    public static String WOMENS_SINGLES = "Women's Singles";
    @Transient
    public static String MENS_DOUBLES = "Men's Doubles";
    @Transient
    public static String WOMENS_DOUBLES = "Women's Doubles";
    @Transient
    public static String MIXED_DOUBLES = "Mixed Doubles";

    public Tournament() {
    }

    public Tournament(String name, String type, Date startDate, Date endDate, double prize) {
        this.name = name;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.prize = prize;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Match> getMatches() {
        return matches;
    }

    @Override
    public List<Referee> getReferees() {
        return referees;
    }

    @Override
    public List<Umpire> getUmpires() {
        return umpires;
    }

    @Override
    public List<Team> getTeams() {
        return teams;
    }

    @Override
    public List<Court> getCourts() {
        return courts;
    }

    @Override
    public Manager getManager() {
        return manager;
    }

    @Override
    public String getType() {
        return type;
    }

    public void setCourts(List<Court> courts) {
        this.courts = courts;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public void setReferees(List<Referee> referees) {
        this.referees = referees;
    }

    public void setUmpires(List<Umpire> umpires) {
        this.umpires = umpires;
    }

    @Override
    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    @Override
    public Date getStartDate() {
        return startDate;
    }

    @Override
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public double getPrize() {
        return prize;
    }

    public void setPrize(double prize) {
        this.prize = prize;
    }
}