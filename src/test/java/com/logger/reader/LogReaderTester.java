package com.logger.reader;

import org.junit.jupiter.api.Test;

import java.util.Hashtable;
import java.util.List;

public class LogReaderTester {
    //TODO: colocar filepath num arquivo de propriedades
    String filePath = "games.log";

    @Test
    void pegarTodosEventos(){
        Hashtable<Integer, List<String>> eventos = new LogReader(filePath).mapAllGames();
        assert (!eventos.isEmpty());
    }
}
