import java.util.ArrayList;
import java.util.Scanner;

public class retourEchange {

	private Produit prodARetourner;
	private String motif;
	private Produit prodRemplacement;

	public double calculerDifference(Produit produitAEchanger, Produit ancienProduit) {
		return produitAEchanger.getPrix() - ancienProduit.getPrix();
	}

	public void effectuerRetour(Acheteur acheteur) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<Commande> commandeLivree = acheteur.obtenirCommandesLivrees();
		boolean idCorrect = false;
		for (Commande commande : commandeLivree) {
			System.out.println(commande);
		}
		System.out.println("Quel article souhaitez-vous retourné?");
		int ID = scanner.nextInt();


		for (Commande commande : commandeLivree) {
			for (Produit produit : commande.getArticles()) {
				if (produit.getId() == ID) {
					commande.setEtatCommande(StatutCommande.en_route_retour);

					Revendeur revendeur = produit.getRevendeur();

					System.out.println("Veuillez renvoyer l'article le plus tot possible.");

					revendeur.ajouterCommandeRetournee(commande, acheteur.getCarteCredit());
					idCorrect = true;
					Notification nouvelleNotification = new Notification(RaisonsNotif.ETAT_COMMANDE);
					break;
				} else {
					System.out.println("ID non correct");
				}
			}
		}
	}


	public void effectuerEchange(Acheteur acheteur) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<Commande> commandesLivrees = acheteur.obtenirCommandesLivrees();

		boolean idCorrect = false;
		for (Commande commande : commandesLivrees) {
			System.out.println(commande);
		}
		System.out.println("Quel article souhaitez-vous retourné?");
		int ID = scanner.nextInt();


		for (Commande commande : commandesLivrees) {
			for (Produit acienProduit : commande.getArticles()) {
				if (acienProduit.getId() == ID) {
					commande.setEtatCommande(StatutCommande.en_route_echange);

					Revendeur revendeur = acienProduit.getRevendeur();

					System.out.println("Veuillez renvoyer l'article le plus tot possible.");

					revendeur.ajouterCommandeRetournee(commande, acheteur.getCarteCredit());
					idCorrect = true;
					Notification nouvelleNotification = new Notification(RaisonsNotif.ETAT_COMMANDE);



				}
				System.out.println("Quel produit souhaitez-vous échanger?");
				int IDPrdouit = scanner.nextInt();
				Produit produitAEchanger = null;

				for (Produit produitEcha : Catalogue.catalogueProduits(acheteur)) {
					if (produitEcha.getId() == ID) {
						produitAEchanger = produitEcha;
						break;
					}
				}
				double difference = calculerDifference(produitAEchanger, acienProduit);
				ArrayList produitsAEchanger = new ArrayList();
				produitsAEchanger.add(produitAEchanger);
				Adresse adresseLivraison = acheteur.getAdresseExpedition();

				Panier panierEchange = new Panier();

				for (Object p : produitsAEchanger){
					panierEchange.ajouterArticle((Produit) p);
				}

				if (difference == 0) {
					Commande commande1 = new Commande(acheteur,StatutCommande.en_production, adresseLivraison, produitAEchanger.getId(), panierEchange);
					acheteur.addHistorique(commande1); // ajouter la commande à l'historique de commandes
					Colis colis = new Colis(commande.getStatutCommande());
				} else{
					System.out.println("Différence de prix détectée : " + difference);

					CarteCredit carteCredit = acheteur.getCarteCredit();
					double soldeCarte = carteCredit.getSolde();

					if (difference > 0 && soldeCarte >= difference) {
						acheteur.payerDifference(difference);
						Commande commande1 = new Commande(acheteur,StatutCommande.en_production, adresseLivraison, produitAEchanger.getId(), panierEchange);
						acheteur.addHistorique(commande1);
						Colis colis = new Colis(commande.getStatutCommande());
					} else if (difference < 0) {
						Commande commande1 = new Commande(acheteur,StatutCommande.en_production, adresseLivraison, produitAEchanger.getId(), panierEchange);
						acheteur.addHistorique(commande1);
						SystemePaiement.rembourserMontant(carteCredit,commande1);
						Colis colis = new Colis(commande.getStatutCommande());
					}else{
						System.out.println("Solde insuffisant sur la carte de crédit.");
					}

				}


			}
		}
	}
}