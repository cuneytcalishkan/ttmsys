/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "singles")
public class SinglesTeam extends Team {

    public SinglesTeam(List<Player> players) {
        super(players);
    }

    public SinglesTeam() {
        super();
    }

    @Override
    public void join(Player p) throws Exception {
        if (getPlayers().isEmpty()) {
            getPlayers().add(p);
        } else {
            throw new Exception("Team capacity is full.");
        }
    }
}
