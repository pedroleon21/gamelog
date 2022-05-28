package com.rest;

import com.entities.Game;
import com.service.Service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Hashtable;
import java.util.List;

@Path("v1/logger")
@RequestScoped
public class LoggerRest {
    @Inject
    Service service;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Game> buscarGames(){
        return service.listaGames();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Game pegarGame(@PathParam("id") Integer id){
        return service.pegarGame(id);
    }

    @Path("raw")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Hashtable<Integer, List<String>> queryOnLogfile() {
        return service.query();
    }
}
