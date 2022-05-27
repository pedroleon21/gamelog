package com.entities;

public class Kill {

    private String killer;
    private String killed;
    private String cause;
    private String time;

    public Kill(String killer, String killed, String cause, String time) {
        this.killer = killer;
        this.killed = killed;
        this.cause = cause;
        this.time = time;
    }

    public String getKiller() {
        return killer;
    }

    public void setKiller(String killer) {
        this.killer = killer;
    }

    public String getKilled() {
        return killed;
    }

    public void setKilled(String killed) {
        this.killed = killed;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
