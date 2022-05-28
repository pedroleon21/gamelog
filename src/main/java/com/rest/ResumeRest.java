package com.rest;

import com.entities.GameKillResume;
import com.entities.ResumeGame;
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

@Path("v1/resumo")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class ResumeRest {
    @Inject
    Service service;
    @GET
    public Hashtable<String, ResumeGame> resumeGames(){
        return service.getAllResumes();
    }
    @GET
    @Path("kills")
    public List<GameKillResume> buscarKillResume(){
        return service.resumeKillsEPlacar();
    }
}
