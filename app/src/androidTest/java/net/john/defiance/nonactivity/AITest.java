package net.john.defiance.nonactivity;

import android.test.AndroidTestCase;

import junit.framework.TestCase;

import net.john.defiance.project.logic.AIMoveFactory;
import net.john.defiance.project.models.Player;
import net.john.defiance.project.models.Teams;

import java.util.ArrayList;

/**
 * Created by John on 12/29/2015.
 */
public class AITest extends AndroidTestCase {
    protected ArrayList<Player> playerList;

    protected Player rebelPlayer1;
    protected Player rebelPlayer2;
    protected Player rebelPlayer3;
    protected Player rebelPlayer4;
    protected Player spyPlayer1;
    protected Player spyPlayer2;

    protected ArrayList<Double> confidenceListPlayer1;
    protected ArrayList<Player> correctTeamList;

    public AITest(){
        super();
        rebelPlayer1 = new Player("rebel1", Teams.REBELS);
        rebelPlayer2 = new Player("rebel2", Teams.REBELS);
        rebelPlayer3 = new Player("rebel3", Teams.REBELS);
        rebelPlayer4 = new Player("rebel4", Teams.REBELS);

        spyPlayer1 = new Player("spy1", Teams.SPIES);
        spyPlayer2 = new Player("spy1", Teams.SPIES);

        //package all players into player list in order
        playerList = new ArrayList<Player>();
        playerList.add(rebelPlayer1);
        playerList.add(rebelPlayer2);
        playerList.add(rebelPlayer3);
        playerList.add(rebelPlayer4);
        playerList.add(spyPlayer1);
        playerList.add(spyPlayer2);

        //create a mock confidence list for rebelplayer1
        confidenceListPlayer1 = new ArrayList<Double>();
        confidenceListPlayer1.add(0.0); //knows he's not a spy
        confidenceListPlayer1.add(0.8); //player 2 might be a spy
        confidenceListPlayer1.add(0.2); //player 3 probably not spy
        confidenceListPlayer1.add(1.0); //player 4 definitely spy
        confidenceListPlayer1.add(0.4); //spy1 probably not spy
        confidenceListPlayer1.add(0.9); //spy2 probably spy
        //this should be the team rebelplayer1 chooses
        correctTeamList = new ArrayList<Player>();

    }

    protected void setUp() throws Exception{
        super.setUp();
    }

    protected void runTest()throws Throwable{
        super.runTest();

        //test mission votes AI
        assertFalse(AIMoveFactory.getMissionVote(spyPlayer1));
        assertTrue(AIMoveFactory.getMissionVote(rebelPlayer1));
        //test team approval votes
    }
}
