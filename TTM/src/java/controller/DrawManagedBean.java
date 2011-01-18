/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;
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
    private Time mTime;
    private List<Umpire> mUmpires;
    private List<Referee> mReferees;
    private Court court;
    private Tournament tournament;

    /** Creates a new instance of DrawManagedBean */
    public DrawManagedBean() {
    }

    public boolean isCreateMatch() {
        return (current.getHomeTeam() != null && current.getAwayTeam() != null);
    }

    public List<Umpire> getUmpires() {
        Query q = em.createQuery("FROM Umpire");
        return q.getResultList();
    }

    public List<Referee> getReferees() {
        Query q = em.createQuery("FROM Referee");
        return q.getResultList();
    }

    public String createMatch() {
        Match match = new Match(mDate, mTime);
        List<Match> matches = null;

        for (Referee referee : mReferees) {
            matches = referee.getMatches();
            matches.add(match);
            try {
                utx.begin();
                em.merge(referee);
                utx.commit();
            } catch (Exception e) {
                try {
                    utx.rollback();
                } catch (Exception ex) {
                }
            }
        }
        match.setReferees(mReferees);

        for (Umpire umpire : mUmpires) {
            matches = umpire.getMatches();
            matches.add(match);
            try {
                utx.begin();
                em.merge(umpire);
                utx.commit();
            } catch (Exception e) {
                try {
                    utx.rollback();
                } catch (Exception ex) {
                }
            }
        }
        match.setUmpires(mUmpires);

        matches = court.getMatches();
        matches.add(match);
        match.setCourt(court);

        matches = homeTeam.getMatches();
        matches.add(match);
        homeTeam.setMatches(matches);
        match.addTeam(homeTeam);

        matches = awayTeam.getMatches();
        matches.add(match);
        awayTeam.setMatches(matches);
        match.addTeam(awayTeam);

        match.setTournament(tournament);
        try {
            utx.begin();
            em.merge(court);
            em.merge(homeTeam);
            em.merge(awayTeam);
            em.merge(tournament);
            em.persist(match);
            utx.commit();
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
        }
        return "manager:editDraw";
    }

    public String linkDraw(Draw d) {
        current = d;
        if (d != null) {
            awayDraw = d.getAwayDraw();
            homeDraw = d.getHomeDraw();
        } else {
            homeDraw = null;
            awayDraw = null;
        }
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

    public Time getmTime() {
        return mTime;
    }

    public void setmTime(Time mTime) {
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
