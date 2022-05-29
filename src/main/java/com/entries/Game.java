package com.entries;

import java.util.List;

public class Game {
    private int totalKills;
    private List<Kill> kills;
    private List<Player> players;
    private List<Item> items;

    public Game(int totalKills, List<Kill> kills, List<Player> players, List<Item> items) {
        super();
        this.totalKills = totalKills;
        this.kills = kills;
        this.players = players;
        this.items = items;
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

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
