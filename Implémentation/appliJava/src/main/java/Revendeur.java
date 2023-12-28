import java.io.FileNotFoundException;
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


	public void gererProbleme() {
		// TODO - implement Revendeur.gérerProbleme
		throw new UnsupportedOperationException();
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

}