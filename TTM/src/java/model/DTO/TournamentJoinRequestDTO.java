/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DTO;

import model.Team;
import model.Tournament;

public interface TournamentJoinRequestDTO {

    long getId();

    Tournament getTournament();

    Team getTeam();
}
