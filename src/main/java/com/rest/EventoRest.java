package com.rest;

import com.entries.Game;
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

@Path("v1/evento")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
public class EventoRest {
    @Inject
    Service service;
    @GET
    public List<Game> buscarGames(){
        return service.listaGames();
    }

    @GET
    @Path("{id}")
    public Game pegarGame(@PathParam("id") Integer id){
        return service.pegarGame(id);
    }

    @Path("raw")
    @GET
    public Hashtable<Integer, List<String>> queryOnLogfile() {
        return service.query();
    }
}
