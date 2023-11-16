public class Main {
    public static void main(String[] args){

        Produit livre1 = new LivresEtManuels("Genie logiciel", 78.90,4, "livresEtManuels", 10, "Livre de génie logiciel", "20/09/2023", "88918ABS", "Aless", "SchoolBooks", "Genie logiciel", "08/10/2022", 8, 1);
        Produit livre2 = new LivresEtManuels("Calcul 1", 66.79,3, "livresEtManuels", 15, "Livre de calcul", "01/10/2023", "9009fjBs", "Aless", "SchoolBooks", "Calcul", "18/01/2022", 2, 1);

        Catalogue.ajouterAuCatalogue(livre1);
        Catalogue.ajouterAuCatalogue(livre2);

        Panier.ajouterArticle(livre1); // ajouter au panier
        Panier.ajouterArticle(livre2); // ajouter au panier

        Panier.voirPanier();
        //Commande.passerCommande();
        //TODO: le panier va ensuite dans la commande
    }
}
