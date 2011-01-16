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
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import model.Match;
import model.Player;
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

    /** Creates a new instance of UmpireManagedBean */
    public UmpireManagedBean() {
        FacesContext context = FacesContext.getCurrentInstance();
        current = (Umpire) context.getExternalContext().getSessionMap().get(RegisteredUserManagedBean.USER_SESSION_KEY);
    }

    public List<Match> getMatches() {
        Query q = em.createQuery("SELECT m FROM Umpire u JOIN u.matches m");
        current.setMatches(q.getResultList());
        return current.getMatches();
    }

    public List<Tournament> getTournaments() {
        Query q = em.createQuery("SELECT t FROM Umpire u JOIN u.tournaments t");
        current.setTournaments(q.getResultList());
        return current.getTournaments();
    }

    public List<Player> getTrackList() {
        Query q = em.createQuery("SELECT t FROM Umpire u JOIN u.trackList t");
        current.setTrackList(q.getResultList());
        return current.getTrackList();
    }

    public void removePlayer(ActionEvent event) {
        Player p = (Player) event.getComponent().getAttributes().get("player");
        current.removeFromTrackList(p);
        try {
            utx.begin();
            em.persist(em.merge(current));
            utx.commit();
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
        }
    }
}
