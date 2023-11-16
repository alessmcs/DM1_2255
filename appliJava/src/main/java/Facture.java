public class Facture {

	private static double montant;
	private String date;
	private static double rabais;

	public Facture(double montant){
		this.montant = montant;
	}

	public static void setRabais(int rabais){
		rabais = rabais;
		montant -= rabais;
	}

}