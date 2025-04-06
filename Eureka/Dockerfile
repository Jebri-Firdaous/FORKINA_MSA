# Utilisation de l'image Java 17 comme base
FROM openjdk:17

# Copier le fichier JAR dans le conteneur
COPY target/Eureka-0.0.1-SNAPSHOT.jar eureka-server.jar

# Exposer le port Eureka
EXPOSE 8761

# Lancer l'application
CMD ["java", "-jar", "eureka-server.jar"]
