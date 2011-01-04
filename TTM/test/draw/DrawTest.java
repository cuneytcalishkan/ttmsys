/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package draw;

import java.util.ArrayList;
import java.util.Date;
import model.DoublesTeam;
import model.Draw;
import model.Player;
import model.Team;
import model.Tournament;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author CUNEYT
 */
public class DrawTest {

    public DrawTest() {
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
    public void GenerateDraws() {
        Tournament t = new Tournament("Test Tournament", Tournament.MENS_DOUBLES, new Date(), new Date(System.currentTimeMillis() + 100000), 1200);
        ArrayList<Team> teams = new ArrayList<Team>();
        for (int i = 1; i < 17; i++) {
            ArrayList<Player> tp = new ArrayList<Player>();
            tp.add(new Player("Player1", "" + i, "player1" + i, ""));
            tp.add(new Player("Player2", "" + i, "player2" + i, ""));
            teams.add(new DoublesTeam(tp));
        }
        t.setTeams(teams);
        t.generateDraw();
        Draw d = t.getDraw();
        System.out.println(d.traverse(d, 0));
    }
}
