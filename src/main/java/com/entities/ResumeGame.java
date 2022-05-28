package com.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Hashtable;
import java.util.List;

public class ResumeGame {
    private Hashtable<String, Long> kills;
    @JsonProperty("players")
    private List<String> players;
    @JsonProperty("total_kills")
    private Integer totalKills;

    public ResumeGame(Game game) {
        totalKills = game.getTotalKills();
        players = game.getPlayers();
        Hashtable<String,Long> table = new Hashtable<String,Long>();
        players.forEach(p -> {
            long totalKills = game.getKills().stream().map(Kill::getKilled).filter(k -> k.equals(p)).count();
            table.put(p, totalKills);
        });
        kills = table;
    }

    public Hashtable<String, Long> getKills() {
        return kills;
    }

    public void setKills(Hashtable<String, Long> kills) {
        this.kills = kills;
    }

    public List<String> getPlayers() {
        return players;
    }

    public void setPlayers(List<String> players) {
        this.players = players;
    }

    public Integer getTotalKills() {
        return totalKills;
    }

    public void setTotalKills(Integer totalKills) {
        this.totalKills = totalKills;
    }
}
