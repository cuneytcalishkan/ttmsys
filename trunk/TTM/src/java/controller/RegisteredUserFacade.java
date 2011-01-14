/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import model.RegisteredUser;

@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class RegisteredUserFacade {

    @PersistenceContext
    private EntityManager em;
    @Resource
    private UserTransaction utx;

    public RegisteredUser getUser(String username) {
        try {
            Query getByUsername = em.createQuery("from RegisteredUser ru where ru.username = :username");
            getByUsername.setParameter("username", username);
            RegisteredUser user = (RegisteredUser) getByUsername.getSingleResult();
            return user;
        } catch (NoResultException nre) {
            return null;
        }
    }

    public String createUser(String name, String surname, String username, String password, String passwordv) {
        FacesContext context = FacesContext.getCurrentInstance();
        RegisteredUser ruser = getUser(username);
        if (ruser == null) {
            if (!password.equals(passwordv)) {
                FacesMessage message = new FacesMessage("The specified passwords do not match. Please try again");
                context.addMessage(null, message);
                return null;
            }
            ruser = new RegisteredUser(name, surname, username, password);
            try {
                utx.begin();
                em.persist(ruser);
                utx.commit();
                return "login";
            } catch (Exception e) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error creating user!",
                        "Unexpected error when creating your account.  Please contact the system Administrator");
                context.addMessage(null, message);
                Logger.getAnonymousLogger().log(Level.SEVERE,
                        "Unable to create new user",
                        e);
                return null;
            }
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Username '"
                    + username
                    + "' already exists!  ",
                    "Please choose a different username.");
            context.addMessage(null, message);
            return null;
        }
    }
}
