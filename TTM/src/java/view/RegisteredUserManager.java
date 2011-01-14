/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.RegisteredUserFacade;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.RegisteredUser;

@Named(value = "userManager")
@RequestScoped
public class RegisteredUserManager {

    public static final String USER_SESSION_KEY = "user";
    private String username;
    private String password;
    private String passwordv;
    private String name;
    private String surname;
    @EJB
    private RegisteredUserFacade ruf;

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

    public String validateUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        RegisteredUser user = getUser();
        if (user != null) {
            if (!user.getPassword().equals(password)) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Login Failed!",
                        "The username/password specified is not correct.");
                context.addMessage(null, message);
                return null;
            }

            context.getExternalContext().getSessionMap().put(USER_SESSION_KEY, user);
            return "index";
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Login Failed!",
                    "The username/password specified is not correct.");
            context.addMessage(null, message);
            return null;
        }
    }

    public String createUser() {
        return ruf.createUser(name, surname, username, password, passwordv);
    }

    public String logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "login";

    }

    private RegisteredUser getUser() {
        return ruf.getUser(username);
    }
}
