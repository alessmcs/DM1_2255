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
	static Map<UUID, Colis> colisMap = new HashMap<>();
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
	 * Obtient la commande associée au colis
	 * @return La commande associée au colis
	 */

	Commande getCommande() {
		return commande;
	}

	/**
	 * Met à jour le staut du colis
	 * @param nouveauStatut
	 */

	void setStatut(StatutCommande nouveauStatut) {
		this.statut = nouveauStatut;
	}

	public void setNumSuivi(UUID numSuivi) {
	}
}