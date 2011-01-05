/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DTO;

import java.util.List;
import model.Match;
import model.Tournament;

public interface RefereeDTO extends RegisteredUserDTO {

    List<Tournament> getTournaments();

    List<Match> getMatches();
}
