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
public interface MatchDTO {

    Court getCourt();

    List<Player> getPlayers();

    List<Umpire> getUmpires();

    List<Referee> getReferees();

    Tournament getTournament();
}
