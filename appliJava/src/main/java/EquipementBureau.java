public class EquipementBureau extends Produit {

	private String categorie = "Équipement de bureau";
	private String marque;
	private String modele;
	private String sousCategorie;

	public EquipementBureau(String titre, double prix, int qte, String cat, int nbPoints, String description, String date) {
		super(titre, prix, qte, cat, nbPoints, description);
	}

	public String toString(){
		return ("Titre: " + titre + "\n" + prix);
	}

	// overriding voirDetails pour imprimer les détails spécifiques du produit
	@Override
	public void voirDetails(Acheteur ach){
		System.out.println("\nID: " + ID + "\nTitre: " + titre + "\n" + categorie + "\n" + description + prix + "\nMarque:" + marque + "\nModèle: " + modele
				+ "\nSous-Catégorie: " + sousCategorie);
		super.voirEval();
		super.demanderAjoutPanier(ach);
	}
}