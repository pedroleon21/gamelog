package com.reader;

import com.entries.GameScore;
import com.entries.Kill;
import com.entries.Game;

import javax.enterprise.context.RequestScoped;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class Parser {

    public static GameScore resumeKills(List<String> eventos) {
        int totalKills = getTotalKills(eventos);
        List<String> players = eventos.stream().filter(e -> e.contains("ClientUserinfoChanged")).map(s -> takeNamePlayer(s)).distinct().collect(Collectors.toList());
        List<Kill> kills = getListKills(eventos);
        Hashtable<String, Long> score = new Hashtable<String, Long>();
        players.forEach(p -> {
            Long playerKills = kills.stream().filter(k -> k.getKiller().equals(p)).count();
            Long playerMortesParaWorld = kills.stream().filter(k -> k.getKiller().equals("world")).count();
            score.put(p, playerKills - playerMortesParaWorld);
        });
        return new GameScore(totalKills, score);
    }

    public static Game resumeGame(List<String> eventos) {
        int totalKills = getTotalKills(eventos);
        List<Kill> kills = getListKills(eventos);
        List<String> players = eventos.stream().filter(e -> e.contains("ClientUserinfoChanged")).map(s -> takeNamePlayer(s)).distinct().collect(Collectors.toList());
        return new Game(totalKills, kills, players);
    }

    private static List<Kill> getListKills(List<String> eventos) {
        return eventos.stream().filter(e -> e.contains("Kill")).map(l -> mapKill(l)).collect(Collectors.toList());
    }

    private static int getTotalKills(List<String> eventos) {
        return eventos.stream().filter(e -> e.contains("Kill")).collect(Collectors.toList()).size();
    }

    public static Kill mapKill(String line) {
        if (!line.contains("Kill")) {
            throw new LineMapException("Erro ao mapear morte");
        }
        String[] splited = line.trim().split("Kill");
        String time = splited[0];
        splited = splited[1].trim().split(":");
        String numbers = splited[1].trim();
        String killer = splited[2].trim().split("killed")[0].trim();
        String killed = splited[2].split("killed")[1].trim().split("by")[0].trim();
        String cause = splited[2].split("killed")[1].trim().split("by")[1].trim();
        return new Kill(killer, killed, cause, time);
    }

    public static String takeNamePlayer(String line) {
        return line.trim().split("n\\\\")[1].trim().split("\\\\t")[0];
    }

    public static List<String> breakLines(String file) {
        String[] eventos = file.split("\\n");
        return Arrays.asList(eventos);
    }

    public static Hashtable<Integer, List<String>> eventosToGames(List<String> eventos) {
        Hashtable<Integer, List<String>> games = new Hashtable<Integer, List<String>>();
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
}
