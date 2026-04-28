# docker-sae203


## Liste des commandes échangées entre serveur & clients  

### Format :  
`[srv ou clt1 ou clt2]_[catégorie de commande]_[nom de la commande](_[contenu de la commande])`
Le contenu de la commande étant optionnel.

Exemple :
`clt1_morpi_dessinerrond_00`

### Catégories :

(écrit avec 5 lettres en minuscules)

systm : système  (connexion, erreur d'envoie/reception de commande)

morpi : commandes de jeu du morpion (pose des pièces, gagné, intialiser le morpion...)

---

### Comment l'état du Morpion est-il communiqué ? (XXXXXXXXX)
Parfois, il sera nécessaire de communiquer l'entièreté du morpion.

Cette information sera communiquer ainsi :  
"x" : croix  
"o" : rond  
"0" : case vide  
  
L'état initial d'un morpion sera communiqué sur une ligne de caractère qui dictera l'état du morpion en allant de gauche à droite et de haut en bas.

Un morpion vide sera alors encodé ainsi :  
`000000000`  

Un morpion avec une ligne haute remplis de croix ainsi :
```
xxx000000
=
xxx
000
000
```  
Un morpion avec une première colonne remplis de rond ainsi :
```
o00o00o00
=
o00
o00
o00
```

## Quand l'état d'un morpion doit être passer en commande, cela sera signifié ainsi :  
`_XXXXXXXXX`

---

### Comment les actions sur le Morpion sont communiquer ? (avec LC pour la ligne et la collonne)
Une action modifiant une case du morpion par l'un des clients sera encodé ainsi :
00 à 33
Premier chiffre pour la ligne
Deuxième chiffre pour la colonne

encodage signifié dans les commandes ainsi :

`_LC`

---

## Commandes :
(écrit tout en minuscule, les indications en majuscule sont expliquer ci-dessus.)


### Serveur :
```
srv_systm_attentejoueur_1
srv_systm_attentejoueur_2

srv_morpi_initmorpion
srv_morpi_checketatmorpion
srv_morpi_updatemorpion_XXXXXXXXX

srv_morpi_attactionj1
srv_morpi_attactionj2

srv_morpi_j1gagner
srv_morpi_j2gagner
```

### Client (avec X pour le num du client) :
```
cltX_systm_connexion
cltX_morpi_etatmorpion_XXXXXXXX (voir au dessus pour l'explications des XXXXXXXXX)
cltX_morpi_resychronise (pour recevoir l'état du morpion)

cltX_morpi_dessiner_LC (Ligne Collonne)
```
