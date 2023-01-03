package com.gihub.smkjke.tests.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.filter.log.LogDetail.ALL;

public class UserSpecs {
    public static ResponseSpecification USER_RESPONSE_SPEC = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(201)
            .build();

    public static ResponseSpecification DELETE_USER_RESPONSE_SPEC = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(204)
            .build();

    public static ResponseSpecification ERROR_USER_RESPONSE_SPEC = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(400)
            .build();
}
