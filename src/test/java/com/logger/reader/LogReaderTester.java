package com.logger.reader;

import org.junit.jupiter.api.*;

import java.util.Hashtable;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LogReaderTester {
    //TODO: colocar filepath num arquivo de propriedades
    String filePath = "games.log";
    LogReaderTest logReader = new LogReaderTest(filePath);
    @Test
    void pegarTodosEventos(){
        Hashtable<String, List<String>> eventos = logReader.mapAllGames();
        assert (!eventos.isEmpty());
    }
}
