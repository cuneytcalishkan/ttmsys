/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DTO;

import java.util.List;
import model.Game;

/**
 *
 * @author CUNEYT
 */
public interface SetDTO {

    long getId();

    String getScore();

    List<Game> getGames();
}
