public class SystemeLivraison {

	public static boolean validerInfosLivraison(Adresse adresse)  {
		// TODO - implement SystemeLivraison.validerInfosLivraison

		// valider adresse de rue -> must contain a number, ville must only be a string
		String street = adresse.getStreet();
		String city = adresse.getCity();
		boolean containsNumber = street.matches(".*\\d+.*"); // if adresse contains number
		boolean containsNumber2 = city.matches(".*\\d+.*"); // if city contains number
		if (containsNumber == false || containsNumber2 == true ) return false;

		String province = adresse.getProvince();
		String country = adresse.getCountry();
		boolean containsNumber3 = province.matches(".*\\d+.*");
		boolean containsNumber4 = country.matches(".*\\d+.*");

		if (containsNumber3 == true || containsNumber4 == true ) return false;
		if (! country.equalsIgnoreCase("canada")) return false;
		String[] provinces = {"AB", "BC", "MB", "NB", "NL", "NS", "ON", "PE", "QC", "SK", "NT", "NU", "YT"};
		for (String p : provinces){
			if (province.equalsIgnoreCase(p)){
				return true;
			}
		}

		return false;
	}

}