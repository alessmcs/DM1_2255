import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.*;
public class Catalogue {
	private static ArrayList<Produit> produits = new ArrayList<>();

	private String categorie;

	public Catalogue(ArrayList<Produit> produits) {
		this.produits = produits;
	}

	public static void ajouterProduit(Produit produit) {
		produits.add(produit);
	}

	// Voir le catalogue de produits

	public static void catalogueProduits(Acheteur acheteur){
		Scanner s = new Scanner(System.in);
		System.out.println("Liste des produits disponibles : ");

		boolean validInput = false;

		for (Produit produit : produits) {
			System.out.println(produit.toString());
		}

		do{
			try{
				System.out.println("Entrez l'ID d'un produit pour voir ses détails");
				System.out.println( "Entrez 0 pour retourner au menu principal" );
				System.out.println( "Entrez 1 pour retourner au menu du catalogue" );

				String choix = s.nextLine();
				if ( ! Main.isNumeric(choix)){
					throw new InputMismatchException();
				} else {
					if (choix.equals("0")){
						validInput = true;
						Utilisateur.afficherMenu(acheteur);
						break;
					} else if(choix.equals("1")) {
						voirCatalogue(acheteur);
						break;
					}
					for (Produit p : produits){
						if ( p.getId() == Integer.parseInt(choix)){
							validInput = true;
							p.voirDetails(acheteur);
							break;
						}
					}
					if(!validInput){
						throw new InputMismatchException();
					}
				}
			} catch (InputMismatchException e){
				System.out.println("Svp entrez 0 ou un ID valide!");
			}
		} while (!validInput);
	}
	public static void voirCatalogue(Acheteur acheteur) {
		Scanner s = new Scanner(System.in);

		System.out.println("Catalogue:");
		// 1 pour voir les produits
		// 2 pour voir les acheteurs
		// 3 pour voir les revendeurs
		// 0 pour retourner au menu principal

		System.out.println("1 : Effectuer une recherche \n2 : Catalogue des produits \n3 : Voir la liste d'acheteurs" +
				"\n4 : Voir la liste de revendeurs \n0 : Retourner au menu principal");

		boolean validInput = false;

		do{
			try{
				String choix = s.nextLine();
				switch(choix){
					case("0") : Utilisateur.afficherMenu(acheteur); // afficher le menu principal
					case("1") : // TODO: IMPLEMENTER RECHERCHE
					case("2") : catalogueProduits(acheteur);
					case("3") : catalogueAcheteurs(acheteur);
					case("4") : // TODO; implementer liste revendeurs
					default : System.out.println("Svp entrez un chiffre valide: 0, 1, 2, 3 ou 4");
				}
			} catch (InputMismatchException e){

			}
		}while(!validInput);



//		while(true){
//			System.out.println("Entrez l'ID d'un produit pour voir ses détails");
//			System.out.println( "Entrez 0 pour revenir au menu" );
//
//			String choix = s.nextLine();
//			if ( ! Main.isNumeric(choix)){
//				System.out.println("Svp entrez l'ID (chiffres) du produit que vous desirez!");
//			} else {
//				if (choix.equals("0")){
//					Utilisateur.afficherMenu(acheteur);
//					break;
//				}
//				for (Produit p : produits){
//					if ( p.getId() == Integer.parseInt(choix)){
//						p.voirDetails(acheteur);
//						break;
//					}
//				}
//			}
//		}

	}

	// TODO: figure out where to put suivre acheteur!!!
	public static void catalogueAcheteurs(Acheteur acheteur){
		// afficher la liste des pseudos des acheteurs à partir du CSV, afficher le profil d'un acheteur choisi
		System.out.println("\nListe des acheteurs : 1");
		// faire une liste de tous les acheteurs pour accéder aux instances
		ArrayList<Acheteur> listeAcheteurs = new ArrayList<>();
		Acheteur achVisionne = null;

		String line = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/main/data/acheteurs.csv"));
			while ((line = br.readLine()) != null)
			{
				String[] ach = line.split(",");
				if(!ach[2].equalsIgnoreCase(acheteur.getPseudo())){
					Acheteur autre = new Acheteur(ach[3], ach[4], ach[5]);
					autre.setPrenom(ach[1]);
					autre.setNom(ach[2]);
					autre.setPseudo(ach[0]);

					autre.setAdresseExpedition(Adresse.adresseBuilder(ach[6] + "," + ach[7] + "," + ach[8] + "," + ach[9] + "," + ach[10]));

					listeAcheteurs.add(autre);
					System.out.println( ach[0] + ach[1] + " (" + ach[2] + ")"); // print tout sauf l'acheteur connecté
				}
			}
		}
		catch (IOException e) {e.printStackTrace();}

		System.out.println("\nPour voir le profil d'un des acheteurs, entrez son pseudo \n1: Retourner au menu du catalogue" +
				"\n1: Retourner au menu principal");
		boolean validInput = false;
		Scanner scanner = new Scanner(System.in);
		do{
			try{
				String choix = scanner.nextLine();
				if(choix.equals("O")){
					validInput = true;
					// go to the menu
					voirCatalogue(acheteur); // retourner au catalogue
				} else if (choix.equals("1")){
					validInput = true;
					Utilisateur.afficherMenu(acheteur); // retourner au menu principal
				} else { // pseudo
					// check if pseudo dans la liste, then access the profile if yes
					for(Acheteur a : listeAcheteurs){
						if(!a.getPseudo().equalsIgnoreCase(choix)){
							throw new InputMismatchException();
						} else {
							validInput = true;
							a.montrerProfil();
							break;
						}
					}
				}
			} catch (InputMismatchException e) {
				System.out.println("Svp entrer 0, 1 ou un pseudo valide");
			}
		} while(!validInput);
	}

	public static void rechercheRevendeur(){

	}
}