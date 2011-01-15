/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import view.RegisteredUserManager;

public class AuthenticationPhaseListener implements PhaseListener {

    private static final String USER_LOGIN_OUTCOME = "login";

    @Override
    public void afterPhase(PhaseEvent event) {

        FacesContext context = event.getFacesContext();

        if (userExists(context)) {
            // allow processing of the requested view
            return;
        } else {
            // send the user to the login view
            if (requestingSecureView(context)) {
                context.responseComplete();
                context.getApplication().
                        getNavigationHandler().handleNavigation(context,
                        null,
                        USER_LOGIN_OUTCOME);
            }
        }

    }

    @Override
    public void beforePhase(PhaseEvent event) {
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

    /**
     * <p>Determine if the user has been authenticated by checking the session
     * for an existing <code>Wuser</code> object.</p>
     *
     * @param context the <code>FacesContext</code> for the current request
     * @return <code>true</code> if the user has been authenticated, otherwise
     *  <code>false</code>
     */
    private boolean userExists(FacesContext context) {
        ExternalContext extContext = context.getExternalContext();
        return (extContext.getSessionMap().containsKey(RegisteredUserManager.USER_SESSION_KEY));
    }

    /**
     * <p>Determines if the requested view is one of the login pages which will
     * allow the user to access them without being authenticated.</p>
     *
     * <p>Note, this implementation most likely will not work if the
     * <code>FacesServlet</code> is suffix mapped.</p>
     *
     * @param context the <code>FacesContext</code> for the current request
     * @return <code>true</code> if the requested view is allowed to be accessed
     *  without being authenticated, otherwise <code>false</code>
     */
    private boolean requestingSecureView(FacesContext context) {
        ExternalContext extContext = context.getExternalContext();
        String path = extContext.getRequestPathInfo();
        return (!"/main.xhtml".equals(path) && !"/login.xhtml".equals(path) && !"/newUser.xhtml".equals(path));
    }
}