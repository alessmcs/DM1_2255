import java.util.ArrayList;
import java.util.Scanner;

public class Panier {

	private static float montantAPayer;
	private static int nbPoints;

	private static ArrayList<Produit> articles = new ArrayList<>();

	// TODO: add continuer de magasiner & return to the catalogue!!!

	public void ajouterArticle(Produit produit) {
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

	public void voirPanier(){
		System.out.println(" --- Votre panier --- ");
		for(Produit p: articles){
			System.out.println(p.toString());
		}

		if (articles.size() != 0){
			Scanner s = new Scanner(System.in);
			System.out.println("\nVotre total: " + montantAPayer);
			System.out.println("Vous accumulerez " + nbPoints + " points");
			System.out.println("Voulez-vous supprimer un article du panier? \n1 : Oui \n2 : Non");
			System.out.println("Voulez vous passer votre commande? \n3 : Oui");
			System.out.println("Voulez-vous quitter le panier?");
			while(true){
				String choix = s.nextLine();
				if (!Main.isNumeric(choix)){
					System.out.println("SVP entrez un chiffre!");
				} else if (choix.equals("1")){
					retirerArticle();
					break;
				} else if (choix.equals("2")){
					System.out.println("Vous avez choisi de ne rien supprimer du panier");
					break;
				} else if(choix.equals("3")){
					Commande.passerCommande(this); // passer la commande
				} else if(choix.equals("4")){ // quitter le panier
					break; // TODO: return to the menu
				}
				else {
					System.out.println("SVP entrez 1, 2, 3 ou 4 !");
				}
			}


		} else {
			System.out.println("Votre panier est vide");
		}

	}

	public void retirerArticle() {

		Scanner s = new Scanner(System.in);
		String removeID;

		// make a list of IDs to make sure the ID exists
		ArrayList<String> listID = new ArrayList<>();
		for (Produit i : articles){
			listID.add(Integer.toString(i.getId()));
		}

		while(true){
			System.out.println("Entrez l'ID de l'article que vous voulez supprimer : ");
			removeID = s.nextLine();
			if(! Main.isNumeric(removeID) ) { // error if the string is not an integer (letter for example)
				System.out.println("Entrée invalide, SVP entrer une valeur numérique");
			} else if(! listID.contains(removeID) ) {
				System.out.println("L'ID que vous avez entré n'est pas valide! Essayez encore SVP");
			} else break;
		}

		for(Produit p : articles){
			if(p.getId() == Integer.parseInt(removeID)) {
				articles.remove(p);
				montantAPayer-= p.getPrix();
				nbPoints -= p.getPoints();
				System.out.println(p.getTitre() + " a été retiré de votre panier!");
			}
			if (articles.size() == 0) break;
		}
		voirPanier();
	}



}