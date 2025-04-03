# Utiliser l'image de base OpenJDK 17
FROM openjdk:17

# Exposer le port 8085 (port de ton microservice)
EXPOSE 8084

# Copier le fichier .jar dans l'image
ADD target/serviceProjecthamzaMbarki-0.0.1-SNAPSHOT.jar projectmicroservice.jar

# Définir le point d’entrée pour exécuter l’application
ENTRYPOINT ["java", "-jar", "projectmicroservice.jar"]
