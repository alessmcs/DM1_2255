public enum RaisonsNotif {
	NOUVEL_ABONNE("Nouvel abonné!"),
	NOUVEAU_PRODUIT("Un nouveau produit est offert par un revendeur!"),
	PROMOTION("Il y a une promotion sur un produit!"),
	LIKE("Un acheteur a aimé un de vos produits!"),
	ETAT_COMMANDE("L'état de votre commande a été changé"),
	SOLUTION_PROBLEME("Un revendeur propose une solution à votre problème"),
	NOUVELLE_COMMANDE_RECUE("Vous avez une nouvelle commande!"),
	NOUVELLE_EVALUATION("Il y a une nouvelle évaluation sur un de vos produits"),
	PROBLEME_SIGNALE("Un acheteur a signalé un problème"),
	LIVRAISON_CONFIRMEE("Le colis est bien recu par le client. ");


	private final String description;

	RaisonsNotif(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
