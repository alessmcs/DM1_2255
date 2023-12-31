import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SystemePaiement {

	/**
	 * Valide les informations de paiement d'un utilisateur
	 *
	 * @param num le numéro de carte
	 * @param date la date d'expiration
	 * @param cvc le cvc de la carte
	 * @return true si les informations sont valides, false sinon
	 */
	public static boolean validerInfosPaiement(String num, String date, String cvc) {

		boolean n = ( num.length() == 16); // check if the credit card number is 16 numbers long
		boolean d = isValidExpirationDate(date);
		boolean c = isValidCvc(cvc);

		if (n && d && c) {
			return true;
		} else { return false; }
	}

	/**
	 * Valide le format de la date d'expiration
	 *
	 * @param date l'entrée de l'utilisateur
	 * @return true si les informations sont valides, false sinon
	 */
	public static boolean isValidExpirationDate(String date) {
		String expirationPattern = "^(0[1-9]|1[0-2])([0-9]{2})$";
		Pattern pattern = Pattern.compile(expirationPattern);
		Matcher matcher = pattern.matcher(date);
		return matcher.matches();
	}

	/**
	 * Valide le format du CVC
	 *
	 * @param cvc l'entrée de l'utilisateur
	 * @return true si les informations sont valides, false sinon
	 */
	public static boolean isValidCvc(String cvc) {
		String cvcPattern = "^[0-9]{3}$";
		Pattern pattern = Pattern.compile(cvcPattern);
		Matcher matcher = pattern.matcher(cvc);
		return matcher.matches();
	}


	/**
	 * Rembourse le montant dû sur la carte de crédit d'un acheteur
	 * 
	 * @param carteCredit carte de crédit 
	 * @param commande la commande concernée
	 */
	public static void rembourserMontant(CarteCredit carteCredit, Commande commande) {
		double montantRemb = 0.0;
		for (Produit produit: commande.getArticles()){
			montantRemb += produit.getPrix();
		}
		double nouveauSold = carteCredit.getSolde() + montantRemb;
		carteCredit.setSolde(nouveauSold);

	}

}