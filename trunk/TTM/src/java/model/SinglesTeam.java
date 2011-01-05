/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "singles")
public class SinglesTeam extends Team {

    public SinglesTeam(Player player) throws Exception {
        this();
        join(player);
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
