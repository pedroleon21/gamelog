package com.entities;

import java.util.Hashtable;
import java.util.List;

public class GameScore {
    private int totalKills;
    private Hashtable<String, Long> score;

    public GameScore(int totalKills, Hashtable<String, Long> score) {
        super();
        this.totalKills = totalKills;
        this.score = score;
    }

    public int getTotalKills() {
        return totalKills;
    }

    public void setTotalKills(int totalKills) {
        this.totalKills = totalKills;
    }

    public Hashtable<String, Long> getScore() {
        return score;
    }

    public void setScore(Hashtable<String, Long> score) {
        this.score = score;
    }
}
