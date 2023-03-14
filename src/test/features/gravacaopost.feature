# encoding: UTF-8
# language: pt

Funcionalidade: Enviar o dados Via Post Teste de Integração
  Como um usuario desejo enviar os dados
  do produto e verficar se o status é 201
  e o retorno é um objeto com os dados de product.

Cenário: Verificar se o método Post está gravando
  Dado  as seguintes informacoes do Product
  Quando enviar uma solicitacao POST para url de gravacao
  Entao eu devo receber uma resposta com codigo HTTP Correta
  E o product criado deve ser retornado na resposta