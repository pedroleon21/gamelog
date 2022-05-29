package com.service;

import com.dao.Dao;
import com.entries.*;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class Service {
    @Inject
    Dao dao;

    public Service() {
    }

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
            List<Player> players = game.getPlayers();
            Hashtable<String, Long> table = new Hashtable<String, Long>();
            players.forEach(p -> {
                long total = game.getKills().stream().map(Kill::getKilled).filter(k -> k.equals(p.getName())).count();
                table.put(p.getName(), total);
            });
            map.put("game_" + i, new ResumeGame(totalKills, players.stream().map(Player::getName).collect(Collectors.toList()), table));
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
