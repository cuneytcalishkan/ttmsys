/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

@Entity(name = "tmatch")
public class Match implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date mDate;
    @Column(nullable = false)
    private String mTime;
    private int homeTeamScore;
    private int awayTeamScore;
    private String report;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "matchid")
    private List<Set> sets;
    @ManyToMany(targetEntity = Team.class)
    private List<Team> teams;
    @ManyToMany(targetEntity = Umpire.class)
    @JoinTable(name = "tmatch_umpire")
    private List<Umpire> umpires;
    @ManyToMany(targetEntity = Referee.class)
    @JoinTable(name = "tmatch_referee")
    private List<Referee> referees;
    @ManyToOne
    private Court court;
    @ManyToOne
    private Tournament tournament;
    @OneToOne(cascade = CascadeType.ALL)
    private MatchStatistics statistics;

    public Match() {
        sets = new ArrayList<Set>();
        teams = new ArrayList<Team>();
        umpires = new ArrayList<Umpire>();
        referees = new ArrayList<Referee>();
        statistics = new MatchStatistics();
    }

    public Match(Date mDate, String mTime) {
        this();
        this.mDate = mDate;
        this.mTime = mTime;
    }

    public Match(Date mDate, String mTime, Court court) {
        this(mDate, mTime);
        this.court = court;
    }

    public Match(Date mDate, String mTime, List<Team> teams, Court court) {
        this(mDate, mTime, court);
        this.teams = teams;
    }

    public void addSet(Set s) {
        if (sets == null) {
            sets = new ArrayList<Set>();
        }
        if (!sets.contains(s)) {
            sets.add(s);
        }
    }

    public void addReferee(Referee r) {
        if (referees == null) {
            referees = new ArrayList<Referee>();
        }
        if (!referees.contains(r)) {
            referees.add(r);
        }
    }

    public void removeReferee(Referee r) {
        referees.remove(r);
    }

    public void addUmpire(Umpire u) {
        if (umpires == null) {
            umpires = new ArrayList<Umpire>();
        }
        if (!umpires.contains(u)) {
            umpires.add(u);
        }
    }

    public void removeUmpire(Umpire u){
        umpires.remove(u);
    }

    public void addTeam(Team t) {
        if (teams == null) {
            teams = new ArrayList<Team>();
        }
        if (!teams.contains(t)) {
            teams.add(t);
        }
    }

    public Team getHomeTeam() {
        if (teams.size() > 0) {
            return teams.get(0);
        } else {
            return null;
        }
    }

    public Team getAwayTeam() {
        if (teams.size() > 1) {
            return teams.get(1);
        } else {
            return null;
        }
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public Court getCourt() {
        return court;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public List<Umpire> getUmpires() {
        return umpires;
    }

    public List<Referee> getReferees() {
        return referees;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setCourt(Court court) {
        this.court = court;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public void setReferees(List<Referee> referees) {
        this.referees = referees;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public void setUmpires(List<Umpire> umpires) {
        this.umpires = umpires;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public void setSets(List<Set> sets) {
        this.sets = sets;
    }

    public void setStatistics(MatchStatistics statistics) {
        this.statistics = statistics;
    }

    public long getId() {
        return id;
    }

    public Date getmDate() {
        return mDate;
    }

    public String getmTime() {
        return mTime;
    }

    public List<Set> getSets() {
        return sets;
    }

    public String getScore() {
        return homeTeamScore + " - " + awayTeamScore;
    }

    public MatchStatistics getStatistics() {
        return statistics;
    }

    public String getReport() {
        return report;
    }

    @Override
    public String toString() {

        String result = "";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        if (teams.size() > 1) {
            result += (teams.get(0) == null ? "-" : teams.get(0)) + " vs " + (teams.get(1) == null ? "-" : teams.get(1)) + ", " + df.format(mDate) + ", " + mTime;
        } else {
            result += (teams.get(0) == null ? "-" : teams.get(0)) + " vs -";
        }
        return result;
    }

    public void removeSet(Set s) {
        sets.remove(s);
    }
}
