/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author CUNEYT
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "teamtype", discriminatorType = DiscriminatorType.STRING)
public abstract class Team implements TeamDTO, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToMany(mappedBy = "teams")
    private List<Match> matches;
    @ManyToOne
    private Tournament tournament;
    @ManyToMany(targetEntity = Player.class)
    private List<Player> players;
    @OneToMany(mappedBy = "team")
    private List<MatchStatistics> statisics;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public List<Match> getMatches() {
        return matches;
    }

    @Override
    public Tournament getTournament() {
        return tournament;
    }

    @Override
    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public List<MatchStatistics> getStatistics() {
        return statisics;
    }
}
