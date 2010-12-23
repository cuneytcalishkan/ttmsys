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
public interface UmpireDTO extends RegisteredUserDTO {

    List<Tournament> getTournaments();

    List<Match> getMatches();
}
