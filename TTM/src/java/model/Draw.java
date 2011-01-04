/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import model.DTO.DrawDTO;

@Entity
public class Draw implements Serializable, DrawDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne
    private Team homeTeam;
    @OneToOne
    private Team awayTeam;
    @OneToOne
    private Draw homeDraw;
    @OneToOne
    private Draw awayDraw;

    public Draw() {
    }

    public Draw(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public Draw(Draw homeDraw, Draw awayDraw) {
        this.homeDraw = homeDraw;
        this.awayDraw = awayDraw;
    }

    public Draw(Team homeTeam, Team awayTeam, Draw homeDraw, Draw awayDraw) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeDraw = homeDraw;
        this.awayDraw = awayDraw;
    }

    @Override
    public Draw getAwayDraw() {
        return awayDraw;
    }

    public void setAwayDraw(Draw awayDraw) {
        this.awayDraw = awayDraw;
    }

    @Override
    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    @Override
    public Draw getHomeDraw() {
        return homeDraw;
    }

    public void setHomeDraw(Draw homeDraw) {
        this.homeDraw = homeDraw;
    }

    @Override
    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return homeTeam + " vs " + awayTeam;
    }

    public String traverse(Draw d, int level) {
        String result = "";
        for (int i = 0; i < level; i++) {
            result += "\t";
        }
        result += d + "\n";
        if (d.homeDraw != null) {
            result += traverse(d.homeDraw, level + 1);
        }
        if (d.awayDraw != null) {
            result += traverse(d.awayDraw, level + 1);
        }
        return result;
    }
}
