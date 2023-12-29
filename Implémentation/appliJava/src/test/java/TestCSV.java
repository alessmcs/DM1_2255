import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

public class TestCSV {

    Acheteur a1; Acheteur a2; ArrayList<Acheteur> acheteurs;
    @BeforeEach
    public void setUp() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("src/main/data/acheteursTEST.csv");
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        acheteurs = new ArrayList<>();

        a1 = new Acheteur("4382232715", "lollipop@gmail.com", "genielog");
        a1.setPrenom("Luna");
        a1.setNom("Delivici");
        a1.setPseudo("luna34");
        a1.setAdresseExpedition(new Adresse("70 rue Rembouillet", "Montréal", "h9n3r5", "QC", "Canada"));

        a2 = new Acheteur("4387052715", "chien45@gmail.com", "jaimeleschiens");
        a2.setPrenom("Felix");
        a2.setNom("Bredon");
        a2.setPseudo("fbredon");
        a2.setAdresseExpedition(new Adresse("71 rue Rembouillet", "Montréal", "h9n3r7", "QC", "Canada"));
    }

    @Test
    public void testerAddAcheteur(){ // tester Main.ecrireAcheteurCSV
        try {
            Main.ecrireAcheteurCSV(a1, "src/main/data/acheteursTEST.csv");
            Main.ecrireAcheteurCSV(a2, "src/main/data/acheteursTEST.csv");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/data/acheteursTEST.csv"));
            while ((line = br.readLine()) != null)
            {
                String[] ach = line.split(",");
                Acheteur autre = new Acheteur(ach[3], ach[4], ach[5]);
                autre.setPrenom(ach[0]);
                autre.setNom(ach[1]);
                autre.setPseudo(ach[2]);
                autre.setAdresseExpedition(Adresse.adresseBuilder(ach[6] + "," + ach[7] + "," + ach[8] + "," + ach[9] + "," + ach[10]));

                acheteurs.add(autre);
            }
        }
        catch (IOException e) {e.printStackTrace();}

        boolean test1 = ((acheteurs.get(0).getPseudo().equals(a1.getPseudo())) && (acheteurs.get(0).getNom().equals(a1.getNom()))
        && (acheteurs.get(0).getPrenom().equals(a1.getPrenom())) && (acheteurs.get(0).getMotDePasse().equals(a1.getMotDePasse())));

        boolean test2 = ((acheteurs.get(1).getPseudo().equals(a2.getPseudo())) && (acheteurs.get(1).getNom().equals(a2.getNom()))
                && (acheteurs.get(1).getPrenom().equals(a2.getPrenom())) && (acheteurs.get(1).getMotDePasse().equals(a2.getMotDePasse())));
        // check for same nom, prenom, mot de passe, courriel, adresse des 2 objets (liste & csv)

        assertTrue( test1 && test2 ); // s'assurer que la liste qu'on a créé contient les 2 acheteurs correctement
    }
}
