# Utiliser a base officielle de debian comme image parent
FROM debian:latest

# Mettre le répertoire de travail dans le conteneur
WORKDIR /app

# Copie le code du répertoire serveur du projet dans le conteneur à partire du répertoire de l'hote
COPY ./serveur /app

# Compilation des fichiers java du répertoire de travail
RUN javac *.java

# Le serveur va lancer cette commande par défaut
CMD ["java", "Serveur 9000"]

# Ouverture du port 9000
EXPOSE 9000
