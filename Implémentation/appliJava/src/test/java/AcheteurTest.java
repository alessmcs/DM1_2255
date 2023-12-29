import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

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
        assertTrue(follower.getListeSuiveurs().contains(acheteurToFollow));
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
    void testConfirmerReceptionCommande_Livree() {

        // Nouveau panier
        Panier panier = new Panier();

        // Créer l'acheteur qui fait la confirmation
        Acheteur acheteur = new Acheteur("4382232715", "lollipop@gmail.com", "genielog");
        Commande commande = new Commande(acheteur, StatutCommande.en_production, acheteur.getAdresseExpedition(),
                33, panier);
        commande.setStatutCommande(StatutCommande.livree); // Mise à jour statut livré
        commande.setId(33);
        acheteur.getHistoriqueCommandes().add(commande);

        // Preparer l'entrée utilisateur
        String input = "33\n"; // ID valide
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Redirect standard output to capture the printed messages
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call the method
        boolean result = acheteur.confirmerReceptionCommande(acheteur);

        // Restore standard input and output
        System.setIn(System.in);
        System.setOut(System.out);

        // Check the result and printed output
        assertTrue(result);
        assertTrue(outContent.toString().contains("Cette commande est deja livrée"));
    }

    @Test
    void testConfirmerReceptionCommande_en_chemin() {

        // Nouveau panier
        Panier panier = new Panier();

        // Créer l'acheteur qui fait la confirmation
        Acheteur acheteur = new Acheteur("4382232715", "lollipop@gmail.com", "genielog");
        Commande commande = new Commande(acheteur, StatutCommande.en_production, acheteur.getAdresseExpedition(),
                33, panier);
        commande.setStatutCommande(StatutCommande.en_chemin); // Mise à jour statut livré
        commande.setId(33);
        acheteur.getHistoriqueCommandes().add(commande);

        // Preparer l'entrée utilisateur
        String input = "33\n"; // ID valide
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Redirect standard output to capture the printed messages
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call the method
        boolean result = acheteur.confirmerReceptionCommande(acheteur);

        // Restore standard input and output
        System.setIn(System.in);
        System.setOut(System.out);

        // Check the result and printed output
        assertTrue(result);
        assertTrue(outContent.toString().contains("Cette commande n'a pas encore été envoyée."));
    }

    @Test
    void testConfirmerReceptionCommande_Invalide() {

        // Nouveau panier
        Panier panier = new Panier();

        // Créer l'acheteur qui fait la confirmation
        Acheteur acheteur = new Acheteur("4382232715", "lollipop@gmail.com", "genielog");
        Commande commande = new Commande(acheteur, StatutCommande.en_production, acheteur.getAdresseExpedition(),
                33, panier);
        commande.setStatutCommande(StatutCommande.en_chemin); // Statut non confirmé
        commande.setId(33); // Exemple ID invalide
        acheteur.getHistoriqueCommandes().add(commande);

        // Preparer l'entrée utilisateur
        String input = "invalid\n"; // Entrée invalide
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Redirige la sortie standard
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Exécution methode
        boolean result = acheteur.confirmerReceptionCommande(acheteur);

        // Restauré I/O standard
        System.setIn(System.in);
        System.setOut(System.out);

        // Vérifier le résultat et la sortie
        assertFalse(result);
        assertTrue(outContent.toString().contains("Svp entrez un chiffre pour l'ID!"));
    }


    @Test
    public void testMontrerProfilAvecInformations() {

        Acheteur acheteur = new Acheteur("4387052715", "chien45@gmail.com", "jaimeleschiens");
        // Initialiser l'Acheteur avec des informations
        acheteur.setPseudo("fbredon");
        acheteur.setPrenom("Felix");
        acheteur.setNom("Bredon");
        acheteur.getListeSuiveurs().add(new Acheteur("4382232715", "lollipop@gmail.com", "genielog")); // Ajouter un suiveur
        acheteur.getListeCommentaires().add(new ArrayList<>()); // Ajouter un commentaire

        // Rediriger la sortie
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Appeler la méthode montrerProfil
        acheteur.montrerProfil();

        // Restaurer la sortie standard
        System.setOut(System.out);

        // Vérification que la sortie correspond aux attentes
        String expectedOutput = "Profil de : fbredon\nFelixBredon\n1 suiveurs\n1 commentaires rédigés\n";
        assertEquals(expectedOutput, outContent.toString());
    }

}

