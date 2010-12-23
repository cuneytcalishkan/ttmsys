/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DTO;

import java.util.List;
import model.Match;
import model.Tournament;

/**
 *
 * @author CUNEYT
 */
public interface CourtDTO {

    long getId();

    String getName();

    List<Match> getMatches();

    List<Tournament> getTournaments();
}
