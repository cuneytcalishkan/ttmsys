/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 *
 * @author CUNEYT
 */
public interface MatchDTO {

    Court getCourt();

    List<Player> getPlayers();

    List<Umpire> getUmpires();

    List<Referee> getReferees();

    Tournament getTournament();

    Date getmDate();

    Time getmTime();

    long getId();

    int getType();
}
