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


	public BilletDeSignalement gererProbleme(Revendeur revendeur, Probleme probleme, BilletDeSignalement billet) {
		Scanner scanner = new Scanner(System.in);
		Acheteur acheteur = billet.getAcheteur();
		retourEchange retourEchange = new retourEchange();

		System.out.println("Nouveau billet de signalement reçu : " + billet.getDescriptionProbleme());

		boolean solutionAcceptee = false;
		while (!solutionAcceptee) {
			// Demander au revendeur de donner une solution
			System.out.println("Options de solution :");
			System.out.println("1. Réparation du produit défectueux");
			System.out.println("2. Retour et remboursement");
			System.out.println("3. Échange");

			int choixSolution = 0;
			try {
				choixSolution = scanner.nextInt();
				scanner.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("Entrée invalide. Veuillez choisir 1, 2, ou 3.");
				scanner.nextLine();
				continue;
			}

			switch (choixSolution) {
				case 1 -> {
					billet.setDescriptionSolution("Le revendeur a proposé de réparer votre produit défectueux");
					// Réexpédier le produit au revendeur
					if (acheteur.acheteurAccepteSolution(scanner)) {
						retourEchange.effectuerRetour(acheteur);
					}
				}
				case 2 -> {
					billet.setDescriptionSolution("Le revendeur a proposé le retour du produit défectueux");
					// Exécuter un retour
					if (acheteur.acheteurAccepteSolution(scanner)) {
						retourEchange.effectuerRetour(acheteur);
					}
				}
				case 3 -> {
					billet.setDescriptionSolution("Le revendeur a proposé un échange du produit défectueux");
					// Exécuter un échange
					if (acheteur.acheteurAccepteSolution(scanner)) {
						retourEchange.effectuerEchange(acheteur);
					}
				}
				default -> {
					System.out.println("Choix invalide. Aucune action n'a été prise.");
					return billet;
				}
			}

			// Envoyer le billet avec la solution à l'acheteur
			acheteur.consulterBilletDeSignalement(billet);

			// Mise à jour de solutionAcceptee en fonction de la réponse de l'acheteur
			solutionAcceptee = acheteur.acheteurAccepteSolution(scanner);

		}

		// Annuler automatiquement la demande de réexpédition après 30 jours
		LocalDate dateEmission = probleme.getDateEmission();
		if (billet.getNumSuiviRemplacement() != 0 &&
				LocalDate.now().isAfter(dateEmission.plus(30, ChronoUnit.DAYS))) {
			System.out.println("La demande de réexpédition est annulée car le produit n'a pas été reçu à temps.");
			billet.setNumSuiviRemplacement(0);
		}
		return billet;
	}

	/**
	 * Cette méthode met à jour l'inventaire à l'ajout de nouveaux produits.
	 * @param p le produit dont l'inventaire est modifié
	 * @throws FileNotFoundException lorsque la liste de produits est introuvable
	 */
	public void updateInventaire(Produit p) throws FileNotFoundException {
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
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e);
			}
		} while (!validInput);
	}

	public void montrerProfil() {
		// afficher les informations
		System.out.println("Profil de : " + pseudo);
		System.out.println(inventaire.size() + " produits offerts");

		System.out.println(commandes.size() + " commandes reçues");

	}

	/**
	 * Cette méthode permet aux revendeurs de recevoir un billet de signalement provenant de l'acheteur.
	 * @param billet le billet de signalement envoyé au revendeur
	 */
	public void recevoirBilletDeSignalement(BilletDeSignalement billet) {
		BilletDeSignalement.ajouterBillet(billet);
	}
}

