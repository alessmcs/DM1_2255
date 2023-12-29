import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
public class TestCommande {
    Commande commande;
    Acheteur acheteur;
    ArrayList<Produit> listeProduits;

    Panier panier;

    @BeforeEach
    public void setUp(){
        // initialiser une liste de produits
        listeProduits = new ArrayList<>();
        listeProduits.add(new MaterielInfo("Écouteurs SONY WH-1000XM4", 409.99, 2, "Matériel informatique",
                20, "Écouteurs", "SONY", "WH-1000XM4", "14/08/2022", "Écouteurs"));
        listeProduits.add(new EquipementBureau("Cahier Canada quadrillé 40 pages", 1.79,230, "Equipement de bureau",
                0, "Cahier d'exercices", "Hilroy", "Canada", "Cahiers"));
        // initialiser un acheteur
        acheteur = new Acheteur("5144241314", "testeur@test.com", "test");
        acheteur.setNom("nom");
        acheteur.setPrenom("prenom");
        acheteur.setPseudo("pseudo");
        acheteur.setAdresseExpedition(new Adresse("92 rue Porcupine", "Vancouver", "BC", "h3k0p3", "Canada"));

        // mock panier
        panier = new Panier();
        panier.ajouterArticle(listeProduits.get(0));
        panier.ajouterArticle(listeProduits.get(1));

        commande = new Commande(acheteur, StatutCommande.en_production, acheteur.getAdresseExpedition(), 2, panier);
    }

    @Test
    public void testToString(){ // tester Commande.toString()
        String expected = "2\n" +
                "En production\n" +
                "ID : 1, Titre: Écouteurs SONY WH-1000XM4\n" +
                "409.99\n" +
                "ID : 2, Titre: Cahier Canada quadrillé 40 pages\n" +
                "1.79\n" +
                "Total : 411.7799987792969";

        String actual = commande.commandeToString(); // get le dernier elem
        assertEquals(actual, expected);
    }

    @Test
    public void testAjouterHistorique(){ // tester que la commande est correctement ajoutée à l'historique (Acheteur.ajouterHistorique())
        acheteur.addHistorique(commande);
        ArrayList<Commande> liste = acheteur.getHistoriqueCommandes();
        assertTrue(liste.contains(commande));
    }

    @Test
    public void testHistoriqueVide(){ // tester Acheteur.afficherHistorique() sur un historique vide
        // s'assurer que le message affiché est correct
        String expected = "Vous n'avez passé aucune commande pour le moment!";
        String actual = acheteur.afficherHistorique();
        assertEquals(actual, expected);
    }






}
