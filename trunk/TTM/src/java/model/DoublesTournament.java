/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author CUNEYT
 */
@Entity
@DiscriminatorValue(value = "doubles")
public class DoublesTournament extends Tournament {

    public DoublesTournament(String name, String type) {
        super(name, type);
    }

    public DoublesTournament(String name) {
        super(name);
    }

    public DoublesTournament() {
        super();
    }
}
