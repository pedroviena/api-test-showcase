# Relatório de Bugs Identificados

## Bug #1: API permite atualizar usuário com e-mail já existente

- **ID:** BUG-001
- **Título:** A API permite que um usuário seja atualizado com um e-mail que já pertence a outro usuário, causando duplicidade de dados.
- **Projeto:** api-test-showcase
- **Severidade:** Alta
- **Prioridade:** Alta
- **Ambiente:** Local (H2 Database)
- **Passos para reproduzir:**
  1. Crie o Usuário A com o e-mail `usuario.a@teste.com` via `POST /users`.
  2. Crie o Usuário B com o e-mail `usuario.b@teste.com` via `POST /users`.
  3. Envie uma requisição `PUT /users/{id_usuario_b}` para atualizar o Usuário B, mas passando no corpo da requisição o e-mail do Usuário A: `{ "email": "usuario.a@teste.com" }`.
- **Resultado Atual:** A API retorna status `200 OK` e atualiza o e-mail do Usuário B. Agora existem dois usuários com o mesmo e-mail no banco.
- **Resultado Esperado:** A API deveria retornar um status `409 Conflict` e uma mensagem de erro indicando que o e-mail já está em uso.

---

## Bug #2: Mensagem de erro não padronizada para campos obrigatórios

- **ID:** BUG-002
- **Título:** Ao tentar criar um produto sem o campo `name`, a mensagem de erro retornada é genérica e não informa qual campo está faltando.
- **Projeto:** api-test-showcase
- **Severidade:** Baixa
- **Prioridade:** Média
- **Ambiente:** Local (H2 Database)
- **Passos para reproduzir:**
  1. Envie uma requisição `POST /products` com o seguinte corpo: `{ "description": "Um bom produto", "price": 50.0 }`.
- **Resultado Atual:** A API retorna status `400 Bad Request` com uma mensagem de erro genérica do Spring Validation.
- **Resultado Esperado:** A API deveria retornar status `400 Bad Request` com um corpo de resposta claro, informando: `O campo 'name' é obrigatório`.