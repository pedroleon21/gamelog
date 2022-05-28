package com.dao;


import com.entries.Game;
import com.entries.Kill;
import com.reader.LogReader;
import com.reader.Parser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Hashtable;
import java.util.List;
import java.util.Objects;

public class DaoTester {
    static String filePath = "games.log";
    static Dao dao = new Dao(new LogReader(filePath));
    static String killLine;
    private static String worldKillLine;
    private static String clientUserInfoLine;
    @Test
    void pegarTodosEventos(){
        Hashtable<Integer, List<String>> eventos = dao.readGames();
        assert (!eventos.isEmpty());
    }
    @Test
    void pegarGame(){
        for(int i =0; i < 20;i++){//existem 20 jogos
            Game game = dao.getGeme(i);
            assert (Objects.nonNull(game));
        }
    }

    @BeforeAll
    static void pegarUmaKill() {
        List<String> eventos = dao.getAllEvents();
        killLine = eventos.stream().filter(x -> x.contains("Kill") && !x.contains("<world>")).findFirst().map(x -> x).orElseThrow(RuntimeException::new);
        worldKillLine = eventos.stream().filter(x -> x.contains("Kill") && x.contains("<world>")).findFirst().map(x -> x).orElseThrow(RuntimeException::new);
        clientUserInfoLine = eventos.stream().filter(x -> x.contains("ClientUserinfoChanged")).findFirst().map(x -> x).orElseThrow(RuntimeException::new); //ClientUserinfoChanged
    }

    @Test
    void maperKill() {
        Kill kill = Parser.mapKill(killLine);
        assert (Objects.nonNull(kill));
        assert killLine.contains(kill.getKilled());
        assert killLine.contains(kill.getCause());
        assert killLine.contains(kill.getTime());
    }

    @Test
    void maperWorldKill() {
        Kill kill = Parser.mapKill(worldKillLine);
        assert (Objects.nonNull(kill));
        assert worldKillLine.contains(kill.getKilled());
        assert worldKillLine.contains(kill.getCause());
        assert worldKillLine.contains(kill.getTime());
    }

    @Test
    void mapGame() {
        Game game = Parser.resumeGame(dao.readGames().get(2));
        assert (Objects.nonNull(game));
    }

    @Test
    void getNamePlayer() {
        String player = Parser.takeNamePlayer(clientUserInfoLine);
        assert (Objects.nonNull(player));
        assert (!player.isEmpty());
        System.out.println(player);
    }
}
