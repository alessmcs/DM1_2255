import java.util.List;

public class Produit {
	private String titre;
	private double prix;
	private int ID;
	private int qteEnStock;
	private String categorie;
	private int nbPoints;
	private String description;
	private String dateMiseEnVente;
	// private List<Commentaire> commentaires;
	private int note;

	public Produit(String titre, double prix, int ID, int qteEnStock, String categorie, int nbPoints, String description, String dateMiseEnVente){
		this.titre = titre;
		this.prix = prix;
		this.ID = ID;
		this.qteEnStock = qteEnStock;
		this.categorie = categorie;
		this.nbPoints = nbPoints;
		this.description = description;
		this.dateMiseEnVente = dateMiseEnVente;
	}

	public String getTitre() {
		return titre;
	}

	public double getPrix() {
	  return prix;
	  }

	public int getID() {
		return ID;
	}

	public int getQteEnStock() {
		return qteEnStock;
	}

	public String getCategorie() {
	  return categorie;
	}

	public int getNbPoints() {
		return nbPoints;
	}

	public String getDescription() {
		return description;
	}

	public String getDateMiseEnVente() {
		return dateMiseEnVente;
	}

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

	//public Commentaire commenter() {
	// TODO - implement Produit.commenter
	//  throw new UnsupportedOperationException();
	//}

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
	public void setPoints() {
	}

	public void setID(int ID) {

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

	public String toString() {
		return titre + "prix: " + prix + "$" + description;
	}
}
