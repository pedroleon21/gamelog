package com.integration;

import com.entries.Game;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;


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
    @Test
    void getGame(){
        Game game = getConfiguredGiven()
                .contentType(MediaType.APPLICATION_JSON)
                .log().all()
                .get("logger/" + new Random().nextInt(21))
                .then()
                .assertThat()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract()
                .as(Game.class);
        assert (Objects.nonNull(game));
    }
}
