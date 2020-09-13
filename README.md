# API LDAP

Api to create, list, find and delete entry from ldap service.

## Installation

Set your ip or host in PATHLDAP.

```Dockerfile
- PATHLDAP=YOUR_HOST
```

To run this aplication, just use docker compose.

```bash
docker-compose up -d
```

After run the docker-compose, create a User in ldap

```bash
ldapmodify -h localhost -p 389 -w '123456' -D 'cn=admin,dc=techinterview,dc=com' <create_ou_users.ldif>
```

## Documentation

For access the documentation, go to http://localhost:8080/swagger-ui.html