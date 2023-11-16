public class MaterielInfo extends Produit {

	private String categorie = "Matériel informatique";
	private String marque;
	private String modele;
	private String dateLancement;
	private String sousCategorie;

	public MaterielInfo(String titre, double prix, int qte, String cat, int nbPoints, String description, String date) {
		super(titre, prix, qte, cat, nbPoints, description, date);
	}

	public String toString(){
		return ("Titre: " + titre + "\n" + prix);
	}

	// overriding voirDetails pour imprimer les détails spécifiques du produit
	@Override
	public void voirDetails(){
		System.out.println ("ID: " + ID + "\nTitre: " + titre + "\n" + categorie + "\n" + prix + "\nMarque:" + marque + "\nModèle: " + modele
				+ "\nSous-Catégorie: " + sousCategorie + "\nDate de lancement: " + dateLancement);
	}
}