package com.dao;

import com.entries.Game;
import com.entries.GameScore;
import com.reader.LogReader;
import com.reader.Parser;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

@RequestScoped
public class Dao {
    @Inject
    LogReader reader;

    public Dao() {
    }

    public Dao(LogReader reader) {
        this.reader = reader;
    }

    public   List<String> getAllEvents() {
        String file = reader.read();
        return Parser.breakLines(file);
    }
    public Hashtable<Integer, List<String>> readGames() {
        return Parser.eventosToGames(getAllEvents());
    }

    public List<GameScore> getKillResume() {
        Hashtable<Integer, List<String>> hashGames = readGames();
        List<GameScore> games = new ArrayList<>();
        for (int i = 0; i < hashGames.size(); i++) {
            games.add(Parser.resumeKills(hashGames.get(i)));
        }
        return games;
    }

    public List<Game> listAllGames() {
        Hashtable<Integer, List<String>> hashGames = readGames();
        List<Game> games = new ArrayList<>();
        for (int i = 0; i < hashGames.size(); i++) {
            games.add(Parser.resumeGame(hashGames.get(i)));
        }
        return games;
    }

    public Game getGeme(int gameNumber) {
        return Parser.resumeGame(readGames().get(gameNumber));
    }
}
