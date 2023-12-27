import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
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
}

