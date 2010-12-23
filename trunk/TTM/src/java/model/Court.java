/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author CUNEYT
 */
@Entity
public class Court implements CourtDTO, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private String name;
    @OneToMany(mappedBy = "court")
    private List<Match> matches;
    @ManyToMany(mappedBy = "courts")
    private List<Tournament> tournaments;

    public Court() {
    }

    public Court(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Match> getMatches() {
        return matches;
    }

    @Override
    public List<Tournament> getTournaments() {
        return tournaments;
    }
}
