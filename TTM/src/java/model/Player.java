/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

/**
 *
 * @author CUNEYT
 */
@Entity
@DiscriminatorValue(value = "player")
public class Player extends RegisteredUser implements PlayerDTO {

    @ManyToMany(mappedBy = "players")
    private List<Team> teams;

    public Player(String name, String surname, String username, String password) {
        super(name, surname, username, password);
    }

    public Player() {
        super();
    }

    @Override
    public List<Team> getTeams() {
        return teams;
    }
}
