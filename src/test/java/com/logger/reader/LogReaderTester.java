package com.logger.reader;

import com.entries.Game;
import com.logger.Parser;
import org.junit.jupiter.api.*;

import java.util.Hashtable;
import java.util.List;
import java.util.Objects;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LogReaderTester {
    //TODO: colocar filepath num arquivo de propriedades
    String filePath = "games.log";
    LogReaderTest logReader = new LogReaderTest(filePath,new Parser());
    @Test
    void pegarTodosEventos(){
        Hashtable<Integer, List<String>> eventos = logReader.readGames();
        assert (!eventos.isEmpty());
    }
    @Test
    void pegarGame(){
        for(int i =0; i < 20;i++){//existem 20 jogos
            Game game = logReader.getGeme(i);
            assert (Objects.nonNull(game));
        }
    }
}
