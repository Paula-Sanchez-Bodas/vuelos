# Etapa 1: Build con Maven
FROM maven:3.8.5-eclipse-temurin-17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package

# Etapa 2: Ejecutar el .jar generado
FROM eclipse-temurin:17
WORKDIR /app
COPY --from=builder /app/target/vuelo-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
