package net.john.defiance.project.models;

import android.util.Log;

import net.john.defiance.project.logic.RuleSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John on 12/27/2015.
 * Contains complete game state without any logic or functionality.
 */
public class GameState {
    private Teams[] turnsWon;
    private int voteCount;
    private ArrayList<Player> playerArrayList;
    private int turnNumber;
    private TurnState turnState;
    private int votesYes;
    private int votesNo;

    public ArrayList<Player> getPlayerList() {
        return playerArrayList;
    }

    private ArrayList<Player> missionTeam;
    private Player teamLeader;

    public GameState(ArrayList<Player> players, int turnNumber, TurnState turnState,
                     int votesYes, int votesNo){
        turnsWon = new Teams[5];
        voteCount = 0;
        this.playerArrayList = players;
        this.turnNumber = turnNumber;
        this.turnState = turnState;
        this.votesYes = votesYes;
        this.votesNo = votesNo;
        missionTeam = new ArrayList<Player>();
    }

    public void addPlayer(Player player, boolean isAI){
        playerArrayList.add(player);
    }

    public Teams getTurnWinner(int turn){
        return turnsWon[turn];
    }

    public int getTurnNumber(){
        return turnNumber;
    }

    public TurnState getTurnState(){
        return turnState;
    }

    public int addYesVote(){
        votesYes++;
        return votesYes + votesNo;
    }

    public int addNoVote(){
        votesNo++;
        return votesYes + votesNo;
    }

    public int getVotesYes(){
        return votesYes;
    }

    public int getVotesNo(){
        return votesNo;
    }

    /* this model contains no logic as to whether or not the turn should advance
     * that is handled by the Game object
     */
    protected void advanceTurn(){
        switch (turnState){
            case TEAMVOTING:
                if (votesYes > votesNo){
                    turnState = TurnState.MISSIONVOTING;
                }
                break;
            case MISSIONVOTING:
                if (RuleSet.isMissionSuccessful(this)){
                    turnsWon[turnNumber - 1] = Teams.REBELS;
                } else {
                    turnsWon[turnNumber - 1] = Teams.SPIES;
                }
                missionTeam.clear();
                turnNumber++;
                break;
            case CHOOSINGTEAM:
                if (missionTeam.size() == playerArrayList.size()){
                    turnState = TurnState.TEAMVOTING;
                }
                break;
        }
        votesYes = 0;
        votesNo = 0;
    }

    public void print(){
        String s = "Turn: " + turnNumber;
        if (turnState.equals(TurnState.TEAMVOTING)){
            s += " voting on team";
        } else {
            s += " voting on mission";
        }
        s+= " " + votesYes + " votes yes " + votesNo + " votes no.";
        Log.d("gameState", s);
    }
}
