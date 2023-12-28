import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class AcheteurTest {

    // Déclaration d'une variable membre pour réutilisation dans plusieurs méthodes de test
    private ArrayList<Acheteur> acheteurs;
    private Acheteur acheteurToFollow;
    private Acheteur follower;

    // Avant chaque test, initialiser les objets communs nécessaires aux tests
    @BeforeEach
    void setUp() {
        // Initialisation des objets communs nécessaires aux tests
        acheteurs = new ArrayList<>();

        // Création d'un Acheteur à suivre
        acheteurToFollow = new Acheteur("4382232715", "lollipop@gmail.com", "genielog");
        acheteurToFollow.setPrenom("Luna");
        acheteurToFollow.setNom("Delivici");
        acheteurToFollow.setPseudo("pseudoToFollow");
        acheteurs.add(acheteurToFollow);

        // Création d'un Acheteur qui effectuera le suivi
        follower = new Acheteur("4387052715", "chien45@gmail.com", "jaimeleschiens");
        follower.setPrenom("Felix");
        follower.setNom("Bredon");
        follower.setPseudo("fbredon");
        follower.setAdresseExpedition(new Adresse("71 rue Rembouillet", "Montréal", "h9n3r7", "QC", "Canada"));
        acheteurs.add(follower);
    }

    // Test pour vérifier la méthode ajouterSuiveur avec un Acheteur existant
    @Test
    void testAjouterSuiveur_AvecAcheteurExistant() {
        // Exécution de la méthode ajouterSuiveur
        follower.ajouterSuiveur(acheteurToFollow);

        // Vérification que le suivi a été effectué avec succès
        assertTrue(follower.getListeSuiveurs().contains(acheteurToFollow));
    }

    // Test pour vérifier la méthode ajouterSuiveur avec un Acheteur inexistant
    @Test
    void testAjouterSuiveur_AvecAcheteurInexistant() {
        // Création d'un Acheteur à suivre avec les mêmes informations
        // (cet Acheteur n'existe pas encore dans la liste)
        Acheteur acheteurToFollow = new Acheteur("4382232715", "lollipop@gmail.com", "genielog");
        acheteurToFollow.setPrenom("Luna");
        acheteurToFollow.setNom("Delivici");
        acheteurToFollow.setPseudo("pseudoToFollow");

        // Vérification que le suivi n'a pas été effectué car l'Acheteur est inexistant
        assertFalse(follower.getListeSuiveurs().contains(acheteurToFollow));
    }
}
