/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import model.DTO.ManagerDTO;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue(value = "manager")
public class Manager extends RegisteredUser implements ManagerDTO {

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
        if (!membershipRequests.contains(mr)) {
            membershipRequests.add(mr);
        }
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
    public List<MembershipRequest> getMembershipRequests() {
        return membershipRequests;
    }

    public void setMembershipRequests(List<MembershipRequest> membershiprequests) {
        this.membershipRequests = membershiprequests;
    }

    public void setTournamentRequests(List<TournamentJoinRequest> tournamentRequests) {
        this.tournamentRequests = tournamentRequests;
    }

    @Override
    public List<TournamentJoinRequest> getTournamentRequests() {
        return tournamentRequests;
    }
}
