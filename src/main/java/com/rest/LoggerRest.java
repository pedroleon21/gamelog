package com.rest;

import com.service.Service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
    public Hashtable<String, List<String>> queryOnLogfile() {
        return service.query();
    }
}
