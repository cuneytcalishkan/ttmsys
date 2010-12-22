/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 *
 * @author CUNEYT
 */
public class Doubles extends Match {

    public Doubles(Date mDate, Time mTime, List<Player> players, Court court) {
        super(mDate, mTime, players, court);
    }

    public Doubles(Date mDate, Time mTime, Court court) {
        super(mDate, mTime, court);
    }

    public Doubles(Date mDate, Time mTime) {
        super(mDate, mTime);
    }

    public Doubles() {
    }
}
