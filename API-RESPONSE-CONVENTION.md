# API Response Convention

## 1. Versioning

All public endpoints use the `/api/v1` prefix.

Example:

```text
GET /api/v1/flowers
```

## 2. Success responses

Single-resource responses return the resource directly.

```json
{
  "id": 1,
  "name": "Atirgul",
  "price": 50000
}
```

Use these HTTP statuses:

| Status | Usage |
|---|---|
| `200 OK` | A resource was read or updated successfully |
| `201 CREATED` | A resource was created successfully |
| `204 NO CONTENT` | A resource was deleted successfully |

## 3. Error responses

All errors use the existing `ApiErrorResponse` format:

```json
{
  "timestamp": "2026-09-14T12:00:00+05:00",
  "status": 400,
  "error": "VALIDATION_ERROR",
  "message": "Request ma'lumotlari noto'g'ri",
  "path": "/api/v1/flowers",
  "details": {
    "name": "Name bo'sh bo'lishi mumkin emas"
  }
}
```

Use these HTTP statuses:

| Status | Usage |
|---|---|
| `400 BAD REQUEST` | Invalid request data |
| `401 UNAUTHORIZED` | Authentication is required or invalid |
| `403 FORBIDDEN` | The user has no permission |
| `404 NOT FOUND` | The requested resource does not exist |
| `409 CONFLICT` | The request conflicts with current data |
| `500 INTERNAL SERVER ERROR` | Unexpected server error |

## 4. Pagination

Collection endpoints use zero-based `page` and `size` query parameters.

```text
GET /api/v1/flowers?page=0&size=20
```

Default values:

```text
page = 0
size = 20
maximum size = 100
```

Paginated responses use this format:

```json
{
  "content": [],
  "page": 0,
  "size": 20,
  "totalElements": 100,
  "totalPages": 5,
  "first": true,
  "last": false
}
```

## 5. Sorting

Collection endpoints support the `sort` query parameter:

```text
GET /api/v1/flowers?sort=price,asc
```

The format is `field,direction`. Supported directions are `asc` and `desc`.
Only explicitly allowed fields may be used for sorting.
