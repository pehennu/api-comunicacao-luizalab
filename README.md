**README para o Projeto: Plataforma de Comunicação - LuizaLab **

---

## Descrição
Este projeto é uma API para gerenciamento de agendamentos de comunicação, desenvolvida com Spring Boot e JPA. A API permite a criação, busca e deleção de agendamentos, além de realizar validações nos dados de entrada e tratar exceções de forma adequada.

## Funcionalidades
- **Criação de Agendamentos**: Permite que os usuários criem novos agendamentos de comunicação.
- **Busca de Agendamentos**: Possibilita a busca de agendamentos existentes por ID.
- **Deleção de Agendamentos**: Permite a remoção de agendamentos pelo ID.
- **Validação de Dados**: Valida os dados de entrada para garantir que estão corretos antes de processá-los.
- **Tratamento de Exceções**: Implementa um manipulador global de exceções para fornecer respostas adequadas em caso de erros.

## Tecnologias Utilizadas
- **Java**: Linguagem de programação principal.
- **Spring Boot**: Framework para desenvolvimento de aplicações Java.
- **Spring Data JPA**: Para interação com o banco de dados.
- **PostgreSQL**: Sistema de gerenciamento de banco de dados.
- **Lombok**: Para reduzir o boilerplate de código.
- **Swagger**: Para documentação da API.

## Estrutura do Projeto
```
.
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── br
│   │   │           └── luizalab
│   │   │               └── comunicacao
│   │   │                   ├── config
│   │   │                   │   ├── SwaggerConfig.java
│   │   │                   │   └── Validacao.java
│   │   │                   ├── controller
│   │   │                   │   └── AgendamentoController.java
│   │   │                   ├── dto
│   │   │                   │   ├── AgendamentoRequest.java
│   │   │                   │   ├── AgendamentoResponse.java
│   │   │                   ├── exception
│   │   │                   │   └── AgendamentoNaoEncontradoException.java
│   │   │                   │   └── GlobalExceptionHandler.java
│   │   │                   ├── mapper
│   │   │                   │   └── AgendamentoMapper.java
│   │   │                   ├── model
│   │   │                   │   ├── AgendamentoComunicacao.java
│   │   │                   │   ├── StatusAgendamento.java
│   │   │                   │   └── TipoComunicacao.java
│   │   │                   ├── repository
│   │   │                   │   └── AgendamentoComunicacaoRepository.java
│   │   │                   └── service
│   │   │                       └── AgendamentoService.java
│   │   └── resources
│   │       ├── application.properties
│   │       └── schema.sql
│   └── test
│       └── java
│           └── com
│               └── br
│                   └── luizalab
│                       └── comunicacao
│                           ├── controller
│                           │   └── AgendamentoControllerTest.java
│                           └── service
│                               └── AgendamentoServiceTest.java
├── pom.xml
└── HELP.md
```

## Endpoints da API

### 1. Criar Agendamento
- **Método**: `POST`
- **Endpoint**: `/agendamentos/agendar`
- **Exemplo de JSON**:
```json
{
  "dataHoraAgendada": "2023-12-01T10:00:00",
  "destinatario": "teste@luizalab.com",
  "mensagem": "Mensagem de teste",
  "tipo": "EMAIL"
}
```

### 2. Buscar Agendamento por ID
- **Método**: `GET`
- **Endpoint**: `/agendamentos/buscar_agendamento/{id}`
- **Exemplo de Requisição**: `/agendamentos/buscar_agendamento/1`
- **Resposta Exemplo**:
```json
{
  "id": 1,
  "dataHoraAgendada": "2023-12-01T10:00:00",
  "destinatario": "teste@magalu.com",
  "mensagem": "Mensagem de teste",
  "tipo": "EMAIL",
  "status": "AGENDADO",
  "criadoEm": "2023-11-01T10:00:00"
}
```

### 3. Deletar Agendamento
- **Método**: `DELETE`
- **Endpoint**: `/agendamentos/deletar_agendamento/{id}`
- **Exemplo de Requisição**: `/agendamentos/deletar_agendamento/1`
- **Resposta Exemplo**:
```json
{
   "mensagem": "Agendamento com ID {id} deletado com sucesso."
}
```

## Schema do Banco de Dados
Um arquivo `schema.sql` está incluído no projeto para facilitar a criação do banco de dados no PostgreSQL. Este arquivo contém as instruções SQL necessárias para criar as tabelas e relacionamentos necessários para o funcionamento da API.

## Como Executar o Projeto
1. **Clone o repositório**:
   ```bash
   git clone https://github.com/pehennu/api-comunicacao-luizalab.git
   cd api-comunicacao-luizalab
   ```

2. **Compile e execute a aplicação**:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

3. **Acesse a API**:
   A API estará disponível em `http://localhost:8080/agendamentos`.

## Documentação da API
A documentação da API pode ser acessada através do Swagger em `http://localhost:8080/swagger-ui.html`.

## Futuras melhorias
- Implementar um endpoint para alterar o status de um agendamento.

## Observação
- Foi disponibilizada uma collection insomnia pronta pra uso dentro de resources.

## Contato
- **Desenvolvedor**: Pedro Nunes
- **Email**: pehennu@gmail.com
- **GitHub**: [pehennu](https://github.com/pehennu)

---

