/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import model.Match;
import model.Player;
import model.Referee;
import model.Set;
import model.Tournament;
import model.Umpire;

@ManagedBean
@SessionScoped
public class MainPageManagedBean implements Serializable {

    @PersistenceContext
    private EntityManager em;
    private Tournament selectedTournament;
    private Match selectedMatch;

    /** Creates a new instance of MainPageManagedBean */
    public MainPageManagedBean() {
    }

    public boolean isPlayer() {
        FacesContext cnt = FacesContext.getCurrentInstance();
        Object ru = cnt.getExternalContext().getSessionMap().get(RegisteredUserManagedBean.USER_SESSION_KEY);
        if (ru != null) {
            return (ru instanceof Player);
        }
        return false;
    }

    public List<Tournament> getTournaments() {
        java.util.Date now = new java.util.Date();
        Query q = em.createQuery("SELECT distinct t from Tournament t where t.endDate >= :gr AND t.startDate <= :ls");
        q.setParameter("gr", now, TemporalType.DATE);
        q.setParameter("ls", now, TemporalType.DATE);
        List list = q.getResultList();
        return list;
    }

    public List<Tournament> getFutureTournaments() {
        java.util.Date now = new java.util.Date();
        Query q = em.createQuery("SELECT distinct t from Tournament t where t.startDate >= :gr");
        q.setParameter("gr", now, TemporalType.DATE);
        List list = q.getResultList();
        return list;
    }

    public Tournament getSelectedTournament() {
        if (selectedTournament == null) {
            FacesContext cnt = FacesContext.getCurrentInstance();
            selectedTournament = (Tournament) cnt.getExternalContext().getSessionMap().get("trnm");
        }
        Query q = em.createQuery("select distinct r from Referee r "
                + "join r.tournaments t "
                + "where t.id = :tid");
        q.setParameter("tid", selectedTournament.getId());
        selectedTournament.setReferees(q.getResultList());
        q = em.createQuery("select distinct u from Umpire u "
                + "join u.tournaments t "
                + "where t.id = :tid");
        q.setParameter("tid", selectedTournament.getId());
        selectedTournament.setUmpires(q.getResultList());
        q = em.createQuery("select distinct c from Court c "
                + "join c.tournaments t "
                + "where t.id = :tid");
        q.setParameter("tid", selectedTournament.getId());
        selectedTournament.setCourts(q.getResultList());
        q = em.createQuery("select distinct t from Team t "
                + "join t.tournament to "
                + "where to.id = :tid");
        q.setParameter("tid", selectedTournament.getId());
        selectedTournament.setTeams(q.getResultList());
        return selectedTournament;
    }

    public List<Match> getMatches() {
        if (selectedTournament == null) {
            return null;
        }
        Query q = em.createQuery("SELECT distinct m FROM tmatch m "
                + "LEFT JOIN FETCH m.teams "
                + "JOIN m.tournament t "
                + "WHERE t.id = :tid");
        q.setParameter("tid", selectedTournament.getId());
        return q.getResultList();
    }

    public String getTournamentDetails(Tournament selectedTournament) {
        this.selectedTournament = selectedTournament;
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.getExternalContext().getSessionMap().put("trnm", selectedTournament);
        return "tournamentDetails";
    }

    public String matchDetails(Match match) {
        this.selectedMatch = match;
        return "matchDetails";
    }

    public void matchDetailsEvent(ActionEvent e) {
        selectedMatch = (Match) e.getComponent().getAttributes().get("match");
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.getExternalContext().getSessionMap().put("mtch", selectedMatch);
    }

    public List<Referee> getMatchReferees() {
        if (selectedMatch == null) {
            return null;
        }
        Query q = em.createQuery("Select distinct r from Referee r "
                + "join r.matches m "
                + "where m.id = :mid");
        q.setParameter("mid", selectedMatch.getId());
        return q.getResultList();
    }

    public List<Umpire> getMatchUmpires() {
        if (selectedMatch == null) {
            return null;
        }
        Query q = em.createQuery("Select distinct r from Umpire r "
                + "join r.matches m "
                + "where m.id = :mid");
        q.setParameter("mid", selectedMatch.getId());
        return q.getResultList();
    }

    public List<Set> getMatchSets() {
        if (selectedMatch == null) {
            selectedMatch = (Match) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("mtch");
        }
        if (selectedMatch == null) {
            return null;
        }
        Query q = em.createQuery("Select distinct st from tmatch m "
                + "join m.sets st "
                + "left join fetch st.games "
                + "where m.id = :mid");
        q.setParameter("mid", selectedMatch.getId());
        return q.getResultList();
    }

    public Match getSelectedMatch() {
        return selectedMatch;
    }

    public void setSelectedMatch(Match selectedMatch) {
        this.selectedMatch = selectedMatch;
    }
}
