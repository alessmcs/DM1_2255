import java.util.List;
public class Produit {

	private String titre;
	private float prix;
	private int ID;
	private int qteEnStock;
	private String catégorie;
	private int nbPoints;
	private String description;
	private String dateMiseEnVente;
	private List<Commentaire> commentaires;
	private int note;

	public void voirDetails() {
		// TODO - implement Produit.voirDetails
		throw new UnsupportedOperationException();
	}

	public void evaluer() {
		// TODO - implement Produit.evaluer
		throw new UnsupportedOperationException();
	}

	public void liker() {
		// TODO - implement Produit.liker
		throw new UnsupportedOperationException();
	}

	public Commentaire commenter() {
		// TODO - implement Produit.commenter
		throw new UnsupportedOperationException();
	}

	public void promouvoir() {
		// TODO - implement Produit.promouvoir
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param d
	 */
	public void setDescription(String d) {
		this.description = d;
	}

	/**
	 * 
	 * @param points
	 */
	public void setPoints(int points) {
		// TODO - implement Produit.setPoints
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param t
	 */
	public void setTitre(String t) {
		this.titre = t;
	}

	/**
	 * 
	 * @param prix
	 */
	public void setPrix(double prix) {
		// TODO - implement Produit.setPrix
		throw new UnsupportedOperationException();
	}

}