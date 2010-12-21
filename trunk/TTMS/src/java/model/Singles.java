/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.DiscriminatorValue;
import org.hibernate.annotations.Entity;

/**
 *
 * @author CUNEYT
 */
@Entity
@DiscriminatorValue(value = "singles")
public class Singles extends Match {
}
