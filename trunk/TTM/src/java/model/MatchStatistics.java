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
