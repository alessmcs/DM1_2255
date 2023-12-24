import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        init();

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

    public static void init(){
        // initialiser catalogue
        Produit livre1 = new LivresEtManuels("Programmation orientée objet et Génie logiciel, 8e édition", 78.90, 10,"Livres et manuels", 20, "Genie logiciel", "0-3923-7489-7", "Valerie Farino et al.", "Nathan", "Manuel scolaire", "3 janvier 2018", 3, 2);
        Produit livre2 = new LivresEtManuels("Calcul à plusieurs variables, 2e édition", 66.79, 15, "Livres et manuels", 20, "Calcul I", "0-9559-3560-1", "Fatima Alenar", "Pearson", "Manuel scolaire", "17 mars 2020", 4, 1);
        Produit livre3 = new LivresEtManuels("Microéconomie", 68.99, 3, "Livres et manuels", 20, "Économie", "0-1419-9315-4", "Jules Tremblay", "Pearson", "Manuel scolaire", "19 décembre 2006", 5, 1);
        Produit materiel1 = new MaterielInfo("Écouteurs SONY WH-1000XM4", 409.99, 2, "Matériel informatique", 20, "Écouteurs", "SONY", "WH-1000XM4", "14/08/2022", "Écouteurs");
        Produit materiel2 = new MaterielInfo("MacBook Pro M3 14p", 2599.00, 1, "Matériel informatique", 20, "Ordinateur portable", "Apple", "Pro", "16/09/2023", "Ordinateurs");
        Produit article1 = new EquipementBureau("Cahier Canada quadrillé 40 pages", 1.79,230, "Equipement de bureau", 0, "Cahier d'exercices", "Hilroy", "Canada", "Cahiers");


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
        BaseDonnees.revendeursList.add(revendeur1);
        revendeur1.updateInventaire(livre1);
        revendeur1.updateInventaire(materiel1);
        livre1.setRevendeur(revendeur1);
        materiel1.setRevendeur(revendeur1);

        BaseDonnees.revendeursList.add(revendeur2);
        revendeur2.updateInventaire(livre2);
        livre2.setRevendeur(revendeur2);

        BaseDonnees.revendeursList.add(revendeur3);
        revendeur3.updateInventaire(livre3);
        livre3.setRevendeur(revendeur3);

        BaseDonnees.revendeursList.add(revendeur4);
        revendeur4.updateInventaire(materiel2);
        materiel2.setRevendeur(revendeur4);

        BaseDonnees.revendeursList.add(revendeur5);
        revendeur5.updateInventaire(article1);
        article1.setRevendeur(revendeur5);

        // initialiser 3 paniers
        acheteur1.ajouterAuPanier(livre1);
        acheteur1.ajouterAuPanier(livre2);
        acheteur1.ajouterAuPanier(livre3);

        acheteur3.ajouterAuPanier(livre2);
        acheteur3.ajouterAuPanier(materiel1);

        acheteur4.ajouterAuPanier(materiel2);
    }

}
