package com.logger.reader;

import com.entities.Kill;
import com.logger.exception.LineMapException;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.RequestScoped;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
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
        List<String> eventos = getAllEvents();
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
    protected Kill mapKill(String line){
        if(!line.contains("Kill")){
            throw new LineMapException("Erro ao mapear morte");
        }
        String[] splited = line.trim().split("Kill");
        String time = splited[0];
        splited = splited[1].trim().split(":");
        String numbers = splited[1].trim();
        String cause = splited[2];
        return new Kill();
    }
}
