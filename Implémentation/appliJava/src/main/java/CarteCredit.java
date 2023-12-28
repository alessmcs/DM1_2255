public class CarteCredit {
    private String numeroCarte;
    private String dateExpiration;
    private String cvc;
    private double solde;

    /**
     * 
     * @param numeroCarte
     * @param solde
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
     * 
     * @return
     */
    public double getSolde(){
        return solde;
    }

    /**
     * 
     * @param solde
     */
    public void setSolde(double solde){
        this.solde= solde;
    }

    /**
     * 
     * @return
     */
    public String getNumeroCarte() {
        return numeroCarte;
    }
}
