import java.io.FileOutputStream;
import java.io.ObjectOutputStream;


public class Serialization {

	public static  void serialise(Sauvegarde sauvegarde){
		try {
			FileOutputStream fichier = new FileOutputStream("gestionCompetition.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fichier);
			oos.writeObject(sauvegarde);
			oos.flush();
			oos.close();
			System.out.println("Sauvegarde effectué");
		}
		catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}
}