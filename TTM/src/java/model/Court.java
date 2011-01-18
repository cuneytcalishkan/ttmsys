/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Court implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false, unique = true)
    private String name;
    @OneToMany(mappedBy = "court")
    private List<Match> matches;
    @ManyToMany(mappedBy = "courts")
    private List<Tournament> tournaments;

    public Court() {
        matches = new ArrayList<Match>();
        tournaments = new ArrayList<Tournament>();
    }

    public Court(String name) {
        this.name = name;
    }

    public Court(String name, List<Match> matches, List<Tournament> tournaments) {
        this.name = name;
        this.matches = matches;
        this.tournaments = tournaments;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public void setTournaments(List<Tournament> tournaments) {
        this.tournaments = tournaments;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public List<Tournament> getTournaments() {
        return tournaments;
    }

    @Override
    public String toString() {
        return name;
    }
}
