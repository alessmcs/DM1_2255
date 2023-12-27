public class BilletDeSignalement {

	private Acheteur acheteur;
	private String descriptionProbleme;
	private String descriptionSolution;
	private boolean confirmationLivraison;
	private String descriptionRemplacement;
	private int numSuiviRemplacement;
	private boolean confirmationLivraisonRemplacement;

	public BilletDeSignalement(String descriptionProbleme) {
		this.descriptionProbleme = descriptionProbleme;
	}
	public Acheteur getAcheteur() {
		return acheteur;
	}

	public String getDescriptionProbleme() {
		return descriptionProbleme;
	}

	public void setDescriptionProbleme(String descriptionProbleme) {
		this.descriptionProbleme = descriptionProbleme;
	}

	public String getDescriptionSolution() {
		return descriptionSolution;
	}

	public void setDescriptionSolution(String descriptionSolution) {
		this.descriptionSolution = descriptionSolution;
	}

	public boolean isConfirmationLivraison() {
		return confirmationLivraison;
	}

	public void setConfirmationLivraison(boolean confirmationLivraison) {
		this.confirmationLivraison = confirmationLivraison;
	}

	public String getDescriptionRemplacement() {
		return descriptionRemplacement;
	}

	public void setDescriptionRemplacement(String descriptionRemplacement) {
		this.descriptionRemplacement = descriptionRemplacement;
	}

	public int getNumSuiviRemplacement() {
		return numSuiviRemplacement;
	}

	public void setNumSuiviRemplacement(int numSuiviRemplacement) {
		this.numSuiviRemplacement = numSuiviRemplacement;
	}

	public boolean isConfirmationLivraisonRemplacement() {
		return confirmationLivraisonRemplacement;
	}

	public void setConfirmationLivraisonRemplacement(boolean confirmationLivraisonRemplacement) {
		this.confirmationLivraisonRemplacement = confirmationLivraisonRemplacement;
	}
}