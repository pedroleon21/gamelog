package com.logger.reader;

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

    public LogReader(String filePath) {
        this.filePath = filePath;
    }

    public Hashtable<String, List<String>> readGames() {
        Hashtable<String,List<String>> games = new Hashtable<String, List<String>>();
        List<String> eventos = getAllEvents();
        int qtdGames=0;
        List<String> game = new ArrayList<>();
        for (String evento : eventos) {
            if(evento.contains("-------------")) continue;
            if(evento.contains("ShutdownGame")){
                game.add(evento);
                games.put("game_" + qtdGames++,game);
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
