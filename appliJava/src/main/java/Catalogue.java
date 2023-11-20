import java.util.ArrayList;
public class Catalogue {
	private ArrayList<Produit> produits;


	Produit livre1 = new Produit("Programmation orientée objet et Génie logiciel, 8e édition", 78.90, -9700 - 4429 - 1, 10,"Livres et manuels", 20, "Genie logiciel", "08/10/2022");
	Produit livre2 = new Produit("Calcul à plusieurs variables, 2e édition", 66.79, -8544 - 1689 - 7, 15, "Livres et manuels", 20, "Calcul I", "18/01/2022");
	Produit livre3 = new Produit("Microéconomie", 68.99, -2749 - 1098 - 6, 3, "Livres et manuels", 20, "Économie", "05/04,2021");
	Produit materiel1 = new Produit("Écouteurs SONY WH-1000XM4", 409.99, -4291 - 2391 - 6, 2, "Matériel informatique", 20, "Écouteurs", "02/12/2022");
	Produit materiel2 = new Produit("MacBook Pro M3 14p", 2599.00, -2009 - 1298 - 2, 1, "Matériel informatique", 20, "Ordinateur portable", "11/10/2023");
	Produit article1 = new Produit("Cahier Canada quadrillé 40 pages", 1.79, -2774 - 3948 - 9, 230, "Articles de papeterie", 0, "Cahier d'exercices", "10/11/2023");

	private String categorie;

	public Catalogue(ArrayList<Produit> produits) {
		this.produits = produits;
	}

	public void ajouterProduit(Produit produit) {
		produits.add(produit);
	}

	public void voirCatalogue(Acheteur acheteur) {
		System.out.println("Catalogue:");
		for (Produit produit : produits) {
			System.out.println(produit);
		}
		throw new UnsupportedOperationException();
	}
}