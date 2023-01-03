package com.gihub.smkjke.tests.tests;

import com.gihub.smkjke.tests.models.ErrorResponse;
import com.gihub.smkjke.tests.models.User;

import com.gihub.smkjke.tests.models.UserResponse;
import org.junit.jupiter.api.Test;

import static com.gihub.smkjke.tests.specs.Specs.*;
import static com.gihub.smkjke.tests.specs.UserSpecs.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


class ReqresTests {

    private static final String USER_NAME = "Misha";
    private static final String USER_JOB = "Developer";
    private static final String USER_EMAIL = "smkjke@mail.ru";
    private static final String USER_PASSWORD = "123456";

    @Test
    void checkTotalUsersOnPageTest() {
        given(REQUEST_SPECIFICATION)
                .get("/users?page=1")
                .then()
                .spec(RESPONSE_SPECIFICATION)
                .body("total", is(12));
    }

    @Test
    void checkSingleUserTest() {
        given(REQUEST_SPECIFICATION)
                .get("/user/1")
                .then()
                .spec(RESPONSE_SPECIFICATION)
                .body("data.id", is(1))
                .body("data.pantone_value", is("15-4020"));
    }


    @Test
    void createUserTest() {
        UserResponse response = given(REQUEST_SPECIFICATION)
                .body(generateUser())
                .when()
                .post("/users")
                .then()
                .spec(USER_RESPONSE_SPEC)
                .extract().as(UserResponse.class);

        assertThat(response.getName(), is("Misha"));
        assertThat(response.getJob(), is("Developer"));

    }

    @Test
    void deleteUserTest() {
        given(REQUEST_SPECIFICATION)
                .body(generateUser())
                .when()
                .delete("users/5")
                .then()
                .spec(DELETE_USER_RESPONSE_SPEC);
    }

    @Test
    void unsuccessfullyRegisterUserTest() {
        ErrorResponse response = given(REQUEST_SPECIFICATION)
                .body(generateUser())
                .when()
                .post("/register")
                .then()
                .spec(ERROR_USER_RESPONSE_SPEC)
                .extract().as(ErrorResponse.class);

        assertThat(response.getError(), is("Missing email or username"));
    }

    @Test
    void findPersonsDateWithLongName() {
        given(REQUEST_SPECIFICATION)
                .when()
                .get("/unknown")
                .then()
                .spec(RESPONSE_SPECIFICATION)
                .body("data.findAll{ it.name.length() >= 12 }.year",
                        hasItems(2001, 2005));
    }

    private User generateUser() {
        return User.builder().name(USER_NAME).job(USER_JOB).build();
    }

}
