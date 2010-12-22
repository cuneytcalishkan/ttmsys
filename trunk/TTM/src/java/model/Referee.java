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
public class Referee extends RegisteredUser implements RefereeDTO {

    @OneToMany(mappedBy = "referees")
    private Match match;
    @OneToMany(mappedBy = "referees")
    private Tournament tournament;

    public Referee(String name, String surname, String username, String password) {
        super(name, surname, username, password);
    }

    public Referee() {
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
