<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Revendeur extends Utilisateur {

	private String adresse;
=======
import java.util.List;
public class Revendeur extends Utilisateur {

	private String adresse;
	private int nom;
>>>>>>> b88a840f83a9681722afe602713d0b33fe3d26c9
	private List<Produit> inventaire;
	private String pseudo;



	public Revendeur(String telephone, String courriel, String motDePasse) {
		super(telephone,courriel,motDePasse);
		inventaire= new ArrayList<>();
	}

	public void inscrireRevendeur() {
		Scanner scanner = new Scanner((System.in));


		System.out.println("Veuillez entrer votre adresse civil: ");
		String adresse = scanner.nextLine();

		System.out.println("Veuillez entrer votre pseudo");
		String pseudo = scanner.nextLine();

		setAdresse(adresse);
		setPseudo(pseudo);

	}

	public void confirmerReceptionRetour() {
		// TODO - implement Revendeur.confirmerReceptionRetour
		throw new UnsupportedOperationException();
	}

	public void gérerProbleme() {
		// TODO - implement Revendeur.gérerProbleme
		throw new UnsupportedOperationException();
	}

	public void updateInventaire() {
		// TODO - implement Revendeur.updateInventaire
		throw new UnsupportedOperationException();
	}

	public String  getPseudo() {
		return pseudo;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
}