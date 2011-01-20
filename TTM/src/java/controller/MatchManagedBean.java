/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Match;
import model.Tournament;

@Named(value="matchManagedBean")
@SessionScoped
public class MatchManagedBean implements Serializable {

    @PersistenceContext
    private EntityManager em;
    private Tournament tournament;
    private Match selectedMatch;

    /** Creates a new instance of MatchManagedBean */
    public MatchManagedBean() {
    }

    public String linkMatch(Match match, Tournament tournament){
        selectedMatch = match;
        this.tournament = tournament;
        return "manager:editMatch";
    }

    public Match getSelectedMatch() {
        return selectedMatch;
    }

    public void setSelectedMatch(Match selectedMatch) {
        this.selectedMatch = selectedMatch;
    }

}
