# Utiliser l'image de base OpenJDK 17
FROM openjdk:17

# Exposer le port sur lequel l'application s'exécutera
EXPOSE 8086

# Copier le fichier .jar dans l'image et le renommer pour plus de simplicité
ADD target/userManagement-0.0.1-SNAPSHOT.jar usermicroservice.jar

# Définir le point d'entrée pour exécuter l'application
ENTRYPOINT ["java", "-jar", "usermicroservice.jar"]
