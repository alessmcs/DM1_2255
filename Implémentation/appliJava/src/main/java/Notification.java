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
	public static ArrayList<Notification> notifierAcheteur(LocalDateTime derniereConnection) {
		ArrayList  <Notification> nouvelleNotification = new ArrayList<>();
		for (Notification notification : notifications){
			LocalDateTime dateNotif = (LocalDateTime) notification.getDateTime();
			if (dateNotif.isAfter(derniereConnection)){
				RaisonsNotif raison = notification.getRaison();

				switch (raison){
					case NOUVEAU_PRODUIT, NOUVEL_ABONNE,SOLUTION_PROBLEME,ETAT_COMMANDE,PROMOTION:
						System.out.println(raison.getDescription());
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

	public static ArrayList<Notification> notifierRevendeur(LocalDateTime derniereConnection) {
		ArrayList  <Notification> nouvelleNotification = new ArrayList<>();
		for (Notification notification : notifications){
			LocalDateTime dateNotif = (LocalDateTime) notification.getDateTime();
			if (dateNotif.isAfter(derniereConnection)){
				RaisonsNotif raison = notification.getRaison();

				switch (raison){
					case PROBLEME_SIGNALE, NOUVELLE_EVALUATION,LIKE,NOUVELLE_COMMANDE_RECUE :
						System.out.println(raison.getDescription());
						nouvelleNotification.add(notification);
						break;
				}
			}
		}
		return nouvelleNotification;
	}




}