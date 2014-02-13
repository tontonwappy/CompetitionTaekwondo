import java.io.Serializable;
import java.util.ArrayList;


public class Sauvegarde implements  Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6685850292374121612L;
	public ArrayList<Categorie> listeCategorie=new ArrayList<Categorie>();
	public  ArrayList<Club> listClub=new ArrayList<Club>();
	public  ArrayList<ListeCombat> listCategorieCombat = new ArrayList<ListeCombat>();
	


	public  void sauvegarder(){
		listeCategorie=Competition.listeCategorie;
		listClub=Competition.listClub;
		listCategorieCombat=Controleur.listCategorieCombat;
		System.out.println(listeCategorie);
	}
	
	public static void chargement(ArrayList<ListeCombat> listCatCombSauv,ArrayList<Club> listClubSauv,ArrayList<Categorie> listCatSauv){
		Controleur.listCategorieCombat.removeAll(Controleur.listCategorieCombat);
		Competition.listClub.removeAll(Competition.listClub);
		Controleur.listCategorieCombat.removeAll(Competition.listeCategorie);
		
		Controleur.listCategorieCombat=listCatCombSauv;
		Competition.listClub=listClubSauv;
		Competition.listeCategorie=listCatSauv;
	}
	
}
