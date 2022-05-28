package com.logger;

import com.entities.GameKillResume;
import com.entities.Kill;
import com.entities.Game;
import com.logger.exception.LineMapException;

import javax.enterprise.context.RequestScoped;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class Parser {

    public GameKillResume resumeKills(List<String> eventos) {
        int totalKills = getTotalKills(eventos);
//        List<String> causes = eventos.stream().filter()
        return null;
    }
    public Game resumeGame(List<String> eventos) {
        int totalKills = getTotalKills(eventos);
        List<Kill> kills = getListKills(eventos);
        List<String> players = eventos.stream().filter(e->e.contains("ClientUserinfoChanged")).map(s->takeNamePlayer(s)).distinct().collect(Collectors.toList());
        return new Game(totalKills,kills,players);
    }

    private List<Kill> getListKills(List<String> eventos) {
        return eventos.stream().filter(e -> e.contains("Kill")).map(l -> mapKill(l)).collect(Collectors.toList());
    }

    private int getTotalKills(List<String> eventos) {
        return eventos.stream().filter(e -> e.contains("Kill")).collect(Collectors.toList()).size();
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
    public String takeNamePlayer(String line){
        return line.trim().split("n\\\\")[1].trim().split("\\\\t")[0];
    }
}
