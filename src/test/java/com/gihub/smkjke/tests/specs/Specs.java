package com.gihub.smkjke.tests.specs;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.gihub.smkjke.tests.helpers.CustomApiListener.withCustomTemplates;

public class Specs {
    private static final String URL = "https://reqres.in/api";

    public static final RequestSpecification REQUEST_SPECIFICATION = RestAssured
            .given()
            .filter(withCustomTemplates())
            .log().all()
            .baseUri(URL)
            .contentType(ContentType.JSON);

    public static final ResponseSpecification RESPONSE_SPECIFICATION = RestAssured
            .given()
            .log().all()
            .expect()
            .statusCode(200);

}
