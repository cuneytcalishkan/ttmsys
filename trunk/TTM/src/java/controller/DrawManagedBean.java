/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import model.Draw;
import model.Match;
import model.Referee;
import model.Team;
import model.Tournament;
import model.Umpire;

@Named(value = "drawManagedBean")
@SessionScoped
public class DrawManagedBean implements Serializable {

    @PersistenceContext
    private EntityManager em;
    @Resource
    private UserTransaction utx;
    private Draw current;
    private Draw awayDraw;
    private Draw homeDraw;
    private Team homeTeam;
    private Team awayTeam;
    private Date mDate;
    private String mTime;
    private List<Umpire> mUmpires;
    private List<Referee> mReferees;
    private Court court;
    private Tournament tournament;

    /** Creates a new instance of DrawManagedBean */
    public DrawManagedBean() {
        mUmpires = new ArrayList<Umpire>();
        mReferees = new ArrayList<Referee>();
        court = new Court();
    }

    public boolean canAssignReferee(Referee r) {
        return !mReferees.contains(r);
    }

    public boolean canAssignUmpire(Umpire u) {
        return !mUmpires.contains(u);
    }

    public String removeUmpire(Umpire u) {
        mUmpires.remove(u);
        return "manager:editDraw";
    }

    public String removeReferee(Referee r) {
        mReferees.remove(r);
        return "manager:editDraw";
    }

    public String addReferee(Referee r) {
        if (!mReferees.contains(r)) {
            mReferees.add(r);
        }
        return "manager:editDraw";
    }

    public String addUmpire(Umpire u) {
        if (!mUmpires.contains(u)) {
            mUmpires.add(u);
        }
        return "manager:editDraw";
    }

    public boolean isCreatingMatchAllowed() {
        return (current.getHomeTeam() != null && current.getAwayTeam() != null);
    }

    public List<Umpire> getUmpireList() {
        Query q = em.createQuery("SELECT distinct u FROM Umpire AS u LEFT JOIN FETCH u.matches "
                + "join u.tournaments t "
                + "where t.id = :tid");
        q.setParameter("tid", tournament.getId());
        List<Umpire> umps = q.getResultList();
        umps.removeAll(mUmpires);
        return umps;
    }

    public List<Referee> getRefereeList() {
        Query q = em.createQuery("SELECT distinct r FROM Referee AS r LEFT JOIN FETCH r.matches "
                + "join r.tournaments t "
                + "where t.id = :tid");
        q.setParameter("tid", tournament.getId());
        List<Referee> refs = q.getResultList();
        refs.removeAll(mReferees);
        return refs;
    }

    public boolean canAssignCourt(Court c) {
        return !court.equals(c);
    }

    public String assignCourt(Court c) {
        court = c;
        return "manager:editDraw";
    }

    public List<Court> getCourtList() {
        Query q = em.createQuery("SELECT distinct c FROM Court AS c LEFT JOIN FETCH c.matches "
                + "join c.tournaments t "
                + "where t.id = :tid");
        q.setParameter("tid", tournament.getId());
        return q.getResultList();
    }

    public String createMatch() {
        Match match = new Match(mDate, mTime);
        List<Match> matches = null;
        try {
            utx.begin();
            em.persist(match);
            /*for (Referee referee : mReferees) {
                matches = referee.getMatches();
                matches.add(match);
                em.merge(referee);
            }*/
            match.setReferees(mReferees);

            /*for (Umpire umpire : mUmpires) {
                matches = umpire.getMatches();
                matches.add(match);
                em.merge(umpire);
            }*/
            match.setUmpires(mUmpires);

            matches = court.getMatches();
            matches.add(match);
            court.setMatches(matches);
            match.setCourt(court);

            /*matches = homeTeam.getMatches();
            matches.add(match);
            homeTeam.setMatches(matches);*/
            match.addTeam(homeTeam);

            /*matches = awayTeam.getMatches();
            matches.add(match);
            awayTeam.setMatches(matches);*/
            match.addTeam(awayTeam);

            /*matches = tournament.getMatches();
            matches.add(match);
            tournament.setMatches(matches);*/
            match.setTournament(tournament);

            em.merge(match);
            em.merge(court);
            em.merge(homeTeam);
            em.merge(awayTeam);
            em.merge(tournament);
            
            utx.commit();
        } catch (Exception e) {
            try {
                Logger.getLogger(DrawManagedBean.class.getName()).log(Level.SEVERE, null, e);
                utx.rollback();
            } catch (Exception ex) {
            }
        }
        return "manager:editDraw";
    }

    public String linkDraw(Draw d, Tournament tourn) {
        current = d;
        if (current != null) {
            homeTeam = current.getHomeTeam();
            awayTeam = current.getAwayTeam();
            awayDraw = current.getAwayDraw();
            homeDraw = current.getHomeDraw();
        }
        tournament = tourn;
        return "manager:editDraw";
    }

    public Draw getCurrent() {
        return current;
    }

    public void setCurrent(Draw current) {
        this.current = current;
    }

    public Draw getAwayDraw() {
        return awayDraw;
    }

    public void setAwayDraw(Draw awayDraw) {
        this.awayDraw = awayDraw;
    }

    public Draw getHomeDraw() {
        return homeDraw;
    }

    public void setHomeDraw(Draw homeDraw) {
        this.homeDraw = homeDraw;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Court getCourt() {
        return court;
    }

    public void setCourt(Court court) {
        this.court = court;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public List<Referee> getmReferees() {
        return mReferees;
    }

    public void setmReferees(List<Referee> mReferees) {
        this.mReferees = mReferees;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }

    public List<Umpire> getmUmpires() {
        return mUmpires;
    }

    public void setmUmpires(List<Umpire> mUmpires) {
        this.mUmpires = mUmpires;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }
}
