# API Test Showcase

Projeto  em automação de testes de API, configuração de ambiente, CI/CD e geração de relatórios de teste.

## Tecnologias

- **Backend**: Java 21 e Spring Boot 3
- **Testes de API**: RestAssured com JUnit 5
- **Relatórios**: Allure Reports
- **Banco de Dados**: PostgreSQL (para desenvolvimento/produção) e H2 (para testes)
- **Build**: Maven

## Funcionalidades Implementadas

* **API CRUD Completa**: Endpoints REST para operações de Criar, Ler, Atualizar e Deletar (CRUD) para as entidades `User` e `Product`.
* **Testes de API com Cobertura Total**: Implementação de testes de integração para todo o ciclo de vida (CRUD) da API, validando cenários de sucesso e de falha.
* **Tratamento de Exceções customizado**: Implementação de um `GlobalExceptionHandler` para retornar respostas de erro padronizadas e códigos de status HTTP corretos (como `404 Not Found`), seguindo as melhores práticas de APIs REST.
* **Ambiente de Teste Isolado**: Configuração de um banco de dados em memória (H2) que é ativado automaticamente durante a execução dos testes, garantindo consistência e independência.
* **Relatórios de Teste com Allure**: Integração completa com o Allure Reports para gerar relatórios detalhados e interativos sobre a execução dos testes.

## Como Executar o Projeto

### Pré-requisitos
* Java 21
* Maven 3.x
* PostgreSQL (opcional, para rodar a aplicação principal)

### Rodando a Aplicação
Para iniciar a API (usando o banco de dados PostgreSQL configurado em `application.properties`):
```sh
mvn spring-boot:run