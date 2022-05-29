package com.integration;

import com.entries.GameScore;
import com.entries.ResumeGame;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class GameRestTester extends RestAssuredUtils {
    String path = "game";
    @Test
    void resumoGemes() {
        getConfiguredGiven()
                .contentType(MediaType.APPLICATION_JSON)
                .log().all()
                .get(path)
                .then()
                .assertThat()
                .statusCode(Response.Status.OK.getStatusCode());
    }
    @Test
    void pegarScoreExpecifico(){
        ResumeGame placar = getConfiguredGiven()
                .contentType(MediaType.APPLICATION_JSON)
                .log().all()
                .get(path +"/game_" + new Random().nextInt(21))
                .then()
                .assertThat()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract()
                .as(ResumeGame.class);
        assert (Objects.nonNull(placar));
    }
}
