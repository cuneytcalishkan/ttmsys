/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import model.Court;
import model.Match;
import model.Referee;
import model.Tournament;
import model.Umpire;

@Named(value = "matchManagedBean")
@SessionScoped
public class MatchManagedBean implements Serializable {

    @PersistenceContext
    private EntityManager em;
    @Resource
    private UserTransaction utx;
    private Tournament tournament;
    private Match selectedMatch;

    /** Creates a new instance of MatchManagedBean */
    public MatchManagedBean() {
    }

    public String linkMatch(Match match, Tournament tournament) {
        selectedMatch = match;
        this.tournament = tournament;
        return "manager:editMatch";
    }

    public String save() {
        try {
            utx.begin();
            em.merge(selectedMatch);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(MatchManagedBean.class.getName()).log(Level.SEVERE, null, e);
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
        }
        return "manager:editTournament";
    }

    public Match getSelectedMatch() {
        return selectedMatch;
    }

    public void setSelectedMatch(Match selectedMatch) {
        this.selectedMatch = selectedMatch;
    }

    public List<Referee> getMatchReferees() {
        if (selectedMatch == null) {
            return null;
        }
        Query q = em.createQuery("Select r from Referee r "
                + "join r.matches m "
                + "where m.id = :mid");
        q.setParameter("mid", selectedMatch.getId());
        selectedMatch.setReferees(q.getResultList());
        return q.getResultList();
    }

    public List<Umpire> getMatchUmpires() {
        if (selectedMatch == null) {
            return null;
        }
        Query q = em.createQuery("Select r from Umpire r "
                + "join r.matches m "
                + "where m.id = :mid");
        q.setParameter("mid", selectedMatch.getId());
        selectedMatch.setUmpires(q.getResultList());
        return q.getResultList();
    }

    public List<Referee> getOtherReferees() {
        Query q = em.createQuery("SELECT distinct r FROM Referee r "
                + "join r.tournaments t "
                + "where t.id = :tid AND r not in "
                + "(select rf from Referee rf join rf.matches m "
                + "where m.id = :mid)");
        q.setParameter("tid", tournament.getId());
        q.setParameter("mid", selectedMatch.getId());
        return q.getResultList();
    }

    public void addReferee(Referee referee) {
        selectedMatch.addReferee(referee);
        try {
            utx.begin();
            em.merge(selectedMatch);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(MatchManagedBean.class.getName()).log(Level.SEVERE, null, e);
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
        }
    }

    public void removeReferee(Referee referee) {
        selectedMatch.removeReferee(referee);
        try {
            utx.begin();
            em.merge(selectedMatch);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(MatchManagedBean.class.getName()).log(Level.SEVERE, null, e);
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
        }
    }

    public void addUmpire(Umpire umpire) {
        selectedMatch.addUmpire(umpire);
        try {
            utx.begin();
            em.merge(selectedMatch);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(MatchManagedBean.class.getName()).log(Level.SEVERE, null, e);
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
        }
    }

    public void removeUmpire(Umpire umpire) {
        selectedMatch.removeUmpire(umpire);
        try {
            utx.begin();
            em.merge(selectedMatch);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(MatchManagedBean.class.getName()).log(Level.SEVERE, null, e);
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
        }
    }

    public List<Umpire> getOtherUmpires() {
        Query q = em.createQuery("SELECT distinct r FROM Umpire r "
                + "join r.tournaments t "
                + "where t.id = :tid AND r not in "
                + "(select rf from Umpire rf join rf.matches m "
                + "where m.id = :mid)");
        q.setParameter("tid", tournament.getId());
        q.setParameter("mid", selectedMatch.getId());
        return q.getResultList();
    }

    public List<Court> getCourts() {
        Query q = em.createQuery("SELECT distinct r FROM Court r "
                + "join r.tournaments t "
                + "where t.id = :tid");
        q.setParameter("tid", tournament.getId());
        return q.getResultList();
    }

    public boolean canAssignCourt(Court court) {
        return (!court.equals(selectedMatch.getCourt()));
    }

    public void assignCourt(Court court) {
        selectedMatch.setCourt(court);
        try {
            utx.begin();
            em.merge(selectedMatch);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(MatchManagedBean.class.getName()).log(Level.SEVERE, null, e);
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
        }
    }
}
