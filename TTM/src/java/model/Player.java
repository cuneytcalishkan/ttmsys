/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.OneToMany;
import org.hibernate.annotations.Entity;

/**
 *
 * @author CUNEYT
 */
@Entity
public class Player extends RegisteredUser implements PlayerDTO {

    @OneToMany(mappedBy = "players")
    private Tournament tournament;
    @OneToMany(mappedBy = "players")
    private Match match;

    public Player(String name, String surname, String username, String password) {
        super(name, surname, username, password);
    }

    public Player() {
        super();
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    @Override
    public Tournament getTournament() {
        return tournament;
    }

    @Override
    public Match getMatch() {
        return match;
    }
}
