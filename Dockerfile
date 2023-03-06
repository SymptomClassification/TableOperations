FROM eclipse-temurin:17-jdk-focal

WORKDIR /app

COPY target ./target

CMD ["java", "-jar", "target/TableOperations-0.0.1-SNAPSHOT.jar"]

