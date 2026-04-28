# Utiliser a base officielle de debian comme image parent
FROM debian:latest

# Mettre le répertoire de travail dans le conteneur
WORKDIR /app

# Copie le code du serveur dans le conteneur
COPY serveur /app

# Compile les fichiers java
RUN javac *.java

# Le serveur va lancer cette commande par défaut
CMD ["java", "Serveur 9000"]
