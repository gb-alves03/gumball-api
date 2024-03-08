# API Rest do Incrível Mundo de Gumball

Bem-vindo ao repositório da API Rest do Incrível Mundo de Gumball! Esta API foi desenvolvida em Java, utilizando Spring Boot, para gerenciar os personagens deste mundo encantador. Aqui você pode cadastrar novos personagens, listar todos os personagens ou apenas por ID, e excluir personagens cadastrados.

## Tecnologias Utilizadas

- **Java**: Linguagem de programação principal utilizada no desenvolvimento da API.
- **Spring Boot**: Framework utilizado para facilitar o desenvolvimento de aplicações em Java.
- **Postgres**: Banco de dados utilizado para armazenar os dados dos personagens.
- **Flyway Migration**: Utilizado para o controle e versionamento das migrações do banco de dados.
- **JPA Repository**: Interface que fornece um mecanismo para interagir com o banco de dados, facilitando a implementação do CRUD.
- **Mockito e JUnit**: Utilizados para realizar testes das classes Mapper, Service e Controller, incluindo testes de integração da API.

## Funcionalidades

- **Cadastro de Personagens**: Permite o cadastro completo de novos personagens, incluindo nome, descrição e outros atributos relevantes.
- **Listagem de Personagens**: Possibilita listar todos os personagens cadastrados no sistema ou buscar um personagem específico por ID.
- **Exclusão de Personagens**: Permite excluir personagens cadastrados no sistema, removendo suas informações do banco de dados.

## Utilização de Records para DTOs

Os Records são utilizados para representar objetos de transferência de dados (DTOs) de forma simples e concisa, seguindo o padrão DTO.

## Como Utilizar

1. Clone este repositório para o seu ambiente local.
2. Certifique-se de ter o Java e o Maven instalados em seu sistema.
3. Configure um banco de dados PostgreSQL e ajuste as configurações no arquivo `application.properties`.
4. Execute as migrações do banco de dados usando o Flyway Migration.
5. Compile o projeto utilizando o Maven: `mvn clean install`.
6. Inicie a aplicação Spring Boot.
7. Utilize o Postman ou qualquer outra ferramenta de sua preferência para interagir com a API através das URLs fornecidas.
