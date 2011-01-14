/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Tournament;


@ManagedBean
@RequestScoped
public class MainPageManagedBean {

    @PersistenceContext
    private EntityManager em;
    /** Creates a new instance of MainPageManagedBean */
    public MainPageManagedBean() {
    }

    public List<Tournament> getTournaments(){
        /*Query q = em.createQuery("from Tournament");
        List list = q.getResultList();*/
        List<Tournament> list = new ArrayList();
        list.add(new Tournament("name", "23", new Date(), new Date(), 23));
        return list;
    }

    public MainPageManagedBean(EntityManager em) {
        this.em = em;
    }
}
