/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DTO;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import model.Court;
import model.MatchStatistics;
import model.Referee;
import model.Set;
import model.Team;
import model.Tournament;
import model.Umpire;

/**
 *
 * @author CUNEYT
 */
public interface MatchDTO {

    Court getCourt();

    List<Umpire> getUmpires();

    List<Referee> getReferees();

    List<Set> getSets();

    Tournament getTournament();

    Date getmDate();

    Time getmTime();

    long getId();

    String getScore();

    List<Team> getTeams();

    MatchStatistics getStatistics();

    String getReport();
}
