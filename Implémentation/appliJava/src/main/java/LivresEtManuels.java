import java.util.InputMismatchException;
import java.util.Scanner;

public class LivresEtManuels extends Produit {

	private String categorie = "Livres et manuels";
	private String ISBN;
	private String auteur;
	private String maisonEdition;
	private String genre;
	private String dateParution;
	private int numEdition;
	private int numVol;

	// Constructeur
	public LivresEtManuels(String titre, double prix, int qte, String cat, int nbPoints, String description,
						   String ISBN, String auteur, String maisonEdition, String genre, String dateParution, int numEd, int numVol) {
		super(titre, prix, qte, cat, nbPoints, description);
		this.ISBN = ISBN;
		this.auteur = auteur;
		this.maisonEdition = maisonEdition;
		this.genre = genre;
		this.dateParution = dateParution;
		this.numEdition = numEd;
		this.numVol = numVol;
	}

	public String getISBN(){
		return ISBN;
	}

	public String getAuteur(){
		return auteur;
	}

	public String getMaisonEdition(){
		return maisonEdition;
	}

	public String getGenre(){
		return genre;
	}

	public String getDateParution(){
		return dateParution;
	}

	public int getNumEdition(){
		return numEdition;
	}

	public int getNumVol(){
		return numVol;
	}

	/**
		Voir la page du produit & donner l'option à l'utilisateur de liker/commenter le produit ou l'ajouter au panier.

		@param util l'utilisateur connecté
	 */
	@Override
	public void voirDetails(Utilisateur util){
		Scanner scanChoix = new Scanner(System.in);

		System.out.println("\nID: " + ID + "\nTitre: " + titre + "\n" + categorie + "\n" + description + "\nPrix:" + prix + "\nISBN:" + ISBN + "\nAuteur: " + auteur + ", Maison d'édition: " + maisonEdition +
				", Genre: " + genre + " , " + numEdition + "e édition , Volume " + numVol + "\nPublié le ") ;

		System.out.println();

		if(util instanceof Acheteur){
			System.out.print("\nEntrez votre choix: ");
			while(true){
				System.out.println("\nQue voulez-vous faire?");
				System.out.println("1. Voir les commentaires");
				System.out.println("2. Commenter et evaluer le produit");
				System.out.println("3. Liker le produit");
				System.out.println("4. Ajouter au panier le produit");
				System.out.println("5. Revenir au catalogue");

				Revendeur revendeur = getRevendeur();
				String choix = scanChoix.nextLine();
				switch(choix){
					case "1":
						if(enregistrerEvalComplete(util) == null){
							System.out.println("Aucun commentaire");
						} else {
							super.voirEval(util);
						}
						break;
					case "2":
						super.commenter(util);
						super.evaluer(revendeur);
						super.verifier(util,revendeur);
						break;
					case "3":

						super.liker((Acheteur) util, revendeur);
						break;
					case "4":
						super.demanderAjoutPanier((Acheteur) util);
						break;
					case "5":
						Catalogue.voirCatalogue((Acheteur) util);
					default:
						System.out.println("Svp entrez une option valide!");
				}
			}
		} else if ( util instanceof Revendeur ){
			// Un revendeur peut seulement voir la page d'un produit
			System.out.print("\nEntrez votre choix: ");
			System.out.println("\n1. Retourner au catalogue\n0: Retourner au menu principal");
			boolean validInput = false;
			Scanner s = new Scanner(System.in);
			do{
				try{
					String choix = s.nextLine();
					switch(choix){
						case "1" :
							Catalogue.voirCatalogue((Acheteur) util);
							break;
						case "0" :
							try{
								Utilisateur.afficherMenu(util); // retourner au menu principal
							}catch (Exception e){
								System.out.println("Une erreur c'est produite veuiller réessayer");
								s.nextLine();
							}					}
				} catch (InputMismatchException e){
					System.out.println("Entrée invalide, svp entrez 1 ou 0");
				}
			} while (!validInput);
		}
		}
}