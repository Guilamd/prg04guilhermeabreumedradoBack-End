# Backend FinTech

Backend inicial de uma aplicacao web FinTech, desenvolvido em Java com Spring Boot.

O projeto foi estruturado em camadas para separar responsabilidades e facilitar a manutencao. Nesta primeira etapa, a entidade implementada e `Usuario`, com endpoints REST para cadastro, listagem, busca, atualizacao e exclusao.

## Tecnologias

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database
- Maven

## Estrutura

```text
src/
  main/
    java/
      fintech/backend/
        BackendApplication.java
        controller/
          UsuarioController.java
        dto/
          ErroResponseDTO.java
          UsuarioRequestDTO.java
          UsuarioResponseDTO.java
        entity/
          Usuario.java
        exception/
          GlobalExceptionHandler.java
          UsuarioNaoEncontradoException.java
        repository/
          UsuarioRepository.java
        service/
          UsuarioService.java
    resources/
      application.properties
  test/
    java/
      fintech/backend/
        controller/
          UsuarioControllerTest.java
```

## Divisao em camadas

### `entity`

Contem as entidades do sistema.

Atualmente possui a entidade `Usuario`, que representa os dados de um usuario no banco.

### `repository`

Camada responsavel pelo acesso ao banco de dados.

O `UsuarioRepository` usa Spring Data JPA para realizar operacoes como salvar, buscar, listar e remover usuarios.

### `service`

Camada responsavel pelas regras de negocio da aplicacao.

O `UsuarioService` centraliza as operacoes relacionadas a usuarios antes de acessar o repository.

### `controller`

Camada responsavel por receber as requisicoes HTTP e retornar as respostas da API.

O `UsuarioController` disponibiliza os endpoints REST da entidade `Usuario`.

### `dto`

Contem os objetos usados para entrada e saida de dados da API.

O projeto separa os dados recebidos nas requisicoes dos dados retornados nas respostas:

- `UsuarioRequestDTO`: usado em `POST` e `PUT`.
- `UsuarioResponseDTO`: usado nas respostas da API.
- `ErroResponseDTO`: usado para padronizar respostas de erro.

### `exception`

Contem as classes responsaveis pelo tratamento de excecoes da API.

O `GlobalExceptionHandler` centraliza o tratamento dos erros usando as anotacoes do Spring.

## Entidade inicial

### `Usuario`

Campos:

- `id`
- `nome`
- `email`
- `senha`

## Endpoints

| Metodo | Rota | Descricao | Status esperado |
| --- | --- | --- | --- |
| GET | `/usuarios` | Lista todos os usuarios | `200 OK` |
| GET | `/usuarios/{id}` | Busca um usuario pelo id | `200 OK` ou `404 Not Found` |
| POST | `/usuarios` | Cria um novo usuario | `201 Created` |
| PUT | `/usuarios/{id}` | Atualiza um usuario existente | `200 OK` ou `404 Not Found` |
| DELETE | `/usuarios/{id}` | Remove um usuario | `204 No Content` ou `404 Not Found` |

## Execucao local

O backend roda localmente na porta `8080`.

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

## Banco de dados

O projeto utiliza H2 em memoria para facilitar os testes iniciais.

As configuracoes do banco estao em:

```text
src/main/resources/application.properties
```
