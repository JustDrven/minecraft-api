# Minecraft API

Easy API for getting user's data. The API uses PostgreSQL to store the data.

### Endpoints:

| Type | EndPoint                 | Description                  |
|------|--------------------------|------------------------------|
| POST | /api/v1/user             | Insert user into the database |
| GET  | /api/v1/user/list        | Return users in json array   |
| GET  | /api/v1/user/nick/{nick} | Return user by his nickname  |
| GET  | /api/v1/user/uuid/{nick} | Return user by his uuid      |

### Security:

The API is protected only by the API Key

<br>

Written by JustDrven