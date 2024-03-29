/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "teamtype", discriminatorType = DiscriminatorType.STRING)
public abstract class Team implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToMany(mappedBy = "teams")
    private List<Match> matches;
    @ManyToOne
    private Tournament tournament;
    @ManyToMany(targetEntity = Player.class, fetch = FetchType.EAGER)
    private List<Player> players;
    @OneToMany(mappedBy = "team")
    private List<MatchStatistics> statistics;

    public Team() {
        players = new ArrayList<Player>();
        statistics = new ArrayList<MatchStatistics>();
    }

    public Team(List<Player> players) {
        this.players = players;
    }

    public void join(Player p) throws Exception {
        players.add(p);
    }

    public List<MatchStatistics> getStatistics() {
        return statistics;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void setStatistics(List<MatchStatistics> statistics) {
        this.statistics = statistics;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public long getId() {
        return id;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public String toString() {
        String result = "";
        for (Player player : players) {
            result += player.toString() + ",";
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Team other = (Team) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.players != other.players && (this.players == null || !this.players.equals(other.players))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }
}
