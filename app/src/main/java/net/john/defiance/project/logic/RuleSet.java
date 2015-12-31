package net.john.defiance.project.logic;

import net.john.defiance.project.models.GameState;
import net.john.defiance.project.models.Player;
import net.john.defiance.project.models.Teams;
import net.john.defiance.project.models.TurnState;

import java.util.ArrayList;

/**
 * Created by John on 12/28/2015.
 * This is for all game logic that does not involve AI e.g.
 * is used by a Game regardless of the type of players.
 */
public class RuleSet {

    public static boolean gameIsOver(GameState gameState){
        int rebelWins = 0;
        int spyWins = 0;
        for (int i = 0; i < 5; i++){
            if (gameState.getTurnWinner(i).equals(Teams.REBELS)){
                rebelWins++;
            } else {
                spyWins++;
            }
        }
        return (rebelWins >= 3 || spyWins >= 3);
    }

    public static boolean isMissionSuccessful(GameState gameState){
        return !(gameState.getVotesNo() > 0);
    }

    public static boolean shouldTurnStateProgress(GameState gameState, ArrayList<Player> chosenTeam){
        ArrayList<Player> playerList = gameState.getPlayerList();
        TurnState turnState = gameState.getTurnState();
        int totalVotes = gameState.getVotesNo() + gameState.getVotesYes();
        if (turnState == TurnState.MISSIONVOTING){
            //assuming that all missions have two members
            return totalVotes == 2;
        } else if (turnState == TurnState.TEAMVOTING){
            //else all players are voting on mission team approval
            return totalVotes == playerList.size();
        } else {
            //turn state must be in mission choosing
            return chosenTeam.size() == 2;
        }
    }
}
