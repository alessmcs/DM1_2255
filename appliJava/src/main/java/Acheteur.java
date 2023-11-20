import java.util.List;
import java.util.Scanner;

public class Acheteur extends Utilisateur {


	private String adresseExpedition;
	private String prenom;
	private String nom;
	private int points;
	private List<Commande> historiqueCommandes;
	private List<Acheteur> followers;
	private int classement;
	private String pseudo;

	public Acheteur(String telephone, String courriel, String motDePasse) {
		super(telephone,courriel,motDePasse);
		this.prenom= prenom;
		this.nom= nom;

	}



	public boolean confirmerReceptionCommande() {
		// TODO - implement Acheteur.confirmerReceptionCommande
		throw new UnsupportedOperationException();
	}

	public void inscrireAcheteur() {

		Scanner scanner = new Scanner(System.in);

		System.out.println("Veuillez entrer votre prénom :");
		String prenom = scanner.nextLine();

		System.out.println("Veuillez entrer votre nom :");
		String nom = scanner.nextLine();

		System.out.println("Veuillez entrer votre pseudo :");
		String pseudo = scanner.nextLine();

		System.out.println("Veuillez entrer votre adressse d'expidition ");
		String adresseExpedition = scanner.nextLine();

		setPrenom(prenom);
		setNom(nom);
		setAdresseExpedition(adresseExpedition);
		setPseudo(pseudo);


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


	public String getPseudo() {
		return pseudo;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	public void setAdresseExpedition(String adresseExpedition) {
		this.adresseExpedition = adresseExpedition;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}


}