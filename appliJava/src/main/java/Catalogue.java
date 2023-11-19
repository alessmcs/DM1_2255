import java.util.ArrayList;
import java.util.List;
public class Catalogue {

	private ArrayList<Produit> produits;

	/**où mettre produits?*/
	Produit livre1 = new Produit("Programmation orienteée objet et Génie logiciel, 8e édition", 78.90, 0-9700-4429-1, 10,"Livres et manuels", "", "Genie logiciel", "08/10/2022");
	Produit livre2 = new Produit("Calcul à plusieurs variables, 2e édition", 66.79, 0-8544-1689-7, 15, "Livres et manuels", "", "Calcul I", "18/01/2022");
	Produit livre3 = new Produit("Microeconomie", 68.99, 0-2749-1098-6, 3, "Livres et manuels", "", "", "05/04,2021");
	Produit materiel1 = new Produit("écouteurs SONY WH-1000XM4", 409.99, "", 2, "matériel électronique et informatique", "", "", "")

	private String categorie;

	public Catalogue(ArrayList<Produit> produits) {
		this.produits = produits;
	}

	public void ajouterProduit(Produit produit) {
		produits.add(produit);
	}

	public void voirCatalogue() {
		System.out.println("Catalogue:");
		for (Produit produit : produits) {
			System.out.println(produit);
		}
		throw new UnsupportedOperationException();
	}

}