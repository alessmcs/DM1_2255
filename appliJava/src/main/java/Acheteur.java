import java.util.ArrayList;
import java.util.List;
public class Acheteur extends Utilisateur {

	static String pseudo;
	private static Adresse adresseExpedition;
	private static int points;
	private static ArrayList<Commande> historiqueCommandes = new ArrayList<>();
	private List<Acheteur> followers;
	private int classement;

	//TODO: constructor & each acheteur has an individual panier


	public boolean confirmerReceptionCommande() {
		// TODO - implement Acheteur.confirmerReceptionCommande
		throw new UnsupportedOperationException();
	}

	public void inscrireAcheteur() {
		// TODO - implement Acheteur.inscrireAcheteur
		throw new UnsupportedOperationException();
	}

	public void suivreAcheteur() {
		// TODO - implement Acheteur.suivreAcheteur
		throw new UnsupportedOperationException();
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