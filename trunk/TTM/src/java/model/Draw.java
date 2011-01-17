/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Draw implements Serializable {

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

    public Draw getAwayDraw() {
        return awayDraw;
    }

    public void setAwayDraw(Draw awayDraw) {
        this.awayDraw = awayDraw;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Draw getHomeDraw() {
        return homeDraw;
    }

    public void setHomeDraw(Draw homeDraw) {
        this.homeDraw = homeDraw;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

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

    public void drawList(Draw d, List<Draw> result) {
        result.add(d);
        if (d.homeDraw != null) {
            drawList(d.homeDraw, result);
        }
        if (d.awayDraw != null) {
            drawList(d.awayDraw, result);
        }
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
