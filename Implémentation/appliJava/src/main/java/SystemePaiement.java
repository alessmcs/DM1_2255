import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SystemePaiement {

	public static boolean validerInfosPaiement(String num, String date, String cvc) {

		boolean n = ( num.length() == 16); // check if the credit card number is 16 numbers long
		boolean d = isValidExpirationDate(date);
		boolean c = isValidCvc(cvc);

		if (n && d && c) {
			return true;
		} else { return false; }
	}


	public static boolean isValidExpirationDate(String date) {
		String expirationPattern = "^(0[1-9]|1[0-2])([0-9]{2})$";
		Pattern pattern = Pattern.compile(expirationPattern);
		Matcher matcher = pattern.matcher(date);
		return matcher.matches();
	}

	public static boolean isValidCvc(String cvc) {
		String cvcPattern = "^[0-9]{3}$";
		Pattern pattern = Pattern.compile(cvcPattern);
		Matcher matcher = pattern.matcher(cvc);
		return matcher.matches();
	}


	public static void rembourserMontant(CarteCredit carteCredit, Commande commande) {
		double montantRemb = 0.0;
		for (Produit produit: commande.getArticles()){
			montantRemb += produit.getPrix();
		}
		double nouveauSold = carteCredit.getSolde() + montantRemb;
		carteCredit.setSolde(nouveauSold);

	}

}