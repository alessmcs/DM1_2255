# Commentaires DM3

Corrigé par An Li

Total: 84%

Votre repo GitHub est mal structuré

- Mettez votre rapport dans le dossier 'Documentation'

## Code source Java du programme [38/45]

- Vos données sont dans src/main/resources, pas src/main/data
- Problèmes avec vos fichiers de données
  - Les fichiers csv Acheteur et Revendeur ne se mettent pas à jour quand ils modifient leur profil
  - Les données sont réinitialisées après chaque sortie
  - Un produit est ajouté deux fois au csv quand un vendeur offre un nouveau produit
- Incohérences et bogues
  - Pour retourner au catalogue, il faut entrer 'O' au lieu de '0' quand on veut voir les acheteurs
  - Le premier produit a l'id = 1, mais vous utilisez 1 pour retourner au menu précédent, ce qui rend impossible de sélectionner le produit avec id = 1
  - Le nombre de points à accumuler doit correspondre au total (1 point par $ arrondi vers le bas)
  - Boucle infinie lors de l'inscription quand l'acheteur essaie de mettre ses informations de carte de crédit
- Fonctionnalités manquantes
  - Un revendeur ne peut pas gérer ses produits

## Tests unitaires en JUnit [19/20]

Bien, mais évitez d'utiliser plusieurs fois System.out.println() pour une sortie qu'on doit assert, parce que dans Windows, chaque System.out.println() imprime '\r\n' à la fin de chaque string

## Configuration Maven [4/5]

Bien, mais ce ne sont pas tous les tests qui passent dans Maven

## Production du JAR [3/5]

- Vous avez généré un JAR, mais il n'est pas exécutable
  - Voir la [diapositive 27 de la démo 9](https://studium.umontreal.ca/pluginfile.php/8767136/mod_resource/content/7/D%C3%A9mo%2009.pdf) pour spécifier la classe main et ajouter les dépendances dans votre pom.xml

## Manuel utilisateur (README) [5/5]

- Mettez vos noms avec le lien vers vos profils GitHub dans le README

## JavaDocs générés [3/5]

- Mettez votre JavaDoc dans le dossier 'Documentation'
- Écrivez une description pour toutes les classes, la plupart des classes n'ont pas de description

## Cohérence entre les modèles et le code [12/15]

- Mettez votre diagramme de classes dans le dossier 'Conception'
- Il n'y a pas de pseudo pour l'utilisateur
- Placer les méthodes qui interagissent avec d'autres classes (e.g., les méthodes des classes Acheteur et Revendeur) dans un contrôleur pour réduire le couplage
- Il n'y a qu'une seule base de données pour toute l'application
  - Ne pas utiliser d'aggrégations des classes telles que 'Acheteur' et 'Revendeur' qui pointent vers cette base de données

## Bonus: Interface graphique [N/A]

## Bonus: Action GitHub [N/A]

Votre action GitHub ne fait pas ce qu'on demande: il n'exécute pas Maven et ne roule pas les tests
