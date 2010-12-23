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

    String getType();

    List<Match> getMatches();

    List<Referee> getReferees();

    List<Umpire> getUmpires();

    List<Team> getTeams();

    List<Court> getCourts();

    Manager getManager();
}
