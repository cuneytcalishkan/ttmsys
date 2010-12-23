/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DTO;

import java.util.Date;
import java.util.List;
import model.Court;
import model.Manager;
import model.Match;
import model.Referee;
import model.Team;
import model.Umpire;

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

    String getReport();

    Date getStartDate();

    Date getEndDate();

    double getPrize();
}
