# Desafio Técnico - Place TI

Este projeto consiste em uma API REST para o gerenciamento de cidades e comércios, desenvolvida como parte do processo seletivo para a unidade de Brasília/DF.

##  Tecnologias Utilizadas
* **Java 17**: Versão LTS utilizada para o desenvolvimento.
* **Spring Boot 3.2.5**: Framework base para a construção da API.
* **Spring Data JPA & H2 Database**: Para persistência de dados em memória.
* **Spring Validation**: Validação de campos obrigatórios e regras de negócio nos DTOs.
* **JUnit & Mockito**: Testes unitários para garantir a qualidade da camada de Service.
* **Lombok**: Para redução de código boilerplate.
*  **Tratamento de Exceções Centralizado:** Implementação de um @ControllerAdvice para capturar exceções de negócio (como CityNotFoundException) e erros de validação, garantindo que a API retorne mensagens claras e códigos HTTP apropriados em vez de stacktraces expostos.
##  Arquitetura
O projeto segue a estrutura de camadas proposta no desafio:
1. **Controller**: Exposição dos endpoints e recebimento de requisições.
2. **Service**: Implementação das regras de negócio e comunicação com o Repository.
3. **Repository**: Interface de abstração para o banco de dados.

Foi adotado o uso de **DTOs (Data Transfer Objects)** para a comunicação entre as camadas, evitando a exposição direta das entidades de banco de dados e garantindo maior segurança e flexibilidade.

##  Endpoints Implementados

### Cidades (`/cidades`)
* `GET /cidades`: Lista todas as cidades cadastradas.
* `GET /cidades/{id}`: Detalha uma cidade específica.
* `POST /cidades`: Cadastra uma nova cidade.
* `PUT /cidades`: Atualiza os dados de uma cidade.
* `DELETE /cidades/{id}`: Remove uma cidade do sistema.
* `GET /cidades/{id}/comercios`: Filtra comércios vinculados a uma cidade específica.

### Comércios (`/comercios`)
* `GET /comercios`: Lista todos os comércios.
* `POST /comercios`: Cria um novo comércio vinculado a uma cidade (valida existência da cidade).
* `PUT /comercios`: Altera dados de um comércio existente.
* `DELETE /comercios/{id}`: Remove um comércio (retorna 204 No Content).

## 🧪 Como Testar
1. Clone o repositório.
2. Certifique-se de ter o Maven e o Java 17 instalados.
3. Execute `mvn spring-boot:run`.