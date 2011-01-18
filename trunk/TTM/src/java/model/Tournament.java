/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.Temporal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class Tournament implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    @Temporal(value = javax.persistence.TemporalType.DATE)
    private Date startDate;
    @Column(nullable = false)
    @Temporal(value = javax.persistence.TemporalType.DATE)
    private Date endDate;
    @Column(nullable = false)
    private double prize;
    @OneToOne(cascade = CascadeType.ALL)
    private Draw draw;
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
        teams = new ArrayList<Team>();
        matches = new ArrayList<Match>();
        umpires = new ArrayList<Umpire>();
        referees = new ArrayList<Referee>();
        courts = new ArrayList<Court>();
        draw = new Draw();
    }

    public Tournament(String name, String type, Date startDate, Date endDate, double prize) {
        this();
        this.name = name;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.prize = prize;
    }

    public void joinTournament(Team t) {
        if (!teams.contains(t)) {
            teams.add(t);
        }
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public List<Referee> getReferees() {
        return referees;
    }

    public List<Umpire> getUmpires() {
        return umpires;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public List<Court> getCourts() {
        return courts;
    }

    public Manager getManager() {
        return manager;
    }

    public String getType() {
        return type;
    }

    public Draw getDraw() {
        return draw;
    }

    public void setDraw(Draw draw) {
        this.draw = draw;
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

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public double getPrize() {
        return prize;
    }

    public void setPrize(double prize) {
        this.prize = prize;
    }

    public List<Draw> getDrawList() {
        ArrayList<Draw> result = new ArrayList<Draw>();
        draw.drawList(draw, result);
        return result;
    }
}
