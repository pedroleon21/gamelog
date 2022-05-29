package com.entries;

public class Player {
    private Integer playerId;
    private String name;

    public Player(Integer integer, String takeNamePlayer) {
        this.playerId = integer;
        this.name = takeNamePlayer;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
