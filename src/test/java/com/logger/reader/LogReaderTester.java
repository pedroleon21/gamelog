package com.logger.reader;

import org.junit.jupiter.api.*;

import java.util.Hashtable;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LogReaderTester {
    //TODO: colocar filepath num arquivo de propriedades
    String filePath = "games.log";
    LogReaderTest logReader = new LogReaderTest(filePath);
    String killLine;
    @BeforeEach
    void preFetch(){
        List<String> eventos = logReader.readFile();
        killLine = eventos.stream().filter(e->e.contains("Kill") && !e.contains("world")).findFirst().map(x->x).orElseThrow(RuntimeException::new);
    }
    @Test
    void pegarTodosEventos(){
        Hashtable<Integer, List<String>> eventos = logReader.mapAllGames();
        assert (!eventos.isEmpty());
    }
    //teste que estao mais dependentes, isso é bom?
    @Test
    void mapearEventosKill(){
        logReader.mapToKill(killLine);
    }
}
