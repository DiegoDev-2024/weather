# Usa una imagen base con Java 17 preinstalado
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo JAR compilado desde build/libs al contenedor
COPY build/libs/weather-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que se ejecutará la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación cuando se inicie el contenedor
CMD ["java", "-jar", "app.jar"]