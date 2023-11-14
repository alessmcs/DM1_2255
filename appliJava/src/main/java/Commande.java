import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Commande {

	private StatutCommande statut;
	private int id;
	private Adresse adresseLivraison;
	private int infosPaiement;

	// inner class for infos paiement???

	public Commande(String status, Adresse adresse){
		// add liste de produits i think
		this.statut = statut;
		this.adresseLivraison = adresse;
	}

	public static void passerCommande() {
		// TODO - implement Commande.passerCommande
		// passerCommande crée une nouvelle instance de commande unique à chaque fois que l'utilisateur passe une commande
		// 1 - l'index unique est ajouté dans Commande

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


		// si tout est valide la commande est passée, ajoutée à l'historique de commandes de l'acheteur, facture est géneéreée
		// TODO: ADD THE PRODUCTS FROM THE CART TO THE COMMANDE 
		Commande commande = new Commande("En production", adresseLivraison);
		Acheteur.addHistorique(commande);

		// TODO: GENERER FACTURE
		// utiliser les informations du panier pour genérer la facture une fois qu'elle est completee
		//Facture facture = new Facture()

	}

	public void annuler() {
		// TODO - implement Commande.annuler
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param e
	 */
	public void setEtatCommande(StatutCommande e) {
		// TODO - implement Commande.setEtatCommande
		throw new UnsupportedOperationException();
	}

}