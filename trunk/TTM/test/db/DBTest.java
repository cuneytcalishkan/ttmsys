/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import controller.HibernateUtil;
import java.util.Date;
import model.Manager;
import model.RegisteredUser;
import model.Tournament;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DBTest {

    private Session s;

    public DBTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        s = HibernateUtil.getSessionFactory().openSession();
    }

    @After
    public void tearDown() {
        s.close();
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void hello() {

        Manager manager = null;
        try {
            Query getByUsername = s.createQuery("from RegisteredUser ru where ru.username = :username");
            getByUsername.setString("username", "cuneytcalishkan");
            RegisteredUser ru = (RegisteredUser) getByUsername.uniqueResult();
            if (ru instanceof Manager) {
                manager = (Manager) ru;
            } else {
                throw new HibernateException("");
            }
        } catch (HibernateException he) {
            manager = new Manager("Cüneyt", "ÇALIŞKAN", "cuneytcalishkan", "auz7prw+");
            s.persist(manager);
        }

        Tournament t = new Tournament(Tournament.MIXED_DOUBLES, Tournament.MIXED_DOUBLES,
                new Date(), new Date(System.currentTimeMillis() + 1000000), 12000);
        t.setManager(manager);
        s.saveOrUpdate(t);
    }
}
