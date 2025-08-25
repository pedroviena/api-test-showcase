package com.pedroviena.api_test_showcase;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.transaction.annotation.Transactional;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@Transactional
@Feature("Gerenciamento de Usuários")
public class UserApiTest extends AbstractIntegrationTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    // ... Seus testes @Test continuam aqui, sem alterações ...
    @Test
    @Story("Criar um novo usuário com dados válidos")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Deve criar um usuário com sucesso")
    @Description("Este teste verifica se a API consegue criar um novo usuário e retorna status 200.")
    void shouldCreateUserSuccessfully() {
        String json = "{\"name\": \"Pedro\", \"email\": \"pedro@test.com\"}";
        given().contentType(ContentType.JSON).body(json)
        .when().post("/users")
        .then().statusCode(200).body("id", notNullValue()).body("name", equalTo("Pedro")).body("email", equalTo("pedro@test.com"));
    }

    @Test
    @Story("Tentar criar um usuário com dados inválidos")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Deve retornar erro 400 ao criar usuário com e-mail inválido")
    @Description("Este teste verifica se a API retorna 'Bad Request' ao tentar criar um usuário com um formato de e-mail incorreto.")
    void shouldReturnBadRequestWhenCreatingUserWithInvalidEmail() {
        String json = "{\"name\": \"Maria\", \"email\": \"maria-email-invalido\"}";
        given().contentType(ContentType.JSON).body(json)
        .when().post("/users")
        .then().statusCode(400);
    }

    @Test
    @Story("Buscar um usuário pelo ID")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Deve buscar um usuário por ID com sucesso")
    @Description("Este teste cria um usuário e depois o busca pelo ID para verificar a resposta.")
    void shouldFindUserByIdSuccessfully() {
        String userToCreate = "{\"name\": \"Carlos\", \"email\": \"carlos@test.com\"}";
        Integer userId = given().contentType(ContentType.JSON).body(userToCreate)
        .when().post("/users")
        .then().statusCode(200).extract().path("id");
        given().pathParam("id", userId)
        .when().get("/users/{id}")
        .then().statusCode(200).body("id", equalTo(userId)).body("name", equalTo("Carlos")).body("email", equalTo("carlos@test.com"));
    }

    @Test
    @Story("Buscar um usuário pelo ID")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Deve retornar erro 404 ao buscar um usuário inexistente")
    @Description("Este teste verifica o comportamento da API ao buscar um usuário com um ID que não existe.")
    void shouldReturnErrorWhenUserNotFound() {
        given().pathParam("id", 99999)
        .when().get("/users/{id}")
        .then().statusCode(404);
    }

    @Test
    @Story("Atualizar um usuário existente")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Deve atualizar os dados de um usuário com sucesso")
    void shouldUpdateUserSuccessfully() {
        String userToCreate = "{\"name\":\"Ana\", \"email\":\"ana@test.com\"}";
        Integer userId = given().contentType(ContentType.JSON).body(userToCreate)
        .when().post("/users")
        .then().statusCode(200).extract().path("id");
        String updatedUser = "{\"name\":\"Ana Silva\", \"email\":\"ana.silva@test.com\"}";
        given().contentType(ContentType.JSON).body(updatedUser).pathParam("id", userId)
        .when().put("/users/{id}")
        .then().statusCode(200).body("id", equalTo(userId)).body("name", equalTo("Ana Silva")).body("email", equalTo("ana.silva@test.com"));
    }

    @Test
    @Story("Deletar um usuário")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Deve deletar um usuário com sucesso")
    void shouldDeleteUserSuccessfully() {
        String userToCreate = "{\"name\":\"Para Deletar\", \"email\":\"deletar@test.com\"}";
        Integer userId = given().contentType(ContentType.JSON).body(userToCreate)
        .when().post("/users")
        .then().statusCode(200).extract().path("id");
        given().pathParam("id", userId)
        .when().delete("/users/{id}")
        .then().statusCode(204);
        given().pathParam("id", userId)
        .when().get("/users/{id}")
        .then().statusCode(404);
    }

    @Test
    @Story("Validação de Segurança")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Não deve expor o campo 'password' ao buscar um usuário")
    @Description("Este teste de segurança verifica se o campo 'password' não está presente na resposta ao consultar um usuário por ID.")
    void shouldNotExposePasswordFieldWhenFindingUser() {
       
        String userToCreate = "{\"name\":\"Usuario Seguro\", \"email\":\"secure@test.com\", \"password\":\"senha123\"}";

        Integer userId = given()
            .contentType(ContentType.JSON)
            .body(userToCreate)
        .when()
            .post("/users")
        .then()
            .statusCode(200)
            .extract().path("id");

        
        given()
            .pathParam("id", userId)
        .when()
            .get("/users/{id}")
        .then()
            .statusCode(200)
           
            .body("$", not(hasKey("password")))
          
            .body("id", equalTo(userId))
            .body("name", equalTo("Usuario Seguro"));
    }
}