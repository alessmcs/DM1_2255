import java.util.ArrayList;

public class Catalogue {

	private static ArrayList<Produit> produits = new ArrayList<Produit>();
	private String categorie;

	private static int idProds;

	public void voirCatalogue() {
		// TODO - implement Catalogue.voirCatalogue
		throw new UnsupportedOperationException();
	}

	public static void ajouterAuCatalogue(Produit produit){
		produits.add(produit);
		idProds++;
		produit.ID = idProds;
	}


}