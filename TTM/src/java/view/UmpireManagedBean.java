/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.Match;
import model.Player;
import model.Tournament;
import model.Umpire;

@ManagedBean
@SessionScoped
public class UmpireManagedBean {

    private Umpire current;

    /** Creates a new instance of UmpireManagedBean */
    public UmpireManagedBean() {
        FacesContext context = FacesContext.getCurrentInstance();
        current = (Umpire) context.getExternalContext().getSessionMap().get(RegisteredUserManagedBean.USER_SESSION_KEY);
    }

    public List<Match> getMatches() {
        return current.getMatches();
    }

    public List<Tournament> getTournaments() {
        return current.getTournaments();
    }

    public List<Player> getTrackList() {
        return current.getTrackList();
    }
}
