/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
@DiscriminatorValue(value = "player")
public class Player extends RegisteredUser {

    @ManyToMany(mappedBy = "players")
    private List<Team> teams;

    public Player(String name, String surname, String username, String password) {
        super(name, surname, username, password);
        init();
    }

    public Player() {
        super();
        init();
    }

    private void init() {
        teams = new ArrayList<Team>();
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public void addTeam(Team team) {
        teams.add(team);
    }

    public void removeTeam(Team team){
        teams.remove(team);
    }

    @Override
    public String toString() {
        return getName() + " " + getSurname();
    }
}
