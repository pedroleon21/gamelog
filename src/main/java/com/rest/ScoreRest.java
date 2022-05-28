package com.rest;

import com.entries.GameScore;
import com.service.Service;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@Path("v1/score")
public class ScoreRest {
    @Inject
    Service service;
    @GET
    public List<GameScore> buscarScoreAll(){
        return service.resumirScores();
    }
    @GET
    @Path("{idGame}")
    public GameScore buscarScore(@PathParam("idGame") Integer idGame){
        return service.resumirScores().get(idGame);
    }
}
