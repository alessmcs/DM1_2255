public class CarteCredit {
    private String numeroCarte;
    private String dateExpiration;
    private String cvc;
    private double solde;
    public CarteCredit(String numeroCarte, double solde){
        this.numeroCarte = numeroCarte;
        this.solde = solde;
    }

    public CarteCredit(String numCarte, String dateExp, String cvc) {

    }

    public CarteCredit(String numCarte, String dateExp, String cvc, double soldeCarte) {

    }

    public double getSolde(){
        return solde;
    }
    public void setSolde(double solde){
        this.solde= solde;
    }
    public String getNumeroCarte() {
        return numeroCarte;
    }
}
