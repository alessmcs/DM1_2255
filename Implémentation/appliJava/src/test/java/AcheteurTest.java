import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import java.util.UUID;


class AcheteurTest {

    @Test
    void testAjouterSuiveur_WithExistingAcheteur() {
        // Crée une liste d'Acheteurs
        ArrayList<Acheteur> acheteurs = new ArrayList<>();

        // Crée un Acheteur à suivre
        Acheteur acheteurToFollow = new Acheteur("4382232715", "lollipop@gmail.com", "genielog");
        acheteurToFollow.setPrenom("Luna");
        acheteurToFollow.setNom("Delivici");
        acheteurToFollow.setPseudo("pseudoToFollow");
        acheteurs.add(acheteurToFollow);

        // Crée un Acheteur qui effectue le suivi
        Acheteur follower = new Acheteur("4387052715", "chien45@gmail.com", "jaimeleschiens");
        follower.setPrenom("Felix");
        follower.setNom("Bredon");
        follower.setPseudo("fbredon");
        follower.setAdresseExpedition(new Adresse("71 rue Rembouillet", "Montréal", "h9n3r7", "QC", "Canada"));
        acheteurs.add(follower);

        // Exécute la méthode ajouterSuiveur
        follower.ajouterSuiveur(acheteurToFollow);

        // Vérifie que le suivi a été effectué avec succès
        Assertions.assertTrue(follower.getListeSuiveurs().contains(acheteurToFollow));
    }

    @Test
    void testAjouterSuiveur_WithNonexistentAcheteur() {
        // Crée une liste d'Acheteurs
        ArrayList<Acheteur> acheteurs = new ArrayList<>();

        // Crée un Acheteur qui effectue le suivi
        Acheteur follower = new Acheteur("4387052715", "chien45@gmail.com", "jaimeleschiens");
        follower.setPrenom("Felix");
        follower.setNom("Bredon");
        follower.setPseudo("fbredon");
        follower.setAdresseExpedition(new Adresse("71 rue Rembouillet", "Montréal", "h9n3r7", "QC", "Canada"));
        acheteurs.add(follower);

        // Crée un Acheteur à suivre avec les mêmes informations (cet Acheteur n'existe pas encore dans la liste)
        Acheteur acheteurToFollow = new Acheteur("4382232715", "lollipop@gmail.com", "genielog");
        acheteurToFollow.setPrenom("Luna");
        acheteurToFollow.setNom("Delivici");
        acheteurToFollow.setPseudo("pseudoToFollow");

        // Vérifie que le suivi n'a pas été effectué car l'acheteur est inexistant
        assertFalse(follower.getListeSuiveurs().contains(acheteurToFollow));

    }


    @Test
    public void testMontrerProfil() {

        Acheteur acheteur = new Acheteur("4387052715", "chien45@gmail.com", "jaimeleschiens");
        // Initialiser l'Acheteur avec des informations
        acheteur.setPseudo("fbredon");
        acheteur.setPrenom("Felix");
        acheteur.setNom("Bredon");
        acheteur.getListeSuiveurs().add(new Acheteur("4382232715", "lollipop@gmail.com", "genielog"));
        acheteur.getListeCommentaires().add(new ArrayList<>());

        // Rediriger la sortie
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Appeler la méthode montrerProfil
        acheteur.montrerProfil();

        // Restaurer la sortie standard
        System.setOut(System.out);

        // Vérification
        String expectedOutput = "Profil de : fbredon\nFelixBredon\n1 suiveurs\n1 commentaires rédigés\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testLikeRevendeurSucces() {
        Acheteur acheteur = new Acheteur("4387052715", "chien45@gmail.com", "jaimeleschiens");
        Revendeur revendeur = new Revendeur("5142928982", "hubert12@gmail.com", "jesuishubert123");
        acheteur.setRevendeursLikes(new ArrayList<>());

        // Appeler la méthode likeRevendeur
        acheteur.likeRevendeur(revendeur, acheteur);

        // Vérifications
        Assertions.assertTrue(revendeur.getAcheteurQuiAime().contains(acheteur));
    }

    @Test
    public void testLikeRevendeurDejaLike() {
        Acheteur acheteur = new Acheteur("4387052715", "chien45@gmail.com", "jaimeleschiens");
        Revendeur revendeur = new Revendeur("5142928982", "hubert12@gmail.com", "jesuishubert123");
        acheteur.setRevendeursLikes(new ArrayList<>());
        acheteur.likeRevendeur(revendeur, acheteur); // Like une première fois

        // Appeler la méthode likeRevendeur une deuxième fois
        acheteur.likeRevendeur(revendeur, acheteur);

        // Vérifications
        assertEquals(1, revendeur.getAcheteurQuiAime().size()); // La taille de la liste ne doit pas changer
    }

//    @Test
//    public void testSuivreEtatColisTrouve() throws FileNotFoundException {
//
//        // initialiser une liste de produits
//        ArrayList<Object> listeProduits = new ArrayList<>();
//        listeProduits.add(new MaterielInfo("Écouteurs SONY WH-1000XM4", 409.99, 2, "Matériel informatique",
//                20, "Écouteurs", "SONY", "WH-1000XM4", "14/08/2022", "Écouteurs"));
//
//        // mock panier
//        Panier panier = new Panier();
//        panier.ajouterArticle((Produit) listeProduits.get(0));
//
//        Acheteur acheteur = new Acheteur("4387052715", "chien45@gmail.com", "jaimeleschiens");
//        Commande commande = new Commande(acheteur, StatutCommande.en_chemin, acheteur.getAdresseExpedition(), 2, panier);
//
//        // Créer colis avec UUID random
//        UUID numSuivi = UUID.randomUUID();
//
//        Colis ColisFictif = new Colis(commande);
//        ColisFictif.setNumSuivi(numSuivi);
//        ColisFictif.setStatut(StatutCommande.en_chemin);
//
//        // Ajouter colis fictif au colisMap
//        Colis.colisMap.put(numSuivi, ColisFictif);
//
//        // Rediriger la sortie
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//
//        // Exécuter la méthode
//        Acheteur.suivreEtat(numSuivi, acheteur);
//
//        System.setOut(System.out);
//
//        assertTrue(outContent.toString().contains("Le statut du colis avec le numéro de suivi " + numSuivi + StatutCommande.en_chemin));
//    }

//    @Test
//    public void testSuivreEtatColisPasTrouve() throws FileNotFoundException {
//
//        UUID numSuivi = UUID.randomUUID();
//
//        Acheteur acheteur = new Acheteur("4387052715", "chien45@gmail.com", "jaimeleschiens");
//
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//
//        Acheteur.suivreEtat(numSuivi, acheteur);
//
//        System.setOut(System.out);
//
//        assertTrue(outContent.toString().contains("Aucun colis trouvé avec le numéro de suivi : " + numSuivi));
//    }

}

