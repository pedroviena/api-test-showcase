# Casos de Teste - API de Usuários e Produtos

## Módulo: Usuários (`/users`)

| ID   | Título do Teste                                | Passos                                                                                                                              | Resultado Esperado                                                                        |
| :--- | :--------------------------------------------- | :---------------------------------------------------------------------------------------------------------------------------------- | :---------------------------------------------------------------------------------------- |
| **CT01** | Criar novo usuário com sucesso                 | 1. Enviar uma requisição `POST` para `/users` com um corpo JSON contendo `name`, `email` e `password` válidos.                         | A API deve retornar status `201 Created` e o corpo da resposta deve conter o usuário criado com um ID. |
| **CT02** | Tentar criar usuário com e-mail duplicado      | 1. Criar um usuário (CT01).<br>2. Enviar uma nova requisição `POST` para `/users` com o mesmo `email` do usuário anterior.          | A API deve retornar status `409 Conflict` com uma mensagem de erro apropriada.           |
| **CT03** | Consultar usuário por ID existente             | 1. Criar um usuário (CT01).<br>2. Enviar uma requisição `GET` para `/users/{id}` usando o ID do usuário criado.                      | A API deve retornar status `200 OK` e os dados completos do usuário correspondente.        |
| **CT04** | Tentar consultar usuário com ID inexistente    | 1. Enviar uma requisição `GET` para `/users/9999` (um ID que não existe).                                                           | A API deve retornar status `404 Not Found`.                                               |
| **CT05** | Atualizar usuário com sucesso                  | 1. Criar um usuário (CT01).<br>2. Enviar uma requisição `PUT` para `/users/{id}` com novos dados para `name` e `email`.              | A API deve retornar status `200 OK` com os dados do usuário atualizados.                   |
| **CT06** | Deletar um usuário com sucesso                 | 1. Criar um usuário (CT01).<br>2. Enviar uma requisição `DELETE` para `/users/{id}`.<br>3. Tentar buscar o mesmo usuário via `GET`. | A primeira requisição deve retornar `204 No Content`. A segunda deve retornar `404 Not Found`. |

---

## Módulo: Produtos (`/products`)

| ID   | Título do Teste                           | Passos                                                                                                         | Resultado Esperado                                                                               |
| :--- | :---------------------------------------- | :------------------------------------------------------------------------------------------------------------- | :----------------------------------------------------------------------------------------------- |
| **CT07** | Criar novo produto com sucesso            | 1. Enviar `POST` para `/products` com `name`, `description` e `price` válidos.                                  | A API deve retornar status `201 Created` e o produto criado.                                     |
| **CT08** | Tentar criar produto com preço negativo   | 1. Enviar `POST` para `/products` com um `price` menor que zero.                                                | A API deve retornar status `400 Bad Request` devido à falha na validação.                         |
| **CT09** | Listar todos os produtos                | 1. Criar 2 ou mais produtos.<br>2. Enviar `GET` para `/products`.                                               | A API deve retornar status `200 OK` e uma lista contendo todos os produtos cadastrados.          |