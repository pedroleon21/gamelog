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
        String[] eventos = file.split("\\n");
        return Arrays.asList(eventos);
    }
    public Hashtable<Integer, List<String>> readGames() {
        Hashtable<Integer, List<String>> games = new Hashtable<Integer, List<String>>();
        List<String> eventos = getAllEvents();
        int qtdGames = 0;
        List<String> game = new ArrayList<>();
        for (String evento : eventos) {
            if (evento.contains("-------------")) continue;
            if (evento.contains("InitGame")) {
                game = new ArrayList<>();
                games.put(qtdGames++, game);
            }
            game.add(evento);
        }
        return games;
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
        Hashtable<Integer, List<String>> hashGames = readGames();
        if (gameNumber >= hashGames.size()) {
            throw new IndexOutOfBoundsException("fora da quantidade de games");
        }

        return Parser.resumeGame(hashGames.get(gameNumber));
    }
}
