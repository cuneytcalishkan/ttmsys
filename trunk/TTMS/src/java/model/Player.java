/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Entity;

/**
 *
 * @author CUNEYT
 */
@Entity
@DiscriminatorValue(value = "player")
public class Player extends RegisteredUser implements PlayerDTO {

    @OneToMany(mappedBy = "players")
    private Tournament tournament;
    @OneToMany(mappedBy = "players")
    private Match match;

    @Override
    public Tournament getTournament() {
        return tournament;
    }

    @Override
    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }
}
