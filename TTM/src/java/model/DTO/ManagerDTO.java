/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DTO;

import java.util.List;
import model.MembershipRequest;
import model.Tournament;
import model.TournamentJoinRequest;

public interface ManagerDTO extends RegisteredUserDTO {

    List<Tournament> getTournaments();

    List<TournamentJoinRequest> getTournamentRequests();

    List<MembershipRequest> getMembershipRequests();
}
