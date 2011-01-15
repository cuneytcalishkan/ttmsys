/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;
import model.Manager;
import model.MembershipRequest;
import model.Player;
import model.Referee;
import model.RegisteredUser;
import model.Umpire;

@ManagedBean(name = "User")
@SessionScoped
public class RegisteredUserManagedBean {

    public static final String USER_SESSION_KEY = "user";
    private String username;
    private String password;
    private String passwordv;
    private String name;
    private String surname;
    private String type;
    private RegisteredUser current;
    @PersistenceContext
    private EntityManager em;
    @Resource
    private UserTransaction utx;

    public RegisteredUserManagedBean() {
    }

    public RegisteredUserManagedBean(EntityManager em) {
        this.em = em;
    }

    public RegisteredUser getCurrent() {
        return current;
    }

    public void setCurrent(RegisteredUser current) {
        this.current = current;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordv() {
        return passwordv;
    }

    public void setPasswordv(String passwordv) {
        this.passwordv = passwordv;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getUserTypes() {
        String[] types = new String[]{"registereduser", "manager", "player", "referee", "umpire"};
        return types;
    }

    public boolean isLogged() {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getExternalContext().getSessionMap().containsKey(USER_SESSION_KEY);
    }

    public String validateUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        current = getUser();
        if (current != null) {
            if (!current.getPassword().equals(password)) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Login Failed!",
                        "The username/password specified is not correct.");
                context.addMessage(null, message);
                return null;
            }

            context.getExternalContext().getSessionMap().put(USER_SESSION_KEY, current);
            if (current instanceof Manager) {
                return "manager:index";
            } else if (current instanceof Referee) {
                return "referee:index";
            } else if (current instanceof Umpire) {
                return "umpire:index";
            } else if (current instanceof Player) {
                return "player:index";
            } else {
                return "registereduser:index";
            }
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Login Failed!",
                    "The username/password specified is not correct.");
            context.addMessage(null, message);
            return null;
        }
    }

    public RegisteredUser getUser() {
        try {
            Query getByUsername = em.createQuery("from RegisteredUser ru where ru.username = :username");
            getByUsername.setParameter("username", username);
            RegisteredUser user = (RegisteredUser) getByUsername.getSingleResult();
            return user;
        } catch (NoResultException nre) {
            return null;
        }
    }

    public Manager getManager() {
        try {
            Query getByUsername = em.createQuery("from Manager man left join fetch man.membershipRequests");
            List<Manager> managers = getByUsername.getResultList();
            if (managers.isEmpty()) {
                return null;
            }
            return managers.get(0);
        } catch (NoResultException nre) {
            return null;
        }
    }

    public String createUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        RegisteredUser usr = getUser();
        if (usr == null) {
            if (!password.equals(passwordv)) {
                FacesMessage message = new FacesMessage("The specified passwords do not match. Please try again");
                context.addMessage(null, message);
                return null;
            }
            Manager man = getManager();
            if (man != null) {
                MembershipRequest mr = new MembershipRequest(name, surname, username, password, type);
                man.addMembershipRequest(mr);
                try {
                    utx.begin();
                    em.persist(mr);
                    em.merge(man);
                    utx.commit();
                    return "login";
                } catch (Exception e) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error creating user!",
                            "Unexpected error occurred while creating your account.  Please contact the system Administrator");
                    context.addMessage(null, message);
                    Logger.getAnonymousLogger().log(Level.SEVERE,
                            "Unable to create new user",
                            e);
                    return null;
                }
            } else {
                RegisteredUser ruser = new RegisteredUser(name, surname, username, password);

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

    public String logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "index";

    }
}
