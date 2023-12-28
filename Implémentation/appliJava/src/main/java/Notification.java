import java.time.LocalDateTime;
import java.util.ArrayList;

public class Notification {

	Plateforme envoie;
	private LocalDateTime date;
	private RaisonsNotif raison;
	private static ArrayList<Notification> notifications = new ArrayList<>();
	public Notification(RaisonsNotif raison){
		this.raison= raison;
		this.envoie= envoie;
		this.date = LocalDateTime.now();
		notifications.add(this);
	}


	/**
	 * Notifie l'acheteur selon une certaine raison (nouvel abonné, promotion, like, etc.)
	 * 
	 * @param derniereConnection dernière connection au site de l'acheteur
	 * @param acheteur 
	 * @return une notification 
	 */
	public static ArrayList<Notification> notifierAcheteur(LocalDateTime derniereConnection, Acheteur acheteur) {
		ArrayList  <Notification> nouvelleNotification = new ArrayList<>();
		for (Notification notification : notifications){
			LocalDateTime dateNotif = (LocalDateTime) notification.getDateTime();
			if (dateNotif.isAfter(derniereConnection)){
				RaisonsNotif raison = notification.getRaison();

				switch (raison){
					case NOUVEAU_PRODUIT, NOUVEL_ABONNE,SOLUTION_PROBLEME,ETAT_COMMANDE,PROMOTION:
						System.out.println(raison.getDescription());
						acheteur.ajouterNotification(notification);
						nouvelleNotification.add(notification);
						break;
				}
			}
		}
		return nouvelleNotification;
	}

	private RaisonsNotif getRaison() {
		return raison;
	}

	private Object getDateTime() {
		return date;
	}


	/**
	 * Notifie le revendeur selon une certaine raison (nouvel abonné, promotion, like, etc.)
	 * 
	 * @param derniereConnection dernière connection au site de l'acheteur
	 * @param revendeur
	 * @return une notification 
	 */
	public static ArrayList<Notification> notifierRevendeur(LocalDateTime derniereConnection, Revendeur revendeur) {
		ArrayList  <Notification> nouvelleNotification = new ArrayList<>();
		for (Notification notification : notifications){
			LocalDateTime dateNotif = (LocalDateTime) notification.getDateTime();
			if (dateNotif.isAfter(derniereConnection)){
				RaisonsNotif raison = notification.getRaison();

				switch (raison){
					case PROBLEME_SIGNALE, NOUVELLE_EVALUATION,LIKE,NOUVELLE_COMMANDE_RECUE :
						System.out.println(raison.getDescription());
						revendeur.ajouterNotification(notification);
						nouvelleNotification.add(notification);
						break;
				}
			}
		}
		return nouvelleNotification;
	}


}