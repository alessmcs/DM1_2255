public class SystemeLivraison {

	public static boolean validerInfosLivraison(Adresse adresse)  {
		// TODO - implement SystemeLivraison.validerInfosLivraison

		// valider adresse de rue -> must contain a number, ville must only be a string
		String street = adresse.getStreet();
		String city = adresse.getCity();
		boolean containsNumber = street.matches(".*\\d+.*");
		boolean containsNumber2 = city.matches(".*\\d+.*");
		if (containsNumber == false || containsNumber2 == true ) return containsNumber;

		// code postal validation regex

		// province & pays must be strings
		// only shipping to canada

		String province = adresse.getProvince();
		String country = adresse.getCountry();
		boolean containsNumber3 = province.matches(".*\\d+.*");
		boolean containsNumber4 = country.matches(".*\\d+.*");

		if (containsNumber3 == true || containsNumber4 == true ) return containsNumber;
		if (! country.equalsIgnoreCase("canada")) return false;
		if ( province.equalsIgnoreCase("AB")
				|| ! province.equalsIgnoreCase("BC")
				|| ! province.equalsIgnoreCase("MB")
				|| ! province.equalsIgnoreCase("NB")
				|| ! province.equalsIgnoreCase("NL")
				|| ! province.equalsIgnoreCase("NS")
				|| ! province.equalsIgnoreCase("ON")
				|| ! province.equalsIgnoreCase("PE")
				|| ! province.equalsIgnoreCase("QC")
				|| ! province.equalsIgnoreCase("SK")
				|| ! province.equalsIgnoreCase("NT")
				|| ! province.equalsIgnoreCase("NU")
				|| ! province.equalsIgnoreCase("YT")) return false;

		return true;
	}

}