/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue(value = "manager")
public class Manager extends RegisteredUser {

    @OneToMany(mappedBy = "manager")
    private List<Tournament> tournaments;
    @OneToMany(mappedBy = "manager")
    private List<TournamentJoinRequest> tournamentRequests;

    public Manager(String name, String surname, String username, String password) {
        super(name, surname, username, password);
        init();
    }

    public Manager() {
        super();
        init();
    }

    public void verifyTournamentRequest(TournamentJoinRequest tjr, boolean ok) {
        if (tournaments.contains(tjr.getTournament())) {
            if (ok) {
                Tournament t = tournaments.get(tournaments.indexOf(tjr.getTournament()));
                t.joinTournament(tjr.getTeam());
                tjr.getTeam().setTournament(t);
            }
            tournamentRequests.remove(tjr);
        }
    }

    public RegisteredUser verifyMembership(MembershipRequest mr) {

        if (mr.getType().equals("manager")) {
            return new Manager(mr.getName(), mr.getSurname(), mr.getUsername(), mr.getPassword());
        } else if (mr.getType().equals("player")) {
            return new Player(mr.getName(), mr.getSurname(), mr.getUsername(), mr.getPassword());
        } else if (mr.getType().equals("referee")) {
            return new Referee(mr.getName(), mr.getSurname(), mr.getUsername(), mr.getPassword());
        } else if (mr.getType().equals("umpire")) {
            return new Umpire(mr.getName(), mr.getSurname(), mr.getUsername(), mr.getPassword());
        } else {
            return new RegisteredUser(mr.getName(), mr.getSurname(), mr.getUsername(), mr.getPassword());
        }
    }

    private void init() {
        tournaments = new ArrayList<Tournament>();
        tournamentRequests = new ArrayList<TournamentJoinRequest>();
    }

    public void addTournamentJoinRequest(TournamentJoinRequest tjr) {
        if (tournamentRequests == null) {
            tournamentRequests = new ArrayList<TournamentJoinRequest>();
        }
        if (!tournamentRequests.contains(tjr)) {
            tournamentRequests.add(tjr);
        }
    }

    public void setTournamentReport(Tournament t, String report) {
        if (tournaments.contains(t)) {
            tournaments.get(tournaments.indexOf(t)).setReport(report);
        }
    }

    public List<Tournament> getTournaments() {
        return tournaments;
    }

    public void setTournaments(List<Tournament> tournaments) {
        this.tournaments = tournaments;
    }

    public void setTournamentRequests(List<TournamentJoinRequest> tournamentRequests) {
        this.tournamentRequests = tournamentRequests;
    }

    public List<TournamentJoinRequest> getTournamentRequests() {
        return tournamentRequests;
    }
}
