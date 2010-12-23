/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author CUNEYT
 */
public interface TeamDTO {

    long getId();

    List<Match> getMatches();

    Tournament getTournament();

    List<Player> getPlayers();

    int getAces();

    int getDoubleFauls();

    int getFirstSPtsWon();

    int getSecondSPtsWon();

    int getReturnPtsWon();

    int getTotalPtsWon();
}
