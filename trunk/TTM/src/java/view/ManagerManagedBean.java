/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Manager;
import model.MembershipRequest;

/**
 *
 * @author Natan
 */
@Named(value="managerManagedBean")
@Dependent
public class ManagerManagedBean {

    @PersistenceContext
    private EntityManager em;
    private Manager manager;

    /** Creates a new instance of ManagerManagedBean */
    public ManagerManagedBean() {
        FacesContext context = FacesContext.getCurrentInstance();
        manager = (Manager) context.getExternalContext().getSessionMap().get(RegisteredUserManagedBean.USER_SESSION_KEY);
    }

    public List<MembershipRequest> getMembershipRequests()
    {
        Query q = em.createQuery("from MembershipRequest");
        return q.getResultList();
    }

}
