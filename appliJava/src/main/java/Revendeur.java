import java.util.ArrayList;
import java.util.Scanner;

public class Revendeur extends Utilisateur {

	private Adresse adresse;
	private int nom;
	protected ArrayList<Produit> inventaire;
	private String pseudo;
	protected ArrayList<Produit> commandes; //TODO ajouter attribut revendeur au produit




	public Revendeur(String telephone, String courriel, String motDePasse) {
		super(telephone,courriel,motDePasse);
		inventaire= new ArrayList<>();
	}

	public void inscrireRevendeur() {
		Scanner scanner = new Scanner((System.in));


		System.out.println("Veuillez entrer votre adresse civil: ");
		while (true) {
			System.out.println("Adresse de rue: ");
			String street = scanner.nextLine();
			System.out.println("Ville: ");
			String city = scanner.nextLine();
			System.out.println("Province (abbr): ");
			String province = scanner.nextLine();
			System.out.println("Code postal: ");
			String postalCode = scanner.nextLine();
			System.out.println("Pays: ");
			String country = scanner.nextLine();

			adresse = new Adresse(street, city, province, postalCode, country);
			// valider l'adresse
			boolean valide = SystemeLivraison.validerInfosLivraison(adresse);
			if (valide) break;
			else System.out.println("L'adresse entrée est invalide, svp réessayer");
		}

		System.out.println("Veuillez entrer votre pseudo");
		String pseudo = scanner.nextLine();

		setAdresse(adresse);
		setPseudo(pseudo);

	}

	public void confirmerReceptionRetour() {
		// TODO - implement Revendeur.confirmerReceptionRetour
		throw new UnsupportedOperationException();
	}


	public void gererProbleme(Revendeur revendeur) {

		if (billet == null) {
			System.out.println("Aucun problème signalé.");
			return;
		} else {
			System.out.println("Nouveau billet de signalement reçu : " + billet.getDescriptionProbleme());
		}

		boolean solutionAcceptee = false;
		while (!solutionAcceptee) {
			// Demander au revendeur de donner une solution
			System.out.println("Options de solution :");
			System.out.println("1. Réparation du produit défectueux");
			System.out.println("2. Retour et remboursement");
			System.out.println("3. Échange");

			int choixSolution = scanner.nextInt();
			scanner.nextLine();

			switch (choixSolution) {
				case 1:
					billet.setDescriptionSolution("Le revendeur a proposé de réparer votre produit défectueux");
					break;
				case 2:
					billet.setDescriptionSolution("Le revendeur a proposé de le retour du produit défectueux");
					break;
				case 3:
					billet.setDescriptionSolution("Le revendeur a proposé un échange du produit défectueux");
					break;
				default:
					System.out.println("Choix invalide. Aucune action n'a été prise.");
					return;
			}

			// Envoyer le billet avec la solution à l'acheteur
			Acheteur acheteur = billet.getAcheteur();
			acheteur.recevoirBilletDeSignalement(billet);

			// L'acheteur peut accepter ou refuser la solution
			do {
				try {
					System.out.print("Voulez-vous accepter la solution proposée ?");
					System.out.println("1. Oui");
					System.out.println("2. Non");

					int choixAcheteur = scanner.nextInt();
					scanner.nextLine();

					if (choixAcheteur == 1) {
						billet.setDescriptionSolution("La solution a été acceptée.");
						solutionAcceptee = true;
					} else if (choixAcheteur == 2) {
						billet.setDescriptionSolution("La solution a été refusée. Veuillez proposer une autre solution.");
					} else {
						System.out.println("Choix invalide. Veuillez choisir 1 pour Oui ou 2 pour Non.");
					}
				} catch (InputMismatchException e) {
					System.out.println("Entrée invalide. Veuillez entrer un nombre entier.");
					scanner.nextLine();
				}
			} while (choixAcheteur != 1 && choixAcheteur != 2);
		}

		// Annuler automatiquement la demande de réexpédition après 30 jours
		if (billet.getNumSuiviRemplacement() != 0 &&
				LocalDate.now().isAfter(billet.getDateEmission().plus(30, ChronoUnit.DAYS))) {
			System.out.println("La demande de réexpédition est annulée car le produit n'a pas été reçu à temps.");
			billet.setNumSuiviRemplacement(0);
		}
	}

	public void updateInventaire() {
		// TODO - implement Revendeur.updateInventaire
		throw new UnsupportedOperationException();
	}

	public String  getPseudo() {
		return pseudo;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
}