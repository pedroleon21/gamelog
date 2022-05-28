package com.integration;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RestAssuredUtils {
    String baseUrl = "http://localhost:5000/v1";
    public RequestSpecification getConfiguredGiven(){
        return given()
                .baseUri(baseUrl);

    }
}
