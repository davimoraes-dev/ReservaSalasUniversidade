# Reserva de Salas - Universidade

Sistema web de agendamento e reserva de salas universitárias, desenvolvido em Java com Jakarta EE e Tomcat.

## Tecnologias

- Java 11
- Jakarta EE 10 (Servlets, JSP, JSTL)
- MySQL
- Apache Tomcat 10.1
- NetBeans 25

## Funcionalidades

- Cadastro, edição e exclusão de Salas
- Cadastro, edição e exclusão de Usuários
- Criação, edição e cancelamento de Reservas
- Consulta de detalhes de salas e reservas
- Gerenciamento de Equipamentos

## Design Patterns utilizados

- **MVC** — separação entre Controller (Servlets), Model e View (JSP)
- **Command** — encapsula as operações de negócio
- **Builder** — construção de objetos complexos (Sala, Reserva, Usuario)
- **Factory** — criação de objetos
- **Decorator** — adição dinâmica de equipamentos à sala (`SalaDecoradorBuilder`)

## Testes Automatizados

Testes unitários com JUnit 4 seguindo o padrão AAA (Arrange, Act, Assert), cobrindo cenários positivos e negativos do padrão Decorator.

## Como executar

1. Configure o banco de dados MySQL e importe o script SQL
2. Abra o projeto no NetBeans
3. Configure o servidor Tomcat
4. Execute o projeto
