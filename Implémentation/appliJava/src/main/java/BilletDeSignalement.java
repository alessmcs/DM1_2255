public class BilletDeSignalement {

	private String descriptionProbleme;
	private String descriptionSolution;
	private boolean confirmationLivraison;
	private String descriptionRemplacement;
	private int numSuiviRemplacement;
	private boolean confirmationLivraisonRemplacement;

	public BilletDeSignalement(String descriptionProbleme){

		this.descriptionProbleme = descriptionProbleme;
	}

	public void setDescriptionSolution(String descriptionSolution) {
		this.descriptionSolution = descriptionSolution;

	public String getDescriptionProbleme() {

		return descriptionProbleme;
	}
	public String getDescriptionSolution() {
		return descriptionSolution;
	}

}