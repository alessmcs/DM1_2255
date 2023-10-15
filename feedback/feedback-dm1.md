# Commentaires DM1

Corrigé par An Li

Total: 82%

## Glossaire [8/10]

Termes manquants:

- UniShop
- Acheteur (mais vous mentionnez compte client, facture, adresse, etc.)
- Commande (mais vous mentionnez certains aspects d'une commande)

Termes à clarifier:

- Métriques: donner des exemples

## Diagramme de cas utilisation [17/25]

- Le diagramme ne s'affiche pas dans le rapport et le lien dans le code du rapport n'est pas en SVG
- Il n'y a pas de fichier .vpp
- Pas vraiment des acteurs
  - Admin
- Problèmes avec les rôles
  - Un client membre et un revendeur ont des actions différentes, le revendeur ne doit pas hériter d'un client membre, ou il manque l'acheteur qui doit hériter un client membre
- Mauvaises relations
  - Pour évaluer un produit, il faut l'avoir acheté
  - Pour faire un retour ou un échange, il faut avoir acheté le produit
  - Pour changer l'état d'une commande, il faut qu'une commande soit passée par l'acheteur
  - Pour confirmer la réception d'un retour, il faut que l'acheur l'ait initié
- Pour alléger le diagramme, on peut assumer que l'utilisateur est déjà connecté pour diminuer le nombre de flèches qui pointent vers "Se connecter"

## Description des cas d'utilisation [50/50]

- S'inscrire
  - Manque une mention explicite du mot de passe exigé lors de l'inscription
- Se connecter
  - Le concept de compte suspendu n'existe pas
- Échange
  - Mentionner le service bancaire et la poste comme acteur secondaire
  - L'acheteur doit payer la différence de prix entre les deux produits
  - L'acheteur doit se présenter à la poste pour livrer le produit à l'entrepôt avant de pouvoir le suivre
  - Le revendeur doit expédier le produit de remplacement à l'acheteur
- Retour et remboursement
  - Mentionner le service bancaire et la poste comme acteur secondaire
  - Assumer que le retour se fait sur le mode de paiement d'origine
  - L'acheteur doit se présenter à la poste pour livrer le produit à l'entrepôt avant de pouvoir le suivre
  - Une fois que le revendeur confirme la réception du retour, le système bancaire doit émettre le remboursement au mode de paiement d'origine

## Risques [3/5]

Les risques suivants peuvent être des conséquences directes des problèmes de connexion:

- Incapable d'enregistrer/modifier les informations d'usager
- Mal gérer l'inventaire
- Problème avec le suivi de la commande

Essayez de nommer des risques causés par d'autres types d'événements

## Besoins non-fonctionnels [2/5]

Vous avez listé les besoins, mais expliquez comment ces besoins non-fonctionnels contribueront à la plateforme UniShop

## Bonne utilisation de GitHub et statistiques [2/5]

- Aucun release créé pour le devoir 1
  - J'ai dû la créer manuellement
- Pas de capture d'écran des Insights dans le rapport
- Peu de tâches et de pull requests par rapport aux membres de l'équipe

## Bonus: Application Java [N/A]
