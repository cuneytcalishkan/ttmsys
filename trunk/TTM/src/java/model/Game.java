/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Game implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private int homeTeamScore;
    @Column(nullable = false)
    private int awayTeamScore;

    public Game() {
    }

    public Game(int homeTeamScore, int awayTeamScore) {
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
    }

    public void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
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

    public String getScore() {
        return homeTeamScore + " - " + awayTeamScore;
    }

    public long getId() {
        return id;
    }
}
