public class Adresse {
    private String street;
    private String city;
    private String province;
    private String postalCode;
    private String country;

    /**
     * Constructeur de la classe Adresse
     * 
     * @param street rue de l'acheteur
     * @param city ville de l'acheteur
     * @param province province de l'acheteur
     * @param postalCode code postal de l'acheteur
     * @param country pays de l'acheteur
     */
    public Adresse(String street, String city, String province, String postalCode, String country) {
        this.street = street;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.country = country;
    }

    /**
     * Retourne la rue de l'acheteur
     * 
     * @return la rue de l'acheteur
     */
    public String getStreet() {
        return street;
    }

    /**
     * Met à jour la rue de l'acheteur
     * 
     * @param street rue de l'acheteur
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Retoune la ville de l'acheteur
     * 
     * @return ville de l'acheteur
     */
    public String getCity() {
        return city;
    }

    /**
     * met à jour la ville de l'acheteur
     * 
     * @param city ville de l'acheteur
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Retourne la province de l'acheteur
     * 
     * @return province de l'acheteur
     */
    public String getProvince() {
        return province;
    }

    /**
     * Met à jour la province de l'acheteur
     * 
     * @param province province de l'acheteur
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * Retourne le code postal de l'acheteur
     * 
     * @return code postal de l'acheteur
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Met à jour le code postal de l'acheteur
     * 
     * @param postalCode code postal de l'acheteur
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Retourne le pays de l'acheteur
     * 
     * @return pays de l'acheteur
     */
    public String getCountry() {
        return country;
    }

    /**
     * Met à jour le pays de l'acheteur
     * 
     * @param country pays de l'acheteur
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
        Formatte correctement l'adresse pour l'afficher

        @return le String formatté
     */
    public String toString() {
        return String.format("%s, %s, %s, %s, %s", street, city, province, postalCode, country);
    }

    /**
        Formatte l'adresse selon un String donné (dans le csv) & instancie l'objet

        @param adresseString l'adresse dans un long String
        @return l'objet Adresse
     */
    public static Adresse adresseBuilder(String adresseString){
        String[] str = adresseString.split(",");
        Adresse result = new Adresse(str[0], str[1], str[2], str[3], str[4]);

        return result;
    }


}
