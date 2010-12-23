/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.DTO.RefereeDTO;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

/**
 *
 * @author CUNEYT
 */
@Entity
@DiscriminatorValue(value = "referee")
public class Referee extends RegisteredUser implements RefereeDTO {

    @ManyToMany(mappedBy = "referees")
    private List<Match> matches;
    @ManyToMany(mappedBy = "referees")
    private List<Tournament> tournaments;

    public Referee(String name, String surname, String username, String password) {
        super(name, surname, username, password);
    }

    public Referee() {
        super();
    }

    public void setMatchReport(Match m, String report) {
        if (matches.contains(m)) {
            matches.get(matches.indexOf(m)).setReport(report);
        }
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public void setTournament(List<Tournament> tournaments) {
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
