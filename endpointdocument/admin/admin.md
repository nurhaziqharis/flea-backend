# Admin Endpoints

> All endpoints require authentication. Allowed roles: `ROLE_Admin` only.

---

## Ping

Base URL: `/api/v1/admin`

### Endpoints

| Method | URL | Request Body | Response | Status |
|--------|-----|--------------|----------|--------|
| `GET` | `/api/v1/admin/ping` | None | `PingResponse` (see below) | `200 OK` |

### Response Body — `GET /api/v1/admin/ping`

```json
{
  "message": "pong",
  "principal": "string"
}
```

| Field | Description |
|-------|-------------|
| `message` | Always `"pong"` |
| `principal` | The username/email of the authenticated admin |

---

## Users

Base URL: `/api/v1/admin/users`

### Endpoints

| Method | URL | Request Body | Response | Status |
|--------|-----|--------------|----------|--------|
| `GET` | `/api/v1/admin/users` | None | `UserResponse[]` (see below) | `200 OK` |
| `POST` | `/api/v1/admin/users` | `CreateUserRequest` (see below) | `CreateUserResponse` (see below) | `201 Created` |
| `PATCH` | `/api/v1/admin/users/{id}/roles` | `AssignRolesRequest` (see below) | `UserResponse` (see below) | `200 OK` |
| `DELETE` | `/api/v1/admin/users/{id}` | None | No body | `204 No Content` |

### Request Body — `POST /api/v1/admin/users`

`CreateUserRequest`

```json
{
  "email": "string",
  "username": "string",
  "password": "string",
  "roles": ["string"]
}
```

| Field | Type | Constraints |
|-------|------|-------------|
| `email` | `string` | Required, valid email format |
| `username` | `string` | Required, 3–50 characters |
| `password` | `string` | Required, minimum 8 characters |
| `roles` | `string[]` | Required, at least one role |

### Request Body — `PATCH /api/v1/admin/users/{id}/roles`

`AssignRolesRequest`

```json
{
  "roles": ["string"]
}
```

| Field | Type | Constraints |
|-------|------|-------------|
| `roles` | `string[]` | Required, at least one role |

### Response Body — `GET /api/v1/admin/users` (list item) and `PATCH /api/v1/admin/users/{id}/roles`

`UserResponse`

```json
{
  "id": "uuid",
  "email": "string",
  "username": "string",
  "roles": ["string"],
  "createdAt": "ISO-8601 timestamp",
  "updatedAt": "ISO-8601 timestamp"
}
```

### Response Body — `POST /api/v1/admin/users`

`CreateUserResponse`

```json
{
  "id": "uuid",
  "email": "string",
  "username": "string",
  "roles": ["string"],
  "createdAt": "ISO-8601 timestamp",
  "updatedAt": "ISO-8601 timestamp",
  "wallet": {
    "id": "uuid",
    "amount": 0.00
  }
}
```

---

## Roles

Base URL: `/api/v1/admin/roles`

### Endpoints

| Method | URL | Request Body | Response | Status |
|--------|-----|--------------|----------|--------|
| `GET` | `/api/v1/admin/roles` | None | `RoleResponse[]` (see below) | `200 OK` |
| `POST` | `/api/v1/admin/roles` | `CreateRoleRequest` (see below) | `RoleResponse` (see below) | `201 Created` |

### Request Body — `POST /api/v1/admin/roles`

`CreateRoleRequest`

```json
{
  "name": "string"
}
```

| Field | Type | Constraints |
|-------|------|-------------|
| `name` | `string` | Required, 2–50 characters |

### Response Body — Both Endpoints

`RoleResponse`

```json
{
  "id": "uuid",
  "name": "string"
}
```
