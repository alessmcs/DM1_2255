public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenue dans Unishop.");
        System.out.println("Veuillez selection ce que vous souhaitez effectuer sur le site en choisant 1 ou 2.");
        System.out.println("1. Creer un profil");
        System.out.println("2. Se connecter ");

        int choix = Integer.parseInt(scanner.nextLine());
        switch (choix) {
            case 1:
                creerProfil();
                break;
            case 2:
                seConnecter();
                break;
            default:
                System.out.println("Choix invalide veuillez selectionner 1 ou 2.");
        }
    }

}
