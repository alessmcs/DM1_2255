public class Adresse {
    private String street;
    private String city;
    private String province;
    private String postalCode;
    private String country;

    // Constructor
    public Adresse(String street, String city, String province, String postalCode, String country) {
        this.street = street;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.country = country;
    }

    // Getters and setters for each field
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    /*
        Formatte correctement l'adresse pour l'afficher

        @return le String formatté
     */
    public String toString() {
        return String.format("%s, %s, %s, %s, %s", street, city, province, postalCode, country);
    }

    /*
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
