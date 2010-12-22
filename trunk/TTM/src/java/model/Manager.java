/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.Entity;

/**
 *
 * @author CUNEYT
 */
@Entity
public class Manager extends RegisteredUser implements ManagerDTO {

    @ManyToOne
    private List<Tournament> tournaments;

    public Manager(String name, String surname, String username, String password) {
        super(name, surname, username, password);
    }

    public Manager() {
        super();
    }

    @Override
    public List<Tournament> getTournaments() {
        return tournaments;
    }

    public void setTournaments(List<Tournament> tournaments) {
        this.tournaments = tournaments;
    }
}
