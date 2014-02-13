import java.io.Serializable;
import java.util.ArrayList;


public class Club  implements  Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6981947146707525084L;
	private String nom;
	private   ArrayList<Competiteur> listCompetiteur=new ArrayList<Competiteur>();
	
	
	Club(int id, String nom){
		this.nom=nom;
	}
	
	Club(){}
	
	public  ArrayList<Competiteur> getListCompetiteur() {
		return listCompetiteur;
	}

	public  void setListCompetiteur(ArrayList<Competiteur> list) {
		listCompetiteur = list;
	}
	
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	

}
