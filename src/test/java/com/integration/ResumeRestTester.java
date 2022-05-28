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

public class ResumeRestTester extends RestAssuredUtils {
    @Test
    void resumoGemes() {
        getConfiguredGiven()
                .contentType(MediaType.APPLICATION_JSON)
                .log().all()
                .get("resumo/game")
                .then()
                .assertThat()
                .statusCode(Response.Status.OK.getStatusCode());
    }
    @Test
    void pegarScoreExpecifico(){
        ResumeGame placar = getConfiguredGiven()
                .contentType(MediaType.APPLICATION_JSON)
                .log().all()
                .get("resumo/game/game_" + new Random().nextInt(21))
                .then()
                .assertThat()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract()
                .as(ResumeGame.class);
        assert (Objects.nonNull(placar));
    }
    @Test
    void listaDePlacares(){
        GameScore[] placareSet = getConfiguredGiven()
                .contentType(MediaType.APPLICATION_JSON)
                .log().all()
                .get("resumo/score")
                .then()
                .assertThat()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract()
                .as(GameScore[].class);
        List<GameScore> placarList = Arrays.asList(placareSet);
        assert (!placarList.isEmpty());
    }

}
