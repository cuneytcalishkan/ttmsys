/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import model.Match;
import model.MatchStatistics;
import model.Tournament;
import model.Umpire;

@ManagedBean
@SessionScoped
public class UmpireManagedBean implements Serializable {

    @PersistenceContext(unitName = "TTMPU")
    private EntityManager em;
    @Resource
    private UserTransaction utx;
    private Umpire current;
    private Match selectedMatch;

    /** Creates a new instance of UmpireManagedBean */
    public UmpireManagedBean() {
        FacesContext context = FacesContext.getCurrentInstance();
        current = (Umpire) context.getExternalContext().getSessionMap().get(RegisteredUserManagedBean.USER_SESSION_KEY);
    }

    public List<Match> getMatches() {
        Query q = em.createQuery("SELECT u FROM Umpire u left JOIN fetch u.matches WHERE u.id = :uid");
        q.setParameter("uid", current.getId());
        current = (Umpire) q.getSingleResult();
        return current.getMatches();
    }

    public List<Tournament> getTournaments() {
        Query q = em.createQuery("SELECT t FROM Umpire u JOIN u.tournaments t");
        current.setTournaments(q.getResultList());
        return current.getTournaments();
    }

    public String linkStatistics(Match match){
        selectedMatch = match;
        return "umpire:editStatistics";
    }

    public String editStatistics(){
        try {
            utx.begin();
            em.persist(em.merge(selectedMatch.getStatistics()));
            em.merge(selectedMatch);
            utx.commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return "umpire:index";
    }

    public MatchStatistics getStatistics(){
        return selectedMatch.getStatistics();
    }

    public Match getSelectedMatch() {
        return selectedMatch;
    }

    public void setSelectedMatch(Match selectedMatch) {
        this.selectedMatch = selectedMatch;
    }
    
}
