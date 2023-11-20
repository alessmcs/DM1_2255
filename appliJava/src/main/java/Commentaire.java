import java.util.ArrayList;
public class Commentaire {

	private Acheteur Utilisateur;
	private ArrayList<String> Contenu;
	private String Date;

	public void setContenu(ArrayList<String> nouvContenu){
		Contenu = nouvContenu;
	}

	public ArrayList<String> getContenu(){
		return Contenu;
	} 



	public ArrayList<ArrayList<String>> listeDeCom(){
		ArrayList<ArrayList<String>> listCommentaires = new ArrayList<>();

		ArrayList<String> com1 = new ArrayList<>();
		com1.add("***");
		com1.add("liker");
		com1.add("J'aime bien!");

		ArrayList<String> com2 = new ArrayList<>();
		com2.add("*****");
		com2.add("liker");
		com2.add("Vous devez l'acheter!");

		ArrayList<String> com3 = new ArrayList<>();
		com3.add("*");
		com3.add("ne pas liker");
		com3.add("C'est un gros non pour moi :(");

		listCommentaires.add(com1);
		listCommentaires.add(com2);
		listCommentaires.add(com3);

		return listCommentaires;
	} 

}