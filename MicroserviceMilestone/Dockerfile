# Utiliser l'image de base OpenJDK 17
FROM openjdk:17

# Exposer le port 8085 (port de ton microservice)
EXPOSE 8085

# Copier le fichier .jar dans l'image
ADD target/MicroserviceMilestone-0.0.1-SNAPSHOT.jar milestonemicroservice.jar

# Définir le point d’entrée pour exécuter l’application
ENTRYPOINT ["java", "-jar", "milestonemicroservice.jar"]
