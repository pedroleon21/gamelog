package com.reader;

import com.dao.Dao;
import com.entries.Game;
import com.entries.Item;
import com.entries.Kill;
import com.entries.Player;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Hashtable;
import java.util.List;
import java.util.Objects;

public class ParserTester {
    static String filePath = "games.log";
    static LogReader reader = new LogReader(filePath);
    static Dao dao = new Dao(reader);
    static String killLine;
    private static String worldKillLine;
    private static String clientUserInfoLine;
    private static String file;
    private static List<String> eventos;
    private static String itemLine;

    @BeforeAll
    static void pegarUmaKill() {
        file = reader.read();
        eventos = dao.getAllEvents();
        killLine = eventos.stream().filter(x -> x.contains("Kill") && !x.contains("<world>")).findFirst().map(x -> x).orElseThrow(RuntimeException::new);
        worldKillLine = eventos.stream().filter(x -> x.contains("Kill") && x.contains("<world>")).findFirst().map(x -> x).orElseThrow(RuntimeException::new);
        clientUserInfoLine = eventos.stream().filter(x -> x.contains("ClientUserinfoChanged")).findFirst().map(x -> x).orElseThrow(RuntimeException::new); //ClientUserinfoChanged
        itemLine = eventos.stream().filter(x -> x.contains("Item")).findFirst().map(x -> x).orElseThrow(RuntimeException::new);
    }
    @Test
    void breakLineTester(){
        List<String> linhas = Parser.breakLines(file);
        assert (!linhas.isEmpty());
    }
    @Test
    void parseGames(){
        Hashtable<Integer, List<String>> games= Parser.eventosToGames(eventos);
        assert (!games.isEmpty());
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
    @Test
    void parsePlayer(){
        Player player = Parser.getPlayer(clientUserInfoLine);
        assert (Objects.nonNull(player));
        assert (Objects.nonNull(player.getName()));
        assert (Objects.nonNull(player.getPlayerId()));
    }
    @Test
    void parseItem(){
        Item item = Parser.getItem(itemLine);
        assert (Objects.nonNull(item));
        assert (Objects.nonNull(item.getTime()));
        assert (Objects.nonNull(item.getPlayerId()));
        assert (Objects.nonNull(item.getWeapon()));
    }
}
