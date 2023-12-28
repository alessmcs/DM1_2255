import static org.junit.Assert.assertEquals;
import java.io.ByteArrayInputStream;

import org.junit.Before;
import org.junit.Test;

public class ProduitTest {
    private Produit produit;
    private static final Utilisateur Utilisateur = null;

    
    @Before
    public void setup(){
        this.produit = new Produit("Écouteurs SONY WH-1000XM4", 409.98, 2, "Matériel informatique", 20, "Écouteurs");
    }


    @Test
    public void testEvaluer() {

        String value = "3";
        ByteArrayInputStream input = new ByteArrayInputStream(value.getBytes());
        System.setIn(input);

        String result = produit.evaluer(null);

        assertEquals("Vous avez choisi de mettre " + "\u001B[1m" + "***" + "\u001B[0m" + " étoile(s) au produit!", result);
    }

    @Test
    public void testCommenter() {
        String comment = "1";
        ByteArrayInputStream input1 = new ByteArrayInputStream(comment.getBytes());
        System.setIn(input1);

        String commentaire = "2";
        ByteArrayInputStream input2 = new ByteArrayInputStream(commentaire.getBytes());
        System.setIn(input2);

        String result = produit.commenter(Utilisateur);

        assertEquals("Vous avez choisi de commenter: " + "\u001B[1m" + "*Aucun commentaire* " + "\u001B[0m", result);
    }

    @Test
    public void testLiker() {
        String like = "1";
        ByteArrayInputStream input1 = new ByteArrayInputStream(like.getBytes());
        System.setIn(input1);

        String result = produit.liker(null, null);

        assertEquals("Vous avez choisi de " + "\u001B[1m" + "liker" + "\u001B[0m" + " le produit!", result);

    }
}
