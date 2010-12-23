/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author CUNEYT
 */
@Entity
@DiscriminatorValue(value = "doubles")
public class DoublesTeam extends Team {

    public DoublesTeam(List<Player> players) {
        super(players);
    }

    public DoublesTeam() {
        super();
    }

    @Override
    public void join(Player p) throws Exception {
        if (getPlayers().size() < 2) {
            getPlayers().add(p);
        } else {
            throw new Exception("Team capacity is full.");
        }
    }
}
