/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import model.Tournament;

@ManagedBean
@RequestScoped
public class MainPageManagedBean {

    @PersistenceContext
    private EntityManager em;

    /** Creates a new instance of MainPageManagedBean */
    public MainPageManagedBean() {
    }

    public List<Tournament> getTournaments() {
        java.util.Date now = new java.util.Date();
        Query q = em.createQuery("SELECT t from Tournament t where t.endDate >= :gr AND t.startDate <= :ls");
        q.setParameter("gr", now, TemporalType.DATE);
        q.setParameter("ls", now, TemporalType.DATE);
        List list = q.getResultList();
        return list;
    }
}
