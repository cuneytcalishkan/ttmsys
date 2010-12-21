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
@DiscriminatorValue(value = "umpire")
public class Umpire extends RegisteredUser implements UmpireDTO {

    @OneToMany(mappedBy = "umpires")
    private Match match;
    @OneToMany(mappedBy = "umpires")
    private Tournament tournament;

    public void setMatch(Match match) {
        this.match = match;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    @Override
    public Match getMatch() {
        return match;
    }

    @Override
    public Tournament getTournament() {
        return tournament;
    }
}
