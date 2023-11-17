import java.util.List;
import java.util.Scanner;
public class Produit {

	private String titre;
	private float prix;
	private int ID;
	private int qteEnStock;
	private String catégorie;
	private int nbPoints;
	private String description;
	private String dateMiseEnVente;
	private List<Commentaire> commentaires;
	private int note;

	Scanner scan = new Scanner(System.in);
    String evalEtoile;
	boolean coeur;
	String review;

	public void voirDetails() {
		// TODO - implement Produit.voirDetails
		throw new UnsupportedOperationException();
	}

	public String evaluer() {
		System.out.println("Combien d'étoiles aimeriez-vous donner au produit?");
        System.out.println("1. *");
        System.out.println("2. **");
        System.out.println("3. ***");
        System.out.println("4. ****");
        System.out.println("5. *****");

        System.out.print("Entrez votre choix: ");
        String eval = scan.nextLine();
        
        switch(eval) {
            case "1":
                evalEtoile = "*";
                break;
            case "2":
                evalEtoile = "**";
                break;
            case "3":
                evalEtoile = "***";
                break;
            case "4":
                evalEtoile = "****";
                break;
            case "5": 
                evalEtoile = "*****";
                break;
            default:
                evalEtoile = "?";
        }

        return "Vous avez choisi de mettre " + "\u001B[1m" + evalEtoile + "\u001B[0m" + " étoile(s) au produit!";
	}

	public String liker() {
		System.out.println("Voulez-vous aimer ce produit?");
		System.out.println("1. Oui");
		System.out.println("2. Non");

		System.out.print("Entrez votre choix: ");
        String like = scan.nextLine();

		switch(like) {
			case "1":
				coeur = true;
				break;
			case "2":
				coeur = false;
				break;
			default:
				break;
		}

		if (coeur == true) {
			return "Vous avez choisi d' " + "\u001B[1m" + "aimer " + "\u001B[0m" + "le produit!";

		}

		else {
			return "Vous avez choisi de ne " + "\u001B[1m" + "pas aimer " + "\u001B[0m" +"le produit!";
		}
	}

	public String commenter() {
		System.out.println("Voulez-vous laisser un commentaire au produit?");

		System.out.println("1. Oui");
		System.out.println("2. Non");

		System.out.print("Entrez votre choix: ");
        String comment = scan.nextLine();

		switch (comment) {
			case "1":
				System.out.print("Veuillez écrire votre commentaire ici: ");
				String commentaire = scan.nextLine();

				Commentaire c = new Commentaire();
				c.setContenu(commentaire);
				review = c.getContenu();
				break;

			case "2":
				return "Aucun commentaire n'est ajouté au produit.";
		}

		return "Vous avez choisi de commenter " + "\u001B[1m" + review + "\u001B[0m" + "!";
	}

	public void promouvoir() {
		// TODO - implement Produit.promouvoir
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param d
	 */
	public void setDescription(String d) {
		this.description = d;
	}

	/**
	 * 
	 * @param points
	 */
	public void setPoints(int points) {
		// TODO - implement Produit.setPoints
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param t
	 */
	public void setTitre(String t) {
		this.titre = t;
	}

	/**
	 * 
	 * @param prix
	 */
	public void setPrix(double prix) {
		// TODO - implement Produit.setPrix
		throw new UnsupportedOperationException();
	}

}