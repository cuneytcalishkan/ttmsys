/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import model.Draw;

@Named(value = "drawManagedBean")
@SessionScoped
public class DrawManagedBean implements Serializable {

    @PersistenceContext
    private EntityManager em;
    @Resource
    private UserTransaction utx;
    private Draw current;
    private Draw awayDraw;
    private Draw homeDraw;

    /** Creates a new instance of DrawManagedBean */
    public DrawManagedBean() {
    }

    public String linkDraw(Draw d) {
        current = d;
        awayDraw = d.getAwayDraw();
        homeDraw = d.getHomeDraw();
        return "manager:editDraw";
    }

    public Draw getCurrent() {
        return current;
    }

    public void setCurrent(Draw current) {
        this.current = current;
    }

    public Draw getAwayDraw() {
        return awayDraw;
    }

    public void setAwayDraw(Draw awayDraw) {
        this.awayDraw = awayDraw;
    }

    public Draw getHomeDraw() {
        return homeDraw;
    }

    public void setHomeDraw(Draw homeDraw) {
        this.homeDraw = homeDraw;
    }
}
