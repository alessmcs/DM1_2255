import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Panier {

	private float montantAPayer;
	private int nbPoints;

	private ArrayList<Produit> articles = new ArrayList<>();

	// TODO: add continuer de magasiner & return to the catalogue!!!

	public void ajouterArticle(Produit produit) {
		articles.add(produit);
		montantAPayer += produit.getPrix();
		nbPoints += produit.getPoints();
	}

	public ArrayList<Produit> getArticles(){
		return articles;
	}

	public float getTotal(){
		return montantAPayer;
	}

	public void voirPanier(Acheteur acheteur){
		Scanner s = new Scanner(System.in);
		System.out.println(" --- Votre panier --- ");

		if (articles.size() != 0){
			for(Produit p: articles){
				System.out.println(p.toString());
			}
			System.out.println("\nVotre total: " + montantAPayer);
			System.out.println("Vous accumulerez " + nbPoints + " points");
			System.out.println("Voulez-vous supprimer un article du panier? \n1 : Oui \n2 : Non");
			System.out.println("Voulez vous passer votre commande? \n3 : Oui");
		} else {
			System.out.println("Votre panier est vide");
		}

		System.out.println("Voulez-vous quitter le panier? Entrez 0");
//		while(true){
//			String choix = s.nextLine();
//			if (choix.equals("0")){
//				Utilisateur.afficherMenu(acheteur);
//			} else if (!Main.isNumeric(choix) && articles.size() != 0){
//				System.out.println("SVP entrez un chiffre!");
//			} else if (choix.equals("1") && articles.size() != 0){
//				retirerArticle(acheteur);
//				break;
//			} else if (choix.equals("2") && articles.size() != 0){
//				System.out.println("Vous avez choisi de ne rien supprimer du panier");
//				break;
//			} else if(choix.equals("3") && articles.size() != 0){
//				Commande.setAcheteur(acheteur);
//				Commande.passerCommande(this); // passer la commande
//				break;
//			}
//			else {
//				System.out.println("SVP entrez 0, 1, 2 ou 3!");
//			}
//		}
		boolean validInput = false;
		do{
			try{
				String choix = s.nextLine();
				if (choix.equals("0")){
					validInput = true;
					Utilisateur.afficherMenu(acheteur);
				} else if (!Main.isNumeric(choix) && articles.size() != 0){
					validInput = true;
					System.out.println("SVP entrez un chiffre!");
				} else if (choix.equals("1") && articles.size() != 0){
					validInput = true;
					retirerArticle(acheteur);
					break;
				} else if (choix.equals("2") && articles.size() != 0){
					validInput = true;
					System.out.println("Vous avez choisi de ne rien supprimer du panier");
					voirPanier(acheteur); // reafficher le panier
					break;
				} else if(choix.equals("3") && articles.size() != 0){
					validInput = true;
					Commande.setAcheteur(acheteur);
					Commande.passerCommande(this); // passer la commande
					break;
				}
				else {
					System.out.println("SVP entrez 0, 1, 2 ou 3!");
				}
			} catch (InputMismatchException e){
				System.out.println("SVP entrez 0, 1, 2 ou 3!");
			}
		} while (!validInput);

	}

	public void retirerArticle(Acheteur ach) {

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
		// duplicate articles list
		//ArrayList<Produit> articles2 = this.articles;

		ArrayList<Produit> articles2 = new ArrayList<>();
		for (Produit p : articles) {
			articles2.add(p);
		}

		for(Produit p : articles2){
			if(p.getId() == Integer.parseInt(removeID)) {
				this.articles.remove(p);
				this.montantAPayer-= p.getPrix();
				this.nbPoints -= p.getPoints();
				System.out.println(p.getTitre() + " a été retiré de votre panier!");
			}
			if (articles.size() == 0) break;
		}
		voirPanier(ach);
	}



}