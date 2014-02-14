import java.io.FileOutputStream;
import java.io.ObjectOutputStream;


public class Serialization {

	public static  void serialise(Competition competition){
		try {
			FileOutputStream fichier = new FileOutputStream(competition.getNomCompetition()+competition.getDateCompetition()+".ser");
			ObjectOutputStream oos = new ObjectOutputStream(fichier);
			oos.writeObject(competition);
			oos.flush();
			oos.close();
			System.out.println(competition.getNomCompetition());
			System.out.println("Sauvegarde effectué");
		}
		catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}
}