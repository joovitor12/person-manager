# Person Manager
<p align="center">
  <a href="#-tecnologias">Tecnologias</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-projeto">Projeto</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-como-executar">Como executar</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-colaboradores">Colaboradores</a>&nbsp;&nbsp;&nbsp;
</p>

<br>

## ✨ Tecnologias

O projeto foi desenvolvido e testado com as seguintes tecnologias:

- [SpringBoot](https://spring.io/projects/spring-boot)
- [JUnit](https://junit.org/junit5/)
- [MongoDB](https://www.mongodb.com)
- [Postman](https://www.postman.com/)
- [GitHub Actions](https://docs.github.com/en/actions)


## 💻 Projeto

O Person Manager é uma API de gerenciamento de pessoas com as funcionalidades de:

- Criar, editar e consultar uma ou mais pessoas
- Criar, editar e consultar um ou mais endereços de uma pessoa
- Poder indicar qual endereço será considerado o principal de uma pessoa

## 🚀 Como executar

- Clone o repositório
- Tenha instalado as seguintes dependencias:
   - [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
   - [Maven 3.8.5](https://maven.apache.org/docs/3.8.5/release-notes.html)
     
- Abra o repositório em alguma IDE (Ex: Intellij, STS etc.) ou no seu próprio terminal
- Abra o terminal do projeto e execute o comando `mvn clean install`
- Apos isso, execute o comando `mvn spring-boot:run` ou apenas rode a classe `PersonManagerApplication.java`

-  [`localhost:8080/api/person/v1`](http://localhost:8080/api/person/v1) -> para serviços apontados para a entidade de pessoas sendo gerenciadas
-  [`localhost:8080/api/address/v1`](http://localhost:8080/api/address/v1) -> para serviços apontados para a entidade de endereços das pessoas gerenciadas

Também está sendo disponibilizada uma coleção do Postman contendo rotas com exemplos para cada rota da aplicação. Clique [aqui](https://github.com/joovitor12/person-manager/files/15205242/person-manager.postman_collection.json) para baixar.

## 👷 Colaboradores

#### Nome: João Vitor Machado Andrade Sousa
- [GitHub](https://github.com/joovitor12)
- [LinkedIn](https://www.linkedin.com/in/jo%C3%A3o-vitor-machado-b23a7820b/)
