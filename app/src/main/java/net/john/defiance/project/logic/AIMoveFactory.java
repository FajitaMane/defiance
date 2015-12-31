package net.john.defiance.project.logic;

import net.john.defiance.project.models.Player;
import net.john.defiance.project.models.Teams;

import java.util.ArrayList;

/**
 * Created by John on 12/29/2015.
 */
public class AIMoveFactory {

    //always vote yes if player is rebel, no if spy
    //true represents yes vote, false no vote
    public static boolean getMissionVote(Player player){
        return player.getTeam() == Teams.REBELS;
    }

    //always choose known rebels or known spies if spies
    //if spy, don't choose more spies than necessary
    public static ArrayList<Player> getTeamChoice(Player player, ArrayList<Player> playerList,
                                                  ArrayList<Double> confidenceList){
        ArrayList<Player> chosenTeam = new ArrayList<Player>();
        if (player.getTeam() == Teams.REBELS){
            for (int i = 0; i < playerList.size(); i++){
                if (confidenceList.get(i) < .5){
                    chosenTeam.add(playerList.get(i));
                }
                //fill up a team with remaining players if all players are suspected
                else if (i > playerList.size() - 3){
                    chosenTeam.add(playerList.get(i));
                }
            }
        } else {
            boolean spyFound = false;
            for (int i = 0; i < playerList.size(); i++){
                if (confidenceList.get(i) == 1 && !spyFound){
                    chosenTeam.add(playerList.get(i));
                    spyFound = true;
                }
            }
        }
        return chosenTeam;
    }

    public static boolean getTeamApproval(Player player, ArrayList<Player> chosenTeam,
                                  ArrayList<Player> playerList, ArrayList<Double> confidenceList){
        for (int i = 0; i < chosenTeam.size(); i++){
            for (int i2 = 0; i2 < playerList.size(); i2++){
                if (playerList.get(i2).getUid() == chosenTeam.get(i).getUid()){
                    //if player is sure a member of the team is a spy
                    if (confidenceList.get(i) == 1){
                        if (player.getTeam() == Teams.REBELS){
                            return false;
                        } else {
                            return true;
                        }
                    }
                }
            }
        }
        return true;
    }
}
