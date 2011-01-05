/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.DTO.MatchDTO;
import java.io.Serializable;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

@Entity(name = "tmatch")
public class Match implements MatchDTO, Serializable {

    @Id
    private long id;
    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date mDate;
    @Column(nullable = false)
    private Time mTime;
    private int homeTeamScore;
    private int awayTeamScore;
    private String report;
    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "matchid")
    private List<Set> sets;
    @ManyToMany(targetEntity = Team.class)
    private List<Team> teams;
    @ManyToMany(targetEntity = Umpire.class)
    private List<Umpire> umpires;
    @ManyToMany(targetEntity = Referee.class)
    private List<Referee> referees;
    @ManyToOne
    private Court court;
    @ManyToOne
    private Tournament tournament;
    @OneToOne
    private MatchStatistics statistics;

    public Match() {
    }

    public Match(Date mDate, Time mTime) {
        this.mDate = mDate;
        this.mTime = mTime;
    }

    public Match(Date mDate, Time mTime, Court court) {
        this.mDate = mDate;
        this.mTime = mTime;
        this.court = court;
    }

    public Match(Date mDate, Time mTime, List<Team> teams, Court court) {
        this.mDate = mDate;
        this.mTime = mTime;
        this.teams = teams;
        this.court = court;
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

    @Override
    public Court getCourt() {
        return court;
    }

    @Override
    public List<Team> getTeams() {
        return teams;
    }

    @Override
    public List<Umpire> getUmpires() {
        return umpires;
    }

    @Override
    public List<Referee> getReferees() {
        return referees;
    }

    @Override
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

    public void setmTime(Time mTime) {
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

    @Override
    public long getId() {
        return id;
    }

    @Override
    public Date getmDate() {
        return mDate;
    }

    @Override
    public Time getmTime() {
        return mTime;
    }

    @Override
    public List<Set> getSets() {
        return sets;
    }

    @Override
    public String getScore() {
        return homeTeamScore + " - " + awayTeamScore;
    }

    @Override
    public MatchStatistics getStatistics() {
        return statistics;
    }

    @Override
    public String getReport() {
        return report;
    }

    @Override
    public String toString() {

        String result = "";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-YYYY");
        result += teams.get(0) + " vs " + teams.get(1) + ", " + df.format(mDate) + ", " + mTime;
        return result;
    }
}
