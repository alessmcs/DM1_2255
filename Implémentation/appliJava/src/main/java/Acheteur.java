import java.io.FileNotFoundException;
import java.util.*;

public class Acheteur extends Utilisateur {


	private Adresse adresseExpedition;
	private String prenom;
	private String nom;
	private int points;
	protected ArrayList<Commande> historiqueCommandes = new ArrayList<>();
	protected String pseudo;
	protected ArrayList<ArrayList<String>> listeCommentaires = new ArrayList<>();

	public Panier panier = new Panier();

	protected ArrayList<Acheteur> listeSuiveurs = new ArrayList<>();
	private Set<Revendeur> revendeursLikes = new HashSet<>();
	private CarteCredit carteCredit;
	protected ArrayList<Notification> notifications = new ArrayList<>();

	public Acheteur(String telephone, String courriel, String motDePasse) {
		super(telephone,courriel,motDePasse);
		this.prenom= prenom;
		this.nom= nom;
		panier = new Panier();
	}
	public void ajouterNotification(Notification notification) {
		notifications.add(notification);
	}

	public ArrayList<Notification> getNotifications() {
		return notifications;
	}
	
	public void suivreAcheteur(Acheteur acheteur) throws FileNotFoundException {
			Acheteur acheteurAjouter = Plateforme.rechercherAcheteur(BaseDonnees.acheteursList);
			if(acheteurAjouter != null){
				if(!this.listeSuiveurs.contains(acheteurAjouter)){
					this.listeSuiveurs.add(acheteurAjouter);
					System.out.println("Vous suivez maintennat" + acheteurAjouter.getPseudo());
					BaseDonnees.acheteursList.get(BaseDonnees.acheteursList.indexOf(acheteurAjouter)).ajouterSuiveur(this);

					try {
						afficherMenu(acheteur);
					} catch (FileNotFoundException e) {
						System.out.println("Une erreur c'est produite veuilles réessayer.");
					}

					acheteurAjouter.ajouterSuiveur(this);
					Notification nouvelleNotification = new Notification(RaisonsNotif.NOUVEL_ABONNE);
					acheteurAjouter.ajouterNotification(nouvelleNotification);

				}else{
						System.out.println("Vous etes déjà abonné a cet acheteur");
					}
		}else{
			System.out.println("Aucun acheteur trouvé avec ce pseudo");
			suivreAcheteur(acheteur);
			}

	}
	public void ajouterSuiveur(Acheteur suiveur) {
			this.listeSuiveurs.add(suiveur);
	}
	public void retirerAcheteur(Acheteur suiveur) {
		this.listeSuiveurs.remove(suiveur);
	}
	public void acheteurSuiviPar(Acheteur acheteur) throws FileNotFoundException {
		Scanner scannerUn = new Scanner((System.in));

		System.out.println("Que voulez-vous faire? ");
		System.out.println("1. Voir ses suiveurs");
		System.out.println("2. Gérer ses suiveurs");
		System.out.println("0. Retour au menu ");
		int choix= Integer.parseInt(scannerUn.nextLine());

		switch (choix){
			case 1 -> {
				System.out.println(acheteur.listeSuiveurs);
				acheteurSuiviPar(acheteur);
			}
			case 2 -> {
				System.out.println("Que voulez-vous faire pour gérer vos suiveurs?");
				System.out.println("1. Suivre un acheteur");
				System.out.println("2. Supprimer un suiveur");
				System.out.println("0. Retour au menu ");
				int gestionChoix = Integer.parseInt(scannerUn.nextLine());

				switch (gestionChoix) {
					case 1 -> {
						suivreAcheteur(acheteur);

					}
					case 2 -> {
						if (listeSuiveurs.isEmpty()) {
							System.out.println("Votre liste de suiveurs est vide. Retour au menu principal.");
							try {
								afficherMenu(acheteur);
							} catch (FileNotFoundException e) {
								System.out.println("Veuillez réessayer, une erreur c'est produite.");
								scannerUn.nextLine();
							}
						} else {
							System.out.println("Voici la liste de vos suiveurs actuels: " + listeSuiveurs);
							System.out.println("Veuillez indiquer le pseudo de l'acheteur à supprimer.");
							String acheteurPseudoSupp = scannerUn.nextLine();

							Acheteur acheteurSupp = Plateforme.rechercherAcheteur(listeSuiveurs);

							if (acheteurSupp != null) {
								acheteur.listeSuiveurs.remove(acheteurSupp);
								System.out.println("Achteur " + acheteurPseudoSupp + " retiré de votre liste de suiveurs");
								acheteurSupp.retirerAcheteur(this);
							} else {
								System.out.println("Aucun acheteur trouvé avec ce pseudo dans la liste de suiveurs : " + acheteurPseudoSupp);
							}
						}
					}
					case 0 -> {
						try {
							afficherMenu(acheteur);
						} catch (FileNotFoundException e) {
							System.out.println("Veuillez réessayer, une erreur c'est produite.");
							scannerUn.nextLine();
						}
						}
					}
				}}}


