package com.service;

import com.dao.Dao;
import com.entries.Game;
import com.entries.ResumeGame;
import com.reader.LogReader;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Test;

import java.util.Hashtable;
import java.util.Objects;
import java.util.Random;

public class ServiceTester {
    Service service = new Service("games.log");

    @Test
    void getAllResumes(){
        Hashtable<String, ResumeGame> tableResume = service.getAllResumes();
        assert (!tableResume.isEmpty());
    }
    @Test
    void pegarResumo(){
        Game game = service.pegarGame(new Random().nextInt(21));
        assert(Objects.nonNull(game));
    }
}
