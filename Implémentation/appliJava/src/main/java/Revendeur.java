
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Revendeur extends Utilisateur {

	private Adresse adresse;
	private int nom;
	protected ArrayList<Produit> inventaire;
	private String pseudo;
	protected ArrayList<Produit> commandes = new ArrayList<>();
	protected Map<Commande, CarteCredit> retours = new HashMap<>();
	protected ArrayList<Acheteur> acheteurQuiAime = new ArrayList<>();
	protected ArrayList<Notification> notifications = new ArrayList<>();
	protected ArrayList<Acheteur> acheteurLikeProd = new ArrayList<>();




	public Revendeur(String telephone, String courriel, String motDePasse) {
		super(telephone, courriel, motDePasse);
		inventaire = new ArrayList<>();
	}

	/**
	 * Ajoute une notification à la liste des notifications
	 * 
	 * @param notification
	 */
	public void ajouterNotification(Notification notification) {
		notifications.add(notification);
	}

	public ArrayList<Notification> getNotifications() {
		return notifications;
	}
	protected ArrayList<Acheteur> getAcheteurQuiAime() {
		return acheteurQuiAime;
	}
	protected void setAcheteurQuiAime(ArrayList<Acheteur> acheteurQuiAime) {
		this.acheteurQuiAime = acheteurQuiAime;
	}
	public Map<Commande, CarteCredit> getCommandesRetournees() {
		return retours;
	}
	protected ArrayList<Acheteur> getAcheteurLikeProd() {
		return acheteurLikeProd;
	}
	protected void setAcheteurLikeProd(ArrayList<Acheteur> acheteurLikeProd) {
		this.acheteurLikeProd = acheteurLikeProd;
	}

	/**
	 * Retourne la liste des commandes du revendeur
	 * @return liste de commande
	 */
	public ArrayList<Produit> getCommandes() {
		return commandes;
	}


	/**
	 * Ajoute la commande retournée aux retours
	 */
	public void ajouterCommandeRetournee(Commande commande, CarteCredit carteCredit) {

		retours.put(commande, carteCredit);
	}


	/**
	 * Permet d'inscrire le revendeur au site UniShop
	 */
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

	/**
	 * Affiche les notifications
	 */
	public void afficherNotifications() {
		for (Notification notification : notifications) {
			System.out.println(notification);
		}
	}

	/**
	 * Confirme la réception de la commande retournée
	 */
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
				SystemePaiement.rembourserMontant(numeroCarte,commande);
				idCorrect = true;
				break; // Sortir de la boucle une fois que la commande a été trouvée
			}
		}

		if (!idCorrect) {
			System.out.println("Aucune commande correspondant à l'ID fourni n'a été trouvée.");
		}
	}



	/**
	 * Mets à jour l'inventaire d'un certain produit
	 * 
	 * @param p produit
	 * @throws FileNotFoundException Exception lorsque le fichier n'est pas trouvé
	 */
	public void updateInventaire(Produit p) throws FileNotFoundException { // lorsquon on ajoute un produit à l'inventaire on le met à jour
		inventaire.add(p);
		try{
			Main.ecrireProduitCSV(p, "src/main/data/listeProduits.csv");
		} catch(FileNotFoundException e){
			e.printStackTrace();
		}

	}
	public String getPseudo() {
		return pseudo;
	}

	/**
	 * Affiche l'inventaure d'un certain produit
	 */
	public void afficherInventaire() {
		for (Produit p : inventaire) {
			System.out.println(p); // uses toString method from produit
		}
	}

	/**
	 * Sert à afficher les commentaires d'un produit qui appartient à un certain revendeur
	 * 
	 * @param utilisateur
	 */

	public void afficherCommentaires(Revendeur utilisateur){
		for (Produit p : ((Revendeur) utilisateur).inventaire){
			for (ArrayList<String> commentaire : p.listCommentaires){
				System.out.println("Produit: " + p.titre);
				System.out.println("Étoile(s): " + commentaire.get(0));
				System.out.println("Commentaire: " + commentaire.get(1));
				System.out.println();
			}
			System.out.println();
		}
	}



	public Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}


	/**
	 * Affiche les métriques du revendeur
	 * 
	 * @param utilisateur
	 */
	protected void afficherMetriques(Revendeur utilisateur) {
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
		do{
			try{
				System.out.println( "Entrez 0 pour retourner au menu principal" );

				String choix = s.nextLine();
				if ( ! Main.isNumeric(choix)){
					throw new InputMismatchException();
				} else {
					if (choix.equals("0")) {
						validInput = true;
						try {
							Utilisateur.afficherMenu(utilisateur);
						} catch (FileNotFoundException e) {
							System.out.println("Une erreur s'est produite veuillez réessayer");
						}
						break;
					}

					if(!validInput){
						throw new InputMismatchException();
					}
				}
			} catch (InputMismatchException e){
				System.out.println("Svp entrez 0!");
			}
		} while (!validInput);
	}

	/**
	 * Montre le profil du revendeur
	 */
	public void montrerProfil(){
		// afficher les informations
		System.out.println("Profil de : " + pseudo);
		System.out.println(inventaire.size() + " produits offerts");

		System.out.println(commandes.size() + " commandes reçues");

	}

	/**
	 * Cette méthode permet à un revendeur de traiter les billets de signalements issus par l'acheteur.
	 * @param revendeur le revendeur qui répond aux problèmes
	 * @param probleme L'instance de la classe Probleme associée au billet de signalement
	 * @param billet  L'instance de la classe BilletDeSignalement à traiter
	 */
	public void gererProbleme(Revendeur revendeur, Probleme probleme, BilletDeSignalement billet) {
		Scanner scanner = new Scanner(System.in);
		Acheteur acheteur = billet.getAcheteur();
		retourEchange retourEchange = new retourEchange();

		System.out.println("Nouveau billet de signalement reçu : " + billet.getDescriptionProbleme());

		// Demander au revendeur de spécifier le statut de livraison du produit défectueux
		boolean choixConfirmationLivraisonValide = false; // Boucle jusqu'à entrée valide
		while (!choixConfirmationLivraisonValide) {
			System.out.println("Étape 1. Est-ce que ce produit a été livré?");
			System.out.println("1. Oui");
			System.out.println("2. Non");

			try {
				int choixConfirmationLivraison = scanner.nextInt();
				scanner.nextLine();

				if (choixConfirmationLivraison == 1) {
					billet.setConfirmationLivraison(true);
					choixConfirmationLivraisonValide = true; // Sortir de la boucle si l'entrée est valide
				} else if (choixConfirmationLivraison == 2) {
					billet.setConfirmationLivraison(false);
					return; // Sortir de la méthode si le produit n'a pas été livré
				} else {
					System.out.println("Choix invalide. Veuillez choisir 1 pour Oui ou 2 pour Non.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Entrée invalide. Veuillez choisir 1 pour Oui ou 2 pour Non.");
				scanner.nextLine();
			}
		}

		boolean solutionAcceptee = false;
		while (!solutionAcceptee) {
			// Demander au revendeur de donner une solution
			System.out.println("Étape 2. Choisissez une des options de solution :");
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
			Notification notification = new Notification(RaisonsNotif.SOLUTION_PROBLEME);
			switch (choixSolution) {
				case 1 -> {
					billet.setDescriptionSolution("Le revendeur a proposé de réparer votre produit défectueux");
					acheteur.ajouterNotification(notification); // L'acheteur recois une réponse de la part du revendeur
					// Réexpédier le produit au revendeur
					if (acheteur.acheteurAccepteSolution(scanner)) {

						retourEchange.effectuerRetour(acheteur);
					}
				}
				case 2 -> {
					billet.setDescriptionSolution("Le revendeur a proposé le retour du produit défectueux");
					acheteur.ajouterNotification(notification); // L'acheteur recois une réponse de la part du revendeur
					// Effectuer un retour
					if (acheteur.acheteurAccepteSolution(scanner)) {
						retourEchange.effectuerRetour(acheteur);
					}
				}
				case 3 -> {
					billet.setDescriptionSolution("Le revendeur a proposé un échange du produit défectueux");
					acheteur.ajouterNotification(notification); // L'acheteur recois une réponse de la part du revendeur
					// Effectuer un échange
					if (acheteur.acheteurAccepteSolution(scanner)) {
						// Demander au revendeur de fournir plus de détails.
						System.out.println("Veuillez spécifier la description du produit de remplacement : ");
						String descriptionRemplacement = scanner.nextLine();
						billet.setDescriptionRemplacement(descriptionRemplacement);

						System.out.println("Entrez son numéro de suivi : ");
						int numSuiviRemplacement = scanner.nextInt();
						billet.setNumSuiviRemplacement(numSuiviRemplacement);
					}
				}
				default -> {
					System.out.println("Choix invalide. Aucune action n'a été prise.");
					return;
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
	}


	/**
	 * Cette méthode permet aux revendeurs de recevoir un billet de signalement provenant de l'acheteur.
	 * @param billet le billet de signalement envoyé au revendeur
	 */
	public void recevoirBilletDeSignalement(BilletDeSignalement billet) {
		BilletDeSignalement.ajouterBillet(billet);
	}

	/**
	 * Permete de changer l'état du colis en utilisant le numéro de suivi
	 * @param numSuivi Le numéro de suivi du colis
	 * @param nouveauStatut Le nouveau statut attrubuer au colis
	 */
	public void changerEtat(UUID numSuivi, StatutCommande nouveauStatut, Revendeur revendeur) throws FileNotFoundException {
		Colis colis = Colis.colisMap.get(numSuivi);
		if (colis != null && colis.getNumSuivi().equals(numSuivi)) {
			Commande commande = colis.getCommande();
			if(commande != null){
				colis.setStatut(nouveauStatut);
				commande.setEtatCommande(nouveauStatut);
				System.out.println("Le statut du colis ayant le numéro : " + numSuivi + " a été changé à " + nouveauStatut);

				Acheteur acheteur = commande.getAcheteur();
				Notification notification = new Notification(RaisonsNotif.ETAT_COMMANDE);
				acheteur.ajouterNotification(notification);

			} else {
				System.out.println("Aucune commande associée au colis avec le numéro de suivi : " + numSuivi);
			}
		}else {
			System.out.println("Aucun colis trouvé avec le numéro de suivi : " + numSuivi);

		}
		Utilisateur.afficherMenu(revendeur);


	}


}