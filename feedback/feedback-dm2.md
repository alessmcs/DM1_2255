# Commentaires DM2

Corrigé par An Li

Total: 92%

## Description du système opérationnel [5/5]

Bien!

## 6 diagrammes d'activité UML [21/25]

- Flux principal
  - Séparer le menu en 2 (acheteur et revendeur)
  - Afficher les options du menu principal selon votre implémentation
- Passer une commande
  - Demander à l'acheteur s'il veut échanger ses points
- Évaluer un produit
  - Normalement, le commentaire est un champ de l'évaluation

## Diagramme de classes UML [17/20]

- Mettez votre diagramme de classes dans le dossier 'Conception'
- Manque un conteneur pour contenir les données, tels que les acheteurs, les revendeurs, les produits et les commandes
- La classe Produit devrait être abstraite
- L'auteur du commentaire devrait être un acheteur
- Placer les méthodes qui interagissent avec d'autres classes (e.g., echangerPoints, confirmerReceptionCommande, payerDifference, etc.) dans un contrôleur pour réduire le couplage
- Le nombre de points de base devrait être calculé automatiquement selon le prix (i.e., 1 point par $ arrondi à la baisse), mais il est possible de spécifier un nombre de points bonus pour tous les produits

## 5 diagrammes de séquence UML [22/25]

- Mettez vos diagrammes de classes dans le dossier 'Conception'
- Passer une commande
  - Demander à l'acheteur s'il veut échanger ses points
- Évaluer un produit
  - Vérifier que l'acheteur a acheté le produit avant de pouvoir évaluer
- Offrir un produit à vendre
  - Valider les informations du produit

## Code source Java du programme et fichier JAR [23/25]

- Mettez le code dans le dossier 'Implémentation'
- Éviter l'utilisation des boucles infinies while(true) pour l'affichage
- Manque option pour la déconnexion

## Bonus: Utilisation de GitHub [4/5]

- Pas d'Issues créés depuis le devoir 1
