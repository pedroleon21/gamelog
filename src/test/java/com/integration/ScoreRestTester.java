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

public class ScoreRestTester extends RestAssuredUtils {
    @Test
    void listaDePlacares(){
        GameScore[] placareSet = getConfiguredGiven()
                .contentType(MediaType.APPLICATION_JSON)
                .log().all()
                .get("score")
                .then()
                .assertThat()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract()
                .as(GameScore[].class);
        List<GameScore> placarList = Arrays.asList(placareSet);
        assert (!placarList.isEmpty());
    }
    @Test
    void placaEspecifoco(){
        GameScore placar = getConfiguredGiven()
                .contentType(MediaType.APPLICATION_JSON)
                .log().all()
                .get("score/" + new Random().nextInt(21))
                .then()
                .assertThat()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract()
                .as(GameScore.class);
    }
}
