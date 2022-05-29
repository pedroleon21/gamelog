package com.entries;

public class Item {
    private String time;
    private Integer playerId;
    private String weapon;

    public Item(String time, Integer playerId, String weapon) {
        this.time = time;
        this.playerId = playerId;
        this.weapon = weapon;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }
}
