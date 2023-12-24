import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Commande {

	private StatutCommande statut;
	private static int id = 0;
	private static Adresse adresseLivraison;
	private static ArrayList<Produit> articles = new ArrayList<>();
	private static Acheteur acheteur;
	private static Revendeur revendeur;

	// Constructeur de commande
	public Commande(Acheteur acheteur, String status, Adresse adresse, int id, ArrayList<Produit> articles){
		this.acheteur = acheteur;
		this.revendeur= revendeur;
		this.statut = statut;
		this.adresseLivraison = adresse;
		this.id = id;
		this.articles = articles;
	}

	/*
		Permet d'assigner un acheteur à un objet Commande, pour qu'on puisse y accéder depuis l'acheteur.

		@param a l'acheteur auquel on veut associer la commande
	 */
	public static void setAcheteur(Acheteur a){
		acheteur = a;
	}

	/*
		Propose à l'acheteur de remplir un formulaire pour qu'il passe la commande contenant les produits de son panier.
		Cette méthode ne retourne rien, mais elle instancie un objet de type "Commande" pour l'ajouter à l'historique
		des commandes de l'acheteur. Elle écrit également les informations de la commande dans le fichier commandes.csv.

		@param p le panier de l'acheteur connecté
	 */
	public static void passerCommande(Panier p) {

		System.out.println(" --- Formulaire de commande ---");

		Adresse adresseFacturation;
		String numTel;
		Scanner s = new Scanner(System.in);

		System.out.println("----- Informations de livraison ----- \n1 : Adresse sur mon profil \n2 : Fournir une autre adresse");

		// infos de livraison
		while (true) {
			String choixAdresse = s.nextLine(); // l'utilisateur doit entrer 1 ou 2, sinon le système ne continue pas
			if (!Main.isNumeric(choixAdresse)) {
				System.out.println("Vous devez entrer un chiffre!");
			} else if (choixAdresse.equals("1")) { // Les informations du profil sont utilisées
				adresseLivraison = acheteur.getAdresseExpedition(); //TODO: customize it w the specific acheteur logged in
				break;
			} else if (choixAdresse.equals("2")) { // l'utilisateur fournit de nouvelles informations
				while (true) {
					System.out.println("Adresse de rue: ");
					String street = s.nextLine();
					System.out.println("Ville: ");
					String city = s.nextLine();
					System.out.println("Province (abbr): ");
					String province = s.nextLine();
					System.out.println("Code postal: ");
					String postalCode = s.nextLine();
					System.out.println("Pays: ");
					String country = s.nextLine();

					adresseLivraison = new Adresse(street, city, province, postalCode, country);

					// valider l'adresse
					boolean valide = SystemeLivraison.validerInfosLivraison(adresseLivraison);
					if (valide) break;
					else System.out.println("L'adresse entrée est invalide, svp réessayer");
				}
				break;
			} else { // Le système lui dit de rentrer 1 ou 2
				System.out.println("Entrer \"1\" ou \"2\" svp!");
			}
		}

		// numéro de telephone
		System.out.println("----- Numéro de téléphone -----\n1 : Numéro sur mon profil \n2 : Fournir un autre numéro");
		while (true) {
			String choixNum = s.nextLine(); // l'utilisateur doit entrer 1 ou 2, sinon le système ne continue pas
			if (!Main.isNumeric(choixNum)) {
				System.out.println("Vous devez entrer un chiffre!");
			} else if (choixNum.equals("1")) {
				numTel = acheteur.getTelephone();
				break;
			} else if (choixNum.equals("2")) {
				while (true) {
					// user enters the number
					System.out.println("Entrez un numéro de la forme \"9990001111\" (10 chiffres, sans espaces)");
					numTel = s.nextLine();

					// regex pattern for a ten-digit phone number
					String phonePattern = "\\d{10}";
					Pattern pattern = Pattern.compile(phonePattern);
					Matcher matcher = pattern.matcher(numTel);
					// check if the phone number matches the pattern
					if (matcher.matches()) {
						break;
					} else {
						System.out.println("Le format du numéro de telephone est invalide");
					}
				}
			} else {
				System.out.println("Entrer \"1\" ou \"2\" svp!");
			}
		}

		// infos de paiement
		while (true) {
			System.out.println("----- Informations de paiement ----- \nNuméro de carte de crédit: ");
			// l'utilisateur entre un numéro de carte, date d'expiration, cvc
			String numCarte = s.nextLine();
			System.out.println("Date d'expiration (MMAA)");
			String dateExp = s.nextLine();
			System.out.println("CVC (ex: 999)");
			String cvc = s.nextLine();
			// validation des infos
			boolean valide = SystemePaiement.validerInfosPaiement(numCarte, dateExp, cvc);
			if (valide) {
				break;
			} else {
				System.out.println("Les informations de paiement sont invalides, svp réessayer");
			}
		}

		System.out.println("----- Adresse de facturation ----- \n1 : Même adresse qu'expédition \n2 : Nouvelle adresse ");
		while (true) {

			String choixAdresse = s.nextLine(); // l'utilisateur doit entrer 1 ou 2, sinon le système ne continue pas
			if (!Main.isNumeric(choixAdresse)) {
				System.out.println("Vous devez entrer un chiffre!");
			} else if (choixAdresse.equals("1")) { // meme qu'expedition
				adresseFacturation = adresseLivraison;
				break;
			} else if (choixAdresse.equals("2")) { // l'utilisateur fournit de nouvelles informations
				while (true) {
					System.out.println("Adresse de rue: ");
					String street = s.nextLine();
					System.out.println("Ville: ");
					String city = s.nextLine();
					System.out.println("Province (abbr): ");
					String province = s.nextLine();
					System.out.println("Code postal: ");
					String postalCode = s.nextLine();
					System.out.println("Pays: ");
					String country = s.nextLine();

					adresseFacturation = new Adresse(street, city, province, postalCode, country);
					// valider l'adresse
					boolean valide = SystemeLivraison.validerInfosLivraison(adresseFacturation);
					if (valide) break;
					else System.out.println("L'adresse entrée est invalide, svp réessayer");
				}
				break;
			} else { // Le système lui dit de rentrer un entier
				System.out.println("Entrer \"1\" ou \"2\" svp!");
			}
		}

		// générer une nouvelle facture
		Facture facture = new Facture(p.getTotal());

		// Il peut échanger ces points sur les produits qu'il achète pour obtenir un rabais à un taux d'un point par 2¢ d'achat
		if (acheteur.getPoints() != 0) {
		System.out.println("Voulez-vous échanger tous vos points pour obtenir un rabais? \n1 : Oui \n2: Non");
		while (true) {
			String choix = s.nextLine();
			if (!Main.isNumeric(choix)) {
				System.out.println("Vous devez entrer un chiffre!");
			} else if (choix.equals("1")) { // oui rabais
				System.out.println("1 : 5$ = 250 points | 2 : 10$ = 500 points | 3 : 15$ = 750 points " +
						"\nVous avez: " + acheteur.getPoints() + "points");
				while (true) {
					String choix2 = s.nextLine();
					if (!Main.isNumeric(choix2)) {
						System.out.println("Vous devez entrer un chiffre!");
					} else if (choix2.equals("1")) {
						if (acheteur.getPoints() < 250) {
							System.out.println("Vous n'avez pas assez de points!");
							break;
						} else {
							facture.setRabais(5);
							acheteur.setPoints(-250);
						}
					} else if (choix2.equals("2")) {
						if (acheteur.getPoints() < 500) {
							System.out.println("Vous n'avez pas assez de points!");
							break;
						} else {
							facture.setRabais(10);
							acheteur.setPoints(-500);
						}
					} else if (choix2.equals("3")) {
						if (acheteur.getPoints() < 750) {
							System.out.println("Vous n'avez pas assez de points!");
							break;
						} else {
							facture.setRabais(15);
							acheteur.setPoints(-750);
						}
					} else {
						System.out.println("SVP entrez un chiffre entre 1 et 3!");
					}
				}
				break;
			} else if (choix.equals("2")) {
				break; // do nothing, they don't want a rabais
			} else {
				System.out.println("SVP entrez un chiffre entre 1 et 2!");
			}
		}
	}


		Commande commande = new Commande(acheteur,"En production", adresseLivraison, id+1, p.getArticles());
		// todo ajouter à l'historique & also écrire dans le csv!!!
		acheteur.addHistorique(commande); // ajouter la commande à l'historique de commandes

		// si les produits achetés ont des points bonus, ajouter les points bonus à l'acheteur qui a passé la commande
		// calculer la somme des points
		int sommePoints = 0;

		// Confirmation de la commande
		System.out.println("Commande confirmée! \n" + id + "\nArticles:");
		for (Produit prod : p.getArticles()){
			System.out.println(prod);
			sommePoints += prod.getPoints();
			acheteur.panier.getArticles().remove(p); // retirer les elements de la commande du panier
			prod.setQte(prod.getQte() - 1); // mettre à jour la quantité de chq produit de la commande
		}
		acheteur.setPoints(sommePoints); // mettre à jour les points dans le profil de l'acheteur
		System.out.printf("\nTotal: " + facture.getTotal());
		System.out.println("\nLivré au : " + acheteur.getAdresseExpedition().toString());
		System.out.println("Contact : " + acheteur.getCourriel() + ", " + acheteur.getTelephone());

		// retourner au catalogue
		System.out.println("\n1: Retourner au catalogue \n2: Retourner au menu");
		String exitChoix = s.nextLine();
		switch(exitChoix){
			case("1") -> Catalogue.voirCatalogue(acheteur);
			case("2") -> Utilisateur.afficherMenu(acheteur);
			default -> System.out.println("Svp entrez 1 ou 2!");
		}

		// nouveau colis généré avec la commande
		Colis colis = new Colis(commande.getStatutCommande());
	}


	/*
		Cette méthode prend en entrée un objet de StatutCommande, qui est, en fait, un String.
		Elle permet de modifier le statut d'une commande.

		@param e l'état désiré auquel on veut changer le statut de la commande
	 */
	public void setEtatCommande(StatutCommande e) {
		this.statut = e;
	}

	/*
		Cette méthode permet à un utilisateur de recevoir le statut de la commande sans directement accéder à l'objet

		@return statut de la commande sur laquelle on appelle la méthode
	 */
	public StatutCommande getStatutCommande(){
		return statut;
	}

	/*
		Cette méthode permet de recevoir l'ID unique de la commande

		@return ID de la commande sur laquelle on appelle la méthode
	 */
	public int getId() {
		return id;
	}

	/*
		Cette méthode permet de recevoir la liste des articles compris dans la commande

		@return liste des articles (de type ArrayList<Produit>)
	 */
	public ArrayList<Produit> getArticles(){
		return articles;
	}

	/*
		Cette méthode formatte simplement la commande pour qu'elle soit affichée correctement
	 */
	public void commandeToString(){
		System.out.println(id + "\n" + statut);
		for(Produit p : articles){
			System.out.println(p.toString());
		}
	}

	public Revendeur getRevendeurDuProduit(int produitId) {
		for (Produit produit : articles) {
			if (produit.getId() == produitId) {
				return produit.getRevendeur();
			}
		}
		return null;
	}


}