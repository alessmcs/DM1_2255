import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Panier {

	private float montantAPayer;
	private int nbPoints;
	private ArrayList<Produit> articles = new ArrayList<>();

	/**
		Ajouter un article au panier sans directement accéder à l'objet

		@param le produit à ajouter
	 */
	public ArrayList<Produit>  ajouterArticle(Produit produit) {
		articles.add(produit);
		montantAPayer += produit.getPrix();
		nbPoints += produit.getPoints();

		return articles;
	}

	/**
		Indirectement accéder à la liste des produits du panier

		@return la liste des articles du panier
	 */
	public ArrayList<Produit> getArticles(){
		return articles;
	}

	/**
		Réinitialise le panier
	 */
	public void nouvPanier(){
		articles = new ArrayList<>();
		montantAPayer = 0;
		nbPoints = 0;
	}

	/**
		Indirectement accéder au montant total du panier

		@return le montant à payer
	 */
	public float getTotal(){
		return montantAPayer;
	}

	/**
		Affiche le panier de l'acheteur et lui donne des options pour le gérer (passer la commande ou
		retirer un article)

		@param acheteur l'acheteur connecté
	 */
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

		System.out.println("Voulez-vous quitter le panier?\n0 : retourner au menu \n9 : retourner au catalogue de produits");

		boolean validInput = false;
		do{
			try{
				String choix = s.nextLine();
				if (choix.equals("0")){
					validInput = true;
					Utilisateur.afficherMenu(acheteur);
				} else if(choix.equals("9")){
					Catalogue.catalogueProduits(acheteur);
				}else if (!Main.isNumeric(choix) && articles.size() != 0){
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
					Commande.passerCommande(this, acheteur); // passer la commande
					break;
				}
				else {
					System.out.println("SVP entrez 0, 1, 2 ou 3!");
				}
			} catch (InputMismatchException e){
				System.out.println("SVP entrez 0, 1, 2 ou 3!");
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e);
			}
		} while (!validInput);

	}

	/**
		Permet à l'utilisateur de retirer un article de son panier

		@param ach l'acheteur connecté
	 */
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