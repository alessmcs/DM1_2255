import java.util.ArrayList;
import java.util.Scanner;
public class Probleme {
	private String Date;
	private String descriptionProbleme;

	/**
	 * Permet de signaler des problèmes provenant d'UniShop
	 */
	public void signalerProbleme(ArrayList<Revendeur> listeRevendeurs) {
		Scanner scanner = new Scanner(System.in);

		// Demander à l'acheteur de décrire le problème
		System.out.print("Décrivez le problème rencontré : ");
		this.descriptionProbleme = scanner.nextLine();

		// Afficher un message pour indiquer que le signalement a été créé
		System.out.println("Le signalement a été créé avec succès.");
		BilletDeSignalement billet = new BilletDeSignalement(descriptionProbleme);


	}
}
