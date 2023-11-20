public class RessourcesApprentissage extends Produit {

	private String categorie = "Ressources d'apprentissage";
	private String marque;
	private String modele;
	private String sousCategorie;

	public RessourcesApprentissage(String titre, float prix, int qte, String cat, int nbPoints, String description, String date,
								   String marque, String modele, String sousCategorie) {
		super(titre, prix, qte, cat, nbPoints, description, date);
		this.marque = marque;
		this.sousCategorie = sousCategorie;
		this.modele = modele;
	}

	@Override
	public void voirDetails(){
		System.out.println("\nID: " + ID + "\nTitre : " + titre + "\n" + categorie + "\n" + description + prix + "\n" + marque + "\n" + modele + "," + marque + "," + modele);
		super.voirEval();
	}

}