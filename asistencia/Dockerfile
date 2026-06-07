# Usamos una imagen base ligera con Java 17
FROM eclipse-temurin:17-jre-jammy

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos el archivo .jar generado por Maven (ajusta el nombre si es necesario)
COPY target/*.jar app.jar

# Exponemos el puerto (asegúrate de que coincida con tu application.properties)
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]