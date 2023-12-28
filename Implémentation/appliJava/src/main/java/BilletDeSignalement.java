import java.util.ArrayList;
import java.util.List;

public class BilletDeSignalement {

	private Acheteur acheteur;
	private String descriptionProbleme;
	private String descriptionSolution;
	private boolean confirmationLivraison;
	private String descriptionRemplacement;
	private int numSuiviRemplacement;
	private boolean confirmationLivraisonRemplacement;

	public BilletDeSignalement(Acheteur acheteur, String descriptionProbleme) {
		this.acheteur = acheteur;
		this.descriptionProbleme = descriptionProbleme;
	}

	public BilletDeSignalement(Acheteur acheteur, String descriptionProbleme, String descriptionSolution,boolean
			confirmationLivraison, String descriptionRemplacement, int numSuiviRemplacement, boolean
			confirmationLivraisonRemplacement) {
		this.acheteur = acheteur;
		this.descriptionProbleme = descriptionProbleme;
		this.descriptionSolution = descriptionSolution;
		this.confirmationLivraison = confirmationLivraison;
		this.descriptionRemplacement = descriptionRemplacement;
		this.numSuiviRemplacement = numSuiviRemplacement;
		this.confirmationLivraisonRemplacement = confirmationLivraisonRemplacement;
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

	public void setNumSuiviRemplacement(int NumSuiviRemplacement) {
		this.descriptionRemplacement = descriptionRemplacement;
	}

	public int getNumSuiviRemplacement() {
		return numSuiviRemplacement;
	}

	public boolean isConfirmationLivraisonRemplacement() {
		return confirmationLivraisonRemplacement;
	}

	public void setConfirmationLivraisonRemplacement(boolean confirmationLivraisonRemplacement) {
		this.confirmationLivraisonRemplacement = confirmationLivraisonRemplacement;
	}
	private static final List<BilletDeSignalement> billets = new ArrayList<>();

	public static void ajouterBillet(BilletDeSignalement billet) {
		billets.add(billet);
	}

	public static List<BilletDeSignalement> getBillets() {
		return new ArrayList<>(billets);
	}
}