	public void ajouterAuPanier(Produit p){
		this.panier.ajouterArticle(p);
	}

	public void addListeCommentaires(ArrayList<String> c){
		listeCommentaires.add(c);
	}

	public boolean confirmerReceptionCommande() {
		Scanner s = new Scanner(System.in);

		System.out.println("Entrez l'ID de la commande que vous voulez confirmer");
		for(Commande c : historiqueCommandes){
			c.commandeToString();
		}
		while(true){
			String choix = s.nextLine();
			if (! Main.isNumeric(choix) ){
				System.out.println("Svp entrez un chiffre pour l'ID!");
			} else {
				for(Commande c : historiqueCommandes){
					if ( Integer.parseInt(choix) == c.getId() && c.getStatutCommande() != StatutCommande.livree ){
						c.setEtatCommande(StatutCommande.livree);
						break;
					} else if( c.getStatutCommande() == StatutCommande.livree ) {
						System.out.println("Cette commande est deja livrée");
						Notification nouvelleNotification = new Notification(RaisonsNotif.LIVRAISON_CONFIRMEE);


					} else if ( c.getStatutCommande() == StatutCommande.en_production) {
						System.out.println("Cette commande n'a pas encore été envoyée.");
					}
					else {
						continue;
					}
				}
			}
		}
	}

	public void inscrireAcheteur() {

		Scanner scanner = new Scanner(System.in);

		System.out.println("Veuillez entrer votre prénom :");
		String prenom = scanner.nextLine();

		System.out.println("Veuillez entrer votre nom :");
		String nom = scanner.nextLine();

		System.out.println("Veuillez entrer votre pseudo :");
		String pseudo = scanner.nextLine();

		System.out.println("Veuillez entrer votre adresse d'expédition ");
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

			adresseExpedition = new Adresse(street, city, province, postalCode, country);
			// valider l'adresse
			boolean valide = SystemeLivraison.validerInfosLivraison(adresseExpedition);
			if (valide) break;
			else System.out.println("L'adresse entrée est invalide, svp réessayer");
		}
		setPrenom(prenom);
		setNom(nom);
		setAdresseExpedition(adresseExpedition);
		setPseudo(pseudo);

