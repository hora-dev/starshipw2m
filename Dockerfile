# Usar una imagen de OpenJDK para ejecutar Spring Boot
FROM openjdk:21-jdk-slim

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el JAR de la aplicación al contenedor
COPY target/starshipw2m-1.0.0-SNAPSHOT.jar app.jar

# Exponer el puerto en el que corre Spring Boot
EXPOSE 8091

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]
