/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DTO;

import java.util.List;
import model.Match;
import model.MatchStatistics;
import model.Player;
import model.Tournament;

public interface TeamDTO {

    long getId();

    List<Match> getMatches();

    Tournament getTournament();

    List<Player> getPlayers();

    List<MatchStatistics> getStatistics();
}
