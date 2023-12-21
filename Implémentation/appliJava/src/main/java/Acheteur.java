import java.util.ArrayList;
import java.util.Scanner;

public class Acheteur extends Utilisateur {


	private Adresse adresseExpedition;
	private String prenom;
	private String nom;
	private int points;
	protected ArrayList<Commande> historiqueCommandes = new ArrayList<>();
	private String pseudo;
	protected ArrayList<ArrayList<String>> listeCommentaires = new ArrayList<>();

	public Panier panier = new Panier();

	protected ArrayList<Acheteur> listeSuiveurs = new ArrayList<>();

	public void suivreAcheteur(Acheteur acheteur) {

	}

	public void acheteurSuiviPar(Acheteur acheteur){

	}

	// Constructeur
	public Acheteur(String telephone, String courriel, String motDePasse) {
		super(telephone,courriel,motDePasse);
		panier = new Panier();
	}

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


	}

	/*
		Affiche les informations du profil de l'acheteur
	 */
	public void montrerProfil(){
		// afficher les informations
		System.out.println("Profil de : " + pseudo);
		System.out.println(prenom + nom);
		System.out.println(listeSuiveurs.size() + " suiveurs");
		System.out.println(listeCommentaires.size() + " commentaires rédigés");
	}

	public void payerDifference() {
		throw new UnsupportedOperationException();
	}

	public int voirClassement() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param points
	 */
	public void echangerPoints(int points) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param r
	 */
	public void likeRevendeur(Revendeur r) {
		throw new UnsupportedOperationException();
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

	// méthode pour gérer les commandes

	/*
		Affiche l'historique des commandes de l'utilisateur
	 */
	protected String afficherHistorique(){
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

	// TODO: brainstorm métriques
	/*
		Affiche les métriques de l'utilisateur
	 */
	protected void afficherMetriques(Acheteur utilisateur){
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