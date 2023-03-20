```bash
export DB_URL="jdbc:mysql://mysql-docker-container:3306/classification?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&useLegacyDatetimeCode=false"
export DB_USERNAME="ahmet"
export DB_PASSWORD="ahmet
```

```bash
./mvnw test
./mvnw clean package
docker compose up
```

# Pre-requisites
- Java JDK 17
- Docker
- Docker Compose