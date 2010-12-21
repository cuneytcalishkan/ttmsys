/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import org.hibernate.annotations.Entity;

/**
 *
 * @author CUNEYT
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "matchtype", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "match")
public abstract class Match implements MatchDTO {

    @Id
    private long id;
    @Column(nullable = false)
    private String type;
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
    public static String MENS_SINGLES = "Men's Singles";
    @Transient
    public static String MENS_DOUBLES = "Men's Doubles";
    @Transient
    public static String WOMENS_SINGLES = "Women's Singles";
    @Transient
    public static String WOMENS_DOUBLES = "Women's Doubles";
    @Transient
    public static String MIXED_DOUBLES = "Mixed Doubles";

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

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getType() {
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
