package com.integration;

import com.entities.Game;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;


public class RestLoggerTester extends RestAssuredUtils{
    @Test
    void getRaw(){
        getConfiguredGiven()
                .contentType(MediaType.APPLICATION_JSON)
                .log().all()
                .get("logger/raw")
                .then()
                .assertThat()
                .statusCode(Response.Status.OK.getStatusCode());
    }
    @Test
    void getGameList(){
        Game[] gamesSet = getConfiguredGiven()
                .contentType(MediaType.APPLICATION_JSON)
                .log().all()
                .get("logger")
                .then()
                .assertThat()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract()
                .as(Game[].class);
        List<Game> gamesList = Arrays.asList(gamesSet);
        assert (!gamesList.isEmpty());
    }
}
