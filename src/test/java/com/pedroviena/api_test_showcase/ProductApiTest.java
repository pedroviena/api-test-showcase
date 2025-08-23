package com.pedroviena.api_test_showcase;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Feature("Gerenciamento de Produtos")
public class ProductApiTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    @Story("Criar um novo produto com dados válidos")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Deve criar um produto com sucesso")
    void shouldCreateProductSuccessfully() {
        String json = """
                {
                  "name": "Notebook Gamer",
                  "price": 5999.90
                }
                """;

        given()
            .contentType(ContentType.JSON)
            .body(json)
        .when()
            .post("/products")
        .then()
            .statusCode(200)
            .body("id", notNullValue())
            .body("name", equalTo("Notebook Gamer"))
            .body("price", is(5999.90F)); // Use is() e 'F' para float
    }

    @Test
    @Story("Tentar criar um produto com dados inválidos")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Deve retornar erro 400 ao criar produto com preço zero")
    void shouldReturnBadRequestWhenCreatingProductWithInvalidPrice() {
        String json = """
                {
                  "name": "Teclado",
                  "price": 0
                }
                """;

        given()
            .contentType(ContentType.JSON)
            .body(json)
        .when()
            .post("/products")
        .then()
            .statusCode(400); // A validação @Min(1) deve falhar
    }
}