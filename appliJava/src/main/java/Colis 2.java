import java.util.UUID;

public class Colis {

	private int numSuivi;
	private String dateArriveeEst;
	private StatutCommande statut;

	public Colis(StatutCommande statutCommande) {
		this.statut = statutCommande;
	}

	public void suivreEtat() {
		// TODO - implement Colis.suivreEtat
		throw new UnsupportedOperationException();
	}

	public void changerEtat() {
		// TODO - implement Colis.changerEtat
		throw new UnsupportedOperationException();
	}

}