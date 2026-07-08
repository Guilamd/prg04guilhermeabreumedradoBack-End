# Backend FinTech

Backend inicial de uma aplicacao web FinTech, desenvolvido em Java com Spring Boot.

O projeto foi estruturado em camadas para separar responsabilidades e facilitar a manutencao. Ele possui 4 CRUDs principais ligados entre si: `Usuario`, `Categoria`, `Conta` e `Transacao`.

## Tecnologias

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database
- PostgreSQL
- Maven

## Estrutura

```text
src/
  main/
    java/
      fintech/backend/
        BackendApplication.java
        config/
          WebClientConfig.java
        exception/
          GlobalExceptionHandler.java
          UsuarioNaoEncontradoException.java
          RecursoNaoEncontradoException.java
        dto/
          ErroResponseDTO.java
        usuario/
          controller/
            UsuarioController.java
          dto/
            UsuarioRequestDTO.java
            UsuarioResponseDTO.java
          entity/
            Usuario.java
          repository/
            UsuarioRepository.java
          service/
            UsuarioService.java
        categoria/
          controller/
            CategoriaController.java
          dto/
            CategoriaRequestDTO.java
            CategoriaResponseDTO.java
          entity/
            Categoria.java
          repository/
            CategoriaRepository.java
          service/
            CategoriaService.java
        conta/
          controller/
            ContaController.java
          dto/
            ContaRequestDTO.java
            ContaResponseDTO.java
          entity/
            Conta.java
          repository/
            ContaRepository.java
          service/
            ContaService.java
        transacao/
          controller/
            TransacaoController.java
          dto/
            TransacaoRequestDTO.java
            TransacaoResponseDTO.java
          entity/
            Transacao.java
            OrigemTransacao.java
            StatusTransacao.java
            TipoMovimentacao.java
            TipoPagamento.java
          repository/
            TransacaoRepository.java
          service/
            TransacaoService.java
        postexterno/
          controller/
            PostExternoController.java
          dto/
            PostExternoDTO.java
          service/
            PostExternoService.java
    resources/
      application.properties
  test/
    java/
      fintech/backend/
        usuario/
          controller/
            UsuarioControllerTest.java
        categoria/
          controller/
            CategoriaControllerTest.java
        postexterno/
          controller/
            PostExternoControllerTest.java
```

## Divisao em camadas

### `entity`

Contem as entidades do sistema.

Possui as entidades principais do dominio financeiro:

- `Usuario`: representa a pessoa que usa o sistema.
- `Categoria`: classifica os gastos/receitas do usuario.
- `Conta`: representa uma conta financeira do usuario.
- `Transacao`: representa uma movimentacao financeira vinculada a uma conta e categoria.

### `repository`

Camada responsavel pelo acesso ao banco de dados.

O `UsuarioRepository` usa Spring Data JPA para realizar operacoes como salvar, buscar, listar e remover usuarios.

### `service`

Camada responsavel pelas regras de negocio da aplicacao.

O `UsuarioService` centraliza as operacoes relacionadas a usuarios antes de acessar o repository.

### `controller`

Camada responsavel por receber as requisicoes HTTP e retornar as respostas da API.

Os controllers disponibilizam os endpoints REST das entidades principais.

### `dto`

Contem os objetos usados para entrada e saida de dados da API.

O projeto separa os dados recebidos nas requisicoes dos dados retornados nas respostas:

- `UsuarioRequestDTO`: usado em `POST` e `PUT`.
- `UsuarioResponseDTO`: usado nas respostas da API.
- `ErroResponseDTO`: usado para padronizar respostas de erro.

## Endpoints

| Metodo | Rota | Descricao | Status esperado |
| --- | --- | --- | --- |
| GET | `/usuarios` | Lista todos os usuarios | `200 OK` |
| GET | `/usuarios/{id}` | Busca um usuario pelo id | `200 OK` ou `404 Not Found` |
| POST | `/usuarios` | Cria um novo usuario | `201 Created` |
| PUT | `/usuarios/{id}` | Atualiza um usuario existente | `200 OK` ou `404 Not Found` |
| DELETE | `/usuarios/{id}` | Remove um usuario | `204 No Content` ou `404 Not Found` |
| GET | `/categorias` | Lista todas as categorias | `200 OK` |
| GET | `/categorias/{id}` | Busca uma categoria pelo id | `200 OK` ou `404 Not Found` |
| POST | `/categorias` | Cria uma categoria vinculada a um usuario | `201 Created` |
| PUT | `/categorias/{id}` | Atualiza uma categoria | `200 OK` ou `404 Not Found` |
| DELETE | `/categorias/{id}` | Remove uma categoria | `204 No Content` ou `404 Not Found` |
| GET | `/contas` | Lista todas as contas | `200 OK` |
| GET | `/contas/{id}` | Busca uma conta pelo id | `200 OK` ou `404 Not Found` |
| POST | `/contas` | Cria uma conta vinculada a um usuario | `201 Created` |
| PUT | `/contas/{id}` | Atualiza uma conta | `200 OK` ou `404 Not Found` |
| DELETE | `/contas/{id}` | Remove uma conta | `204 No Content` ou `404 Not Found` |
| GET | `/transacoes` | Lista todas as transacoes | `200 OK` |
| GET | `/transacoes/{id}` | Busca uma transacao pelo id | `200 OK` ou `404 Not Found` |
| POST | `/transacoes` | Cria uma transacao vinculada a conta e categoria | `201 Created` |
| PUT | `/transacoes/{id}` | Atualiza uma transacao | `200 OK` ou `404 Not Found` |
| DELETE | `/transacoes/{id}` | Remove uma transacao | `204 No Content` ou `404 Not Found` |


URL base da API:

```text
http://localhost:8080
```

Exemplo:

```text
http://localhost:8080/usuarios
```

## Testes HTTP

Os testes dos endpoints foram feitos pelo arquivo:

```text
requests.http
```

Esse arquivo pode ser executado diretamente pelo IntelliJ, sem necessidade de Postman ou Insomnia.

## DTO, ObjectMapper e excecoes

O projeto utiliza DTOs para evitar que a entidade seja usada diretamente na entrada e saida da API.

As conversoes entre DTOs e entidade sao feitas com `ObjectMapper` na camada de service.

O tratamento de erros foi centralizado com `@RestControllerAdvice` e `@ExceptionHandler`, retornando uma resposta padronizada quando um usuario nao e encontrado.
