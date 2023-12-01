import java.util.ArrayList;
import java.util.Scanner;

public class Revendeur extends Utilisateur {

	private Adresse adresse;
	private int nom;
	protected ArrayList<Produit> inventaire;
	private String pseudo;
	protected ArrayList<Produit> commandes; //TODO ajouter attribut revendeur au produit


	public Revendeur(String telephone, String courriel, String motDePasse) {
		super(telephone,courriel,motDePasse);
		inventaire= new ArrayList<>();
	}

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

	public void confirmerReceptionRetour() {
		// TODO - implement Revendeur.confirmerReceptionRetour
		throw new UnsupportedOperationException();
	}

	public void gererProbleme() {
		// TODO - implement Revendeur.gérerProbleme
		throw new UnsupportedOperationException();
	}

	public void updateInventaire(Produit p) { // lorsquon on ajoute un produit à l'inventaire on le met à jour
		inventaire.add(p);
	}

	public String  getPseudo() {
		return pseudo;
	}
	public void afficherInventaire(){
		for(Produit p : inventaire){
			System.out.println(p); // uses toString method from produit
		}
	}

	public Adresse getAdresse() { return adresse; };
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

}