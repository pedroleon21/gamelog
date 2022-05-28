package com.service;

import com.entities.Game;
import com.entities.GameKillResume;
import com.entities.ResumeGame;
import com.logger.reader.LogReader;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Hashtable;
import java.util.List;

@RequestScoped
public class Service {
    @Inject
    LogReader reader;
    public Hashtable<Integer, List<String>> query() {
        return reader.readGames();
    }

    public List<Game> listaGames() {
        return reader.listAllGames();
    }

    public Game pegarGame(Integer id) {
        return reader.getGeme(id);
    }

    public Hashtable<String, ResumeGame> getAllResumes() {
        List<Game> games = reader.listAllGames();
        Hashtable<String, ResumeGame> map = new Hashtable<String, ResumeGame>();
        for(int i=0; i < games.size();i++){
            map.put("game_" + i,new ResumeGame(games.get(i)));
        }
        return map;
    }

    public List<GameKillResume> resumeKillsEPlacar() {
        return reader.getKillResume();
    }
}
