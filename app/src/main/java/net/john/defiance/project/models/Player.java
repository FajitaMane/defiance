package net.john.defiance.project.models;

/**
 * Created by John on 12/27/2015.
 */
public class Player {
    private String name;
    private int uid;
    private Teams team;

    public Player(String name, Teams team){
        this.name = name;
        this.uid = System.identityHashCode(name);
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public int getUid() {
        return uid;
    }

    public Teams getTeam() {
        return team;
    }
}
