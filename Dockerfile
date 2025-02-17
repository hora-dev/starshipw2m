# Usar una imagen de OpenJDK para ejecutar Spring Boot
FROM openjdk:21-jdk-slim AS build

# Instalar Maven
RUN apt-get update && apt-get install -y maven

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar los archivos de tu proyecto al contenedor
COPY . .

# Ejecutar mvn clean package para compilar y empaquetar la aplicación
RUN mvn clean package

# Usar una imagen de OpenJDK más ligera para ejecutar la aplicación
FROM openjdk:21-jdk-slim

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el JAR de la aplicación al contenedor
COPY --from=build /app/target/starshipw2m-1.0.0-SNAPSHOT.jar app.jar

# Exponer el puerto en el que corre Spring Boot
EXPOSE 8091

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]