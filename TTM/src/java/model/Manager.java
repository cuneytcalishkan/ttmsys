/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.TransactionAttribute;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue(value = "manager")
public class Manager extends RegisteredUser {

    @OneToMany(mappedBy = "manager")
    private List<Tournament> tournaments;
    @OneToMany
    private List<MembershipRequest> membershipRequests;
    @OneToMany
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
        if (ok) {
            if (tournaments.contains(tjr.getTournament())) {
                tournaments.get(tournaments.indexOf(tjr)).joinTournament(tjr.getTeam());
                tournamentRequests.remove(tjr);
            }
        }
    }

    public RegisteredUser verifyMembership(MembershipRequest mr, boolean ok) {
        try {
            if (!ok) {
                return null;
            } else {
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
        } finally {
            membershipRequests.remove(mr);
        }
    }

    private void init() {
        tournaments = new ArrayList<Tournament>();
        membershipRequests = new ArrayList<MembershipRequest>();
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

    public void addMembershipRequest(MembershipRequest mr) {
        if (membershipRequests == null) {
            membershipRequests = new ArrayList<MembershipRequest>();
        }
        if (!getMembershipRequests().contains(mr)) {
            membershipRequests.add(mr);
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

    public List<MembershipRequest> getMembershipRequests() {
        return membershipRequests;
    }

    public void setMembershipRequests(List<MembershipRequest> membershiprequests) {
        this.membershipRequests = membershiprequests;
    }

    public void setTournamentRequests(List<TournamentJoinRequest> tournamentRequests) {
        this.tournamentRequests = tournamentRequests;
    }

    public List<TournamentJoinRequest> getTournamentRequests() {
        return tournamentRequests;
    }
}
