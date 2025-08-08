# Desafio Técnico - Microsserviços NTT DATA

![Java](https://img.shields.io/badge/java-%23007396.svg?style=for-the-badge&logo=java&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Spring Boot](https://img.shields.io/badge/SpringBoot-F2F4F9?style=for-the-badge&logo=spring-boot)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)
![Microservices](https://img.shields.io/badge/Microservices-yellow?style=for-the-badge)
![REST API](https://img.shields.io/badge/REST_API-0099FF?style=for-the-badge)

## 📄 Introdução

Este projeto é uma solução para o desafio técnico da NTT DATA, focado no desenvolvimento de uma aplicação baseada em microsserviços. A aplicação simula um sistema de gestão de pedidos com um catálogo de produtos, utilizando uma arquitetura moderna com Spring Boot, Spring Cloud Eureka e Spring Cloud Gateway.

O objetivo é demonstrar a implementação de conceitos como **Service Discovery**, **API Gateway** e comunicação entre serviços, além de boas práticas de desenvolvimento de APIs REST.

## 🏛️ Arquitetura da Solução

A arquitetura do projeto é dividida em quatro componentes principais, orquestrados para trabalhar em conjunto:

* **`eureka-server`**: O servidor de Service Discovery (Eureka Server), responsável por registrar a localização de todos os outros microsserviços. É o cérebro da nossa infraestrutura.
* **`microservice-products`**: Microsserviço para o Catálogo de Produtos. Ele gerencia as operações de CRUD de produtos e utiliza um banco de dados em memória H2 para persistência.
* **`microservice-orders`**: Microsserviço para a simulação de Pedidos. Ele se comunica com o `microservice-products` para buscar informações de produtos e simular a criação de um pedido. Não possui persistência própria.
* **`api-gateway`**: O ponto de entrada único para a aplicação. Ele é responsável por rotear as requisições externas para os microsserviços corretos, além de centralizar a lógica de autenticação.

O fluxo de uma requisição se dá da seguinte forma: o cliente envia uma requisição para o **API Gateway**, que consulta o **Eureka Server** para encontrar o endereço do microsserviço de destino e, então, a redireciona.

## 🛠️ Tecnologias Utilizadas

* **Spring Boot**: Framework principal para o desenvolvimento de todos os microsserviços.
* **Spring Cloud Eureka**: Implementação do padrão Service Discovery.
* **Spring Cloud Gateway**: Utilizado para criar o API Gateway de forma reativa e eficiente.
* **Spring Data JPA**: Abstração para a camada de persistência.
* **H2 Database**: Banco de dados em memória para o microsserviço de produtos.
* **Lombok**: Biblioteca para reduzir código repetitivo (getters, setters, etc.).
* **Maven**: Ferramenta de automação de construção e gerenciamento de dependências.

## ⚙️ Como Executar o Projeto

Para rodar a aplicação, siga a ordem correta de inicialização dos serviços.

### Pré-requisitos
* Java JDK 17+
* Maven 3.6+
* Uma IDE (como IntelliJ IDEA ou VS Code)
* Git

### Passos
1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/EnzoLFranco/Desafio-NTT-Data.git
    cd Desafio-NTT-Data
    ```
2.  **Compile os projetos:**
    Navegue até a pasta de cada microsserviço e execute o comando:
    ```bash
    # Exemplo:
    cd eureka-server
    mvn clean install
    # Repita para os outros projetos
    ```
3.  **Inicie os serviços na ordem correta:**
    * **1º Eureka Server**: Rode a classe principal `EurekaServerApplication.java` (porta `8761`).
    * **2º Microsserviço de Catálogo**: Rode `MicroserviceProductsApplication.java` (porta `8100`).
    * **3º Microsserviço de Pedidos**: Rode `MicroserviceOrdersApplication.java` (porta `8200`).
    * **4º API Gateway**: Rode `ApiGatewayApplication.java` (porta `8700`).

## 🚀 Testando a API

O API Gateway implementa uma autenticação simples via cabeçalho `Authorization: Bearer <token>`. O token fixo usado é: `seu-token-secreto-aqui`.

### Passo 1: Verificar o Eureka Dashboard
Acesse `http://localhost:8761/` no seu navegador para ver se todos os serviços foram registrados.

### Passo 2: Criar um Produto
Requisição via Postman para criar um novo produto. O status `201 Created` e o corpo da resposta com os detalhes do produto, incluindo um ID gerado, indicam sucesso.

### Passo 3: Listar Produtos
Requisição para listar todos os produtos. O status `200 OK` e o corpo da resposta em formato JSON contendo a lista dos produtos cadastrados confirmam o sucesso da operação.

### Passo 4: Criar um Pedido
Requisição para simular um pedido. O status `200 OK` e uma mensagem de sucesso no corpo da resposta indicam que o microsserviço de pedidos se comunicou corretamente com o de produtos e processou a simulação.

### Passo 5: Teste de Autenticação
Requisição sem o cabeçalho `Authorization`. O status de erro `401 Unauthorized` na resposta confirma que o filtro de segurança do API Gateway está funcionando e bloqueando requisições não autenticadas.