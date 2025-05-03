# UrlShortener

# Projeto Encurtador de URL

Este é um projeto simples de encurtador de URLs desenvolvido com Java, Spring Boot 3.x, Spring Data JPA e MySQL, seguindo princípios de Clean Code, SOLID e boas práticas de segurança.

## Tecnologias Utilizadas

- Java 17+
- Spring Boot 3.x
- Spring Data JPA
- MySQL
- Lombok
- Swagger/OpenAPI
- Hibernate Validator

## Estrutura de Pacotes

- `controller`: expõe os endpoints REST.
- `service`: lógica de negócios e geração do hash.
- `repository`: acesso ao banco de dados com Spring Data JPA.
- `dto`: objetos de transferência de dados (entrada/saída).
- `model`: entidade JPA.

## Endpoints Disponíveis

### POST `/encurtar`

Encurta uma URL original.

**Requisição JSON:**
```
{
  "originalUrl": "https://www.example.com"
}
```

**Resposta:**
```
{
  "originalUrl": "https://www.example.com",
  "shortUrl": "http://localhost:8080/AbC123xY"
}
```

### GET `/{hash}`

Redireciona para a URL original a partir do hash.

Exemplo:  
`GET http://localhost:8080/AbC123xY` → Redireciona para `https://www.example.com`

## Como Executar

1. Configure o banco MySQL com um schema chamado `urlshortener`.
2. Altere o arquivo `application.yml` com suas credenciais de acesso ao banco.
3. Rode o projeto com `mvn spring-boot:run` ou diretamente com sua IDE.
4. Acesse a documentação Swagger: `http://localhost:8080/swagger-ui.html`

## Segurança

- Validação de URL com `@Valid` e `@URL`
- Geração segura de hash com `SecureRandom`
- Redirecionamento seguro via HTTP 302
- Proteção automática contra SQL Injection via JPA

## Autor

Desenvolvido por João G Amaral Java com foco em boas práticas e arquitetura limpa.
