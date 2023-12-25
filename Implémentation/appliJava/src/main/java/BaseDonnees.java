import java.io.*;
import java.util.ArrayList;

public class BaseDonnees {
    public static ArrayList<Acheteur> acheteursList= new ArrayList<>();
    public static ArrayList<Revendeur> revendeursList= new ArrayList<>();
    public static ArrayList<Commande> commandesList = new ArrayList<>();
    public static ArrayList<Produit> produitsList = Catalogue.getProduits();

    // ajouter les acheteurs de acheteurs.csv dans acheteursList
    public static void creerListeAcheteurs(){

        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/data/acheteurs.csv"));
            while ((line = br.readLine()) != null)
            {
                String[] ach = line.split(",");
                    Acheteur autre = new Acheteur(ach[3], ach[4], ach[5]);
                    autre.setPrenom(ach[0]);
                    autre.setNom(ach[1]);
                    autre.setPseudo(ach[2]);
                    autre.setAdresseExpedition(Adresse.adresseBuilder(ach[6] + "," + ach[7] + "," + ach[8] + "," + ach[9] + "," + ach[10]));

                    acheteursList.add(autre);
            }
        }
        catch (IOException e) {e.printStackTrace();}
    }

    public static void creerListeRevendeurs(){

        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/data/revendeurs.csv"));
            while ((line = br.readLine()) != null)
            {
                String[] rev = line.split(",");
                    Revendeur autre = new Revendeur(rev[1], rev[2], rev[3]);
                    autre.setPseudo(rev[0]);
                    autre.setAdresse(Adresse.adresseBuilder(rev[4] + "," + rev[5] + "," + rev[6] + "," + rev[7] + "," + rev[8]));
                    revendeursList.add(autre);
            }
        }
        catch (IOException e) {e.printStackTrace();}
    }


}
