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
		listeCategorie=Controleur.listeCategorie;
		listClub=Controleur.listClub;
		listCategorieCombat=Controleur.listCategorieCombat;
		System.out.println(listeCategorie);
	}
	
	public static void chargement(ArrayList<ListeCombat> listCatCombSauv,ArrayList<Club> listClubSauv,ArrayList<Categorie> listCatSauv){
		Controleur.listCategorieCombat.removeAll(Controleur.listCategorieCombat);
		Controleur.listClub.removeAll(Controleur.listClub);
		Controleur.listCategorieCombat.removeAll(Controleur.listeCategorie);
		
		Controleur.listCategorieCombat=listCatCombSauv;
		Controleur.listClub=listClubSauv;
		Controleur.listeCategorie=listCatSauv;
	}
	
}
