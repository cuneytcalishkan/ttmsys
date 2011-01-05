/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DTO;

import java.util.List;
import model.Player;

public interface RegisteredUserDTO {

    long getId();

    String getName();

    String getSurname();

    String getPassword();

    String getUsername();

    List<Player> getTrackList();
}
