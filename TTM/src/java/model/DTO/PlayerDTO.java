/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DTO;

import java.util.List;
import model.Team;

/**
 *
 * @author CUNEYT
 */
public interface PlayerDTO extends RegisteredUserDTO {

    List<Team> getTeams();
}
