import java.util.ArrayList;
public class Acheteur extends Utilisateur {

	static String pseudo;
	private static Adresse adresseExpedition;
	private static int points;
	private static ArrayList<Commande> historiqueCommandes = new ArrayList<>();
	private ArrayList<Acheteur> followers;
	private int classement;

	private static Panier panier = new Panier();

	//TODO: constructor & each acheteur has an individual panier

	public  void ajouterPanierAcheteur(Produit p) {
		panier.ajouterArticle(p);
	}

	public void voirPanierAcheteur(){
		panier.voirPanier();
	}


	public boolean confirmerReceptionCommande() {
		// TODO - implement Acheteur.confirmerReceptionCommande
		throw new UnsupportedOperationException();
	}

	public void inscrireAcheteur() {
		// TODO - implement Acheteur.inscrireAcheteur
		throw new UnsupportedOperationException();
	}

	// L'acheteur connecté peut suivre un autre acheteur de son choix
	public void suivreAcheteur(Acheteur a) {
		// TODO : vérifier si l'acheteur est dans la liste d'acheteurs dans la base de données (-> Soundous)
		a.ajouterFollower(this);
		points += 5; // l'acheteur qui suit un autre utilisateur gagne 5 points
	}

	public void unfollow(Acheteur a){
		a.supprimerFollower(this);
		points -= 5;
	}

	// La liste de suiveurs de l'acheteur choisi par un autre est mise à jour et il gagne des points
	public void ajouterFollower(Acheteur a){
		followers.add(a);
		points += 5; // l'utilisateur qui se fait suivre gagne 5 points
	}

	public void supprimerFollower(Acheteur a){
		followers.remove(a);
		points -= 5;
	}



	public void payerDifference() {
		// TODO - implement Acheteur.payerDifference
		throw new UnsupportedOperationException();
	}

	public int voirClassement() {
		// TODO - implement Acheteur.voirClassement
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param points
	 */
	public void echangerPoints(int points) {
		// TODO - implement Acheteur.echangerPoints
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param r
	 */
	public void likeRevendeur(Revendeur r) {
		// TODO - implement Acheteur.likeRevendeur
		throw new UnsupportedOperationException();
	}

	public class pseudo {

	}

	public static Adresse getAdresseExpedition(){
		return adresseExpedition;
	}

	public static void addHistorique(Commande c){
		historiqueCommandes.add(c);
	}

	public static int getPoints(){ return points; };

	public static void setPoints(int newPoints){
		points += newPoints;
		// si l'acheteur utilise les points pour avoir un rabais, newPoints < 0 alors ca sera une soustraction
		// si l'acheteur gagne des points, newPoints > 0 alors c'est une addition
	}
}