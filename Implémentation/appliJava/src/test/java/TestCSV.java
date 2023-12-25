import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class TestCSV {

    Produit p1; Produit p2;
    @Before
    public void setUp() {
        p1 = new LivresEtManuels("Programmation orientée objet et Génie logiciel", 78.90, 10,"Livres et manuels", 20, "Genie logiciel", "0-3923-7489-7", "Valerie Farino et al.", "Nathan", "Manuel scolaire", "3 janvier 2018", 8, 2);
        p2 = new LivresEtManuels("Calcul à plusieurs variables", 66.79, 15, "Livres et manuels", 20, "Calcul I", "0-9559-3560-1", "Fatima Alenar", "Pearson", "Manuel scolaire", "17 mars 2020", 2, 1);

    }

//    @Test
//    public void testEcrireProduits() throws FileNotFoundException { // tester Main.ecrireProduitCSV()
//        // set up 2 produits
//        // write them dans le csv using la methode dans main
//        // read the file & go through the arrayList
//        // check using contains
//        try {
//            Main.ecrireProduitCSV(p1, "src/main/data/listeProduitsTEST.csv");
//            Main.ecrireProduitCSV(p2, "src/main/data/listeProduitsTEST.csv");
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//
//        String line = "";
//        ArrayList<Produit> verif = new ArrayList<>();
//        BufferedReader br = new BufferedReader(new FileReader("src/main/data/listeProduitsTEST.csv"));
//        while ((line = br.readLine()) != null)
//        {
//            String[] ach = line.split(",");
//            Acheteur autre = new Acheteur(ach[3], ach[4], ach[5]);
//            autre.setPrenom(ach[0]);
//            autre.setNom(ach[1]);
//            autre.setPseudo(ach[2]);
//            autre.setAdresseExpedition(Adresse.adresseBuilder(ach[6] + "," + ach[7] + "," + ach[8] + "," + ach[9] + "," + ach[10]));
//
//            acheteursList.add(autre);
//        }
//
//    }

    @Test
    public void testerAddAcheteur(){ // tester Main.ecrireAcheteurCSV
        // set up 2 acheteurs
//        // write them dans le csv using la methode dans main
//        // read the file & go through the arrayList
//        // check using contains
    }
}
