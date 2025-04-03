# Utilisation de l'image Java 11 comme base
FROM openjdk:17

# Copier le fichier JAR du microservice Eureka dans le conteneur
COPY target/Eureka-0.0.1-SNAPSHOT.jar eureka-server.jar

# Exposer le port 8761 (port par défaut d'Eureka)
EXPOSE 8761

# Exécuter l'application Eureka
CMD ["java", "-jar", "eureka-server.jar"]
