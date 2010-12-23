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

    List<MatchStatistics> getStatistics();
}
