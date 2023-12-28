import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class TestPanier {
    // tester ajouterArticle et/ou voirPanier
    // todo: maybe initialise a list d'articles???

    Panier panierTest;
    Produit article1;
    Produit article2;
    ArrayList<Produit> listeProduits;

    Commande commande;
    Acheteur acheteur;

    @Before
    public void setUp() {
        panierTest = new Panier();
        listeProduits = new ArrayList<>();

        this.article1 = new EquipementBureau("Cahier Canada quadrillé 40 pages", 1.79,230, "Equipement de bureau",
                0, "Cahier d'exercices", "Hilroy", "Canada", "Cahiers");
        this.article2 = new MaterielInfo("Écouteurs SONY WH-1000XM4", 409.99, 2, "Matériel informatique",
                20, "Écouteurs", "SONY", "WH-1000XM4", "14/08/2022", "Écouteurs");

        listeProduits.add(article1);
        listeProduits.add(article2);

        acheteur = new Acheteur("5144241314", "testeur@test.com", "test");
        acheteur.setPrenom("prenomTest");
        acheteur.setNom("nomTest");
        acheteur.setPseudo("testeur1");
        acheteur.setAdresseExpedition(new Adresse("92 rue Porcupine", "Vancouver", "BC", "h3k0p3", "Canada"));

    }

    @Test
    public void testAjouterArticle() {
        panierTest.ajouterArticle(article1);
        ArrayList<Produit> articles = panierTest.getArticles();
        assertTrue(articles.contains(article1));
    }

}
