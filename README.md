# docker-sae203

## Morpion

### Description

Ce projet est un jeu de Morpion qui utilise docker afin de permettre à deux joueurs de s'affronter.

---

### Instructions de lancement

- Construire l'image décrite dans le Dockerfile :
```shell
docker build -t morpion
```

- Lancer l'image en exposant le port 9000 :
```shell
docker run --rm -p <port hôte>:9000 morpion
```
