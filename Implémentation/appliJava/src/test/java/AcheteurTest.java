import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;


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
    public void testAfficherMetriques() {
        // Créer un Acheteur fictif pour tester la méthode
        Acheteur acheteur = new Acheteur("123", "test@example.com", "pseudo");

        // Nouveau panier
        Panier panier = new Panier();

        // Créer une liste d'historique de commandes fictive
        ArrayList<Commande> historiqueCommandes = new ArrayList<>();
        historiqueCommandes.add(new Commande(acheteur, StatutCommande.en_production, acheteur.getAdresseExpedition(),
                33, panier));
        acheteur.setHistoriqueCommandes(historiqueCommandes);

        // Rediriger l'entrée utilisateur pour simuler une réponse "1"
        ByteArrayInputStream in = new ByteArrayInputStream("1\n".getBytes());
        System.setIn(in);

        // Capturer la sortie standard
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        try {
            // Appeler la méthode afficherMetriques avec le scanner modifié
            acheteur.afficherMetriques(acheteur);

            // Vérifier la sortie
            String expectedOutput = "Vos métriques d'acheteur: \n" +
                    "Nombre de commandes : 1\n" +
                    "Produits achetés\n" +
                    "/* Ajoutez les détails des produits ici */\n" +
                    "Nombre total: /* Ajoutez le nombre total de produits ici */\n" +
                    "Vos commentaires: \n" +
                    "\u001B[1mÉtoile(s): \u001B[0m/* Ajoutez le nombre d'étoiles ici */\n" +
                    "\u001B[1mLike: \u001B[0m/* Ajoutez le nombre de likes ici */\n" +
                    "\u001B[1mCommentaire: \u001B[0m/* Ajoutez le commentaire ici */\n" +
                    "Souhaitez-vous retourner au menu principal?\n1-Oui\n2-Non\n";

            assertEquals(expectedOutput, outContent.toString());
        } catch (FileNotFoundException e) {
            fail("Exception inattendue : " + e.getMessage());
        } finally {
            // Restaurer la sortie standard
            System.setIn(System.in);
            System.setOut(System.out);
        }
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

}

