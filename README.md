# Table Operations

This repository contains the source code for managing the persistent data of the symptom classifications, i.e., chapters and subchapters and their structure.

To start the microservice, you'll need to:
- fulfill the prerequisites
- export the configuration of your local database
- build and start this microservice.

The steps are specified in the following.

## Prerequisites
Please install the following:
- Java JDK 17
- Docker
- Docker Compose

## Building and Starting

To build and start the microservices, do:
### Create Docker Network
```bash
docker network create classificationnetwork
```

```bash
./mvnw test
./mvnw clean package
docker compose up 
```
# After the application is up and running
- You can access the Swagger UI of application at http://localhost:8098/swagger-ui.html/index.html
