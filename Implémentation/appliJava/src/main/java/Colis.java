import java.util.UUID;

public class Colis {

	private int numSuivi;
	private String dateArriveeEst;
	private StatutCommande statut;

	public Colis(StatutCommande statutCommande) {
		this.statut = statutCommande;
	}

	public void suivreEtat() {
		throw new UnsupportedOperationException();
	}

	public void changerEtat() {
		throw new UnsupportedOperationException();
	}

}