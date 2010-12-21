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
import org.hibernate.annotations.Entity;

/**
 *
 * @author CUNEYT
 */
@Entity
public class Match implements MatchDTO{

    @Id
    private long id;
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Player> getPlayers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Umpire> getUmpires() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Referee> getReferees() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Tournament getTournament() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
