public class BilletDeSignalement {

	private String descriptionProbleme;
	private String descriptionSolution;
	private boolean confirmationLIvraison;
	private String descriptionRemplacement;
	private int numSuiviRemplacement;
	private boolean confirmationLivraisonRemplacement;

	public BilletDeSignalement(String descriptionProbleme){
		this.descriptionProbleme = descriptionProbleme;
	}
	public String getDescriptionProbleme() {
		return descriptionProbleme;
	}

}