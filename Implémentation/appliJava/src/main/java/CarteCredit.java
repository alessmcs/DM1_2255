public class CarteCredit {
    private String numeroCarte;
    private String dateExpiration;
    private String cvc;
    private double solde;

    /**
     * Constructeur de la classe CarteCredit
     * 
     * @param numeroCarte numéro de la carte de crédit de l'utilisateur 
     * @param solde solde de la carte de crédit de l'utilisateur
     */
    public CarteCredit(String numeroCarte, double solde){
        this.numeroCarte = numeroCarte;
        this.solde = solde;
    }

    public CarteCredit(String numCarte, String dateExp, String cvc) {

    }

    public CarteCredit(String numCarte, String dateExp, String cvc, double soldeCarte) {

    }

    /**
     * Retourne le solde de la carte de crédit
     * 
     * @return le solde de la carte de cérdit
     */
    public double getSolde(){
        return solde;
    }

    /**
     * Met à jour le solde de la carte de crédit
     * 
     * @param solde
     */
    public void setSolde(double solde){
        this.solde= solde;
    }

    /**
     * Retourne le numéro de carte de cérdit de l'utilisateur
     * 
     * @return numéro de carte de crédit
     */
    public String getNumeroCarte() {
        return numeroCarte;
    }
}
