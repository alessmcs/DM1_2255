import java.util.ArrayList;

public class Panier {

	private static float montantAPayer;
	private static int nbPoints;

	private static ArrayList<Produit> articles = new ArrayList<>();

	public static void ajouterArticle(Produit produit) {
		articles.add(produit);
		montantAPayer += produit.getPrix();
		nbPoints += produit.getPoints();
	}

	public static ArrayList<Produit> getArticles(){
		return articles;
	}

	public static float getTotal(){
		return montantAPayer;
	}

	public static void voirPanier(){
		for(Produit p: articles){
			p.voirDetails();
		}
		System.out.println("Votre total: " + montantAPayer);
		System.out.println("Vous accumulerez " + nbPoints + " points");
	}

	public void retirerArticle(int ID) {
		// TODO: system will ask which product the user wants to delete by choosing its id
		for(Produit p : articles){
			if(p.ID == ID) articles.remove(p);
			montantAPayer-= p.getPrix();
			nbPoints -= p.getPoints();
		}
	}

}