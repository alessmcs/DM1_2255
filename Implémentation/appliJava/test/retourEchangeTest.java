import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class retourEchangeTest {
    @Test
    public void testCalculerDifference() {
        retourEchange retourEchange = new retourEchange();

        // Cas de test 1 : Comparaison de deux produits avec le même prix
        Produit produit1 = new Produit("Produit1", 10.0, 1, "TestCategory", 5, "Description");
        Produit produit2 = new Produit("Produit2", 10.0, 1, "TestCategory", 5, "Description");

        double difference1 = retourEchange.calculerDifference(produit1, produit2);
        assertEquals(0.0, difference1, 0.01);

        // Cas de test 2 : Comparaison de deux produits avec des prix différents
        Produit produit3 = new Produit("Produit3", 12.0, 1, "TestCategory", 5, "Description");
        Produit produit4 = new Produit("Produit4", 22.0, 1, "TestCategory", 5, "Description");

        double difference2 = retourEchange.calculerDifference(produit3, produit4);
        assertEquals(-10.0, difference2, 0.01);
    }
}
