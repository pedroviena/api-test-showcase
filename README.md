# API Test Showcase: Arquitetura Completa de Testes para API REST

![Build Status](https://github.com/pedroviena/api-test-showcase/actions/workflows/ci-pipeline.yml/badge.svg)
![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.5-brightgreen)
![Testcontainers](https://img.shields.io/badge/Testcontainers-PostgreSQL-blueviolet)
![Pact](https://img.shields.io/badge/Pact-Contrato-orange)
![Cucumber](https://img.shields.io/badge/Cucumber-BDD-green)
![Allure](https://img.shields.io/badge/Allure_Report-Visual-red)

## 📄 Sobre o Projeto

Este projeto é uma demonstração abrangente de uma arquitetura de testes automatizados para uma API REST, construída com as tecnologias mais relevantes do ecossistema Java. O objetivo é servir como um portfólio técnico, exibindo proficiência em múltiplos níveis e tipos de teste, desde a validação funcional até testes de contrato e a configuração de um pipeline de CI/CD.

A API de exemplo, desenvolvida com Spring Boot, gerencia um CRUD simples de usuários e produtos.

---

## ✨ Principais Habilidades Demonstradas

* **Testes de Integração com Banco de Dados Real:** Uso do **Testcontainers** para instanciar um banco de dados PostgreSQL em um contêiner Docker a cada execução, garantindo um ambiente de teste limpo, isolado e fiel à produção.
* **Testes de Contrato (Consumer-Driven Contracts):** Implementação do ciclo completo do **Pact**, com testes de consumidor que geram o contrato e testes de provedor que validam a conformidade da API com o contrato estabelecido.
* **Behavior-Driven Development (BDD):** Escrita de cenários de teste em linguagem natural (Gherkin) com **Cucumber**, melhorando a comunicação e o alinhamento com os requisitos de negócio.
* **Automação e CI/CD:** Criação de um pipeline de integração contínua com **GitHub Actions** que compila o projeto, executa todos os tipos de teste e publica os relatórios do Allure a cada push.
* **Relatórios Avançados:** Configuração do **Allure Reports** para gerar dashboards interativos e detalhados, facilitando a análise de resultados e a identificação de falhas.
* **Validação de Segurança:** Inclusão de testes para garantir que dados sensíveis (como senhas) não sejam expostos nos endpoints da API.

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Finalidade |
| :--- | :--- |
| **Java 17 & Spring Boot** | Criação da API REST (Provedor) para os testes. |
| **RestAssured** | Framework para automação dos testes funcionais e de integração da API. |
| **JUnit 5** | Framework base para a execução dos testes. |
| **PostgreSQL & Testcontainers**| Utilização de um banco de dados real em um contêiner Docker para os testes de integração. |
| **Pact** | Implementação de Testes de Contrato (Consumer-Driven Contracts). |
| **Cucumber** | Implementação de testes no formato BDD com Gherkin. |
| **Allure Reports** | Geração de relatórios de teste avançados e detalhados. |
| **GitHub Actions** | Automação do processo de build e execução dos testes (CI/CD). |
| **Maven** | Gerenciador de dependências e do ciclo de vida do projeto. |

---

## 🚀 Como Executar

### Pré-requisitos

- Java (JDK 21 ou superior)
- Maven
- **Docker Desktop** (precisa estar em execução para os testes de integração)

### Executando os Testes

1.  Clone o repositório:
    ```bash
    git clone [https://github.com/pedroviena/api-test-showcase.git]
    ```
2.  Navegue até a pasta do projeto:
    ```bash
    cd api-test-showcase
    ```
3.  Execute todos os testes com o Maven Wrapper:
    ```bash
    ./mvnw clean test
    ```

### Visualizando os Relatórios Allure

Após a execução dos testes, para visualizar o relatório HTML, execute o seguinte comando:

```bash
mvn allure:serve
 ```


###  Contato
Pedro Viena

LinkedIn: https://www.linkedin.com/in/pedro-arian-viena/
GitHub: https://github.com/pedroviena/
