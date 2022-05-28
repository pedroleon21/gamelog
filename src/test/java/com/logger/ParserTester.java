package com.logger;


import com.entities.Kill;
import com.logger.reader.LogReaderTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

public class ParserTester {
    Parser parser = new Parser();
    String filePath = "games.log";
    LogReaderTest logReader = new LogReaderTest(filePath);
    String killLine;
    private String worldKillLine;

    @BeforeEach
    void pegarUmaKill(){
        List<String> eventos=logReader.readFile();
        killLine = eventos.stream().filter(x -> x.contains("Kill") && !x.contains("<world>")).findFirst().map(x -> x).orElseThrow(RuntimeException::new);
        worldKillLine = eventos.stream().filter(x -> x.contains("Kill") && x.contains("<world>")).findFirst().map(x -> x).orElseThrow(RuntimeException::new);
    }
    @Test
    void maperKill(){
        Kill kill = parser.mapKill(killLine);
        assert (Objects.nonNull(kill));
        assert killLine.contains(kill.getKilled());
        assert killLine.contains(kill.getCause());
        assert killLine.contains(kill.getTime());
    }
    @Test
    void maperWorldKill(){
        Kill kill = parser.mapKill(worldKillLine);
        assert (Objects.nonNull(kill));
        assert worldKillLine.contains(kill.getKilled());
        assert worldKillLine.contains(kill.getCause());
        assert worldKillLine.contains(kill.getTime());
    }
    @Test
    void mapGame(){
//        parser.resumeGame()
    }
}
