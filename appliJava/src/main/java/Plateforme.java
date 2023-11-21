import java.util.InputMismatchException;
import java.util.Scanner;


public class Plateforme {

	public static String offrirProduit(Revendeur revendeur) throws InputMismatchException, IllegalArgumentException{
		// Demander au revendeur de specifier un titre
		Scanner scanner = new Scanner(System.in);

		System.out.print("Veuillez specifier le titre du produit a vendre: ");
		String titre = scanner.nextLine();

		// Valider si le titre n'est pas vide
		while (titre.trim().isEmpty()) {
			System.out.println("Attention: Le titre ne peut pas être vide. Veuillez réessayer.");
			titre = scanner.nextLine();
		}

		// Demander au revendeur de choisir une catégorie
		String categorie = null;
		System.out.println("À quelle catégorie appartient ce produit?");
		System.out.println("1. Livres et manuels");
		System.out.println("2. Ressources d'apprentissage");
		System.out.println("3. Matériel informatique");
		System.out.println("4. Équipement de bureau");

		while (categorie == null) {
			try {
				System.out.print("Veuillez entrer le numéro de la catégorie : ");
				int choixCategorie = scanner.nextInt();

				// Vérifier si la catégorie est valide
				categorie = switch (choixCategorie) {
					case 1 -> "Livres et manuels";
					case 2 -> "Ressources d'apprentissage";
					case 3 -> "Matériel informatique";
					case 4 -> "Équipement de bureau";
					default -> throw new Exception("Veuillez choisir une catégorie existante.");
				};
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		// Demander au revendeur d'entrer une description
		System.out.print("Veuillez donner une description de ce produit : ");
		scanner.nextLine();
		String description = scanner.nextLine();

		// Valider si la description n'est pas vide
		while (description.trim().isEmpty()) {
			System.out.println("Attention: La description ne peut pas être vide. Veuillez réessayer.");
			description = scanner.nextLine();
		}

		// Demander au revendeur d'entrer une quantité
		int qteEnStock = 0;
		do {
			try {
				System.out.print("Quelle quantité voulez-vous offrir? : ");
				qteEnStock = scanner.nextInt();

				if (qteEnStock <= 0) {
					throw new IllegalArgumentException("Attention: Vous devez mettre une quantité en stock.");
				}

			} catch (IllegalArgumentException e) {
				// Qte inférieure à 0
				System.out.println(e.getMessage());

			} catch (InputMismatchException e) {
				// Utilisateur n'entre pas un int
				System.out.println("Attention: Vous devez entrer un nombre entier pour la quantité.");
				scanner.nextLine();
			}
		} while (qteEnStock <= 0);

		// Demander au revendeur d'entrer un prix
		double prix;
		do {
			System.out.println("Veuillez spécifier le prix unitaire : ");
			try {
				prix = scanner.nextDouble();
				if (prix <= 0) {
					throw new IllegalArgumentException("Attention: Le prix doit être supérieur à 0.");
				}
			} catch (InputMismatchException e) {
				// Avertissement lorsque prix n'est pas un double
				System.out.println("Attention: Vous devez entrer un nombre valide pour le prix.");
				scanner.nextLine();
				prix = -1;

			} catch (IllegalArgumentException e) {
				// Avertissement lorsque prix est inférieur ou égal à 0
				System.out.println(e.getMessage());
				prix = -1;
			}
		} while (prix <= 0);

		System.out.println("Voulez vous donner des points bonus pour ce produit(oui/non)?");
		String choixPtsBonus = scanner.next().toLowerCase();

		int nbPoints = 0;
		if (choixPtsBonus.equals("oui")) {
			// Calculer les points bonus
			nbPoints = (int) prix;
			if (nbPoints > 20) {
				nbPoints = 20;
			}
			nbPoints = (int) Math.floor(nbPoints);
		}

		// todo: switch case, formulaire différent selon le type de produit, puis le TYPE du produit (materielInfo, equipementBureau etc) est
		// créé selon le type précisé par le revendeur

		System.out.println("Succès!" + titre + "a été publié à la plateforme. En voici les détails:");
		Produit produit = new Produit(titre, prix, qteEnStock, categorie, nbPoints, description);

		System.out.println("Pour quitter le formulaire d'offre, entrez 0");
		System.out.println("Pour offrir un autre produit, entrez 1");
		String choix = scanner.nextLine();

		if (choix.equals("0")){
			Utilisateur.afficherMenu(revendeur);
		} else if (!Main.isNumeric(choix)){
			System.out.println("SVP entrez un chiffre!");
		} else if (choix.equals("1")){
			offrirProduit(revendeur); // offrir un autre produit
		}

		// tous les produits ont un formulaire différent
		return "Titre: " + titre + "\n" + "Catégorie: " + categorie + "\n" + "Quantité en stock: " + qteEnStock + "\n"
				+ prix + "\n" + "Points bonus: " + nbPoints;
	}
}
