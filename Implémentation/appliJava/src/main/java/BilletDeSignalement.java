public class BilletDeSignalement {

	private String descriptionProbleme;
	private String descriptionSolution;
	private boolean confirmationLIvraison;
	private String descriptionRemplacement;
	private int numSuiviRemplacement;
	private boolean confirmationLivraisonRemplacement;

	/**
	 * Constructeur de la classe BilletDeSignalement
	 * 
	 * @param descriptionProbleme description du problème selon le type de billet de signalement
	 */
	public BilletDeSignalement(String descriptionProbleme){
		this.descriptionProbleme = descriptionProbleme;
	}

	/**
	 * Retourne la descprition du problème
	 * 
	 * @return descrpition du problème
	 */
	public String getDescriptionProbleme() {
		return descriptionProbleme;
	}

}