# Literalura

## Sobre o projeto

O Literalura é uma aplicação web desenvolvida em Java com Spring Boot que permite aos usuários criar um catálogo personalizado de livros. Através da integração com a API Gutendex, é possível pesquisar e adicionar livros ao catálogo, juntamente com seus autores. Os dados são armazenados em um banco de dados PostgreSQL, utilizando JPA Hibernate para a persistência.

## Objetivo

Demonstrar as habilidades em desenvolvimento Java adquiridas durante a Fase de Especialização do Programa ONE.
Apresentar um exemplo prático de consumo de APIs externas e persistência de dados.

## Funcionalidades Principais

- **Pesquisa e adição de livros:** Os usuários podem pesquisar livros na API Gutendex e adicioná-los ao seu catálogo.
 - **Gerenciamento de autores:** A aplicação automaticamente adiciona os autores dos livros ao banco de dados e permite a listagem de todos os autores cadastrados.
- **Filtros e buscas:** É possível filtrar os livros por idioma e listar os autores vivos em um determinado ano.
- **Listagem dos livros mais baixados:** Uma funcionalidade adicional que permite aos usuários visualizar os livros mais populares do catálogo.

## Tecnologias Utilizadas
- Java
- Spring Boot: Framework Java para desenvolvimento de aplicações web.
- JPA Hibernate: Framework de mapeamento objeto-relacional para persistência de dados.
- PostgreSQL: Banco de dados relacional utilizado para armazenar as informações dos livros e autores.
- Maven: Ferramenta de gerenciamento de dependências.
- API Gutendex: Fonte de dados externa para obter informações sobre livros.

