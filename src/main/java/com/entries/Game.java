package com.entries;

import java.util.List;

public class Game {
    private int totalKills;
    private List<Kill> kills;
    private List<String> players;

    public Game(int totalKills, List<Kill> kills, List<String> players) {
        super();
        this.totalKills = totalKills;
        this.kills = kills;
        this.players = players;
    }

    public int getTotalKills() {
        return totalKills;
    }

    public void setTotalKills(int totalKills) {
        this.totalKills = totalKills;
    }

    public List<Kill> getKills() {
        return kills;
    }

    public void setKills(List<Kill> kills) {
        this.kills = kills;
    }

    public List<String> getPlayers() {
        return players;
    }

    public void setPlayers(List<String> players) {
        this.players = players;
    }
}
