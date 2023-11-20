import java.util.List;
<<<<<<< HEAD
import java.util.UUID;

=======
>>>>>>> b88a840f83a9681722afe602713d0b33fe3d26c9
public class Produit {

	protected String titre;
	protected double prix;
	protected int ID;
	protected static int qteEnStock;
	protected String catégorie;
	protected int nbPoints;
	protected String description;
	protected String dateMiseEnVente;
	protected List<Commentaire> commentaires;
	protected int note;

	private String image; // nom du fichier

	public Produit(String titre, double prix, int qte, String cat, int nbPoints, String description, String date){
		this.titre = titre;
		this.prix = prix;
		this.catégorie = cat;
		this.qteEnStock = qte;
		this.nbPoints = nbPoints;
		this.description = description;
		this.dateMiseEnVente = date;
	}

	public void addImage(String fileName){
		this.image = fileName;
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

	public int getPoints() {
		return this.nbPoints;
	}

	public int getId(){
		return ID;
	}

	/**
	 * 
	 * @param t
	 */
	public void setTitre(String t) {
		this.titre = t;
	}

	public String getTitre(){
		return titre;
	}

	public double getPrix(){
		return prix;
	}

	public void setPrix(double prix) {
		// TODO - implement Produit.setPrix
		throw new UnsupportedOperationException();
	}

	public static int getQte(){
		return qteEnStock;
	}
	public static void setQte(int nouvQuant){
		qteEnStock = nouvQuant;
	}



}