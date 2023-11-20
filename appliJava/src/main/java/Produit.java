import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Produit {

	protected String titre;
	protected float prix;
	protected int ID;
	protected int qteEnStock;
	protected String catégorie;
	protected int nbPoints;
	protected String description;
	protected String dateMiseEnVente;
	protected ArrayList<ArrayList<String>> commentaires;
	private int note;

    Scanner scan = new Scanner(System.in);
    String evalEtoile;
	String coeur;
	String review;

	public Produit(String titre, float prix, int qte, String cat, int nbPoints, String description, String date) {
		this.titre = titre;
		this.prix = prix;
		this.qteEnStock = qte;
		this.catégorie = cat;
		this.nbPoints = nbPoints;
		this.description = description;
		this.dateMiseEnVente = date;
	}


	public void voirEval() {
		ArrayList<ArrayList<String>> listeComplete = enregistrerEvalComplete();

		for (ArrayList<String> elements : listeComplete){
			System.out.println("\u001B[1m" + "Étoile(s): " + "\u001B[0m" + elements.get(0));
			System.out.println("\u001B[1m" + "Like: " + "\u001B[0m" + elements.get(1));
			System.out.println("\u001B[1m" + "Commentaire: " + "\u001B[0m" + elements.get(2));

			System.out.println();
	  	}
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
				System.out.println();
				System.out.println("Veuillez choisir entre les cinq options données.");
				System.out.println();
                evaluer();
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
				coeur = "liker";
				break;
			case "2":
				coeur = "ne pas liker";
				break;
			default:
				System.out.println();
				System.out.println("Veuillez choisir entre les deux options données");
				System.out.println();
				liker();
		}

		return "Vous avez choisi de " + "\u001B[1m" + coeur + "\u001B[0m" + " le produit!";
	}



	public String commenter() { 
		review = "";

		System.out.println("Voulez-vous laisser un commentaire au produit?");

		System.out.println("1. Oui");
		System.out.println("2. Non");

		System.out.print("Entrez votre choix: ");
        String comment = scan.nextLine();

		switch (comment) {
			case "1":
				System.out.print("Veuillez écrire votre commentaire ici: ");
				String commentaire = scan.nextLine();
				review = commentaire;
				break;

			case "2":
				review = "*Aucun commentaire* ";
				break;

			default: 
				System.out.println();
				System.out.println("Veuillez choisir entre les deux options données.");
				System.out.println();
				commenter();
				break;
		}

		return "Vous avez choisi de commenter: " + "\u001B[1m" + review + "\u001B[0m";

	}

	public String verifier() {
		System.out.println("Voici les données recueillies: ");
		System.out.println("Étoile(s): " + "\u001B[1m" + evalEtoile + "\u001B[0m");
		System.out.println("Like: " + "\u001B[1m" + coeur + "\u001B[0m");
		System.out.println("Commentaire: " + "\u001B[1m" + review + "\u001B[0m");
		System.out.println();

		System.out.println("Sont-elles correctes? : ");

		System.out.println("1. Oui");
		System.out.println("2. Non");

		System.out.print("Entrez votre choix: ");
        String verif= scan.nextLine();
		System.out.println();

		switch (verif) {
			case "1":
				break;
			case "2":
				System.out.println("Quelle donnée souhaitez-vous changer? : ");
				System.out.println("1. Étoile(s)");
				System.out.println("2. Like");
				System.out.println("3. Commentaire");

				System.out.print("Entrez votre choix: ");
        		String modification= scan.nextLine();
				System.out.println();

				switch (modification) {
					case "1":
						evaluer();
						verifier();
						break;
					case "2":
						liker();
						verifier();
						break;
					case "3": 
						commenter();
						verifier();
						break;
					default:
						System.out.println();
						System.out.println("Veuillez choisir entre les trois options données.");
						System.out.println();
						break;
				}
				break;
			default:
				System.out.println();
				System.out.println("Veuillez choisir entre les deux options données.");
				System.out.println();
				verifier();
				break;
		}
		return "Vos données ont été enregistrées!";
	}


	public ArrayList<ArrayList<String>> enregistrerEvalComplete() {
		ArrayList<String> evalComplete = new ArrayList<String>();

		Commentaire c = new Commentaire();
		ArrayList<ArrayList<String>> listCommentaires = c.listeDeCom();

		evalComplete.add(evalEtoile);
		evalComplete.add(coeur);
		evalComplete.add(review);
		c.setContenu(evalComplete); 

		listCommentaires.add(c.getContenu());

		return listCommentaires;
	}



	public void promouvoir() {
		// TODO - implement Produit.promouvoir
	}

	public int getQte(){
		return this.qteEnStock;
	}

	public void setQte(int qte){
		this.qteEnStock = qte;
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
	}

	public float getPrix() {
		return this.prix;
	}

	public int getPoints() {
		return this.nbPoints;
	}

	public int getId() {
		return this.ID;
	}

	public String getTitre() {
		return this.titre;
	}

	public void voirDetails() {
	}

	public String toString(){
		return ("Titre: " + titre + "\n" + prix);
	}

}