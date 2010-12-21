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
public interface TournamentDTO {

    long getId();

    String getName();

    List<Match> getMatches();

    List<Referee> getReferees();

    List<Umpire> getUmpires();

    List<Player> getPlayers();

    List<Court> getCourts();

    Manager getManager();
}
