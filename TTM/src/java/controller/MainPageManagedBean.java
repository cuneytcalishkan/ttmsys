/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import model.Player;
import model.Tournament;

@ManagedBean
@RequestScoped
public class MainPageManagedBean {

    @PersistenceContext
    private EntityManager em;
    private Tournament selectedTournament;

    /** Creates a new instance of MainPageManagedBean */
    public MainPageManagedBean() {
    }

    public boolean isPlayer(){
        FacesContext cnt = FacesContext.getCurrentInstance();
        Object ru = cnt.getExternalContext().getSessionMap().get(RegisteredUserManagedBean.USER_SESSION_KEY);
        if(ru != null){
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
        if(selectedTournament == null) return null;
        Query q = em.createQuery("SELECT distinct m FROM tmatch m "
                + "LEFT JOIN FETCH m.teams "
                + "JOIN m.tournament t "
                + "WHERE t.id = :tid");
        q.setParameter("tid", selectedTournament.getId());
        selectedTournament.setMatches(q.getResultList());
        q = em.createQuery("select distinct r from Referee r "
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

    public String getTournamentDetails(Tournament selectedTournament) {
        this.selectedTournament = selectedTournament;
        return "tournamentDetails";
    }


}