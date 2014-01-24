import java.io.Serializable;
import java.util.ArrayList;


public class ListeCombat  implements  Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7190058967572600684L;
	private Categorie categorie;
	private String genre;
	private ArrayList<Competiteur> listeCombattant=new ArrayList<Competiteur>();
	private ArrayList<Combat> listeCombat=new ArrayList<Combat>();
	
	ListeCombat(Categorie categorie,String genre){
		this.categorie=categorie;
		this.genre=genre;
	}
	
	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public String toString(){
		return("-->categorie :"+categorie.getNom()+" ** Genre :"+this.genre+"<--");
	}

	public ArrayList<Combat> getListeCombat() {
		return listeCombat;
	}

	public void setListeCombat(ArrayList<Combat> listeCombat) {
		this.listeCombat = listeCombat;
	}

	public ArrayList<Competiteur> getListeCombattant() {
		return listeCombattant;
	}

	public void setListeCombattant(ArrayList<Competiteur> listeCombattant) {
		this.listeCombattant = listeCombattant;
	}



}
