public class Main {
    public static Acheteur acheteurConnecte = new Acheteur("theStudent123", new Adresse("12 rue Clark", "Montréal", "QC", "H8N 9P4", "Canada"), "5142234542", "chien1243", "mtlStudent@gmail.com" );

    public static void main(String[] args){


        Produit livre1 = new LivresEtManuels("Genie logiciel", 78.90,4, "livresEtManuels", 10, "Livre de génie logiciel", "20/09/2023", "0-9700-4429-1", "Aless", "SchoolBooks", "Genie logiciel", "08/10/2022", 8, 1);
        Produit livre2 = new LivresEtManuels("Calcul 1", 66.79,3, "livresEtManuels", 15, "Livre de calcul", "01/10/2018", "0-8544-1689-7", "Aless", "SchoolBooks", "Calcul", "18/01/2022", 2, 1);

        Catalogue.ajouterAuCatalogue(livre1);
        Catalogue.ajouterAuCatalogue(livre2);

        //Acheteur acheteur2 = new Acheteur("theStudent123", new Adresse("12 rue Clark", "Montréal", "QC", "H8N 9P4", "Canada"), "5142234542", "chien1243", "mtlStudent@gmail.com" );

        acheteurConnecte.ajouterPanierAcheteur(livre1);
        acheteurConnecte.ajouterPanierAcheteur(livre2);

        acheteurConnecte.voirPanierAcheteur();

    }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }
}
