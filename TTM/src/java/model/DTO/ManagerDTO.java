/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DTO;

import java.util.List;
import model.Tournament;

/**
 *
 * @author CUNEYT
 */
public interface ManagerDTO extends RegisteredUserDTO {

    List<Tournament> getTournaments();
}
