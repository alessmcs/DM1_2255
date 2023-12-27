import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.io.ByteArrayInputStream;

class PlateformeTest {

    @Test
    public void testRechercherAcheteur_PseudoExistant() {
        // Crée une liste d'Acheteurs
        ArrayList<Acheteur> acheteurs = new ArrayList<>();

        // Crée un nouvel Acheteur avec le pseudo "pseudoTest"
        Acheteur acheteurTest = new Acheteur("4382232715", "lollipop@gmail.com", "genielog");
        acheteurTest.setPrenom("Luna");
        acheteurTest.setNom("Delivici");
        acheteurTest.setPseudo("pseudoTest");
        acheteurTest.setAdresseExpedition(new Adresse("70 rue Rembouillet", "Montréal", "h9n3r5", "QC", "Canada"));
        acheteurs.add(acheteurTest);

        // Configure l'entrée utilisateur simulée avec "pseudoTest"
        setSystemIn("pseudoTest\n");

        // Redirige la sortie standard vers une chaîne pour la vérification
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Exécute la méthode rechercherAcheteur
        Acheteur resultat = Plateforme.rechercherAcheteur(acheteurs);

        // Vérifie que le résultat est l'Acheteur créé
        assertEquals(acheteurTest, resultat);
    }

    @Test
    public void testRechercherAcheteur_PseudoNonExistant() {
        // Crée une liste d'Acheteurs
        ArrayList<Acheteur> acheteurs = new ArrayList<>();

        // Crée un nouvel Acheteur avec le pseudo "pseudoTest"
        Acheteur acheteurTest = new Acheteur("4382232715", "lollipop@gmail.com", "genielog");
        acheteurTest.setPrenom("Luna");
        acheteurTest.setNom("Delivici");
        acheteurTest.setPseudo("pseudoTest1");
        acheteurTest.setAdresseExpedition(new Adresse("70 rue Rembouillet", "Montréal", "h9n3r5", "QC", "Canada"));
        acheteurs.add(acheteurTest);

        // Configure l'entrée utilisateur simulée avec "pseudoInexistant"
        setSystemIn("pseudoInexistant\n");

        // Redirige la sortie standard vers une chaîne pour la vérification
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Exécute la méthode rechercherAcheteur
        Acheteur resultat = Plateforme.rechercherAcheteur(acheteurs);

        // Vérifie que le résultat est null car le pseudo n'existe pas
        assertNull(resultat);
    }

    // Méthode utilitaire pour configurer l'entrée utilisateur simulée
    private void setSystemIn(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }
}
