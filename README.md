# Reserva de Salas - Universidade

Sistema web de agendamento e reserva de salas universitárias, desenvolvido em Java com Jakarta EE e Apache Tomcat, aplicando múltiplos padrões de projeto e arquitetura em camadas.

---

## Tecnologias

| Camada | Tecnologia |
|--------|-----------|
| Linguagem | Java 11 |
| Servidor | Apache Tomcat 10.1 |
| API Web | Jakarta EE 10 (Servlets, JSP, JSTL) |
| Banco de Dados | MySQL 8.0+ |
| Testes | JUnit 4 |
| Build | Apache Ant (NetBeans) |

---

## Padrões de Projeto Aplicados

### MVC (Model-View-Controller)
A aplicação segue estritamente a arquitetura MVC:
- **Model** — classes `model/` e interfaces `dao/`
- **View** — páginas JSP em `web/jsp/`
- **Controller** — Servlets anotados com `@WebServlet`

### Builder
Construção fluente dos objetos de domínio (`Usuario`, `Sala`, `Reserva`), evitando construtores com muitos parâmetros.

```java
Usuario usuario = new Usuario.Builder()
    .id(1)
    .nome("João Silva")
    .email("joao@universidade.com")
    .tipo("aluno")
    .build();
```

### Decorator
Adiciona equipamentos às salas de forma dinâmica e combinável, sem alterar a classe base.

```java
SalaComponente sala = new SalaDecoradorBuilder()
    .comProjetor()
    .comComputador()
    .build();
// Descrição: "Projetor, Computador"
```

### Command
Encapsula cada operação de banco de dados (inserir, atualizar, excluir) como um objeto comando, desacoplando quem solicita de quem executa.

```java
Comando cmd = new InserirReservaComando(reservaDAO, reserva);
cmd.executar();
```

### DAO (Data Access Object)
Abstrai o acesso ao banco de dados por meio de interfaces (`ISalaDAO`, `IReservaDAO`, `IUsuarioDAO`), separando a lógica de persistência do restante da aplicação (princípio DIP).

### Service Layer
Camada intermediária entre Controllers e DAOs que centraliza as regras de negócio, como validação de conflito de horários e verificações antes de persistir dados.

### Factory Method
Criação de objetos com lógica customizada, como geração automática de e-mail ao criar um usuário do tipo aluno.

```java
Usuario aluno = UsuarioFactory.criarAluno("Maria Santos");
// E-mail gerado: maria.santos@universidade.com
```

---

## Funcionalidades


- **Salas** — cadastro, edição, exclusão e detalhes; equipamentos adicionados via Decorator (projetor, computador)
- **Usuários** — cadastro de alunos e professores, edição, exclusão e detalhes
- **Reservas** — nova reserva com validação de conflito de horários, edição e cancelamento
- Mensagens de sucesso e erro em todas as operações

---

## Estrutura do Projeto

```
src/
└── com/exemplo/reservas/
    ├── model/          Usuario, Sala, Reserva (com Builder interno)
    ├── controller/     Servlets (IndexServlet, NovaReservaServlet, ...)
    ├── service/        ReservaService, SalaService, UsuarioService
    ├── dao/            Interfaces + implementações MySQL
    ├── command/        Comandos CRUD para cada entidade
    ├── decorator/      SalaBasica, ProjetorDecorator, ComputadorDecorator
    ├── exception/      NegocioException
    └── util/           Conexao (pool JDBC)

web/
├── jsp/
│   ├── layout/         header.jsp (menu compartilhado)
│   ├── reservas/       listaReservas, novaReserva, editarReserva
│   ├── salas/          listarSalas, formSala, detalhesSala
│   └── usuarios/       listarUsuarios, formUsuario, detalhesUsuario
├── css/estilo.css
└── index.jsp           (Dashboard)

test/
└── com/exemplo/reservas/decorator/
    └── DecoratorTest.java   (8 testes JUnit 4)
```

---

## Como Executar

### Pré-requisitos
- Java 11+
- Apache Tomcat 10.1
- MySQL 8.0+
- NetBeans (ou qualquer IDE com suporte a Ant)

### 1. Banco de Dados

Crie o banco e as tabelas no MySQL:

```sql
CREATE DATABASE reservas_universidade;
USE reservas_universidade;

CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    tipo VARCHAR(20) NOT NULL
);

CREATE TABLE salas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    capacidade INT NOT NULL,
    localizacao VARCHAR(100),
    equipamentos VARCHAR(255)
);

CREATE TABLE reservas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    sala_id INT NOT NULL,
    usuario_id INT NOT NULL,
    data DATE NOT NULL,
    hora_inicio TIME NOT NULL,
    hora_fim TIME NOT NULL,
    motivo VARCHAR(255),
    status VARCHAR(20) DEFAULT 'confirmada',
    FOREIGN KEY (sala_id) REFERENCES salas(id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);
```

> Credenciais padrão configuradas em `Conexao.java`: host `localhost:3306`, usuário `root`, senha `1234`.

### 2. Build e Deploy

1. Abra o projeto no NetBeans
2. Configure o Tomcat em **Tools → Servers**
3. Clique em **Run** (ou `F6`) — o NetBeans compila, gera o `.war` e faz o deploy automaticamente

### 3. Acesso

```
http://localhost:8080/ReservaSalasUniversidade/
```

---

## Testes

Os testes cobrem o padrão Decorator com 8 casos de teste em JUnit 4:

```
test/com/exemplo/reservas/decorator/DecoratorTest.java
```

Para executar: clique com o botão direito no arquivo → **Test File** no NetBeans, ou via Ant:

```bash
ant test
```
