# Usa una imagen base de JDK de OpenJDK
FROM openjdk:17-jdk-alpine

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /com.distribuidos.notifications

# Copia el archivo JAR de tu aplicación a la imagen
COPY target/notifications-1.0.0.jar com.distribuidos.notifications.jar

# Define el comando que se ejecutará cuando el contenedor se inicie
ENTRYPOINT ["java", "-jar", "com.distribuidos.notifications.jar"]
