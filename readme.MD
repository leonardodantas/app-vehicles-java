# Vehicle API

API RESTful para gerenciamento de veículos com Spring Boot e MongoDB, incluindo endpoints para criação, atualização, listagem, deleção e filtros avançados.

---

## 🔧 Stack

- Java 17+
- Spring Boot
- MongoDB
- Swagger/OpenAPI 3
- Docker (MongoDB e Mongo Express)

---

## 📘 Estrutura dos Endpoints

### 📌 Base URL

```
/v1/vehicles
```

---

### ✅ Criar um veículo

**POST /v1/vehicles**

Cria um novo veículo.

#### Request Body (`VehicleRequest`):

```json
{
  "mark": "Ford",
  "year": 2000,
  "model": "Fiesta",
  "description": "Compacto, econômico e com ar-condicionado",
  "sold": false
}
```

#### Response 201:

`VehicleResponse`

---

### 🔍 Buscar veículos (com filtros)

**GET /v1/vehicles**

#### Parâmetros:

- `available`: (opcional) `s` ou `n`
- `decade`: (opcional) década do veículo, ex: 1990
- `mark`: (opcional) marca do veículo
- `page`: default `0`
- `size`: default `20`

#### Response 200:

Lista paginada de `VehicleResponse`

---

### 🕒 Buscar veículos da última semana

**GET /v1/vehicles/last-week**

Retorna veículos cadastrados nos últimos 7 dias.

#### Parâmetros:

- `page`: default `0`
- `size`: default `20`

---

### ✏️ Atualizar um veículo completamente

**PUT /v1/vehicles/{id}**

Substitui todos os dados do veículo com o `id` especificado.

#### Request Body: `VehicleRequest`

#### Response: `VehicleResponse`

---

### 🩹 Atualizar parcialmente (descrição e vendido)

**PATCH /v1/vehicles/{id}**

Atualiza apenas a descrição e o status de venda do veículo.

#### Request Body: `VehicleUpdateRequest`

```json
{
  "description": "Novo texto",
  "sold": true
}
```

---

### 🗑️ Deletar veículo

**DELETE /v1/vehicles/{id}**

Deleta o veículo com o ID informado.

#### Response 200:

Sem corpo de resposta.

---

## 📤 Exemplo de `VehicleResponse`

```json
{
  "id": "661f7e9be91cc909bfe9db7c",
  "mark": "Ford",
  "year": 2000,
  "model": "Fiesta",
  "description": "Compacto, econômico e com ar-condicionado",
  "sold": "false"
}
```

---

## ❗ Tratamento de Erros

### `ErrorResponse`

```json
{
  "timestamp": "2025-04-29T21:58:00",
  "status": 400,
  "message": "Campo 'mark' é obrigatório",
  "path": "/v1/vehicles"
}
```

---

## 🧪 Swagger UI

Acesse via:

```
http://localhost:8080/swagger-ui.html
```

---

## 🐳 Docker MongoDB

Exemplo no `docker-compose.yml`:

```yaml
services:
  mongo:
    image: mongo:4.4
    ports:
      - 27017:27017
    environment:
      - MONGO_INITDB_ROOT_USERNAME=leonardo
      - MONGO_INITDB_ROOT_PASSWORD=123456
  mongo-express:
    image: mongo-express
    ports:
      - "8081:8081"
    environment:
      ME_CONFIG_MONGODB_ADMINUSERNAME: leonardo
      ME_CONFIG_MONGODB_ADMINPASSWORD: 123456
      ME_CONFIG_MONGODB_SERVER: mongo
```

---

## 📦 MongoDB URI

Exemplo:

```yaml
spring:
  data:
    mongodb:
      uri: mongodb://leonardo:123456@localhost:27017/vehicles?authSource=admin
```

---

## 📬 Testes via Postman

### 📥 Importação

- Clique em `Import` no Postman.
- Escolha o tipo `Raw text` e cole um dos exemplos abaixo.
- Altere a URL base conforme seu ambiente (`localhost:8080`).

### Criar um veículo

```
POST http://localhost:8080/v1/vehicles
Content-Type: application/json

{
  "mark": "Ford",
  "year": 2000,
  "model": "Fiesta",
  "description": "Compacto, econômico",
  "sold": false
}
```

### Buscar veículos com filtro por década e marca

```
GET http://localhost:8080/v1/vehicles?decade=1990&mark=Ford&page=0&size=10
```

### Buscar veículos da última semana

```
GET http://localhost:8080/v1/vehicles/last-week?page=0&size=10
```

### Atualizar um veículo

```
PUT http://localhost:8080/v1/vehicles/{id}
Content-Type: application/json

{
  "mark": "Fiat",
  "year": 2010,
  "model": "Palio",
  "description": "Compacto atualizado",
  "sold": true
}
```

### Atualização parcial

```
PATCH http://localhost:8080/v1/vehicles/{id}
Content-Type: application/json

{
  "description": "Novo texto",
  "sold": false
}
```

### Deletar veículo

```
DELETE http://localhost:8080/v1/vehicles/{id}
```

---


