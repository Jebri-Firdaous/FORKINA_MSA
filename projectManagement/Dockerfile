FROM openjdk:17-slim



EXPOSE 8084
ADD target/projectManagement-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]