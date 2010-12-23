/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author CUNEYT
 */
@Entity
@DiscriminatorValue(value = "sinlges")
public class SinglesTournament extends Tournament implements Serializable {

    public SinglesTournament(String name, String type) {
        super(name, type);
    }

    public SinglesTournament(String name) {
        super(name);

    }

    public SinglesTournament() {
        super();
    }
}
