package com.logger;


import com.entries.Game;
import com.entries.Kill;
import com.logger.reader.LogReader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

public class ParserTester {
    Parser parser = new Parser();
    static String filePath = "games.log";
    static LogReader logReader = new LogReader(filePath);
    static String killLine;
    private static String worldKillLine;
    private static String clientUserInfoLine;
//    @Test
//    void pegarTodosEventos(){
//        Hashtable<Integer, List<String>> eventos = logReader.readGames();
//        assert (!eventos.isEmpty());
//    }
//    @Test
//    void pegarGame(){
//        for(int i =0; i < 20;i++){//existem 20 jogos
//            Game game = logReader.getGeme(i);
//            assert (Objects.nonNull(game));
//        }
//    }

    @BeforeAll
    static void pegarUmaKill() {
        List<String> eventos = logReader.getAllEvents();
        killLine = eventos.stream().filter(x -> x.contains("Kill") && !x.contains("<world>")).findFirst().map(x -> x).orElseThrow(RuntimeException::new);
        worldKillLine = eventos.stream().filter(x -> x.contains("Kill") && x.contains("<world>")).findFirst().map(x -> x).orElseThrow(RuntimeException::new);
        clientUserInfoLine = eventos.stream().filter(x -> x.contains("ClientUserinfoChanged")).findFirst().map(x -> x).orElseThrow(RuntimeException::new); //ClientUserinfoChanged
    }

    @Test
    void maperKill() {
        Kill kill = parser.mapKill(killLine);
        assert (Objects.nonNull(kill));
        assert killLine.contains(kill.getKilled());
        assert killLine.contains(kill.getCause());
        assert killLine.contains(kill.getTime());
    }

    @Test
    void maperWorldKill() {
        Kill kill = parser.mapKill(worldKillLine);
        assert (Objects.nonNull(kill));
        assert worldKillLine.contains(kill.getKilled());
        assert worldKillLine.contains(kill.getCause());
        assert worldKillLine.contains(kill.getTime());
    }

    @Test
    void mapGame() {
        Game game = parser.resumeGame(logReader.readGames().get(2));
        assert (Objects.nonNull(game));
    }

    @Test
    void getNamePlayer() {
        String player = parser.takeNamePlayer(clientUserInfoLine);
        assert (Objects.nonNull(player));
        assert (!player.isEmpty());
        System.out.println(player);
    }
}
