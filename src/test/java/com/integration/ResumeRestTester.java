package com.integration;

import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ResumeRestTester extends RestAssuredUtils {
    @Test
    void resumoGemes() {
        getConfiguredGiven()
                .contentType(MediaType.APPLICATION_JSON)
                .log().all()
                .get("resumo")
                .then()
                .assertThat()
                .statusCode(Response.Status.OK.getStatusCode());
    }
}
