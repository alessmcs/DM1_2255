import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;


public abstract class Utilisateur {

	private String telephone;
	private String motDePasse;
	private String courriel;
	private long temps;
	protected ArrayList<Notification> notifications = new ArrayList<>();
	private LocalTime derniereConnexion;

	public Utilisateur(String telephone, String courriel, String motDePasse) {
		this.telephone = telephone;
		this.courriel = courriel;
		this.motDePasse = motDePasse;
		this.temps = System.currentTimeMillis();
		this.derniereConnexion= LocalTime.now();
	}

	/**
	 * Désactive le compte d'un utilisateur s'il ne se connecte pas à temps
	 * 
	 * @return le temps
	 */
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

	/**
	 * Met à jour le courriel de l'utilisateur
	 * 
	 * @param courriel
	 */
	public void setCourriel(String courriel) {
		this.courriel = courriel;
	}

	/**
	 * Met à jour le mot de passe de l'utilisateur
	 * 
	 * @param motDePasse
	 */
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}


	public String getMotDePasse() {
		return motDePasse;
	}



	/**
	 * Permet de créer le profil d'un utilisateur
	 * 
	 * @throws FileNotFoundException Exception lorsque le fichier n'est pas trouvé
	 */
	public static void creerProfil() throws FileNotFoundException {
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
			case 1 -> {
				Revendeur revendeur = new Revendeur(telephone, courriel, motDePasse);
				revendeur.inscrireRevendeur();
				BaseDonnees.revendeursList.add(revendeur);
				System.out.println("Vous avez 24 heures pour vous connecter. Si vous ne respecrtez pas le delais," +
						" le compte sera annuler ");
				afficherMenu(revendeur);
			}
			case 2 -> {
				Acheteur acheteur = new Acheteur(telephone, courriel, motDePasse);
				acheteur.inscrireAcheteur();
				BaseDonnees.acheteursList.add(acheteur);
				System.out.println("Vous avez 24 heures pour vous connecter. Si vous ne respectez pas le délais," +
						" le compte sera désactivé ");
				afficherMenu(acheteur);
			}
			default -> {
				System.out.println("Choix invalide veuillez sélectionner 1 ou 2.");
				choix = Integer.parseInt(scanner.nextLine());
			}
		}
	}

	/**
	 * Permet de se connecter au site UniShop
	 */
	public static void seConnecter() {
		Scanner scanner = new Scanner((System.in));

		System.out.println(" 1. Se connecter en tant que revendeur");
		System.out.println(" 2. Se connecter en tant qu'acheteur ");

		boolean validInput = false;

		do {
			try {
				String choix = scanner.nextLine();
				switch (choix) {
				case "1" -> {
					validInput = true; // If no exception, set flag to exit the loop
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
								LocalDateTime derniereConnection = LocalDateTime.now();
								revendeur.setDerniereConnection(derniereConnection);
								ArrayList<Notification> newNotifications = Notification.notifierRevendeur(derniereConnection, revendeur);
								afficherMenu(revendeur);
							}
						}
					}
				}
				case "2" -> {
					// do while and try catch???
					boolean validInput2 = false;
					do{
							validInput = true; // If no exception, set flag to exit the loop
							System.out.println("Entrer votre pseudo");
							String pseudoAcheteur = scanner.nextLine();

							System.out.println("Veuillez entrer votre mot de passe.");
							String motDePasseAcheteur = scanner.nextLine();

							boolean profilTrouver = false;
							for (Acheteur acheteur : BaseDonnees.acheteursList) {
								if (acheteur.getPseudo().equalsIgnoreCase(String.valueOf(pseudoAcheteur)) &&
										acheteur.getMotDePasse().equalsIgnoreCase(motDePasseAcheteur)) {
									profilTrouver = true;
									validInput2 = true;

									if (acheteur.desactiver()){ // si le compte est desactivé
										System.out.println("Vous n'avez pas respecté les 24 heures. Votre compte est désactivé.");
										System.exit(0);

									} else  {
										LocalDateTime derniereConnection = LocalDateTime.now();
										acheteur.setDerniereConnection(derniereConnection);
										ArrayList<Notification> newNotifications = Notification.notifierAcheteur(derniereConnection, acheteur);

										afficherMenu(acheteur);
										break;
									}
								} else {
									profilTrouver = false;
								}
							}
							if (!profilTrouver) {
								System.out.println("Vos données sont inexactes, svp réessayer");
							}
					} while (!validInput2);

				} default -> System.out.println("Choix invalide veuillez sélectionner 1 ou 2.");
			}
			} catch (InputMismatchException e) {
				System.out.println("Choix invalide veuillez sélectionner 1 ou 2.");
				scanner.nextLine();
			} catch (FileNotFoundException e) {
				System.out.println("Une erreur c'est produite veuillez ressayer.");
				scanner.nextLine();
			}
		} while (!validInput);

	}

	LocalDateTime setDerniereConnection(LocalDateTime derniereConnection) {
		return derniereConnection;
	}


	/**
	 * Affiche le profil de l'utilisateur
	 * 
	 * @param <T> paramètre provenant de la classe Utilisateur
	 * @param utilisateur
	 * @throws FileNotFoundException Exception lorsque le fichier n'est pas trouvé
	 */
	public <T extends Utilisateur> void afficherProfil(T utilisateur) throws FileNotFoundException {
		if (utilisateur instanceof Acheteur){
			System.out.println("Bienvenue dans votre profil, " + ((Acheteur) utilisateur).getPrenom() + " " + ((Acheteur) utilisateur).getNom());
			System.out.println("Votre pseudo : " + ((Acheteur) utilisateur).getPseudo());
			System.out.println("Vos points : " + ((Acheteur) utilisateur).getPoints() + " points");
			System.out.println("Informations de contact: \n" + "Adresse courriel : " + utilisateur.getCourriel() +
					"\nTéléphone : " + utilisateur.getTelephone() + "\nAdresse d'expédition : " + ((Acheteur) utilisateur).getAdresseExpedition());
			Scanner s = new Scanner(System.in);
			System.out.println("Autres options:\n1 : Accéder à l'historique de vos commandes\n2: Accéder à vos suiveurs" +
					"\n0: Retourner au menu principal");


			int choix = Integer.parseInt(s.nextLine());
			switch(choix){
				case 1 :
					// get the historique de commandes from acheteur
					((Acheteur) utilisateur).afficherHistorique();

					System.out.println("Voulez-vous... \n1: Retourner au profil\n0 : Retourner au menu");
					int choix2 = Integer.parseInt(s.nextLine());

					switch(choix2){
						case 1: afficherProfil(utilisateur); // le profil sera affiché
						case 0: afficherMenu(utilisateur); // retourner au menu
						default:
							System.out.println("Choix invalide veuillez sélectionner 0 ou 1.");
							choix = Integer.parseInt(s.nextLine());
					}
					break;
				case 2 :
					// l'acheteur veut voir ses followers & les gérer
					((Acheteur) utilisateur).acheteurSuiviPar((Acheteur) utilisateur);

				case 0 :
					afficherMenu(utilisateur); // afficher menu pour acheteur
					break;
				default:
					System.out.println("Choix invalide veuillez sélectionner 0 ou 1.");
					choix = Integer.parseInt(s.nextLine());
			}
		}
		else if (utilisateur instanceof Revendeur){
			System.out.println("Bienvenue dans votre profil de revendeur, " + ((Revendeur) utilisateur).getPseudo());
			System.out.println("Informations de contact: \n" + "Adresse courriel : " + utilisateur.getCourriel() +
					"\nTéléphone : " + utilisateur.getTelephone() + "\nAdresse : " + ((Revendeur) utilisateur).getAdresse());
			Scanner s = new Scanner(System.in);

			System.out.println("Autres options:\n1 : Accéder à vos produits\n0: Retourner au menu principal");
			System.out.println("Note: pour promouvoir un produit, il faut accéder à l'inventaire en entrant 1");
			int choix = Integer.parseInt(s.nextLine());
			switch(choix){
				case 1 :
					// afficher la liste de produits offerts par le revendeur
					System.out.println("Votre inventaire: ");
					Revendeur.montrerInventaire((Revendeur) utilisateur);

					System.out.println("Voulez-vous... \n1: Retourner au profil\n0 : Retourner au menu");
					int choix2 = Integer.parseInt(s.nextLine());

					switch(choix2){
						case 1: afficherProfil(utilisateur); // le profil sera affiché
						case 0: afficherMenu(utilisateur); // retourner au menu
						default:
							System.out.println("Choix invalide veuillez sélectionner 0 ou 1.");
							choix = Integer.parseInt(s.nextLine());
					}
					break;
				case 0 :
					afficherMenu(utilisateur); // afficher menu pour revendeur
					break;
				default:
					System.out.println("Choix invalide veuillez sélectionner 0 ou 1.");
					choix = Integer.parseInt(s.nextLine());
			}
		}
	}


	/**
	 * Afficher les métriques d'un utilisateur
	 * 
	 * @param <T> paramètre provenant de la classe Utilisateur
	 * @param utilisateur
	 */
	public static <T extends Utilisateur> void afficherMetriques(T utilisateur) {
		if (utilisateur instanceof Revendeur){
			((Revendeur) utilisateur).afficherMetriques(utilisateur);
		} else if ( utilisateur instanceof Acheteur ){
			((Acheteur) utilisateur).afficherMetriques(utilisateur);
		}
	}


	/**
	 * Affiche le menu de l'utilisateur
	 * 
	 * @param <T> paramètre provenant de la classe Utilisateur
	 * @param utilisateur
	 * @throws FileNotFoundException Exception lorsque le fichier n'est pas trouvé
	 */
	public static <T extends Utilisateur> void afficherMenu(T utilisateur) throws FileNotFoundException {
		Scanner scanner = new Scanner((System.in));
		if (utilisateur instanceof Revendeur) {
			System.out.println("Menu principal, que souhaitez-vous ouvrir?");
			System.out.println("1. Offrir un produit");
			System.out.println("2. Confirmer Reception Retour");
			System.out.println("3. Modifier le profil");
			System.out.println("4. Afficher les métriques de mes activités");
			System.out.println("5. Voir mon profil");
			System.out.println("6. Voir mes notifications");
			System.out.println("7. Rechercher un acheteur");
			System.out.println("8. Rechercher un revendeur");
			System.out.println("9. Promouvoir un produit");
			System.out.println("10. Changer l'état d'une commande");
			System.out.println("0. Déconnexion");

			int choixUn = Integer.parseInt(scanner.nextLine());

			Revendeur revendeur = (Revendeur) utilisateur;
			switch (choixUn) {
				case 1 -> Plateforme.offrirProduit(revendeur);
				case 2 -> revendeur.confirmerReceptionRetour();
				case 3 -> revendeur.modifierProfil(revendeur);
				case 4 -> revendeur.afficherMetriques(revendeur);
				case 5 -> revendeur.afficherProfil(revendeur);
				case 6 -> revendeur.afficherNotifications();
				case 7 -> {
					Acheteur acheteur = Plateforme.rechercherAcheteur(BaseDonnees.acheteursList);

				}
				case 8 -> {
					Revendeur revendeur1 = Plateforme.rechercheRevendeur(BaseDonnees.revendeursList);
				}
				case 9 -> {
					revendeur.montrerInventaire(revendeur);
				}case 10 -> {
					for (Map.Entry<UUID, Colis> entry : Colis.colisMap.entrySet()) {
						UUID numSuivi = entry.getKey();
						Colis colis = entry.getValue();
						System.out.println("Numéro de suivi : " + numSuivi);
						System.out.println("Statut du colis : " + colis.getStatut());
						System.out.println("------------------------------");
					}
					System.out.println("Quels articules souhaitez vous modifier? ");
					UUID numSuivi = UUID.fromString(scanner.nextLine());
					System.out.println("Selectionner le nouveau statut");
					System.out.println("1. en_production");
					System.out.println("2. en_chemin");
					System.out.println("3. livree");
					int choix = scanner.nextInt();
					switch (choix) {
						case 1 -> {
							revendeur.changerEtat(numSuivi, StatutCommande.en_production, revendeur);
						}
						case 2 -> {
							revendeur.changerEtat(numSuivi, StatutCommande.en_chemin, revendeur);
						}
						case 3 -> {
							revendeur.changerEtat(numSuivi, StatutCommande.livree, revendeur);
						}
						default -> {
							System.out.println("Veuillez sélectionner 1, 2 ou 3");
						}
					}
				}
				case 0 -> {
					revendeur.seDeconnecter(revendeur);
				}default -> {System.out.println("Choix invalide veuillez sélectionner 1, 2, 3 ou 4");}

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
			System.out.println("7. Voir mon profil");
			System.out.println("8. Gérer mes suiveurs");
			System.out.println("9. Voir mes notifications");
			System.out.println("10. Chercher un acheteur");
			System.out.println("11. Chercher/Liker  un revendeur");
			System.out.println("12. Vérifier l'état de la commande. ");
			System.out.println("0. Déconnexion");

			int choix1 = Integer.parseInt(scannerUn.nextLine());
			Acheteur acheteur = (Acheteur) utilisateur;
			switch (choix1) {

				case 1 -> acheteur.confirmerReceptionCommande(acheteur);
				case 2 -> {
					ArrayList<Commande> commandesLivrees = (acheteur).obtenirCommandesLivrees();
					System.out.println("Pour quels commande souhaitez-vous signalé un problème?");

					if(commandesLivrees.isEmpty()){
						System.out.println("Aucune commande livrée trouvée. Impossible de signaler un problème.");
						afficherMenu(acheteur);
					}else{
						System.out.println("Liste des commandes livrées :");
						for (Commande commande : commandesLivrees) {
							System.out.println("Numéro de commande : " + commande.getId());
						}
						System.out.print("Entrez l'ID de la commande pour laquelle vous signalez un problème : ");
						int numeroCommande = scanner.nextInt();

						ArrayList<Revendeur> listeRevendeurs = new ArrayList<>();

						for (Commande commande : commandesLivrees) {
							if(commande.getId() == numeroCommande){
								ArrayList<Produit> produits = commande.getArticles();
								for (Produit produit :produits){
									Revendeur revendeur = produit.getRevendeur();
									listeRevendeurs.add(revendeur);
								}
							}
						}
						Probleme probleme = new Probleme();
						probleme.signalerProbleme(acheteur, listeRevendeurs);
					}
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
				case 7 -> {
					acheteur.afficherProfil(acheteur);
				}
				case 0 -> {
					acheteur.seDeconnecter(acheteur);
				}
				case 8-> {
					acheteur.acheteurSuiviPar(acheteur);

				}
				case 9 -> {
					acheteur.afficherNotifications(acheteur);
				}
				case 10 -> {
					Acheteur acheteurChercher = Plateforme.rechercherAcheteur(BaseDonnees.acheteursList);
					if (acheteurChercher != null){
					System.out.println("Voulez-vous suivre cet acheteur?" + acheteurChercher);
					System.out.println("1. Oui ");
					System.out.println("2. Non");
					System.out.println("0. Retour au Menu ");
					int choix= Integer.parseInt(scanner.nextLine());
					switch (choix){
						case 1 -> acheteur.suivreAcheteur(acheteurChercher);
						case 2,0 -> acheteur.afficherMenu(acheteur);
					}}else {
						System.out.println("Aucun revendeur n'a été trouvé.");
						afficherMenu(acheteur);
					}
				}case 11 ->{
					Revendeur revendeurChercher = Plateforme.rechercheRevendeur(BaseDonnees.revendeursList);
					if (revendeurChercher != null){
					System.out.println("Voulez-vous liké ce revendeur?" + revendeurChercher);
					System.out.println("1. Oui ");
					System.out.println("2. Non");
					System.out.println("0. Retour au Menu ");
					int choix= Integer.parseInt(scanner.nextLine());
					switch (choix){
						case 1 -> acheteur.likeRevendeur(revendeurChercher, acheteur );
						case 2,0 -> acheteur.afficherMenu(acheteur);
					}}else {
						System.out.println("Aucun revendeur n'a été trouvé.");
						afficherMenu(acheteur);
					}
				}case 12 ->{
					System.out.println("Veuillez indiquer le numéro de suivi.");
					UUID numSuivi = UUID.fromString(scanner.nextLine());
					acheteur.suivreEtat(numSuivi, acheteur);
				}

				default -> System.out.println("Choix invalide veuillez sélection 1, 2, 3, 4, 5, ou 6");
			}
		}
	}

	/**
	 * Permet de modifier le profil de l'utilisateur
	 * 
	 * @param <T> paramètre provenant de la classe Utilisateur
	 * @param utilisateur
	 * @throws FileNotFoundException Exception lorsque le fichier n'est pas trouvé
	 */
	public <T extends Utilisateur> void modifierProfil(T utilisateur) throws FileNotFoundException {
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


	/**
	 * Permet de se déconnecter de son profil (utilisateur)
	 * 
	 * @param <T> paramètre provenant de la classe Utilisateur
	 * @param utilisateur
	 */
	public <T extends Utilisateur> void seDeconnecter(T utilisateur){
		System.out.println("Voulez-vous vous déconnecter? \n1: Oui \n0: Non, retour au menu principal");
		// Quand l'utilisateur se deconnecte, on retourne a l'accueil
		Scanner scanner = new Scanner((System.in));
		boolean validInput = false;
		do{
			try{
				String choix = scanner.nextLine();
				switch(choix){
					case "1" : {
						validInput = true;
						System.out.println("Vous avez été déconnecté avec succès!\n");
						Main.accueil();
						break;
					}
					case "0" : {
						validInput = true;
						afficherMenu(utilisateur);
						break;
					}
					default : {
						System.out.println("Choix invalide veuillez sélectionner 0 ou 1");
					}
				}
			} catch (InputMismatchException e){
				System.out.println("Choix invalide veuillez sélectionner 0 ou 1");
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e);
			}
		} while (!validInput);
	}

	/**
	 * Affiche les notifications de l'utilisateur
	 * @param utilisateur l'utilisateur connecté
	 * @param <T>
	 */
	public  <T extends Utilisateur> void afficherNotification(T utilisateur){
		try {
			if (utilisateur instanceof Revendeur) {
				Revendeur revendeur = (Revendeur) utilisateur;
				System.out.println("Notifications pour le revendeur " + revendeur.getPseudo() + ":");
				revendeur.afficherNotifications();
			} else if (utilisateur instanceof Acheteur) {
				Acheteur acheteur = (Acheteur) utilisateur;
				System.out.println("Notifications pour l'acheteur " + acheteur.getPseudo() + " depuis la dernière connexion :");
				acheteur.afficherNotifications(acheteur);

			}
		}catch (FileNotFoundException e) {
			try {
				afficherMenu(utilisateur);
			} catch (FileNotFoundException ex) {
				System.out.println("Erreur veuillez ressayer");
			}
	}}
	public String getCourriel() {
		return this.courriel;
	}
}



