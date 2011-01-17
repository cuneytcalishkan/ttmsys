/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Time;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import model.Court;
import model.Manager;
import model.Match;
import model.Player;
import model.Referee;
import model.RegisteredUser;
import model.Team;
import model.Tournament;
import model.Umpire;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Natan
 */
public class Fill {

    public Fill() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void hello() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();t.begin();
        RegisteredUser reg = new RegisteredUser("Registered", "User", "ru", "ru");
        Manager man = new Manager("Manager", "man", "m", "m");
        Player ply = new Player("Player", "pl", "p", "p");
        Referee ref = new Referee("Referee", "rf", "r", "r");
        Umpire ump = new Umpire("Umpire", "ump", "u", "u");
        s.persist(ump);
        s.persist(reg);
        s.persist(man);
        s.persist(ply);
        s.persist(ref);
        GregorianCalendar cal1 = new GregorianCalendar(2010, 2, 2);
        GregorianCalendar cal2 = new GregorianCalendar(2011, 5, 2);
        Tournament tour = new Tournament("The Tournament", "görlz", cal1.getTime(), cal2.getTime(), 1000);
        ArrayList referees = new ArrayList<Referee>();
        referees.add(ref);
        ArrayList umpires = new ArrayList<Umpire>();
        umpires.add(ump);
        Court court = new Court("Court1");s.persist(court);
        cal1.set(2011, 2, 15);
        ArrayList aTeam = new ArrayList<Player>();
        aTeam.add(ply);
        Team team = new Team(aTeam);s.persist(team);
        Match match = new Match(cal1.getTime(), new Time(2500), court);
        match.addReferee(ref);
        match.addTeam(team);
        match.addUmpire(ump);s.persist(match);
        reg.addToTrackList(team);
        tour.setManager(man);
        tour.setReferees(referees);
        tour.setUmpires(umpires);
        s.persist(tour);
        t.commit();
        s.close();
    }
}
