# Utilisez l'image OpenJDK comme image de base
FROM openjdk:11-jre-slim

# Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Copier les fichiers de votre application dans le conteneur
COPY . /app

# Commande pour exécuter votre application Java
CMD ["java", "-jar", "proxy.jar"]
