package com.logger.reader;

import com.entities.Game;
import com.logger.Parser;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@RequestScoped
public class LogReader {

    @ConfigProperty(name = "log.file.path")
    String filePath;

    @Inject
    Parser parser;

    public LogReader() {
    }

    public LogReader(String filePath, Parser parser) {
        this.filePath = filePath;
        this.parser = parser;
    }

    public Game getGeme(int gameNumber){
        Hashtable<Integer,List<String>> hashGames = readGames();
        if(gameNumber >= hashGames.size()){
            throw new IndexOutOfBoundsException("fora da quantidade de games");
        }

        return parser.resumeGame(hashGames.get(gameNumber));
    }
    public Hashtable<Integer, List<String>> readGames() {
        Hashtable<Integer,List<String>> games = new Hashtable<Integer, List<String>>();
        List<String> eventos = getAllEvents();
        int qtdGames=0;
        List<String> game = new ArrayList<>();
        for (String evento : eventos) {
            if(evento.contains("-------------")) continue;
            if(evento.contains("InitGame")){
                game = new ArrayList<>();
                games.put(qtdGames++,game);
            }
            game.add(evento);
        }
        return games;
    }

    protected List<String> getAllEvents() {
        String file = read();
        String[] eventos = file.split("\\n");
        return Arrays.asList(eventos);
    }

    private String read() {
        try {
            return Files.readString(Path.of(filePath));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
