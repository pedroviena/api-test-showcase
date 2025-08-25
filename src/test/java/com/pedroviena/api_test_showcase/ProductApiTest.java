package com.pedroviena.api_test_showcase;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.transaction.annotation.Transactional;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@Transactional
@Feature("Gerenciamento de Produtos")
public class ProductApiTest extends AbstractIntegrationTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    @Story("Criar um novo produto")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Deve criar um produto com sucesso")
    void shouldCreateProductSuccessfully() {
        String json = "{\"name\": \"Notebook Gamer\", \"price\": 5999.90}";
        given().contentType(ContentType.JSON).body(json)
        .when().post("/products")
        .then().statusCode(200).body("id", notNullValue()).body("name", equalTo("Notebook Gamer")).body("price", is(5999.90F));
    }

    @Test
    @Story("Criar um novo produto")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Deve retornar erro 400 ao criar produto com preço inválido")
    void shouldReturnBadRequestWhenCreatingProductWithInvalidPrice() {
        String json = "{\"name\": \"Teclado\", \"price\": 0}";
        given().contentType(ContentType.JSON).body(json)
        .when().post("/products")
        .then().statusCode(400);
    }

    @Test
    @Story("Buscar um produto pelo ID")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Deve buscar um produto por ID com sucesso")
    void shouldFindProductByIdSuccessfully() {
        String productToCreate = "{\"name\":\"Mouse Sem Fio\", \"price\":150.75}";
        Integer productId = given().contentType(ContentType.JSON).body(productToCreate)
        .when().post("/products")
        .then().statusCode(200).extract().path("id");
        given().pathParam("id", productId)
        .when().get("/products/{id}")
        .then().statusCode(200).body("id", equalTo(productId)).body("name", equalTo("Mouse Sem Fio")).body("price", is(150.75F));
    }

    @Test
    @Story("Buscar um produto pelo ID")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Deve retornar erro 404 ao buscar um produto inexistente")
    void shouldReturnNotFoundWhenProductDoesNotExist() {
        given().pathParam("id", 99999)
        .when().get("/products/{id}")
        .then().statusCode(404);
    }

    @Test
    @Story("Atualizar um produto existente")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Deve atualizar os dados de um produto com sucesso")
    void shouldUpdateProductSuccessfully() {
        String productToCreate = "{\"name\":\"Monitor 24p\", \"price\":800.00}";
        Integer productId = given().contentType(ContentType.JSON).body(productToCreate)
        .when().post("/products")
        .then().statusCode(200).extract().path("id");
        String updatedProduct = "{\"name\":\"Monitor 27 Polegadas\", \"price\":1200.50}";
        given().contentType(ContentType.JSON).body(updatedProduct).pathParam("id", productId)
        .when().put("/products/{id}")
        .then().statusCode(200).body("id", equalTo(productId)).body("name", equalTo("Monitor 27 Polegadas")).body("price", is(1200.50F));
    }

    @Test
    @Story("Deletar um produto")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Deve deletar um produto com sucesso")
    void shouldDeleteProductSuccessfully() {
        String productToCreate = "{\"name\":\"Produto para Deletar\", \"price\":10.00}";
        Integer productId = given().contentType(ContentType.JSON).body(productToCreate)
        .when().post("/products")
        .then().statusCode(200).extract().path("id");
        given().pathParam("id", productId)
        .when().delete("/products/{id}")
        .then().statusCode(204);
        given().pathParam("id", productId)
        .when().get("/products/{id}")
        .then().statusCode(404);
    }
}