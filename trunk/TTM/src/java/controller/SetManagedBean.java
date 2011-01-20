/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import model.Match;
import model.Set;

/**
 *
 * @author Natan
 */
@Named(value = "setManagedBean")
@SessionScoped
public class SetManagedBean implements Serializable {

    @PersistenceContext
    private EntityManager em;
    @Resource
    private UserTransaction utx;
    private Set set;
    private Match match;
    private boolean isNew;

    /** Creates a new instance of SetManagedBean */
    public SetManagedBean() {
    }

    public String linkSet(Set set, Match match) {
        Query q = em.createQuery("select s from tmatch m "
                + "join m.sets s "
                + "left join fetch s.games "
                + "where m.id =:mid");
        q.setParameter("mid", match.getId());
        match.setSets(q.getResultList());
        isNew = false;
        this.set = set;
        this.match = match;
        if (set == null) {
            this.set = new Set();
            isNew = true;
        }
        return "umpire:editSet";
    }

    public Set getSet() {
        return set;
    }

    public void setSet(Set set) {
        this.set = set;
    }

    public String addSet() {
        try {
            utx.begin();
            if (!isNew) {
                em.merge(set);
            } else {
                em.persist(em.merge(set));
                match.addSet(set);
                em.merge(match);
            }
            utx.commit();
        } catch (Exception ex) {
            Logger.getLogger(SetManagedBean.class.getName()).log(Level.SEVERE, ex.getMessage());
            try {
                utx.rollback();
            } catch (Exception e) {
            }
            return "umpire:editSet";
        }
        return "umpire:matchDetails";
    }

    public void removeSet(Set s, Match m) {
        Query q = em.createQuery("select s from tmatch m "
                + "join m.sets s "
                + "left join fetch s.games "
                + "where m.id =:mid");
        q.setParameter("mid", m.getId());
        m.setSets(q.getResultList());

        try {
            utx.begin();
            em.remove(em.merge(s));
            m.removeSet(s);
            em.merge(m);
            utx.commit();
        } catch (Exception ex) {
            Logger.getLogger(SetManagedBean.class.getName()).log(Level.SEVERE, ex.getMessage());
            try {
                utx.rollback();
            } catch (Exception e) {
            }
        }

    }
}
