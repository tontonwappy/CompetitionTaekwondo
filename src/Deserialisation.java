import java.io.FileInputStream;
import java.io.ObjectInputStream;



public class Deserialisation {

	@SuppressWarnings({ "resource" })
	public static void deserialise(String nomFichier){
		try {
			FileInputStream fichier = new FileInputStream(nomFichier);
			ObjectInputStream ois = new ObjectInputStream(fichier);
			
			Sauvegarde  restauration = (Sauvegarde) ois.readObject();
			System.out.println("Deserialisation"+restauration.listClub);
			Controleur.competitionEnCours.listClub=restauration.listClub;
			Controleur.competitionEnCours.listeCategorie=restauration.listeCategorie;
			Controleur.chargementCategorie();
			Controleur.remplirListClub(PanelAjoutClub.listModel);
			Controleur.refreshCheckBox(PanelAjoutCategorie.contenuBas);
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
	
	public static String retrouveNomCompetition(String nomFile){
		String resultat="null";
		try {
		FileInputStream fichier = new FileInputStream(nomFile);
		ObjectInputStream ois = new ObjectInputStream(fichier);
		
		Competition  restauration = (Competition) ois.readObject();
		resultat=restauration.getNomCompetition();

		ois.close();
		}
		catch (java.io.IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return resultat;	
	}
	
	public static Competition rechercheCompetitionDansFichier(String nomFile){
		Competition restauration = null;
		try {
		FileInputStream fichier = new FileInputStream(nomFile);
		ObjectInputStream ois = new ObjectInputStream(fichier);
		restauration = (Competition) ois.readObject();
		ois.close();
		}
		catch (java.io.IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return restauration;	
	}
		
		
	
}
