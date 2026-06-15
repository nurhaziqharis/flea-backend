# Participator Endpoints

Base URL: `/api/v1/participator`

> All endpoints require authentication. Allowed roles: `USER`, `ADMINISTRATOR`.

---

### Endpoints

| Method | URL Example | Request Body | Response |
|--------|-------------|--------------|----------|
| `GET` | `/api/v1/participator?start=0&off=20` | None | Paginated `ParticipatorResponseBase` (see below) |
| `GET` | `/api/v1/participator/{id}` | None | Single `ParticipatorResponseBase` object |
| `POST` | `/api/v1/participator` | `NewParticipatorRequest` (see below) | Created `ParticipatorResponseBase` object |

---

### Query Parameters — `GET /api/v1/participator`

| Parameter | Type | Required | Default | Description |
|-----------|------|----------|---------|-------------|
| `start` | `int` | No | `0` | Page number (zero-based) |
| `off` | `int` | No | `20` | Page size (number of records per page) |
| `filter` | `string[]` | No | — | Filter expressions in the format `field:OPERATOR:value`. Repeatable. Supported operators: `LIKE`, `EQ` |

---

### Request Body — `POST /api/v1/participator`

`NewParticipatorRequest`

```json
{
  "username": "string",
  "poolTitle": "string",
  "isAgree": true,
  "poolId": "uuid",
  "userId": "uuid",
  "turn": 1
}
```

| Field | Type | Description |
|-------|------|-------------|
| `username` | `string` | Username of the participator |
| `poolTitle` | `string` | Title of the pool to join |
| `isAgree` | `boolean` | Whether the user agrees to the pool terms |
| `poolId` | `string (uuid)` | ID of the pool to join |
| `userId` | `string (uuid)` | ID of the user participating |
| `turn` | `integer` | Turn number for the participator within the pool (must be unique per pool) |

---

### Response Body — All Endpoints

`ParticipatorResponseBase`

```json
{
  "username": "string",
  "poolTitle": "string",
  "isAgree": true,
  "turn": 1,
  "participatorPool": {
    "id": "uuid",
    "title": "string",
    "poolAmount": 1000.00,
    "minParticipants": 5,
    "maxParticipants": 20,
    "description": "string",
    "status": "RECRUITING | ACTIVE | COMPLETED",
    "dayPayments": 30,
    "poolAdmin": null
  },
  "participatorUser": {
    "id": "uuid",
    "email": "string",
    "username": "string",
    "fullname": "string",
    "identityNumber": "string",
    "gender": "MALE | FEMALE",
    "phoneNumber": "string",
    "birthDate": "YYYY-MM-DD",
    "lastLogin": "YYYY-MM-DDTHH:mm:ss",
    "isVerified": false,
    "isBanned": false
  }
}
```

Paginated responses (`GET /api/v1/participator`) wrap the above in a Spring `Page` envelope:

```json
{
  "content": [ /* array of ParticipatorResponseBase */ ],
  "totalElements": 100,
  "totalPages": 5,
  "size": 20,
  "number": 0,
  "first": true,
  "last": false
}
```
