/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.DTO.SetDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity(name = "mset")
public class Set implements SetDTO, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int homeTeamScore;
    private int awayTeamScore;
    @OneToMany(orphanRemoval = true)
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

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getScore() {
        return homeTeamScore + " - " + awayTeamScore;
    }

    @Override
    public List<Game> getGames() {
        return games;
    }
}
