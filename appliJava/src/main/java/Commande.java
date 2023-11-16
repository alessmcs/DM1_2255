import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Commande {

	private StatutCommande statut;
	private static int id = 0;
	private Adresse adresseLivraison;

	public Commande(String status, Adresse adresse, int id){
		// add liste de produits i think
		this.statut = statut;
		this.adresseLivraison = adresse;
		this.id = id;
	}

	public static void passerCommande() {

		Adresse adresseLivraison;
		Adresse adresseFacturation;
		String numTel;
		Scanner s = new Scanner(System.in);

		System.out.println("----- Informations de livraison ----- \n1 : Adresse sur mon profil \n2 : Fournir une autre adresse");

		// infos de livraison
		while(true){

			int choixAdresse = s.nextInt(); // l'utilisateur doit entrer 1 ou 2, sinon le système ne continue pas
			s.nextLine();

			if (choixAdresse == 1){ // Les informations du profil sont utilisées
				adresseLivraison = Acheteur.getAdresseExpedition(); //TODO: customize it w the specific acheteur logged in
				break;

			} else if (choixAdresse == 2){ // l'utilisateur fournit de nouvelles informations

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
					if (valide) break; else System.out.println("L'adresse entrée est invalide, svp réessayer");
				}

				break;
			} else { // Le système lui dit de rentrer un entier
				System.out.println("Entrer \"1\" ou \"2\" svp!");
			}
		}

		// numéro de telephone
		System.out.println("----- Numéro de téléphone -----\n1 : Numéro sur mon profil \n2 : Fournir un autre numéro");
		while(true){
			int choixNum = s.nextInt(); // l'utilisateur doit entrer 1 ou 2, sinon le système ne continue pas
			s.nextLine();

			if (choixNum == 1){
				numTel = Acheteur.getTel();
				break;
			} else if ( choixNum == 2){
				while(true){
					// user enters the num
					System.out.println("Entrez un numéro de la forme \"5140001111\" (10 chiffres, sans espaces)");
					numTel = s.nextLine();

					// Define the regex pattern for a ten-digit phone number
					String phonePattern = "\\d{10}";
					Pattern pattern = Pattern.compile(phonePattern);
					Matcher matcher = pattern.matcher(numTel);
					// Check if the phone number matches the pattern
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
		while(true){
			System.out.println("----- Informations de paiement ----- \nNumero de carte de crédit: ");
			// l'utilisateur entre un numéro de carte, date d'expiration, cvc
			String numCarte = s.nextLine();
			System.out.println("Date d'expiration (MMAA)");
			String dateExp = s.nextLine();
			System.out.println("CVC (ex: 999)");
			String cvc = s.nextLine();
			// validation des infos
			boolean valide = SystemePaiement.validerInfosPaiement(numCarte, dateExp, cvc);
			if(valide){
				break;
			} else {
				System.out.println("Les informations de paiement sont invalides, svp réessayer");
			}
		}

		System.out.println("----- Adresse de facturation ----- \n1 : Même adresse qu'expédition \n2 : Nouvelle adresse ");
		while(true){

			int choixAdresse = s.nextInt(); // l'utilisateur doit entrer 1 ou 2, sinon le système ne continue pas
			s.nextLine();

			if (choixAdresse == 1){ // meme qu'expedition
				adresseFacturation = adresseLivraison;
				break;
			} else if (choixAdresse == 2){ // l'utilisateur fournit de nouvelles informations

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
					if (valide) break; else System.out.println("L'adresse entrée est invalide, svp réessayer");
				}

				break;
			} else { // Le système lui dit de rentrer un entier
				System.out.println("Entrer \"1\" ou \"2\" svp!");
			}
		}

		Facture facture = new Facture(Panier.getTotal());

		// Il peut échanger ces points sur les produits qu'il achète pour obtenir un rabais à un taux d'un point par 2¢ d'achat
		System.out.println("Voulez-vous échanger tous vos points pour obtenir un rabais? \n1 : Oui \n2: Non");
		while(true){
			int choix = s.nextInt();
			if(choix == 1){ // oui rabais
				System.out.println("1 : 5$ = 250 points | 2 : 10$ = 500 points | 3 : 15$ = 750 points " +
						"\nVous avez: " + Acheteur.getPoints() + "points");
				while(true){
					int choix2 = s.nextInt();
					if ( choix2 == 1){
						if (Acheteur.getPoints() < 250){
							System.out.println("Vous n'avez pas assez de points!");
							break;
						} else {
							facture.setRabais(5);
							Acheteur.setPoints(-250);
						}
					} else if(choix2 == 2){
						if (Acheteur.getPoints() < 500){
							System.out.println("Vous n'avez pas assez de points!");
							break;
						} else {
							facture.setRabais(10);
							Acheteur.setPoints(-500);
						}
					} else if(choix2 == 3){
						if (Acheteur.getPoints() < 750){
							System.out.println("Vous n'avez pas assez de points!");
							break;
						} else {
							facture.setRabais(15);
							Acheteur.setPoints(-750);
						}
					} else {
						System.out.println("SVP entrez un chiffre entre 1 et 3!");
					}
				}
				break;
			} else if(choix == 2){
				break; // do nothing, they don't want a rabais
			} else {
				System.out.println("SVP entrez un chiffre entre 1 et 2!");
			}
		}



		Commande commande = new Commande("En production", adresseLivraison, id+1);
		Acheteur.addHistorique(commande); // ajouter la commande à l'historique de commandes


		// TODO: if acheteur is in acheteurs likeurs, then notify all the acheteurs in the list of the revendeurs
		// TODO: not sure if we should do notification
		// the revendeur will be associated to the product
//		Notification notif = new Notification(nouvCommandeRecue);
//		notif.notifierRevendeur();

		//TODO a new colis is made with each commande
		Colis colis = new Colis(commande.getStatutCommande());

		// Confirmation de la commande
		System.out.println("Commande confirmée! \n#" + id + "\nArticles: \n");
		for (Produit p : Panier.getArticles()){
			System.out.println(p);
			p.setQte(p.getQte() - 1); // mettre à jour la quantité de chq produit de la commande
		}
		System.out.println("\nTotal: " + Panier.getTotal());

	}

	public void annuler() {
		// TODO - implement Commande.annuler
	}

	// maybe add another parameter for this method
	public void setEtatCommande(StatutCommande e) {
		// TODO - implement Commande.setEtatCommande
		this.statut = e;
	}

	public StatutCommande getStatutCommande(){
		return statut;
	}

}