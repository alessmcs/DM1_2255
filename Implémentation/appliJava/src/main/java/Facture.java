public class Facture {

	private static double montant;
	private String date;
	private static double rabais;

	/**
		Définir le montant de la facture & le conserver dans une variable locale

		@param montant le montant total de la facture
	 */
	public Facture(double montant){
		this.montant = montant;
	}

	/**
		Changer le montant total d'une commande et la conserver dans une variable pour l'afficher dans la facture

		@param rabais le rabais qu'on souhaite appliquer au montant
	 */
	public static void setRabais(int rabais){
		rabais = rabais;
		montant -= rabais;
	}

	/**
		Obtenir le montant total du panier

		@return le montant total du panier
	 */
	public static double getTotal(){
		return montant;
	}

}