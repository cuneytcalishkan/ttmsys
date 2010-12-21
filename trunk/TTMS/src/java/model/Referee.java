/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.OneToMany;
import org.hibernate.annotations.Entity;

/**
 *
 * @author CUNEYT
 */
@Entity
public class Referee extends RegisteredUser implements RefereeDTO {

    @OneToMany(mappedBy = "referees")
    private Match match;
    @OneToMany(mappedBy = "referees")
    private Tournament tournament;
}
