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
public class Umpire extends RegisteredUser implements UmpireDTO {

    @OneToMany(mappedBy = "umpires")
    private Match match;
    @OneToMany(mappedBy = "umpires")
    private Tournament tournament;
}
