import java.util.Scanner;

public class EquipementBureau extends Produit {

	private String categorie = "Équipement de bureau";
	private String marque;
	private String modele;
	private String sousCategorie;

	public EquipementBureau(String titre, double prix, int qte, String cat, int nbPoints, String description, String marque,
							String modele, String sousCategorie) {
		super(titre, prix, qte, cat, nbPoints, description);
	}


	// overriding voirDetails pour imprimer les détails spécifiques du produit
	@Override
	public void voirDetails(Acheteur ach){
		Scanner scanChoix = new Scanner(System.in);

		System.out.println("\nID: " + ID + "\nTitre: " + titre + "\n" + categorie + "\n" + description + prix + "\nMarque:" + marque + "\nModèle: " + modele
				+ "\nSous-Catégorie: " + sousCategorie);

		System.out.println();

		System.out.print("Entrez votre choix: ");


		while(true){
			System.out.println("\nQue voulez-vous faire?");
			System.out.println("1. Voir les commentaires");
			System.out.println("2. Commenter et evaluer le produit");
			System.out.println("3. Liker le produit");
			System.out.println("4. Ajouter au panier le produit");
			System.out.println("5. Revenir au catalogue");

			String choix = scanChoix.nextLine();
			switch(choix){
				case "1":
					if(enregistrerEvalComplete(ach) == null){
						System.out.println("Aucun commentaire");
					} else {
						super.voirEval(ach);
					}
					break;
				case "2":
					super.commenter(ach);
					super.evaluer();
					super.verifier(ach);
					break;
				case "3":
					super.liker();
					break;
				case "4":
					super.demanderAjoutPanier(ach);
					break;
				case "5":
					Catalogue.voirCatalogue(ach);
				default:
					System.out.println("Svp entrez une option valide!");
			}
		}


	}
}