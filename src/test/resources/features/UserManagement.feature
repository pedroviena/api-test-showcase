# language: pt
Funcionalidade: Gerenciamento de Usuários
  Como um usuário da API,
  Eu quero poder criar e gerenciar usuários
  Para manter os dados do sistema atualizados.

  Cenário: Criar um novo usuário com sucesso
    Dado que eu tenho os dados de um novo usuário: nome "João" e e-mail "joao@example.com"
    Quando eu enviar uma requisição POST para "/users"
    Então o código de status da resposta deve ser 201
    E o corpo da resposta deve conter o nome "João" e o e-mail "joao@example.com"