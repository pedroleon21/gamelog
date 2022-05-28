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
}
