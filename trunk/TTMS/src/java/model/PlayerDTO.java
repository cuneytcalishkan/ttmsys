/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author CUNEYT
 */
public interface PlayerDTO extends RegisteredUserDTO {

    Tournament getTournament();

    Match getMatch();
}
