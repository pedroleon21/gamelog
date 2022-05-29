package com.service;

import com.dao.Dao;
import com.entries.Game;
import com.entries.GameScore;
import com.entries.Kill;
import com.entries.ResumeGame;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Hashtable;
import java.util.List;

@RequestScoped
public class Service {
    @Inject
    Dao dao;

    public Service(String path) {
        this.dao = new Dao(path);
    }

    public Hashtable<Integer, List<String>> query() {
        return dao.readGames();
    }

    public List<Game> listaGames() {
        return dao.listAllGames();
    }

    public Game pegarGame(Integer id) {
        return dao.getGeme(id);
    }

    public Hashtable<String, ResumeGame> getAllResumes() {
        List<Game> games = dao.listAllGames();
        Hashtable<String, ResumeGame> map = new Hashtable<String, ResumeGame>();
        for (int i = 0; i < games.size(); i++) {
            Game game = games.get(i);
            int totalKills = game.getTotalKills();
            List<String> players = game.getPlayers();
            Hashtable<String, Long> table = new Hashtable<String, Long>();
            players.forEach(p -> {
                long total = game.getKills().stream().map(Kill::getKilled).filter(k -> k.equals(p)).count();
                table.put(p, total);
            });
            map.put("game_" + i, new ResumeGame(totalKills, players, table));
        }
        return map;
    }

    public List<GameScore> resumirScores() {
        return dao.getKillResume();
    }

    public ResumeGame pegarReumo(String gameKey) {
        return getAllResumes().get(gameKey);
    }
}
