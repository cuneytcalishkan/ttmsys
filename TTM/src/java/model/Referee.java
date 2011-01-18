/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
@DiscriminatorValue(value = "referee")
public class Referee extends RegisteredUser {

    @ManyToMany(mappedBy = "referees")
    private List<Match> matches;
    @ManyToMany(mappedBy = "referees")
    private List<Tournament> tournaments;

    public Referee(String name, String surname, String username, String password) {
        super(name, surname, username, password);
        init();
    }

    public Referee() {
        super();
        init();
    }

    private void init() {
        matches = new ArrayList<Match>();
        tournaments = new ArrayList<Tournament>();
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

    public List<Tournament> getTournaments() {
        return tournaments;
    }

    public List<Match> getMatches() {
        return matches;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        final Referee other = (Referee) obj;
        if (this.getId() != other.getId()) {
            return false;
        }
        if ((this.getName() == null) ? (other.getName() != null) : !this.getName().equals(other.getName())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (int) (this.getId() ^ (this.getId() >>> 32));
        hash = 97 * hash + (this.getName() != null ? this.getName().hashCode() : 0);
        return hash;
    }
}
