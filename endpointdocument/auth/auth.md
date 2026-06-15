# Auth Endpoints

Base URL: `/api/v1/auth`

> No authentication required. These endpoints are publicly accessible.

---

### Endpoints

| Method | URL | Request Body | Response | Status |
|--------|-----|--------------|----------|--------|
| `POST` | `/api/v1/auth/register` | `RegisterRequest` (see below) | `AuthResponse` (see below) | `200 OK` |
| `POST` | `/api/v1/auth/login` | `LoginRequest` (see below) | `AuthResponse` (see below) | `200 OK` |

---

### Request Body — `POST /api/v1/auth/register`

`RegisterRequest`

```json
{
  "email": "string",
  "username": "string",
  "password": "string"
}
```

| Field | Type | Constraints |
|-------|------|-------------|
| `email` | `string` | Required, valid email format |
| `username` | `string` | Required, 3–50 characters |
| `password` | `string` | Required, minimum 8 characters |

---

### Request Body — `POST /api/v1/auth/login`

`LoginRequest`

```json
{
  "email": "string",
  "password": "string"
}
```

| Field | Type | Constraints |
|-------|------|-------------|
| `email` | `string` | Required |
| `password` | `string` | Required |

---

### Response Body — Both Endpoints

`AuthResponse`

```json
{
  "access_token": "string",
  "refresh_token": "string"
}
```

| Field | Description |
|-------|-------------|
| `access_token` | JWT bearer token to use in `Authorization` header for protected endpoints |
| `refresh_token` | Token used to obtain a new access token when it expires |
