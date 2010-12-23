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
public interface SetDTO {

    long getId();

    String getScore();

    List<Game> getGames();
}
