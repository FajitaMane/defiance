package net.john.defiance.project.models;

import android.util.Log;

import net.john.defiance.project.logic.RuleSet;

import java.util.ArrayList;

/**
 * Created by John on 12/28/2015.
 */
public class Game {
    private ArrayList<GameState> gameStateList;
    private ArrayList<Player> playerListMaster;
    private GameState currentState;
    private boolean gameIsWon;
    private boolean isAIGame;

    public Game(ArrayList<Player> players){
        this.playerListMaster = players;
        gameIsWon = false;
    }

    private void addGameState(GameState state){
        this.gameStateList.add(state);
        this.currentState = state;
        //check for the win condition
        if (RuleSet.gameIsOver(state)){
            gameIsWon = true;
            Log.d("rules", "game is over");
        }
    }

    public void onYes(){
        if (!gameIsWon){
            currentState.addYesVote();
            currentState.print();
            gameIsWon = RuleSet.gameIsOver(currentState);
        }
    }

    public void onNo(){
        if (!gameIsWon){
            currentState.addNoVote();
            currentState.print();
            gameIsWon = RuleSet.gameIsOver(currentState);
        }
    }

    public GameState getCurrentState(){
        return currentState;
    }

    private TurnState getTurnState(){
        return currentState.getTurnState();
    }
}
