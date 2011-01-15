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
        Query q = em.createQuery("from Tournament");
        List list = q.getResultList();
        return list;
    }
}
