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
@DiscriminatorValue(value = "umpire")
public class Umpire extends RegisteredUser implements UmpireDTO {

    @ManyToMany(mappedBy = "umpires")
    private List<Match> matches;
    @ManyToMany(mappedBy = "umpires")
    private List<Tournament> tournaments;

    public Umpire(String name, String surname, String username, String password) {
        super(name, surname, username, password);
    }

    public Umpire() {
        super();
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public void setTournaments(List<Tournament> tournaments) {
        this.tournaments = tournaments;
    }

    @Override
    public List<Tournament> getTournaments() {
        return tournaments;
    }

    @Override
    public List<Match> getMatches() {
        return matches;
    }
}
