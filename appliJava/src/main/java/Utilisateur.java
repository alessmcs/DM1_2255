import java.util.Scanner;


public abstract class Utilisateur {

<<<<<<< HEAD
	private String telephone;
	private String motDePasse;
	private String courriel;
	private long temps;
=======
	private int telephone;
	private String motDePasse;
	private String courriel;
>>>>>>> b88a840f83a9681722afe602713d0b33fe3d26c9

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


	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Bienvenue dans Unishop.");
		System.out.println("Veuillez selection ce que vous souhaitez effectuer sur le site en choisant 1 ou 2.");
		System.out.println("1. Creer un profil");
		System.out.println("2. Se connecter ");

		int choix = Integer.parseInt(scanner.nextLine());
		switch (choix) {
			case 1:
				creerProfil();
				break;
			case 2:
				seConnecter();
				break;
			default:
				System.out.println("Choix invalide veuillez selectionner 1 ou 2.");
		}
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

		System.out.println(" Veuillez entrer votre adresse.");
		String adresse = scanner.nextLine();

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
				System.out.println("Vous avez 24 heures pour vous connecter. Si vous ne respecrtez pas le delais," +
						" le compte sera annuler ");
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
							System.out.println("Vous n'avez pas respecter les 24 heures. Votre compte est désactiver.");
							System.exit(0);

						} else  {
							afficherMenu(revendeur);
						}
					}
				}
			}
			case 2 -> {
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
							System.out.println("Vous n'avez pas respecter les 24 heures. Votre compte est désactiver.");
							System.exit(0);

						} else  {
							afficherMenu(acheteur);
						}
					}
				}
			}
			default -> System.out.println("Choix invalide veuillez selectionner 1 ou 2.");
		}
	}

	public static void afficherProfil() {
		// TODO - implement Utilisateur.afficherProfil
		throw new UnsupportedOperationException();
	}

	public void afficherMetriques() {
		// TODO - implement Utilisateur.afficherMetriques
		throw new UnsupportedOperationException();
	}

	public static <T extends Utilisateur> void afficherMenu(T utilisateur) {
		Scanner scanner = new Scanner((System.in));
		if (utilisateur instanceof Revendeur) {
			System.out.println("Menu principal, que souhaitez-vous ouvrir?");
			System.out.println("1. Update Inventaire");
			System.out.println("2. Gérer Probleme");
			System.out.println("3. Confirmer Reception Retour");
			System.out.println("4. Modifier le profil");

			int choixUn = Integer.parseInt(scanner.nextLine());

			Revendeur revendeur = (Revendeur) utilisateur;
			switch (choixUn) {
				case 1 -> revendeur.updateInventaire();
				case 2 -> revendeur.gérerProbleme();
				case 3 -> revendeur.confirmerReceptionRetour();
				case 4 -> revendeur.modifierProfil(revendeur);
				default -> System.out.println("Choix invalide veuillez selectionner 1, 2 ou 3.");
			}

		} else if (utilisateur instanceof Acheteur) {
			Scanner scannerUn = new Scanner((System.in));

			System.out.println("Menu principal, que souhaitez-vous ouvrir?");
			System.out.println("1. Confirmer Reception Commande.");
			System.out.println("2. Signaler un probleme.");
			System.out.println("3. Modifier profil.");

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
					String adresse = scanner.nextLine();
					revendeur.setAdresse(adresse);
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

					acheteur.setAdresseExpedition(adresseExpedition);
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
	}