		while (true) {
			System.out.println("----- Informations de paiement ----- \nNuméro de carte de crédit: ");
			// l'utilisateur entre un numéro de carte, date d'expiration, cvc
			String numCarte = scanner.nextLine();
			System.out.println("Date d'expiration (MMAA)");
			String dateExp = scanner.nextLine();
			System.out.println("CVC (ex: 999)");
			String cvc = scanner.nextLine();
			System.out.println("Veuillez entrer le solde de votre carte de crédit :");
			double soldeCarte = scanner.nextDouble();
			scanner.nextLine();
			// validation des infos
			boolean valide = SystemePaiement.validerInfosPaiement(numCarte, dateExp, cvc);
			if (valide) {
				CarteCredit carteCredit1 = new CarteCredit(numCarte, dateExp, cvc, soldeCarte);
				getAcheteur().setCarteCredit(carteCredit1);
			} else {
				System.out.println("Les informations de paiement sont invalides, svp réessayer");
			}
		}

	}
	public CarteCredit getCarteCredit() {
		return carteCredit;
	}

	public void setCarteCredit(CarteCredit carteCredit) {
		this.carteCredit = carteCredit;
	}

	public void montrerProfil(){
		// afficher les informations
		System.out.println("Profil de : " + pseudo);
		System.out.println(prenom + nom);
		System.out.println(listeSuiveurs.size() + " suiveurs");
		System.out.println(listeCommentaires.size() + " commentaires rédigés");
	}
	public void afficherNotifications(Acheteur acheteur) throws FileNotFoundException {

		if (notifications.isEmpty()) {
			System.out.println("Vous n'avez aucune notification");
			try {
				afficherMenu(acheteur);
			} catch (FileNotFoundException e) {
				System.out.println("Veuillez réessayer, une erreur c'est produite.");
				afficherMenu(acheteur);
			}

		} else {
			for (Notification notification : notifications) {
				System.out.println(notification);
			}
		}
	}


	public void payerDifference(double difference) {
		double nouveauSolde = carteCredit.getSolde() - difference;
	}



	public void likeRevendeur(Revendeur revendeur, Acheteur acheteur) {
		if(acheteur.revendeursLikes.contains(revendeur)){
			System.out.println("Vous avez déjà liké ce revendeur.");
		} else{
			acheteur.revendeursLikes.add(revendeur);
			revendeur.acheteurQuiAime.add(acheteur);
			System.out.println("Revendeur Liké avec succès.");
		}
	}


	public String getPseudo() {
		return pseudo;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}
	public int getPoints() {
		return this.points;
	}
	public Adresse getAdresseExpedition() {
		return this.adresseExpedition;
	}

	public Acheteur getAcheteur(){
		return this;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	public void setAdresseExpedition(Adresse adresseExpedition) {
		this.adresseExpedition = adresseExpedition;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public void setPoints(int points){
		this.points = points;
	}


	public void addHistorique(Commande commande) {
		historiqueCommandes.add(commande);
	}

	protected String  afficherHistorique(){
		// parse the csv de toutes les commandes & only keep the ones with the correct username
		// System.out.println(Arrays.toString(value.split(",(?=\")"))); regex to parse the string
		String message = null;
		if (historiqueCommandes.size() == 0){
			message = "Vous n'avez passé aucune commande pour le moment!";
			System.out.println(message);
			return message;
		}
		for(Commande c : historiqueCommandes){
			c.commandeToString();
			System.out.println("\n");
		}
		return null;
	}
	public ArrayList<Commande> getHistoriqueCommandes(){
		return historiqueCommandes;
	}

	protected void afficherMetriques(Acheteur utilisateur){
		Scanner scanner = new Scanner(System.in);
		int nbCommandes = utilisateur.historiqueCommandes.size();

		ArrayList<String> produitsAchetes = new ArrayList<>();
		ArrayList<String> commentairesDonnes = new ArrayList<>(); // 2e elem du arrayList
		for (Commande c : utilisateur.historiqueCommandes) {
			// Voir les produits achetés
			ArrayList<Produit> produits = c.getArticles();
			for (Produit p : produits) {
				if (!produitsAchetes.contains(p.toString())) {
					produitsAchetes.add(p.toString());
				}
			}
		}

		System.out.println("Vos métriques d'acheteur: ");
		System.out.println("Nombre de commandes : " + nbCommandes);
		System.out.println("Produits achetés");
		for (String s : produitsAchetes) {
			System.out.println(s);
		}
		System.out.println("Nombre total: " + produitsAchetes.size());
		System.out.println("Vos commentaires: ");
		for (ArrayList<String> com : utilisateur.listeCommentaires) {
			System.out.println("\u001B[1m" + "Étoile(s): " + "\u001B[0m" + com.get(0));
			System.out.println("\u001B[1m" + "Like: " + "\u001B[0m" + com.get(1));
			System.out.println("\u001B[1m" + "Commentaire: " + "\u001B[0m" + com.get(2));
		}

		System.out.println("Souhaitez-vous retourner au menu principal?\n1-Oui\n2-Non");
		int gestionChoix = Integer.parseInt(scanner.nextLine());

		if (gestionChoix == 1) {
		try {
			afficherMenu(utilisateur);
			} catch (FileNotFoundException e) {
			System.out.println("Une erreur c'est produite veuilles réessayer.");
			}

		}
	}

	public ArrayList<Commande> obtenirCommandesLivrees() {
		ArrayList<Commande> commandesLivrees = new ArrayList<>();

		for (Commande commande : historiqueCommandes) {
			if (commande.getStatutCommande() == StatutCommande.livree) {
				commandesLivrees.add(commande);
			}
		}

		return commandesLivrees;
	}

	public ArrayList<Acheteur> getListeSuiveurs() {
		return listeSuiveurs;
	}

}