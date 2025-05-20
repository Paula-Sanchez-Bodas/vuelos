# Usar una imagen base con Java
FROM openjdk:17-jdk-alpine

# Copiar el JAR generado al contenedor
COPY target/vuelo-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto que usa Spring Boot (por defecto 8080)
EXPOSE 8080

# Ejecutar la app
ENTRYPOINT ["java","-jar","/app.jar"]
