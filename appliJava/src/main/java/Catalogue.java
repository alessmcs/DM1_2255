import java.util.ArrayList;
import java.util.Scanner;

public class Catalogue {
	private static ArrayList<Produit> produits = new ArrayList<>();

	// TODO: revendeurs

	private String categorie;

	public Catalogue(ArrayList<Produit> produits) {
		this.produits = produits;
	}

	public static void ajouterProduit(Produit produit) {
		produits.add(produit);
	}

	public static void voirCatalogue(Acheteur acheteur) {
		Scanner s = new Scanner(System.in);

		System.out.println("Catalogue:");

		for (Produit produit : produits) {
			System.out.println(produit.toString());
		}

		while(true){
			System.out.println("Entrez l'ID d'un produit pour voir ses détails");
			System.out.println( "Entrez 0 pour revenir au menu" );

			String choix = s.nextLine();
			if ( ! Main.isNumeric(choix)){
				System.out.println("Svp entrez l'ID (chiffres) du produit que vous desirez!");
			} else {
				if (choix.equals("0")){
					Utilisateur.afficherMenu(acheteur);
					break;
				}
				for (Produit p : produits){
					if ( p.getId() == Integer.parseInt(choix)){
						p.voirDetails(acheteur);
						break;
					}
				}
			}
		}

	}
}