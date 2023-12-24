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

	/*
            Cette méthode permet d'ajouter un produit à la liste des produits sans directement accéder à l'objet

            @param produit le produit à ajouter
         */
	public static void ajouterProduit(Produit produit) {
		produits.add(produit);
	}

	public static ArrayList<Produit> getProduits(){
		return produits;
	}

	/*
    	Cette méthode permet à l'utilisateur de voir la liste des produits disponibles, et éventuellement d'en
    	choisir un pour voir ses détails

    	@param util l'utilisateur connecté (peut être un revendeur ou un acheteur)
 	*/
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

	/*
		Cette méthode affiche le menu du catalogue et donne à l'utilisateur le choix de voir la liste d'acheteurs,
		la liste de revendeurs, le catalogue des produits ou de retourner au menu principal

		@param util l'utilisateur connecté
	 */
	public static void voirCatalogue(Utilisateur util) {
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
					case("0") :
						Utilisateur.afficherMenu(util); // afficher le menu principal
						break;
					case("1") :
					case("2") :
						catalogueProduits((Acheteur) util);
						break;
					case("3") :
						catalogueAcheteurs((Acheteur) util);
						break;
					case("4") :
						catalogueRevendeurs(util);
						break;
					default : System.out.println("Svp entrez un chiffre valide: 0, 1, 2, 3 ou 4");
				}
			} catch (InputMismatchException e){

			}
		}while(!validInput);

	}

	/*
		Cette méthode affiche le catalogue des acheteurs à partir de la liste des acheteurs (acheteurs.csv) et donne
		l'option de voir le profil d'un des acheteurs en entrant son pseudo.

		@param util l'utilisateur connecté (peut être un acheteur ou un revendeur)
	 */
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

	/*
    Cette méthode affiche le catalogue des revendeurs à partir de la liste des revendeurs (revendeurs.csv) et donne
    l'option de voir le profil d'un des revendeurs en entrant son pseudo.

    @param util l'utilisateur connecté (peut être un acheteur ou un revendeur)
 */
	public static void catalogueRevendeurs(Utilisateur util){
		// afficher la liste des pseudos des revendeurs à partir du CSV
		System.out.println("\nListe des revendeurs : ");
		// faire une liste de tous les revendeurs pour accéder aux instances
		ArrayList<Revendeur> listeRevendeurs = new ArrayList<>();

		String line = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/main/data/revendeurs.csv"));
			while ((line = br.readLine()) != null)
			{
				String[] rev = line.split(",");
				// Si l'utilisateur est un acheteur ou un revendeur, on veut juste skip son pseudo dans l'affichage de la liste
				if( ( util instanceof Acheteur && !rev[0].equalsIgnoreCase(((Acheteur) util).getPseudo()) ) ||
						( util instanceof Revendeur && !rev[0].equalsIgnoreCase(((Revendeur) util).getPseudo()))){
					Revendeur autre = new Revendeur(rev[1], rev[2], rev[3]);
					autre.setPseudo(rev[0]);
					autre.setAdresse(Adresse.adresseBuilder(rev[4] + "," + rev[5] + "," + rev[6] + "," + rev[7] + "," + rev[8]));
					listeRevendeurs.add(autre);
					System.out.println( rev[0]); // print tout sauf l'acheteur connecté
				}
			}
		}
		catch (IOException e) {e.printStackTrace();}

		System.out.println("\nPour voir le profil d'un des revendeurs, entrez son pseudo \n1: Retourner au menu du catalogue" +
				"\n0: Retourner au menu principal");
		boolean validInput = false;
		Scanner scanner = new Scanner(System.in);
		do{
			try{
				String choix = scanner.nextLine();
				if(choix.equals("1")){
					validInput = true;
					// go to the menu
					voirCatalogue(util); // retourner au catalogue
				} else if (choix.equals("0")){
					validInput = true;
					Utilisateur.afficherMenu(util); // retourner au menu principal
				} else { // pseudo
					// check if pseudo dans la liste, then access the profile if yes
					for(Revendeur r : listeRevendeurs){
						if(r.getPseudo().equalsIgnoreCase(choix)){
							validInput = true;
							r.montrerProfil();

							System.out.println("\n1: Retourner au menu du catalogue" +
									"\n0: Retourner au menu principal");
							boolean validInput2 = false;
							Scanner scanner2 = new Scanner(System.in);
							do{
								try{
									String choix2 = scanner2.nextLine();
									if(choix2.equals("1")){
										validInput2 = true;
										// go to the menu
										voirCatalogue(util); // retourner au catalogue
									} else if (choix2.equals("0")){
										validInput2 = true;
										Utilisateur.afficherMenu(util); // retourner au menu principal
									}
								} catch (InputMismatchException e) {
									System.out.println("Svp entrer 0 ou 1");
								}
							} while(!validInput2);
							break;
						}
					}
					if(validInput == false){
						throw new InputMismatchException();
					}
				}
			} catch (InputMismatchException e) {
				System.out.println("Svp entrer 0, 1 ou un pseudo valide");
			}
		} while(!validInput);
	}


}