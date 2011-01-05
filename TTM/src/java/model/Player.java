/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import model.DTO.PlayerDTO;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
@DiscriminatorValue(value = "player")
public class Player extends RegisteredUser implements PlayerDTO {

    @ManyToMany(mappedBy = "players")
    private List<Team> teams;
    @ManyToMany(mappedBy = "trackList")
    private List<RegisteredUser> trackers;

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
        trackers = new ArrayList<RegisteredUser>();
    }

    @Override
    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    @Override
    public String toString() {
        return getName() + " " + getSurname();
    }

    @Override
    public List<RegisteredUser> getTrackers() {
        return trackers;
    }

    public void setTrackers(List<RegisteredUser> trackers) {
        this.trackers = trackers;
    }
}
