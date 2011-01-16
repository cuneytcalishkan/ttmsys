/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.List;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import model.Match;
import model.Player;
import model.Referee;
import model.Tournament;

/**
 *
 * @author Natan
 */
@Named(value = "refereeManagedBean")
@Dependent
public class RefereeManagedBean {

    @PersistenceContext(unitName = "TTMPU")
    private EntityManager em;
    @Resource
    private UserTransaction utx;
    private Referee referee;

    /** Creates a new instance of RefereeManagedBean */
    public RefereeManagedBean() {
        FacesContext context = FacesContext.getCurrentInstance();
        referee = (Referee) context.getExternalContext().getSessionMap().get(RegisteredUserManagedBean.USER_SESSION_KEY);
    }

    public List<Tournament> getTournaments() {
        Query q = em.createQuery("SELECT m FROM Referee r JOIN r.tournaments m");
        return q.getResultList();
    }

    public List<Match> getMatches() {
        Query q = em.createQuery("SELECT m FROM Referee r JOIN r.matches m WHERE r.id = :uid");
        q.setParameter("uid", referee.getId());
        return q.getResultList();
    }

    public List<Player> getTrackList() {
        Query q = em.createQuery("SELECT t FROM Referee r JOIN r.trackList t");
        referee.setTrackList(q.getResultList());
        return referee.getTrackList();
    }

        public void removePlayer(ActionEvent event) {
        Player p = (Player) event.getComponent().getAttributes().get("player");
        referee.removeFromTrackList(p);
        try {
            utx.begin();
            em.persist(em.merge(referee));
            utx.commit();
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
        }
    }
}
