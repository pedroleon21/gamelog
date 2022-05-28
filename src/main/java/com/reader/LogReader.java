package com.reader;

import com.entries.Game;
import com.entries.GameScore;
import com.reader.Parser;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.RequestScoped;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@RequestScoped
public class LogReader {

    @ConfigProperty(name = "log.file.path")
    String filePath;


    public LogReader() {
    }

    public LogReader(String filePath) {
        this.filePath = filePath;
    }
    //talvez mudar para streaming de arquivo??
    public String read() {
        try {
            return Files.readString(Path.of(filePath));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public Game getGeme(int gameNumber) {
        Hashtable<Integer, List<String>> hashGames = readGames();
        if (gameNumber >= hashGames.size()) {
            throw new IndexOutOfBoundsException("fora da quantidade de games");
        }

        return Parser.resumeGame(hashGames.get(gameNumber));
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

    public List<String> getAllEvents() {
        String file = read();
        String[] eventos = file.split("\\n");
        return Arrays.asList(eventos);
    }



    public List<Game> listAllGames() {
        Hashtable<Integer, List<String>> hashGames = readGames();
        List<Game> games = new ArrayList<>();
        for (int i = 0; i < hashGames.size(); i++) {
            games.add(Parser.resumeGame(hashGames.get(i)));
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
}
