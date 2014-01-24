import java.io.FileInputStream;
import java.io.ObjectInputStream;



public class Deserialisation {

	@SuppressWarnings({ "resource" })
	public static void deserialise(){
		try {
			FileInputStream fichier = new FileInputStream("gestionCompetition.ser");
			ObjectInputStream ois = new ObjectInputStream(fichier);
			
			Sauvegarde  restauration = (Sauvegarde) ois.readObject();
			System.out.println("Deserialisation"+restauration.listClub);
			Controleur.listClub=restauration.listClub;
			Controleur.listeCategorie=restauration.listeCategorie;
			Controleur.listCategorieCombat=restauration.listCategorieCombat;
			Controleur.chargementCategorie();
			PanelAjoutClub.remplirListClub();
			PanelAjoutCategorie.refreshCheckBox(PanelAjoutCategorie.contenuBas);
			for(Club cl : restauration.listClub){
				System.out.println("*********");
				System.out.println(cl.getNom());
			}
			for(Categorie  cat : restauration.listeCategorie){
				System.out.println("*********");
				System.out.println(cat.getNom());
			}
			
		}
		catch (java.io.IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
