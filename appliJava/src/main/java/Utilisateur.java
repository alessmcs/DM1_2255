import java.util.Scanner;
import java.util.ArrayList;


public abstract class Utilisateur {

	private String telephone;
	private String motDePasse;
	private String courriel;
	private long temps;

	public Utilisateur(String telephone, String courriel, String motDePasse) {
		this.telephone = telephone;
		this.courriel = courriel;
		this.motDePasse = motDePasse;
		this.temps = System.currentTimeMillis();

	}
	public boolean desactiver() {
		long tempsAcctuel = System.currentTimeMillis();
		long tempsPassee= tempsAcctuel- temps;
		long twentyFourHoursInMillis = 24 * 60 * 60 * 1000;
		return tempsPassee > twentyFourHoursInMillis;
	}

	public String getTelephone() { return this.telephone; }
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setCourriel(String courriel) {
		this.courriel = courriel;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	public String getMotDePasse() {
		return motDePasse;
	}



	public static void creerProfil() {
		Scanner scanner = new Scanner((System.in));

		System.out.println("Veuillez entrer votre numéro de téléphone.");
		String telephone = scanner.nextLine();
		while (!telephone.matches("\\d+") || telephone.length() != 10) {
			System.out.println("Le numéro de téléphone doit contenir uniquement des chiffres et etre de longueur 10. " +
					"Veuillez réessayer :");
			telephone = scanner.nextLine();
		}

		System.out.println("Veuillez entrer un courriel.");
		String courriel = scanner.nextLine();
		while (!courriel.contains("@")) {
			System.out.println("L'adresse courriel doit contenir un '@'. Veuillez réessayer :");
			courriel = scanner.nextLine();
		}

		System.out.println(" Veuillez entrer un mot de passe.");
		String motDePasse = scanner.nextLine();

		System.out.println("Veuillez selectionner le type de compte que vous souhaitez creer.");
		System.out.println(" 1. Revendeur");
		System.out.println(" 2. Acheteur ");
		int choix = Integer.parseInt(scanner.nextLine());

		switch (choix) {
			case 1:
				Revendeur revendeur = new Revendeur(telephone, courriel, motDePasse);
				revendeur.inscrireRevendeur();
				BaseDonnees.revendeursList.add(revendeur);
				System.out.println("Vous avez 24 heures pour vous connecter. Si vous ne respecrtez pas le delais," +
						" le compte sera annuler ");
				afficherMenu(revendeur);
				break;
			case 2:
				Acheteur acheteur = new Acheteur(telephone, courriel, motDePasse);
				acheteur.inscrireAcheteur();

				BaseDonnees.acheteursList.add(acheteur);
				System.out.println("Vous avez 24 heures pour vous connecter. Si vous ne respectez pas le delais," +
						" le compte sera désactivé ");
				afficherMenu(acheteur);
				break;
			default:
				System.out.println("Choix invalide veuillez selectionner 1 ou 2.");
				choix = Integer.parseInt(scanner.nextLine());
		}
	}

	public static void seConnecter() {
		Scanner scanner = new Scanner((System.in));

		System.out.println(" 1. Se connecter en tant que revendeur");
		System.out.println(" 2. Se connecter en tant qu'acheteur ");

		int choix = Integer.parseInt(scanner.nextLine());

		switch (choix) {
			case 1 -> {
				System.out.println("Entrer votre pseudo");
				String pseudoRevendeur = scanner.nextLine();

				System.out.println("Veuillez entrer votre mot de passe");
				String motDePasseRevendeur = scanner.nextLine();

				boolean profilTrouver = false;
				for (Revendeur revendeur : BaseDonnees.revendeursList) {
					if (revendeur.getPseudo().equalsIgnoreCase(pseudoRevendeur) &&
							revendeur.getMotDePasse().equals(motDePasseRevendeur)) {
						profilTrouver = true;
						if (revendeur.desactiver()){
							System.out.println("Vous n'avez pas respecté les 24 heures. Votre compte est désactivé.");
							System.exit(0);

						} else  {
							afficherMenu(revendeur);
						}
					}
				}
			}
			case 2 -> {
				while(true){
					System.out.println("Entrer votre pseudo");
					String pseudoAcheteur = scanner.nextLine();

					System.out.println("Veuillez entrer votre mot de passe.");
					String motDePasseAcheteur = scanner.nextLine();
					boolean profilTrouver = false;
					for (Acheteur acheteur : BaseDonnees.acheteursList) {
						if (acheteur.getPseudo().equalsIgnoreCase(String.valueOf(pseudoAcheteur)) &&
								acheteur.getMotDePasse().equalsIgnoreCase(motDePasseAcheteur)) {
							profilTrouver = true;
							if (acheteur.desactiver()){
								System.out.println("Vous n'avez pas respecté les 24 heures. Votre compte est désactivé.");
								System.exit(0);

							} else  {
								afficherMenu(acheteur);
								break;
							}
						} else {
							profilTrouver = false;
						}
					} if (!profilTrouver) System.out.println("Vos données sont inexactes, svp réessayer");
				}

			}
			default -> System.out.println("Choix invalide veuillez selectionner 1 ou 2.");
		}
	}

	public static void afficherProfil() {
		// TODO - implement Utilisateur.afficherProfil
		throw new UnsupportedOperationException();
	}

	public static <T extends Utilisateur> void afficherMetriques(T utilisateur) {
		if (utilisateur instanceof Revendeur){
			float revenu = 0;
			int nbVendu = 0;
			int nbArticles = ((Revendeur) utilisateur).inventaire.size();
			for (Produit p : ((Revendeur) utilisateur).inventaire){
				if(p.qteInitiale != p.qteEnStock){
					int n = p.qteInitiale - p.qteEnStock;
					nbVendu += n;
					revenu += p.prix * n;
				}
			}

		} else if ( utilisateur instanceof Acheteur ){
			int nbCommandes = ((Acheteur) utilisateur).historiqueCommandes.size();

			ArrayList<String> produitsAchetes = new ArrayList<>();
			ArrayList<String> commentairesDonnes = new ArrayList<>(); //2e elem du arrayList
			for(Commande c : ( (Acheteur) utilisateur).historiqueCommandes ){
				//voir les produits achetés
				ArrayList<Produit> produits = c.getArticles();
				for (Produit p : produits){
					if (!produitsAchetes.contains(p)){
						produitsAchetes.add(p.toString());
					}
				}
			}

			System.out.println("Vos métriques d'acheteur: ");
			System.out.println("Nombre de commandes : " + nbCommandes);
			System.out.println("Produits achetés");
			for (String s : produitsAchetes){
				System.out.println(s);
			}
			System.out.println("Nombre total: " + produitsAchetes.size());
			System.out.println("Vos commentaires: ");
			for (ArrayList<String> com : ((Acheteur) utilisateur).listeCommentaires){
				System.out.println("\u001B[1m" + "Étoile(s): " + "\u001B[0m" + com.get(0));
				System.out.println("\u001B[1m" + "Like: " + "\u001B[0m" + com.get(1));
				System.out.println("\u001B[1m" + "Commentaire: " + "\u001B[0m" + com.get(2));
			}
		}
	}

	public static <T extends Utilisateur> void afficherMenu(T utilisateur) {
		Scanner scanner = new Scanner((System.in));
		if (utilisateur instanceof Revendeur) {
			System.out.println("Menu principal, que souhaitez-vous ouvrir?");
			System.out.println("1. Offrir un produit");
			System.out.println("2. Gérer problème");
			System.out.println("3. Confirmer Reception Retour");
			System.out.println("4. Modifier le profil");
			System.out.println("5. Afficher métriques");
			System.out.println("6. Déconnexion");

			int choixUn = Integer.parseInt(scanner.nextLine());

			Revendeur revendeur = (Revendeur) utilisateur;
			switch (choixUn) {
				case 1 -> Plateforme.offrirProduit(revendeur);
				case 2 -> revendeur.gererProbleme();
				case 3 -> revendeur.confirmerReceptionRetour();
				case 4 -> revendeur.modifierProfil(revendeur);
				case 5 -> revendeur.afficherMetriques(revendeur);
				default -> System.out.println("Choix invalide veuillez sélectionner 1, 2 ou 3.");
			}

		} else if (utilisateur instanceof Acheteur) {
			Scanner scannerUn = new Scanner((System.in));

			System.out.println("Menu principal, que souhaitez-vous ouvrir?");
			System.out.println("1. Confirmer Reception Commande.");
			System.out.println("2. Signaler un problème.");
			System.out.println("3. Modifier profil.");
			System.out.println("4. Voir catalogue de produits");
			System.out.println("5. Voir mon panier");
			System.out.println("6. Afficher les métriques de mes activités");
			System.out.println("7. Consulter l'état de ma commande");

			int choix1 = Integer.parseInt(scannerUn.nextLine());
			Acheteur acheteur = (Acheteur) utilisateur;
			switch (choix1) {

				case 1 -> acheteur.confirmerReceptionCommande();
				case 2 -> {
					Probleme probleme = new Probleme();
					probleme.signalerProbleme();
				}
				case 3 -> {
					acheteur.modifierProfil(acheteur);
				}
				case 4 -> {
					Catalogue.voirCatalogue(acheteur);
				}
				case 5 -> {
					acheteur.panier.voirPanier(acheteur);
				}
				case 6 -> {
					acheteur.afficherMetriques(acheteur);
				}
				default -> System.out.println("Choix invalide veuillez selectionner 1, 2 ou 3.");
			}
		}
	}
	public <T extends Utilisateur> void modifierProfil(T utilisateur) {
		Scanner scanner = new Scanner(System.in);

		if (utilisateur instanceof Revendeur) {
			Revendeur revendeur = (Revendeur) utilisateur;
			System.out.println("Quelles informations souhaitez-vous modifier?");
			System.out.println("1. Numéro de téléphone");
			System.out.println("2. Courriel");
			System.out.println("3. Mot de passe");
			System.out.println("5. Adresse civil");
			System.out.println("6. Pseudo");
			System.out.println("7. Retour au menu principal");

			int choix = Integer.parseInt(scanner.nextLine());
			switch (choix) {
				case 1:
					System.out.println("Veuillez entrer votre nouveau numéro de téléphone:");
					String newTelephone = scanner.nextLine();

					revendeur.setTelephone(newTelephone);
					System.out.println("Numéro de téléphone mis à jour avec succès!");
					break;
				case 2:
					System.out.println("Veuillez entrer votre nouveau courriel:");
					String courriel = scanner.nextLine();

					revendeur.setCourriel(courriel);
					System.out.println("Courriel mis à jour avec succès!");
					break;
				case 3:
					System.out.println("Veuillez entrer votre nouveau mot de passe:");
					String motDePasse = scanner.nextLine();

					revendeur.setMotDePasse(motDePasse);
					System.out.println("Mot de passe mis à jour avec succès!");
					break;

				case 5:
					System.out.println("Veuillez entrer votre adresse civile: ");

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

						Adresse adresse = new Adresse(street, city, province, postalCode, country);
						// valider l'adresse
						boolean valide = SystemeLivraison.validerInfosLivraison(adresse);
						if (valide) {
							revendeur.setAdresse(adresse);
							break;
						} else System.out.println("L'adresse entrée est invalide, svp réessayer");
					}

					System.out.println("Adresse civile mise à jour avec succès!");
					break;
				case 6:
					System.out.println("Veuillez entrer votre pseudo");
					String pseudo = scanner.nextLine();
					revendeur.setPseudo(pseudo);
					System.out.println("Pseudo mis à jour avec succès!");
					break;

				case 7:
					afficherMenu(revendeur);
					break;
				default:
					System.out.println("Choix invalide veuillez sélectionner autre chose.");
			}
	} else if (utilisateur instanceof Acheteur) {
			Acheteur acheteur = (Acheteur) utilisateur;
			System.out.println("Quelles informations souhaitez-vous modifier?");
			System.out.println("1. Numéro de téléphone");
			System.out.println("2. Courriel");
			System.out.println("3. Mot de passe");
			System.out.println("4. Prénom");
			System.out.println("5. Nom");
			System.out.println("6. Pseudo");
			System.out.println("7. Adresse d'expédition");
			System.out.println("8. Retour au menu principal");

			int choix = Integer.parseInt(scanner.nextLine());

			switch (choix) {
				case 1:
					System.out.println("Veuillez entrer votre nouveau numéro de téléphone:");
					String newTelephone = scanner.nextLine();

					acheteur.setTelephone(newTelephone);
					System.out.println("Numéro de téléphone mis à jour avec succès!");
					break;
				case 2:
					System.out.println("Veuillez entrer votre nouveau courriel:");
					String courriel = scanner.nextLine();

					acheteur.setCourriel(courriel);
					System.out.println("Courriel mis à jour avec succès!");
					break;
				case 3:
					System.out.println("Veuillez entrer votre nouveau mot de passe:");
					String motDePasse = scanner.nextLine();

					acheteur.setMotDePasse(motDePasse);
					System.out.println("Mot de passe mis à jour avec succès!");
					break;
				case 4:
					System.out.println("Veuillez entrer un nouveau prénom:");
					String prenom = scanner.nextLine();

					acheteur.setPrenom(prenom);
					System.out.println("Prénom mis à jour avec succès!");
					break;
				case 5:
					System.out.println("Veuillez entrer un nouveau nom:");
					String nom = scanner.nextLine();

					acheteur.setNom(nom);
					System.out.println("Nom mis à jour avec succès!");
					break;
				case 6:
					System.out.println("Veuillez entrer votre pseudo");
					String pseudo = scanner.nextLine();

					acheteur.setPseudo(pseudo);
					System.out.println("Pseudo mis à jour avec succès!");
					break;
				case 7:
					System.out.println("Veuillez entrer votre adresse d'expédition :");
					String adresseExpedition = scanner.nextLine();

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

						Adresse adresse = new Adresse(street, city, province, postalCode, country);
						// valider l'adresse
						boolean valide = SystemeLivraison.validerInfosLivraison(adresse);
						if (valide) {
							acheteur.setAdresseExpedition(adresse);
							break;
						}
						else System.out.println("L'adresse entrée est invalide, svp réessayer");
					}

					System.out.println("Adresse d'expédition mise à jour avec succès!");
					break;
				case 8:
					afficherMenu(acheteur);
					break;
				default:
					System.out.println("Choix invalide veuillez sélectionner autre chose.");
			}
		}
		System.out.println("Voulez-vous modifier autre chose? ");
			System.out.println("1. Oui");
			System.out.println("2. Non");

			int choixContinue = Integer.parseInt(scanner.nextLine());
			switch (choixContinue){
				case 1:
					modifierProfil(utilisateur);
					break;
				case 2:
					afficherMenu(utilisateur);
			}
		}

	public String getCourriel() {
		return this.courriel;
	}
}



