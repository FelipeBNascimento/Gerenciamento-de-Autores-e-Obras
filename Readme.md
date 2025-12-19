# Gerenciamento de Autores e Obras 📚


API REST para gerenciamento de obras literárias e seus respectivos autores, desenvolvida com Spring Boot. O projeto foca em boas práticas de arquitetura e regras de negócio validadas.

## Funcionalidades Principais

* **Cadastro de Obras:** Cadastra obras com título, descrição e data de publicação.
* **Gestão de Autores:** Vinculação de múltiplos autores a uma obra.
* **Validação de Nacionalidade (Regra de Negócio):**
    * **Brasileiros:** O preenchimento do CPF é obrigatório. Caso não informado,irá lança uma exceção personalizada `CPFObrigatorio`.
    * **Estrangeiros:** O CPF é opcional. Se não for informado, o sistema gera automaticamente um identificador aleatório de 11 dígitos.
* **Segurança:** Implementação de Spring Security para proteção de endpoints, gerando token através
da senha do autor

##  Tecnologias Utilizadas

* **Java 17**
* **Spring Boot 4.0** (Web, Data JPA, Security, Validation)
* **MapStruct:** Para mapeamento entre Entidades e DTOs.
* **JUnit 5 & Mockito:** Para testes unitários e de integração.
* **PostgreSQL:** Banco de dados relacional.
* **Lombok:** Para redução de código boilerplate.

##  Arquitetura

O projeto segue a arquitetura em camadas para facilitar a manutenção e escalabilidade:

1.  Controller: endpoints REST.
2.  Business: Camada de inteligência e regras de negócio.
3.  Repository: Interface de comunicação com o banco de dados via Spring Data JPA.
4.  Infrastructure: Configurações de segurança, tratamento de exceções e mappers.


 ## Guia de Uso e Fluxo da API

Para utilizar os recursos de Obras, é necessário seguir o fluxo de autenticação abaixo, pois os endpoints são protegidos por Spring Security.

##  Passo: Cadastrar um Autor (Usuário)
Antes de logar, você precisa de um registro no banco de dados. O autor cadastrado aqui será usado como sua credencial de acesso.

- **Endpoint:** `POST /autores`
- **Corpo da Requisição (JSON):**

JSON abaixo

{
  "nome": "Felipe Nascimento",
  "sexo": "Masculino",
  "email": "felipe@teste.com",
  "data_nascimento": "2000-01-01",
  "pais_origem": "Brasil",
  "cpf": "12345678901",
  "senha": "123"
}


## Passo: Realizar Login (Gerar Token)
Envie o e-mail e a senha cadastrados para obter o Token JWT.

Endpoint: POST /auth/login

Corpo da Requisição (JSON):

JSON abaixo

{
  "email": "felipe@teste.com",
  "senha": "123"
}

A api retornara um token copiei esse token completo


## Passo: Cadastrar uma Obra (Requer Autenticação)
Agora, com o token em mãos, você pode acessar os recursos protegidos.

Endpoint: POST /obras

Configuração no Postman:

Vá na aba no Header na aba Authorization.

Selecione o tipo Bearer Token.

Cole o token gerado no Passo 2.

Corpo da Requisição (JSON):

JSON abaixo

{
  "titulo": "Programa de teste",
  "descricao": "Um desafio Java .",
  "data_publicacao": "2025-12-19",
  "autores": [
    {
      "nome": "Felipe Nascimento",
      "sexo": "Masculino",
      "email": "felipe@teste.com",
      "data_nascimento": "2000-01-01",
      "pais_origem": "Brasil",
      "cpf": "12345678901"
    }
  ]
}



