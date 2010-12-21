/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Entity;

/**
 *
 * @author CUNEYT
 */
@Entity
public class Tournament implements TournamentDTO {

    @Id
    @Column(nullable = false)
    private long id;
    @ManyToOne
    private List<Player> players;
    @ManyToOne
    private List<Match> matches;
    @ManyToOne
    private List<Umpire> umpires;
    @ManyToOne
    private List<Referee> referees;
    @ManyToOne
    private List<Court> courts;
    @OneToMany(mappedBy = "tournaments")
    private Manager manager;

    public Tournament() {
    }
}
