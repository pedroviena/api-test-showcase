package com.pedroviena.api_test_showcase;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserStepDefinitions {

    @LocalServerPort
    private int port;

    private Response response;
    private String requestBody;

    @Dado("que eu tenho os dados de um novo usuário: nome {string} e e-mail {string}")
    public void que_eu_tenho_os_dados_de_um_novo_usuario(String name, String email) {
        requestBody = String.format("{\"name\":\"%s\", \"email\":\"%s\"}", name, email);
    }

    @Quando("eu enviar uma requisição POST para {string}")
    public void eu_enviar_uma_requisicao_post_para(String endpoint) {
        response = given()
            .contentType(ContentType.JSON)
            .body(requestBody)
            .port(port)
        .when()
            .post(endpoint);
    }

    @Então("o código de status da resposta deve ser {int}")
    public void o_codigo_de_status_da_resposta_deve_ser(Integer statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }

    @Então("o corpo da resposta deve conter o nome {string} e o e-mail {string}")
    public void o_corpo_da_resposta_deve_conter_o_nome_e_o_email(String name, String email) {
        response.then()
            .body("name", equalTo(name))
            .body("email", equalTo(email));
    }
}