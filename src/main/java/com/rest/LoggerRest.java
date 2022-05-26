package com.rest;

import com.service.Service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("v1/logger")
@RequestScoped
public class LoggerRest {
    @Inject
    Service service;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String queryOnLogfile() {
        return service.query();
    }
}
