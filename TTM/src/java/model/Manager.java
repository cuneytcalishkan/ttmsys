/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.DTO.ManagerDTO;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author CUNEYT
 */
@Entity
@DiscriminatorValue(value = "manager")
public class Manager extends RegisteredUser implements ManagerDTO {

    @OneToMany(mappedBy = "manager")
    private List<Tournament> tournaments;
    @OneToMany
    private List<RegisteredUser> usersToActivate;

    public Manager(String name, String surname, String username, String password) {
        super(name, surname, username, password);
    }

    public Manager() {
        super();
    }

    public void addUserToActivate(RegisteredUser u) {
        if (!usersToActivate.contains(u)) {
            usersToActivate.add(u);
        }
    }

    public void removeUserToActivate(RegisteredUser u) {
        usersToActivate.remove(u);
    }

    public void setTournamentReport(Tournament t, String report) {
        if (tournaments.contains(t)) {
            tournaments.get(tournaments.indexOf(t)).setReport(report);
        }
    }

    @Override
    public List<Tournament> getTournaments() {
        return tournaments;
    }

    public void setTournaments(List<Tournament> tournaments) {
        this.tournaments = tournaments;
    }

    @Override
    public List<RegisteredUser> getUsersToActivate() {
        return usersToActivate;
    }

    public void setUsersToActivate(List<RegisteredUser> usersToActivate) {
        this.usersToActivate = usersToActivate;
    }
}
