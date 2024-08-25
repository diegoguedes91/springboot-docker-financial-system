# Financial System API

## Descrição

Este é um sistema simples de gerenciamento financeiro que permite o cadastro de usuários e a realização de transações financeiras entre eles. O sistema é construído usando Spring Boot, MySQL, RabbitMQ, Docker, e Flyway para gerenciamento de migrações de banco de dados. A Swagger UI está integrada para documentação e testes das APIs.

## Funcionalidades

- **Cadastro de Usuários:**
  - Endpoint para cadastrar novos usuários.
  - Endpoint para listar todos os usuários.

- **Transações Financeiras:**
  - Utiliza RabbitMQ para realizar transações financeiras entre usuários.
  - Endpoint para listar transações financeiras.

## Tecnologias Utilizadas

- **Spring Boot** - Framework principal para a construção da aplicação.
- **MySQL** - Banco de dados relacional para armazenar os dados dos usuários e transações.
- **RabbitMQ** - Mensageria para processar transações financeiras de forma assíncrona.
- **Flyway** - Gerenciamento de migrações de banco de dados.
- **Docker & Docker Compose** - Containerização e orquestração dos serviços.
- **Swagger UI** - Documentação e interface para teste das APIs.

## Pré-requisitos

- Docker e Docker Compose instalados na máquina.

## Instruções para Rodar o Projeto

1. **Clone o repositório:**

   ```
   git clone https://github.com/seu-usuario/financial-system.git
   cd financial-system
   ```

2. **Build e subir os containers:**

    Na raiz do projeto, execute o comando abaixo para construir e iniciar os containers Docker:

    ```
    docker-compose upbash --build
    ```
    
    Este comando irá:

    - Construir a aplicação Java.
    - Iniciar o MySQL e RabbitMQ.
    - Iniciar a aplicação Spring Boot.
    
3. **Acessar a Swagger UI:**

    Após os containers estarem em execução, você pode acessar a Swagger UI para explorar e testar os endpoints:
    
    ```
    http://localhost:8080/swagger-ui/index.html
    ```
    
4. **Testando a aplicação:**


    - **Cadastro de Usuários:**
    
        - POST /users - Cria um novo usuário.
        - GET /users - Lista todos os usuários.
    
    - **Transações Financeiras:**
    
        - POST /transactions - Realiza uma transação financeira entre dois usuários.
        - GET /transactions - Lista todas as transações financeiras.