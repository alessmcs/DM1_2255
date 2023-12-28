import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
public class Probleme {
	private LocalDate dateEmission;
	private LocalDate dateLimiteSignalement;
	private String descriptionProbleme;
	private BilletDeSignalement billet;

	/**
	 * Cette méthode permet à un acheteur qui est dissatisfait avec un produit livré de reporter un problème.
	 *
	 * @param acheteur  L'acheteur qui signale un problème encontré avec le produit
	 * @param revendeur Le renvendeur traitant le problème
	 */
	public void signalerProbleme(Acheteur acheteur, Revendeur revendeur) {

		// Initialiser les champs nécessaires dans la méthode
		LocalDate dateEmission = LocalDate.now();
		LocalDate dateLimiteSignalement = dateEmission.plus(365, ChronoUnit.DAYS);

		// Vérifier la validité de la demande
		if (LocalDate.now().isAfter(dateLimiteSignalement)) {
			System.out.println("Désolé, vous avez dépasser la période de 365 jours pour signaler un problème.");
			return;
		}

		Scanner scanner = new Scanner(System.in);

		System.out.print("Veuillez décrire le problème que vous avez rencontré : ");
		String descriptionProbleme = scanner.nextLine();

		// Afficher un message pour indiquer que le signalement a été créé
		System.out.println("Votre signalement a été crée.");
		// Créer un billet de signalement
		billet = new BilletDeSignalement(acheteur, descriptionProbleme);

		// Envoyer le billet au profil du revendeur
		revendeur.recevoirBilletDeSignalement(billet);

		// Traiter le billet de signalement
		revendeur.gererProbleme(revendeur, new Probleme(), billet);
	}
	public LocalDate getDateEmission() {
		return dateEmission;
	}
}
