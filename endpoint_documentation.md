# API Endpoint Documentation

## Pool Endpoints

Base URL: `/api/v1/pools`

> All endpoints require authentication. Allowed roles: `USER`, `ADMINISTRATOR`.

---

### Endpoints

| Method | URL Example | Request Body | Response |
|--------|-------------|--------------|----------|
| `GET` | `/api/v1/pools?start=0&off=20` | None | Paginated `PoolResponseOnly` (see below) |
| `GET` | `/api/v1/pools?start=0&off=20&filter=title:LIKE:savings&filter=status:EQ:ACTIVE` | None | Paginated `PoolResponseOnly` with filters applied |
| `GET` | `/api/v1/pools/{id}` | None | Single `PoolResponseOnly` object |
| `POST` | `/api/v1/pools` | `NewPoolRequest` (see below) | Created `PoolResponseOnly` object |

---

### Query Parameters — `GET /api/v1/pools`

| Parameter | Type | Required | Default | Description |
|-----------|------|----------|---------|-------------|
| `start` | `int` | No | `0` | Page number (zero-based) |
| `off` | `int` | No | `20` | Page size (number of records per page) |
| `filter` | `string[]` | No | — | Filter expressions in the format `field:OPERATOR:value`. Repeatable. Supported operators: `LIKE`, `EQ` |

---

### Request Body — `POST /api/v1/pools`

`NewPoolRequest`

```json
{
  "title": "string",
  "poolAmount": 1000.00,
  "minParticipants": 5,
  "maxParticipants": 20,
  "description": "string",
  "dayPayments": 30
}
```

> `poolAdmin` is resolved automatically from the JWT token in the `Authorization` header. All fields are optional.

---

### Response Body — `GET /api/v1/pools` and `GET /api/v1/pools/{id}`

`poolAdmin` is not included in these responses.

```json
{
  "id": "uuid",
  "title": "string",
  "poolAmount": 1000.00,
  "minParticipants": 5,
  "maxParticipants": 20,
  "description": "string",
  "status": "RECRUITING | ...",
  "dayPayments": 30,
  "poolAdmin": null
}
```

### Response Body — `POST /api/v1/pools`

`poolAdmin` is included with a partial `UserResponse` (only `id`, `email`, and `fullname`).

```json
{
  "id": "uuid",
  "title": "string",
  "poolAmount": 1000.00,
  "minParticipants": 5,
  "maxParticipants": 20,
  "description": "string",
  "status": "RECRUITING | ...",
  "dayPayments": 30,
  "poolAdmin": {
    "id": "uuid",
    "email": "string",
    "fullname": "string"
  }
}
```

Paginated responses (`GET /api/v1/pools`) wrap the above in a Spring `Page` envelope:

```json
{
  "content": [ /* array of PoolResponseOnly */ ],
  "totalElements": 100,
  "totalPages": 5,
  "size": 20,
  "number": 0,
  "first": true,
  "last": false
}
```
