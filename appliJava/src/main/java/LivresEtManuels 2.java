public class LivresEtManuels extends Produit {

	private String categorie = "Livres et manuels";
	private String ISBN;
	private String auteur;
	private String maisonEdition;
	private String genre;
	private String dateParution;
	private int numEdition;
	private int numVol;

	public LivresEtManuels(String titre, double prix, int qte, String cat, int nbPoints, String description,
						   String ISBN, String auteur, String maisonEdition, String genre, String dateParution, int numEd, int numVol) {
		super(titre, prix, qte, cat, nbPoints, description);
		this.ISBN = ISBN;
		this.auteur = auteur;
		this.maisonEdition = maisonEdition;
		this.genre = genre;
		this.dateParution = dateParution;
		this.numEdition = numEd;
		this.numVol = numVol;
	}


	// toString utile pour le panier
	public String toString(){
		return ("Titre: " + titre + "\n" + prix);
	}

	// overriding voirDetails pour imprimer les détails spécifiques du produit
	@Override
	public void voirDetails(Acheteur ach){
		System.out.println("\nID: " + ID + "\nTitre: " + titre + "\n" + categorie + "\n" + description + "\nPrix:" + prix + "\nISBN:" + ISBN + "\nAuteur: " + auteur + ", Maison d'édition: " + maisonEdition +
				", Genre: " + genre + " , " + numEdition + "e édition , Volume " + numVol + "\nPublié le ") ;
		super.voirEval();
		super.demanderAjoutPanier(ach);
	}
}