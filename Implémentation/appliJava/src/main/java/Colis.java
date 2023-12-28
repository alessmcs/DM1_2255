import jdk.jshell.execution.Util;

import java.io.FileNotFoundException;
import java.nio.channels.AsynchronousChannelGroup;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * La classe colis représente un colis d'une commande
 */

public class Colis {

	private UUID numSuivi;
	private String dateArriveeEst;
	private StatutCommande statut;
	private static Map<UUID, Colis> colisMap = new HashMap<>();
	protected Commande commande;

	/**
	 * Constructeur pour initialiser un Colis avec une Commande donnée.
	 *
	 * @param commande La Commande associée.
	 */
	public Colis(Commande commande) {
		this.commande = commande;
		this.statut = commande.getStatutCommande();
		this.numSuivi = genererNumeroSuivi();
	}

	/**
	 * Génère un numéro de suivi aléatoire sous forme de UUID
	 * @return Le numéro de suivi généré
	 */
	private UUID genererNumeroSuivi() {
		return UUID.randomUUID();
	}
	/**
	 * Obtient le numéro de suivi du colis
	 *
	 * @return Le numéro de suivi du colis
	 */
	public UUID getNumSuivi() {
		return numSuivi;
	}

	/**
	 * Obtient le statu actuel du colis
	 * @return Le statut actuel du colis
	 */
	public StatutCommande getStatut() {
		return statut;
	}

	/**
	 * Permet de suivre l'état du colis en utilisant le numéro de suivi
	 * @param numSuivi Le numéro de suivi du colis
	 * @return Le statut actuel du colis ou null s'il n'est pas trouvé
	 */

	public static void suivreEtat(UUID numSuivi, Acheteur acheteur) throws FileNotFoundException {
		Colis colis = colisMap.get(numSuivi);
		if (colis != null && colis.getNumSuivi().equals(numSuivi)) {
			System.out.println("Le statut du colis avec le numéro de suivi " + numSuivi + " : " + colis.getStatut());

		} else {
			System.out.println("Aucun colis trouvé avec le numéro de suivi : " + numSuivi);

		}
		Utilisateur.afficherMenu(acheteur);
	}


	/**
	 * Permete de changer l'état du colis en utilisant le numéro de suivi
	 * @param numSuivi Le numéro de suivi du colis
	 * @param nouveauStatut Le nouveau statut attrubuer au colis
	 */
	public void changerEtat(UUID numSuivi, StatutCommande nouveauStatut, Revendeur revendeur) throws FileNotFoundException {
		Colis colis = colisMap.get(numSuivi);
		if (colis != null && colis.getNumSuivi().equals(numSuivi)) {
			Commande commande = colis.getCommande();
			if(commande != null){
			colis.setStatut(nouveauStatut);
			commande.setEtatCommande(nouveauStatut);
			System.out.println("Le statut du colis ayant le numéro : " + numSuivi + " a été changé à " + nouveauStatut);

		} else {
			System.out.println("Aucune commande associée au colis avec le numéro de suivi : " + numSuivi);
		}
	}else {
		System.out.println("Aucun colis trouvé avec le numéro de suivi : " + numSuivi);

		}
		Utilisateur.afficherMenu(revendeur);


	}

	/**
	 * Obtient la commande associée au colis
	 * @return La commande associée au colis
	 */

	private Commande getCommande() {
		return commande;
	}

	/**
	 * Met à jour le staut du colis
	 * @param nouveauStatut
	 */

	private void setStatut(StatutCommande nouveauStatut) {
		this.statut = nouveauStatut;
	}

}