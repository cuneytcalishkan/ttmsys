/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DTO;

import model.Match;
import model.Team;

/**
 *
 * @author CUNEYT
 */
public interface MatchStatisticsDTO {

    Team getTeam();

    Match getMatch();

    long getId();

    int getAces();

    int getDoubleFauls();

    int getFirstSPtsWon();

    int getSecondSPtsWon();

    int getReturnPtsWon();

    int getTotalPtsWon();
}
