/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.DTO.MatchStatisticsDTO;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author CUNEYT
 */
@Entity
public class MatchStatistics implements MatchStatisticsDTO, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne(mappedBy = "statistics")
    private Match match;
    @ManyToOne
    private Team team;
    private int aces;
    private int doubleFauls;
    private int firstSPtsWon;
    private int secondSPtsWon;
    private int returnPtsWon;
    private int totalPtsWon;

    public MatchStatistics() {
    }

    public MatchStatistics(int aces, int doubleFauls, int firstSPtsWon, int secondSPtsWon, int returnPtsWon, int totalPtsWon) {
        this.aces = aces;
        this.doubleFauls = doubleFauls;
        this.firstSPtsWon = firstSPtsWon;
        this.secondSPtsWon = secondSPtsWon;
        this.returnPtsWon = returnPtsWon;
        this.totalPtsWon = totalPtsWon;
    }

    public MatchStatistics(Match match, Team team, int aces, int doubleFauls, int firstSPtsWon, int secondSPtsWon, int returnPtsWon, int totalPtsWon) {
        this.match = match;
        this.team = team;
        this.aces = aces;
        this.doubleFauls = doubleFauls;
        this.firstSPtsWon = firstSPtsWon;
        this.secondSPtsWon = secondSPtsWon;
        this.returnPtsWon = returnPtsWon;
        this.totalPtsWon = totalPtsWon;
    }

    public void setAces(int aces) {
        this.aces = aces;
    }

    public void setDoubleFauls(int doubleFauls) {
        this.doubleFauls = doubleFauls;
    }

    public void setFirstSPtsWon(int firstSPtsWon) {
        this.firstSPtsWon = firstSPtsWon;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public void setReturnPtsWon(int returnPtsWon) {
        this.returnPtsWon = returnPtsWon;
    }

    public void setSecondSPtsWon(int secondSPtsWon) {
        this.secondSPtsWon = secondSPtsWon;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setTotalPtsWon(int totalPtsWon) {
        this.totalPtsWon = totalPtsWon;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public int getAces() {
        return aces;
    }

    @Override
    public int getDoubleFauls() {
        return doubleFauls;
    }

    @Override
    public int getFirstSPtsWon() {
        return firstSPtsWon;
    }

    @Override
    public int getSecondSPtsWon() {
        return secondSPtsWon;
    }

    @Override
    public int getReturnPtsWon() {
        return returnPtsWon;
    }

    @Override
    public int getTotalPtsWon() {
        return totalPtsWon;
    }

    @Override
    public Team getTeam() {
        return team;
    }

    @Override
    public Match getMatch() {
        return match;
    }
}
