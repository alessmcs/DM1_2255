import java.util.Scanner;
public class Probleme {
	private LocalDate dateEmission;
	private LocalDate dateLimiteSignalement;
	private String descriptionProbleme;
	private BilletDeSignalement billet;

	/*public void signalerProbleme() {
		// TODO - implement Probleme.signalerProbleme
		Scanner scanner = new Scanner(System.in);

		// Demander à l'acheteur de décrire le problème
		System.out.print("Décrivez le problème rencontré : ");
		this.descriptionProbleme = scanner.nextLine();

		// Afficher un message pour indiquer que le signalement a été créé
		System.out.println("Le signalement a été créé avec succès.");
		BilletDeSignalement billet = new BilletDeSignalement(descriptionProbleme);


	}
	 */

	/**
	 * @param acheteur L'acheteur qui signale un problème encontré avec le produit
	 * @param revendeur Le renvendeur traitant le problème
	 * @param descriptionProbleme Une description du problème en question
	 */
	public void signalerProbleme(Acheteur acheteur, Revendeur revendeur, String descriptionProbleme) {

		// Initialiser les champs nécessaires dans la méthode
		this.dateEmission = LocalDate.now();
		this.dateLimiteSignalement = dateEmission.plus(365, ChronoUnit.DAYS);

		// Vérifier la validité de la demande
		if (LocalDate.now().isAfter(dateLimiteSignalement)) {
			System.out.println("Désolé, vous avez dépasser la période de 365 jours pour signaler un problème.");
			return;
		}

		Scanner scanner = new Scanner(System.in);

		System.out.print("Veuillez décrire le problème que vous avez rencontré : ");
		descriptionProbleme = scanner.nextLine();

		// Afficher un message pour indiquer que le signalement a été créé
		System.out.println("Votre signalement a été crée.");
		// Créer un billet de signalement
		billet = new BilletDeSignalement(descriptionProbleme);

		// Envoyer le billet au profil du revendeur
		revendeur.recevoirBilletDeSignalement(billet);
	}
}
