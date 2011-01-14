/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.DTO.RegisteredUserDTO;
import model.RegisteredUser;

@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class RegisteredUserFacade {

    @PersistenceContext
    private EntityManager em;

    public RegisteredUserDTO getUser(String username) {
        try {
            Query getByUsername = em.createQuery("from RegisteredUser ru where ru.username = :username");
            getByUsername.setParameter("username", username);
            RegisteredUser user = (RegisteredUser) getByUsername.getSingleResult();
            return user;
        } catch (NoResultException nre) {
            return null;
        }
    }
}
