/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import model.Court;
import model.Draw;
import model.Manager;
import model.Referee;
import model.Team;
import model.Tournament;
import model.Umpire;

@Named(value = "tournamentManagedBean")
@SessionScoped
public class TournamentManagedBean implements Serializable {

    private Date startDate;
    private Date endDate;
    private String type;
    private String name;
    private double prize;
    private String report;
    @PersistenceContext
    private EntityManager em;
    @Resource
    private UserTransaction utx;
    private Tournament current;

    /** Creates a new instance of TournamentManagedBean */
    public TournamentManagedBean() {
    }

    public String createTournament() {
        FacesContext context = FacesContext.getCurrentInstance();
        Manager manager = (Manager) context.getExternalContext().getSessionMap().get(RegisteredUserManagedBean.USER_SESSION_KEY);
        Tournament t = new Tournament(name, type, startDate, endDate, prize);
        t.setReport(report);
        t.setManager(manager);
        try {
            utx.begin();
            em.persist(t);
            em.persist(em.merge(manager));
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(TournamentManagedBean.class.getName()).log(Level.SEVERE, null, e);
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
        }
        return "manager:index";
    }

    public String[] getTournamentTypes() {
        String[] result = new String[]{
            Tournament.MENS_SINGLES,
            Tournament.WOMENS_SINGLES,
            Tournament.MENS_DOUBLES,
            Tournament.WOMENS_DOUBLES,
            Tournament.MIXED_DOUBLES};
        return result;
    }

    public void linkTournament(Tournament t) {
        current = t;
    }

    public String editTournament() {
        try {
            utx.begin();
            em.merge(current);
            utx.commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return "manager:index";
    }

    public List<Team> getTeams() {
        String query = "select distinct t from Tournament t left join fetch t.teams "
                + "where t.id = :tid";
        Query q = em.createQuery(query);
        q.setParameter("tid", current.getId());
        current = (Tournament) q.getSingleResult();

        for (Team team : current.getTeams()) {
            query = "select distinct team from Team team where team.id = :tid";
            q = em.createQuery(query);
            q.setParameter("tid", team.getId());
            team = (Team) q.getSingleResult();
        }

        return current.getTeams();
    }

    public String generateDraws() {
        List<Team> teams = current.getTeams();
        if (teams == null || teams.isEmpty()) {
            return "manager:editTournament";
        }
        java.util.Collections.shuffle(teams);
        LinkedList<Team> tteams = new LinkedList<Team>(teams);
        LinkedList<Draw> draws = new LinkedList<Draw>();
        int dCount = ((tteams.size() % 2 == 0) ? tteams.size() / 2 : tteams.size() / 2 + 1);
        for (int i = 0; i < dCount; i++) {
            Team ht = tteams.removeFirst();
            Team at = null;
            if (!tteams.isEmpty()) {
                at = tteams.removeFirst();
            }
            Draw d = new Draw(ht, at);
            try {
                utx.begin();
                em.persist(d);
                utx.commit();
            } catch (Exception e) {
                try {
                    utx.rollback();
                } catch (Exception ex) {
                }
            }
            draws.add(d);
        }
        generateDrawPairs(draws);
        try {
            utx.begin();
            em.merge(current);
            utx.commit();
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
        }
        return "manager:editTournament";
    }

    private void generateDrawPairs(LinkedList<Draw> draws) {
        int dCount = ((draws.size() % 2 == 0) ? draws.size() / 2 : draws.size() / 2 + 1);
        LinkedList<Draw> ddraws = new LinkedList<Draw>();
        for (int i = 0; i < dCount; i++) {
            Draw hd = draws.removeFirst();
            Draw ad = null;
            if (!draws.isEmpty()) {
                ad = draws.removeFirst();
            }
            Draw d = new Draw(hd, ad);
            try {
                utx.begin();
                em.persist(d);
                utx.commit();
            } catch (Exception e) {
                try {
                    utx.rollback();
                } catch (Exception ex) {
                }
            }
            ddraws.add(d);
        }
        if (ddraws.size() > 1) {
            generateDrawPairs(ddraws);
        } else {
            current.setDraw(ddraws.removeFirst());
            try {
                utx.begin();
                em.merge(current);
                utx.commit();
            } catch (Exception e) {
                try {
                    utx.rollback();
                } catch (Exception ex) {
                }
            }
        }
    }

    public String removeReferee(Referee ref) {
        current.removeReferee(ref);
        try {
            utx.begin();
            em.merge(current);
            utx.commit();
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
        }
        return null;
    }

    public String removeUmpire(Umpire ump) {
        current.removeUmpire(ump);
        try {
            utx.begin();
            em.merge(current);
            utx.commit();
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
        }
        return null;
    }

    public String removeCourt(Court court){
        current.removeCourt(court);
        try {
            utx.begin();
            em.merge(current);
            utx.commit();
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
        }
        return null;
    }

    public String removeTeam(Team team){
        current.removeTeam(team);
        try {
            utx.begin();
            em.merge(current);
            utx.commit();
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
        }
        return null;
    }

    public List<Draw> getDraw() {
        return current.getDrawList();
    }

    public Tournament getCurrent() {
        Query q = em.createQuery("select distinct r from Referee r "
                + "join r.tournaments t "
                + "where t.id = :tid");
        q.setParameter("tid", current.getId());
        current.setReferees(q.getResultList());
        q = em.createQuery("select distinct u from Umpire u "
                + "join u.tournaments t "
                + "where t.id = :tid");
        q.setParameter("tid", current.getId());
        current.setUmpires(q.getResultList());
        q = em.createQuery("select distinct c from Court c "
                + "join c.tournaments t "
                + "where t.id = :tid");
        q.setParameter("tid", current.getId());
        current.setCourts(q.getResultList());
        return current;
    }

    public void setCurrent(Tournament current) {
        this.current = current;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrize() {
        return prize;
    }

    public void setPrize(double prize) {
        this.prize = prize;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
