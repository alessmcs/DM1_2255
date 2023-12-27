import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try {
            // Nous initialisons les CSVs en utilisant les méthodes d'écriture plus bas
            init();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        accueil();

    }

    public static void accueil(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenue dans Unishop.");
        System.out.println("Veuillez selection ce que vous souhaitez effectuer sur le site en choisissant 1 ou 2.");
        System.out.println("1. Créer un profil");
        System.out.println("2. Se connecter ");

        boolean validInput = false;
        do {
            try{
                String choix = scanner.nextLine();
                switch (choix) {
                    case "1":
                        Utilisateur.creerProfil();
                        break;
                    case "2":
                        Utilisateur.seConnecter();
                        break;
                    default:
                        System.out.println("Choix invalide veuillez sélectionner 1 ou 2.");
                        break;
                }
            } catch(InputMismatchException e){
                scanner.nextLine();
            } catch (FileNotFoundException e) {
                System.out.println("Une erreur s'est produite. Veuillez réessayer.");
                scanner.nextLine();
            }
        } while(!validInput);
    }

    /*
        Vérifie si un String donné contient des nombres (important lorsqu'on vérifie l'adresse, le numéro de téléphone,
        les informations de paiement, etc.)

        @param str le String à vérifier
        @return vrai si ça contient des caractères numériques, sinon faux
     */
    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    /*
        Ajoute les informations d'une nouvelle commande dans le fichier commandes.csv

        @param commande la nouvelle commande
     */
    public static void ecrireCommandeCSV(Commande commande) throws FileNotFoundException {
        File csvFile = new File("src/main/data/commandes.csv");
        FileWriter out = null;
        try {
            out = new FileWriter(csvFile, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String builder = commande.getAcheteur().getPseudo() + "," + commande.getId() + "," + commande.getStatutCommande() + ",\"" + commande.getAdresse() +  "\",";
        for (Produit p : commande.getArticles()){
            builder += ("\"" + p.getCategorie() +  "," + p.getTitre() + "," + String.valueOf(p.getPrix()) + 1 +  "\",");
        }
        builder += commande.getTotal();

        try {
            out.write(builder + "\n");
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*
      Ajoute les informations d'un nouveau revendeur dans le fichier revendeurs.csv

      @param revendeur le nouveau revendeur
   */
    public static void ecrireRevendeurCSV(Revendeur revendeur) throws FileNotFoundException{
        File csvFile = new File("src/main/data/revendeurs.csv");
        FileWriter out = null;
        try {
            out = new FileWriter(csvFile, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String builder = revendeur.getPseudo() + "," + revendeur.getCourriel() + "," + revendeur.getTelephone() + "," + revendeur.getMotDePasse() + "," + "\"" + revendeur.getAdresse() + "\"";
        try {
            out.write(builder + "\n");
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /*
       Ajoute les informations d'un nouvel acheteur dans le fichier acheteurs.csv

       @param acheteur le nouvel acheteur
    */
    public static void ecrireAcheteurCSV(Acheteur acheteur, String filePath) throws FileNotFoundException{
        File csvFile = new File(filePath);
        FileWriter out = null;
        try {
            out = new FileWriter(csvFile, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String builder = acheteur.getPrenom() + "," + acheteur.getNom() + "," + acheteur.getPseudo() + "," + acheteur.getTelephone() + "," + acheteur.getCourriel() + ","
                + acheteur.getMotDePasse() + "," + acheteur.getAdresseExpedition() ;
        try {
            out.write(builder + "\n");
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*
       Écrit les infomations du produit, selon son type, dans le fichier listeProduits.csv, lorsqu'un revendeur le rajoute à la plateform

      @param p le produit à marquer
    */
    public static void ecrireProduitCSV(Produit p, String filePath) throws FileNotFoundException{
        File csvFile = new File(filePath);
        FileWriter out = null;
        try {
            out = new FileWriter(csvFile, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String builder = null;
        // écrire qqch de different ds le csv selon le type du produit
        if(p instanceof MaterielInfo){ // ID, titre, prix, qte, categorie, points, description, marque, modele, date, sous-categorie
            builder = p.getId() + "," + p.getTitre() + "," + p.getPrix() + "," + p.getQte() + "," + p.getCategorie()
                    + "," + p.getPoints() + "," + p.getDescription() + ","
                    + ((MaterielInfo) p).getMarque() + "," + ((MaterielInfo) p).getModele() + "," + ((MaterielInfo) p).getDate() + "," + ((MaterielInfo) p).getSousCategorie();
        } else if(p instanceof EquipementBureau){ // ID, titre, prix, qte, categorie, points, description, marque, modele, sous-categorie
            builder = p.getId() + "," + p.getTitre() + "," + p.getPrix() + "," + p.getQte() + "," + p.getCategorie()
                    + "," + p.getPoints() + "," + p.getDescription() + ","
                    + ((EquipementBureau) p).getMarque() + "," + ((EquipementBureau) p).getModele() + "," + ((EquipementBureau) p).getSousCategorie();;
        } else if(p instanceof ArticlesDePapeterie){ // ID, titre, prix, qte, categorie, points, description, marque, modele, sous-categorie
            builder = p.getId() + "," + p.getTitre() + "," + p.getPrix() + "," + p.getQte() + "," + p.getCategorie()
                    + "," + p.getPoints() + "," + p.getDescription() + "," + ((ArticlesDePapeterie) p).getMarque() + "," + ((ArticlesDePapeterie) p).getModele()
                    + "," + ((ArticlesDePapeterie) p).getSousCategorie();
        } else if(p instanceof LivresEtManuels){ // ID, titre, prix, qte, categorie, points, description , ISBN , auteur, maison, genre, date, ed, vol
            builder = p.getId() + "," + p.getTitre() + "," + p.getPrix() + "," + p.getQte() + "," + p.getCategorie()
                    + "," + p.getPoints() + "," + p.getDescription() + "," + ((LivresEtManuels) p).getISBN() + "," + ((LivresEtManuels) p).getAuteur()
                    + "," + ((LivresEtManuels) p).getMaisonEdition() + "," + ((LivresEtManuels) p).getGenre() + "," + ((LivresEtManuels) p).getDateParution()
                    + "," + ((LivresEtManuels) p).getNumEdition() + "," + ((LivresEtManuels) p).getNumVol();
        } else if(p instanceof RessourcesApprentissage){ // ID, titre, prix, qte, categorie, points, description, marque, modele, sous-categorie
            builder = p.getId() + "," + p.getTitre() + "," + p.getPrix() + "," + p.getQte() + "," + p.getCategorie()
                    + "," + p.getPoints() + "," + p.getDescription() + "," + ((RessourcesApprentissage) p).getMarque() + "," + ((RessourcesApprentissage) p).getModele() + ((RessourcesApprentissage) p).getSousCategorie() ;
        }

        try {
            out.write(builder + "\n");
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*
        Initialise les bases de donnés à chaque fois que le programme débute. Ajoute les instances nécessaires aux arrayLists
        de la classe BaseDonnees pour pouvoir les manipuler plus tard.
     */
    public static void init() throws FileNotFoundException{
        // fichiers .csv doivent être vides au début
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("src/main/data/listeProduits.csv");
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {

            throw new RuntimeException(e);
        }

        try {
            writer = new PrintWriter("src/main/data/acheteurs.csv");
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            writer = new PrintWriter("src/main/data/revendeurs.csv");
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // initialiser catalogue
        Produit livre1 = new LivresEtManuels("Programmation orientée objet et Génie logiciel", 78.90, 10,"Livres et manuels", 20, "Genie logiciel", "0-3923-7489-7", "Valerie Farino et al.", "Nathan", "Manuel scolaire", "3 janvier 2018", 8, 2);
        Produit livre2 = new LivresEtManuels("Calcul à plusieurs variables", 66.79, 15, "Livres et manuels", 20, "Calcul I", "0-9559-3560-1", "Fatima Alenar", "Pearson", "Manuel scolaire", "17 mars 2020", 2, 1);
        Produit livre3 = new LivresEtManuels("Microéconomie", 68.99, 3, "Livres et manuels", 20, "Économie", "0-1419-9315-4", "Jules Tremblay", "Pearson", "Manuel scolaire", "19 décembre 2006", 5, 1);
        Produit materiel1 = new MaterielInfo("Écouteurs SONY WH-1000XM4", 409.99, 2, "Matériel informatique", 20, "Écouteurs", "SONY", "WH-1000XM4", "14/08/2022", "Écouteurs");
        Produit materiel2 = new MaterielInfo("MacBook Pro M3 14p", 2599.00, 1, "Matériel informatique", 20, "Ordinateur portable", "Apple", "Pro", "16/09/2023", "Ordinateurs");
        Produit article1 = new EquipementBureau("Cahier Canada quadrillé 40 pages", 1.79,230, "Equipement de bureau", 0, "Cahier d'exercices", "Hilroy", "Canada", "Cahiers");

        // les produits sont deja ecrits dans le csv lors de updateInventaire
        Catalogue.ajouterProduit(livre1);
        Catalogue.ajouterProduit(livre2);
        Catalogue.ajouterProduit(livre3);
        Catalogue.ajouterProduit(materiel1);
        Catalogue.ajouterProduit(materiel2);
        Catalogue.ajouterProduit(article1);

        // initialiser les commentaires sur les produits
        for(ArrayList<String> com : Commentaire.listeDeCom()){
            livre1.listCommentaires.add(com);
            livre2.listCommentaires.add(com);
            livre3.listCommentaires.add(com);
            materiel1.listCommentaires.add(com);
            materiel2.listCommentaires.add(com);
            article1.listCommentaires.add(com);
        }

        Acheteur acheteur1 = new Acheteur("4382232715", "lollipop@gmail.com", "genielog");
        acheteur1.setPrenom("Luna");
        acheteur1.setNom("Delivici");
        acheteur1.setPseudo("luna34");
        acheteur1.setAdresseExpedition(new Adresse("70 rue Rembouillet", "Montréal", "h9n3r5", "QC", "Canada"));

        Acheteur acheteur2 = new Acheteur("4387052715", "chien45@gmail.com", "jaimeleschiens");
        acheteur2.setPrenom("Felix");
        acheteur2.setNom("Bredon");
        acheteur2.setPseudo("fbredon");
        acheteur2.setAdresseExpedition(new Adresse("71 rue Rembouillet", "Montréal", "h9n3r7", "QC", "Canada"));

        Acheteur acheteur3 = new Acheteur("4384262015", "doudou@gmail.com", "catlover");
        acheteur3.setPrenom("Henri");
        acheteur3.setNom("Dublin");
        acheteur3.setPseudo("henry420");
        acheteur3.setAdresseExpedition(new Adresse("73 rue Rembouillet", "Montréal", "h9n4m8", "QC", "Canada"));

        Acheteur acheteur4 = new Acheteur("4521223715", "popo@gmail.com", "bouteille");
        acheteur4.setPrenom("Clémentine");
        acheteur4.setNom("Prairie");
        acheteur4.setPseudo("cloclorange");
        acheteur4.setAdresseExpedition(new Adresse("77 rue Rembouillet", "Montréal", "h9n9l7", "QC", "Canada"));

        Acheteur acheteur5 = new Acheteur("5140976515", "udem@gmail.com", "ordinateur");
        acheteur5.setPrenom("Suzanne");
        acheteur5.setNom("Gaultier");
        acheteur5.setPseudo("suzette123");
        acheteur5.setAdresseExpedition(new Adresse("80 rue Rembouillet", "Montréal", "h9n6m0", "QC", "Canada"));

        Acheteur acheteur6 = new Acheteur("5141126515", "girly@gmail.com", "stem");
        acheteur6.setPrenom("Fernande");
        acheteur6.setNom("Peltier");
        acheteur6.setPseudo("Fpeltier");
        acheteur6.setAdresseExpedition(new Adresse("85 rue Rembouillet", "Montréal", "h9n0m0", "QC", "Canada"));

        Acheteur acheteur7 = new Acheteur("4389951122", "chateau@gmail.com", "castle");
        acheteur7.setPrenom("Paul");
        acheteur7.setNom("Gourmet");
        acheteur7.setPseudo("gourmet123");
        acheteur7.setAdresseExpedition(new Adresse("88 rue Rembouillet", "Montréal", "h7n1k3", "QC", "Canada"));

        Acheteur acheteur8 = new Acheteur("4381597531", "lovescience@gmail.com", "chemistry1");
        acheteur8.setPrenom("Théodore");
        acheteur8.setNom("Fisher");
        acheteur8.setPseudo("chipmunk");
        acheteur8.setAdresseExpedition(new Adresse("91 rue Rembouillet", "Montréal", "j7l0m1", "QC", "Canada"));

        Acheteur acheteur9 = new Acheteur("5147891230", "phonenumber@gmail.com", "apple");
        acheteur9.setPrenom("Cindy");
        acheteur9.setNom("Buffet");
        acheteur9.setPseudo("foodlover");
        acheteur9.setAdresseExpedition(new Adresse(" 93 rue Rembouillet", "Montréal", "h2n0k9", "QC", "Canada"));

        Acheteur acheteur10 = new Acheteur("4385514876", "arcenciel@gmail.com", "nuage");
        acheteur10.setPrenom("Léonard");
        acheteur10.setNom("Hubert");
        acheteur10.setPseudo("souris88");
        acheteur10.setAdresseExpedition(new Adresse("100 rue Rembouillet", "Montréal", "b8k6h1", "QC", "Canada"));

        ecrireAcheteurCSV(acheteur1, "src/main/data/acheteurs.csv");
        ecrireAcheteurCSV(acheteur2, "src/main/data/acheteurs.csv");
        ecrireAcheteurCSV(acheteur3, "src/main/data/acheteurs.csv");
        ecrireAcheteurCSV(acheteur4, "src/main/data/acheteurs.csv");
        ecrireAcheteurCSV(acheteur5, "src/main/data/acheteurs.csv");
        ecrireAcheteurCSV(acheteur6, "src/main/data/acheteurs.csv");
        ecrireAcheteurCSV(acheteur7, "src/main/data/acheteurs.csv");
        ecrireAcheteurCSV(acheteur8, "src/main/data/acheteurs.csv");
        ecrireAcheteurCSV(acheteur9, "src/main/data/acheteurs.csv");
        ecrireAcheteurCSV(acheteur10, "src/main/data/acheteurs.csv");

        BaseDonnees.acheteursList.add(acheteur1);
        BaseDonnees.acheteursList.add(acheteur2);
        BaseDonnees.acheteursList.add(acheteur3);
        BaseDonnees.acheteursList.add(acheteur4);
        BaseDonnees.acheteursList.add(acheteur5);
        BaseDonnees.acheteursList.add(acheteur6);
        BaseDonnees.acheteursList.add(acheteur7);
        BaseDonnees.acheteursList.add(acheteur8);
        BaseDonnees.acheteursList.add(acheteur9);
        BaseDonnees.acheteursList.add(acheteur10);


        Revendeur revendeur1 = new Revendeur("5142928982", "hubert12@gmail.com", "jesuishubert123");
        revendeur1.setPseudo("hu3ert");
        revendeur1.setAdresse(new Adresse("42 rue Lagayette", "longueuil", "j4c3d6", "QC", "Canada"));

        Revendeur revendeur2 = new Revendeur("5148982093", "monjardin000@gmail.com", "123fleur");
        revendeur2.setPseudo("petra23");
        revendeur2.setAdresse(new Adresse("43 rue Lagayette", "longueuil", "j4c2d4", "QC", "Canada"));

        Revendeur revendeur3 = new Revendeur("5143237989", "bookss@gmail.com", "jaimeLire12");
        revendeur3.setPseudo("bookseller1212");
        revendeur3.setAdresse(new Adresse("45 rue Lagayette", "longueuil", "j5c2d8", "QC", "Canada"));

        Revendeur revendeur4 = new Revendeur("5142928837", "anitaa99@gmail.com", "inf0");
        revendeur4.setPseudo("an1ta");
        revendeur4.setAdresse(new Adresse("50 rue Lagayette", "longueuil", "j2c4d0", "QC", "Canada"));

        Revendeur revendeur5 = new Revendeur("5140976515", "roger_tremblay@gmail.com", "mrRoger12");
        revendeur5.setPseudo("roger427");
        revendeur5.setAdresse(new Adresse("67 rue Lagayette", "longueuil", "2", "QC", "Canada"));

        // revendeurs offrent des produits
        ecrireRevendeurCSV(revendeur1);
        revendeur1.updateInventaire(livre1);
        revendeur1.updateInventaire(materiel1);
        livre1.setRevendeur(revendeur1);
        materiel1.setRevendeur(revendeur1);
        BaseDonnees.revendeursList.add(revendeur1);

        ecrireRevendeurCSV(revendeur2);
        revendeur2.updateInventaire(livre2);
        livre2.setRevendeur(revendeur2);
        BaseDonnees.revendeursList.add(revendeur2);

        ecrireRevendeurCSV(revendeur3);
        revendeur3.updateInventaire(livre3);
        livre3.setRevendeur(revendeur3);
        BaseDonnees.revendeursList.add(revendeur3);

        ecrireRevendeurCSV(revendeur4);
        revendeur4.updateInventaire(materiel2);
        materiel2.setRevendeur(revendeur4);
        BaseDonnees.revendeursList.add(revendeur4);

        ecrireRevendeurCSV(revendeur5);
        revendeur5.updateInventaire(article1);
        article1.setRevendeur(revendeur5);
        BaseDonnees.revendeursList.add(revendeur5);
        //BaseDonnees.creerListeRevendeurs();

        // initialiser 3 paniers
        BaseDonnees.acheteursList.get(BaseDonnees.acheteursList.indexOf(acheteur1)).ajouterAuPanier(livre1);
        BaseDonnees.acheteursList.get(BaseDonnees.acheteursList.indexOf(acheteur1)).ajouterAuPanier(livre2);
        BaseDonnees.acheteursList.get(BaseDonnees.acheteursList.indexOf(acheteur1)).ajouterAuPanier(livre3);

        BaseDonnees.acheteursList.get(BaseDonnees.acheteursList.indexOf(acheteur2)).ajouterAuPanier(livre2);
        BaseDonnees.acheteursList.get(BaseDonnees.acheteursList.indexOf(acheteur2)).ajouterAuPanier(materiel1);

        BaseDonnees.acheteursList.get(BaseDonnees.acheteursList.indexOf(acheteur4)).ajouterAuPanier(materiel2);
    }

}
