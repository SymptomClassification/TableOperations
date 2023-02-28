FROM openjdk:17-oracle

WORKDIR /app

COPY target ./target

CMD ["java", "-jar", "target/SymptomSubtitle-0.0.1-SNAPSHOT.jar"]

