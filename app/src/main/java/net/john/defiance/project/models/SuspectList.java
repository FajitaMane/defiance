package net.john.defiance.project.models;

import java.util.ArrayList;

/**
 * Created by John on 12/29/2015.
 * Only rebels have this list. Values are associated by their
 * position in the array.
 */
public class SuspectList {
    private ArrayList<Player> playerList;
    private ArrayList<Double> confidenceList; //1 is sure spy, 0 is sure rebel
    private Double fOne;
    private Double fZero;

    public SuspectList(ArrayList<Player> playerList){
        this.playerList = playerList;
        this.confidenceList = new ArrayList<Double>();
        fOne = 1.0;
        fZero = 0.0;
    }

    public void isSureSpy(int index){
        confidenceList.set(index, fOne);
    }

    public void isSureRebel(int index){
        confidenceList.set(index, fZero);
    }

    public ArrayList<Double> getConfidenceList(){
        return confidenceList;
    }
}
