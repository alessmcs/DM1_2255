import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.Scanner;

public class Revendeur extends Utilisateur {

	private Adresse adresse;
	private int nom;
	protected ArrayList<Produit> inventaire;
	private String pseudo;
	protected ArrayList<Produit> commandes = new ArrayList<>();
	protected Map<Commande, CarteCredit> retours = new HashMap<>();


	public Revendeur(String telephone, String courriel, String motDePasse) {
		super(telephone, courriel, motDePasse);
		inventaire = new ArrayList<>();
	}

	public void setPseudo (String pseudo){
		this.pseudo = pseudo;
	}

	public String getPseudo() {
		return pseudo;
	}

	public Adresse getAdresse () {
		return adresse;
	}
	public void setAdresse (Adresse adresse){
		this.adresse = adresse;
	}


	public Map<Commande, CarteCredit> getCommandesRetournees() {
		return retours;
	}


	public void ajouterCommandeRetournee(Commande commande, CarteCredit carteCredit) {

		retours.put(commande, carteCredit);
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

	public void afficherNotifications() {
		for (Notification notification : notifications) {
			System.out.println(notification);
		}
	}

	public void confirmerReceptionRetour() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Voici les commandes retournées : ");
		for (Map.Entry<Commande, CarteCredit> entry : retours.entrySet()) {
			Commande commande = entry.getKey();
			CarteCredit numeroCarte = entry.getValue();

			System.out.println(commande);
		}

		System.out.println("Veuillez entrer l'ID de la commande que vous avez reçue : ");
		int ID = scanner.nextInt();
		scanner.nextLine(); // Pour consommer la nouvelle ligne

		boolean idCorrect = false;

		for (Map.Entry<Commande, CarteCredit> entry : retours.entrySet()) {
			Commande commande = entry.getKey();
			CarteCredit numeroCarte = entry.getValue();

			if (commande.getId() == ID) {
				commande.setEtatCommande(StatutCommande.retour_recu);
				SystemePaiement.rembourserMontant(numeroCarte, commande);
				idCorrect = true;
				break; // Sortir de la boucle une fois que la commande a été trouvée
			}
		}

		if (!idCorrect) {
			System.out.println("Aucune commande correspondant à l'ID fourni n'a été trouvée.");
		}
	}


	/**
	 * La méthode suivante permet au revendeurs de traiter les billets de signalement
	 *
	 * @param revendeur Le revendeur qui reçoit le billet de signalement
	 * @return
	 */
	public BilletDeSignalement gererProbleme(Revendeur revendeur) {
		Scanner scanner = new Scanner(System.in);
		BilletDeSignalement billet = new BilletDeSignalement();

		if (billet == null) {
			System.out.println("Aucun problème signalé.");
			return billet;
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
				case 1 -> billet.setDescriptionSolution("Le revendeur a proposé de réparer votre produit défectueux");
				case 2 -> billet.setDescriptionSolution("Le revendeur a proposé de le retour du produit défectueux");
				case 3 -> billet.setDescriptionSolution("Le revendeur a proposé un échange du produit défectueux");
				default -> {
					System.out.println("Choix invalide. Aucune action n'a été prise.");
					return billet;
				}
			}

			// Envoyer le billet avec la solution à l'acheteur
			Acheteur acheteur = billet.getAcheteur();
			acheteur.recevoirBilletDeSignalement(billet);

			// L'acheteur peut accepter ou refuser la solution
			int choixAcheteur = 0;
			do {
				try {
					System.out.print("Voulez-vous accepter la solution proposée ?");
					System.out.println("1. Oui");
					System.out.println("2. Non");

					choixAcheteur = scanner.nextInt();
					scanner.nextLine();

					if (choixAcheteur == 1) {
						billet.setDescriptionSolution("L'acheteur a accepté la solution.");
						solutionAcceptee = true;
					} else if (choixAcheteur == 2) {
						billet.setDescriptionSolution("La solution a été refusée. Veuillez proposer une autre solution.");
					} else {
						System.out.println("Choix invalide. Veuillez choisir 1 pour Oui ou 2 pour Non.");
					}
				} catch (InputMismatchException e) {
					System.out.println("Entrée invalide. Veuillez choisir 1 pour Oui ou 2 pour Non.");
					scanner.nextLine();
				}
			} while (choixAcheteur != 1 && choixAcheteur != 2);
		}

		// Annuler automatiquement la demande de réexpédition après 30 jours
		if (billet.getNumSuiviRemplacement() != 0 &&
				LocalDate.now().isAfter(billet.getDateLivraison().plus(30, ChronoUnit.DAYS))) {
			System.out.println("La demande de réexpédition est annulée car le produit n'a pas été reçu à temps.");
			billet.setNumSuiviRemplacement(0);
		}
		return billet;
	}

		public void updateInventaire(Produit p) throws FileNotFoundException
		{ // lorsqu'on on ajoute un produit à l'inventaire on le met à jour
			inventaire.add(p);
			try {
				Main.ecrireProduitCSV(p, "src/main/data/listeProduits.csv");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}


		public void afficherInventaire () {
			for (Produit p : inventaire) {
				System.out.println(p); // uses toString method from produit
			}
		}

	/**
	 * @param Utilisateur Sert à afficher les commentaires d'un produit qui appartient à un certain revendeur
	 */

	public void afficherCommentaires(Revendeur Utilisateur utilisateur) {
		for (Produit p : ((Revendeur) utilisateur).inventaire) {
			for (ArrayList<String> commentaire : p.listCommentaires) {
				System.out.println("Produit: " + p.titre);
				System.out.println("Étoile(s): " + commentaire.get(0));
				System.out.println("Commentaire: " + commentaire.get(1));
				System.out.println();
			}
			System.out.println();
		}
	}


	protected void afficherMetriques (Revendeur utilisateur){
		float revenu = 0;
		int nbVendu = 0;
		int nbArticles = ((Revendeur) utilisateur).inventaire.size();
		for (Produit p : ((Revendeur) utilisateur).inventaire) {
			if (p.qteInitiale != p.qteEnStock) {
				int n = p.qteInitiale - p.qteEnStock;
				nbVendu += n;
				revenu += p.prix * n;
			}
		}

		System.out.println("Vos métriques de revendeur: ");
		System.out.println("Nombre de produits offerts: " + nbArticles);
		System.out.println("Nombre de produits vendus: " + nbVendu);
		System.out.println("Revenu total: " + revenu);
		System.out.println("Nombre de commentaires laissés sur vos produits: ");
		for (Produit p : ((Revendeur) utilisateur).inventaire) {
			if (p.listCommentaires != null) {
				System.out.println(p.getTitre() + ": " + p.listCommentaires.size());
			}
		}
		System.out.println("Nombre de commandes retournées: " + retours.size());

		Scanner s = new Scanner(System.in);
		boolean validInput = false;
		do {
			try {
				System.out.println("Entrez 0 pour retourner au menu principal");

				String choix = s.nextLine();
				if (!Main.isNumeric(choix)) {
					throw new InputMismatchException();
				} else {
					if (choix.equals("0")) {
						validInput = true;
						Utilisateur.afficherMenu(utilisateur);
						break;
					}
				}
			} catch (InputMismatchException e) {
				System.out.println("Svp entrez 0!");
			}
		} while (!validInput);
	}

	public void montrerProfil() {
		// afficher les informations
		System.out.println("Profil de : " + pseudo);
		System.out.println(inventaire.size() + " produits offerts");

		System.out.println(commandes.size() + " commandes reçues");

	}

	public void recevoirBilletDeSignalement(BilletDeSignalement billet) {
	}
}

