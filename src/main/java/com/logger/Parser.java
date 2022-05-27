package com.logger;

import com.entities.Kill;
import com.entities.Game;
import com.logger.exception.LineMapException;

import javax.enterprise.context.RequestScoped;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class Parser {
    private List<Kill> kills;
    private int totalKills;

    public Game resumeGame(List<String> eventos) {
        totalKills = eventos.stream().filter(e->e.contains("Kill")).collect(Collectors.toList()).size();
        kills = eventos.stream().filter(e->e.contains("Kill") && !e.contains("<world>")).map(l->mapKill(l)).collect(Collectors.toList());

        return null;
    }

    public Kill mapKill(String line){
        if(!line.contains("Kill")){
            throw new LineMapException("Erro ao mapear morte");
        }
        String[] splited = line.trim().split("Kill");
        String time = splited[0];
        splited = splited[1].trim().split(":");
        String numbers = splited[1].trim();
        String killer = splited[2].trim().split("killed")[0];
        String killed = splited[2].split("killed")[1].trim().split("by")[0].trim();
        String cause = splited[2].split("killed")[1].trim().split("by")[1].trim();
        return new Kill(killer,killed,cause,time);
    }
}
