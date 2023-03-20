# Table Operations

This repository contains the source code for managing the persistent data of the symptom classifications, i.e., chapters and subchapters and their structure.

To start the microservice, you'll need to:
- fullfil the prerequisites
- export the configuration of your local database
- build and start this microservice.

The steps are specified in the following.

## Prerequisites
Please install the following:
- Java JDK 17
- Docker
- Docker Compose

## Exporting the database configuration
A mysql database should be configured via `$DB_URL`, `$DB_USERNAME` and `$DB_PASSWORD.`, e.g.:
```bash
export DB_URL="jdbc:mysql://mysql-docker-container:3306/classification?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&useLegacyDatetimeCode=false"
export DB_USERNAME="ahmet"
export DB_PASSWORD="mypass"
```

## Building and Starting

To build and start the microservice, do:
```bash
./mvnw test
./mvnw clean package
docker compose up
```
# After the application is up and running
- You can access the Swagger UI of application at http://localhost:8098/swagger-ui.html/index.html
