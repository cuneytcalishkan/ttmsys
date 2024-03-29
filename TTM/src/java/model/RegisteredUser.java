/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "usertype", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "registereduser")
public class RegisteredUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @ManyToMany(targetEntity = Team.class)
    private List<Team> trackList;

    public RegisteredUser() {
        trackList = new ArrayList<Team>();
    }

    public RegisteredUser(String name, String surname, String username, String password) {
        this();
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString(){
        return name + " " + surname;
    }

    public void addToTrackList(Team p) {
        if (trackList == null) {
            trackList = new ArrayList<Team>();
        }
        trackList.add(p);
    }

    public void removeFromTrackList(Team p) {
        trackList.remove(p);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Team> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<Team> trackList) {
        this.trackList = trackList;
    }
}
