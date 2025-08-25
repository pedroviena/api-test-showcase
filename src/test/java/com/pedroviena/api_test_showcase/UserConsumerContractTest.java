package com.pedroviena.api_test_showcase;

import static au.com.dius.pact.consumer.dsl.LambdaDsl.newJsonBody;
import static org.hamcrest.Matchers.equalTo;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.PactSpecVersion;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@ExtendWith(PactConsumerTestExt.class)

@PactTestFor(providerName = "UserApiProvider", port = "8081", pactVersion = PactSpecVersion.V3)
public class UserConsumerContractTest {

    @Pact(consumer = "UserApiClientConsumer")
    public RequestResponsePact getUserById(PactDslWithProvider builder) {
        DslPart body = newJsonBody(o -> {
            o.integerType("id", 1);
            o.stringType("name", "Ana Silva");
            o.stringMatcher("email", "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", "ana.silva@example.com");
        }).build();

        return builder
            .given("um usuário com ID 1 existe")
            .uponReceiving("uma requisição para buscar o usuário de ID 1")
                .method("GET")
                .path("/users/1")
            .willRespondWith()
                .status(200)
                .headers(Map.of("Content-Type", "application/json"))
                .body(body)
            .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "getUserById")
    void testGetUserById() {
        RestAssured.baseURI = "http://localhost:8081";

        RestAssured
            .given()
                .contentType(ContentType.JSON)
            .when()
                .get("/users/1")
            .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("name", equalTo("Ana Silva"));
    }
}