package com.logger.reader;

import com.DTO.GameDTO;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.RequestScoped;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

@RequestScoped
public class LogReader {

    @ConfigProperty(name = "log.file.path")
    String filePath;

    public LogReader() {
    }

    public LogReader(String filePath) {
        this.filePath = filePath;
    }

    public Hashtable<Integer, List<String>> mapAllGames() {
        Hashtable<Integer,List<String>> games = new Hashtable<Integer, List<String>>();
        String file = read();
        String[] eventos = file.split("\\n");
        int qtdGames=0;
        List<String> game = new ArrayList<>();
        for (String evento : eventos) {
            if(evento.contains("-------------")) continue;
            if(evento.contains("ShutdownGame")){
                game.add(evento);
                games.put(qtdGames++,game);
            }
            game.add(evento);
        }
        return games;
    }
    private String read() {
        try {
            return Files.readString(Path.of(filePath));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
