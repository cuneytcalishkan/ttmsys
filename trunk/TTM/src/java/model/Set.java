/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity(name = "mset")
public class Set implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int homeTeamScore;
    private int awayTeamScore;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "setid")
    private List<Game> games;

    public Set() {
        games = new ArrayList<Game>();
    }

    public Set(int homeTeamScore, int awayTeamScore) {
        this();
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
    }

    public void addGame(Game g) {
        if (games == null) {
            games = new ArrayList<Game>();
        }
        if (!games.contains(g)) {
            games.add(g);
        }
    }

    public void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public long getId() {
        return id;
    }

    public String getScore() {
        return homeTeamScore + " - " + awayTeamScore;
    }

    public List<Game> getGames() {
        return games;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Set other = (Set) obj;
        if (this.id != other.id) {
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
