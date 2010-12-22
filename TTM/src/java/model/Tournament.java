/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
public class Tournament implements TournamentDTO {

    @Id
    @Column(nullable = false)
    private long id;
    @Column(nullable = false)
    private String name;
    @ManyToOne
    private List<Player> players;
    @ManyToOne
    private List<Match> matches;
    @ManyToOne
    private List<Umpire> umpires;
    @ManyToOne
    private List<Referee> referees;
    @ManyToOne
    private List<Court> courts;
    @OneToMany(mappedBy = "tournaments")
    private Manager manager;

    public Tournament() {
    }

    public Tournament(String name) {
        this.name = name;
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
    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public List<Court> getCourts() {
        return courts;
    }

    @Override
    public Manager getManager() {
        return manager;
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

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void setReferees(List<Referee> referees) {
        this.referees = referees;
    }

    public void setUmpires(List<Umpire> umpires) {
        this.umpires = umpires;
    }
}
