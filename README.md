
# Forkina 🍴
**Plateforme de gestion de projets académiques**

---

## 📚 Description du Mini-Projet  
Forkina est une application web distribué dédié à la gestion de projets intégrés. L'application gère les utilisateurs, les projets, les tâches, les milestones et les fichiers associés. Elle permet de suivre l'avancement des projets, d'attribuer des tâches spécifiques, de gérer les milestones et de partager des fichier entre les membres de l'équipe.
Un module de chat facilitera la communication et la collaboration en temps réel.

### 🎯 Objectifs Clés  
- Implémenter une architecture microservices avec **Spring Boot**.  
- Assurer la communication entre les microservices via **Eureka Discovery Server** et **API Gateway**.  
- Gérer différentes entités de manière indépendante avec des **bases de données adaptées**.  

---

## 🏗️ Architecture  

L’application est composée de plusieurs microservices indépendants :  

### 🔹 Microservices Individuels  

- **Gestion des Utilisateurs** (Habib) → MySQL  
- **Gestion des Projets** (Hamza) → H2  
- **Gestion des Tâches** (Firdaous) → H2  
- **Gestion des Jalons** (Melek) → MySQL  
- **Gestion des Fichiers** (Nada) → MySQL  

### 🔹 Microservice d'Équipe  

- **Gestion du Chat en Temps Réel**
  → Développé avec Node.js et WebSocket pour une communication instantanée.
  Utilise MongoDB pour stocker l’historique des messages et les métadonnées des conversations.

Tous les services sont orchestrés via **Eureka Discovery Server** et sécurisés par une **API Gateway**.  

---

## 💻 Technologies Utilisées  

### 🛠 Backend  
- **Spring Boot** (développement des microservices)  
- **Spring Cloud Eureka** (service de découverte)  
- **Spring Cloud Gateway** (passerelle API)  
- **Spring Data JPA** (gestion des bases de données)  
- **H2 / MySQL** (stockage des données)  

### 🎨 Frontend  
- **React.js** (interface utilisateur)  


### 📌 Outils de Gestion  

- **Git & GitHub** (gestion de version et collaboration)  
- **Docker** (conteneurisation des services)
- **Keycloak** (sécurisation de l'API Gateway) 



**🎓 Projet académique réalisé par Madinalab**  
**Encadré par [Madame Elmejid Ines]**  
