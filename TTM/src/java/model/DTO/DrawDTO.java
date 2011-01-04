/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DTO;

import model.Draw;
import model.Team;

public interface DrawDTO {

    long getId();

    Team getHomeTeam();

    Team getAwayTeam();

    Draw getHomeDraw();

    Draw getAwayDraw();
}
