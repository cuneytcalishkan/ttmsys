/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DTO;

import java.util.List;
import model.RegisteredUser;
import model.Team;

public interface PlayerDTO extends RegisteredUserDTO {

    List<Team> getTeams();

    List<RegisteredUser> getTrackers();
}
