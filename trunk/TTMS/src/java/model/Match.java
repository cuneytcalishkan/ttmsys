/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import org.hibernate.annotations.Entity;

/**
 *
 * @author CUNEYT
 */
@Entity
public abstract class Match implements MatchDTO {

    @Id
    private long id;
    @Column(nullable = false)
    private int type;
    @Column(nullable = false)
    private Date mDate;
    @Column(nullable = false)
    private Time mTime;
    @ManyToOne
    private List<Player> players;
    @ManyToOne
    private List<Umpire> umpires;
    @ManyToOne
    private List<Referee> referees;
    @OneToMany(mappedBy = "matches")
    private Court court;
    @OneToMany(mappedBy = "matches")
    private Tournament tournament;
    @Transient
    public static int MENS_SINGLES = 1;
    @Transient
    public static int MENS_DOUBLES = 2;
    @Transient
    public static int WOMENS_SINGLES = 3;
    @Transient
    public static int WOMENS_DOUBLES = 4;
    @Transient
    public static int MIXED_DOUBLES = 5;

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

    public Match(Date mDate, Time mTime, List<Player> players, Court court) {
        this.mDate = mDate;
        this.mTime = mTime;
        this.players = players;
        this.court = court;
    }

    @Override
    public Court getCourt() {
        return court;
    }

    @Override
    public List<Player> getPlayers() {
        return players;
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

    public void setPlayers(List<Player> players) {
        this.players = players;
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

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int getType() {
        return type;
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
}
