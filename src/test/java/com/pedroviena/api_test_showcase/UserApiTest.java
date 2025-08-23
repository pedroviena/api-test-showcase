package com.pedroviena.api_test_showcase;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserApiTest {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test
    void shouldCreateUserSuccessfully() {
        String json = """
                {
                  "name": "Pedro",
                  "email": "pedro@test.com"
                }
                """;

        given()
            .contentType(ContentType.JSON)
            .body(json)
        .when()
            .post("/users")
        .then()
            .statusCode(200)
            .body("id", notNullValue())
            .body("name", equalTo("Pedro"))
            .body("email", equalTo("pedro@test.com"));
    }
}

