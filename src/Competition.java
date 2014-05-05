import java.io.Serializable;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Competition implements  Serializable{
	@Override
	public String toString() {
		return "Competition [nomCompetition=" + nomCompetition
				+ ", dateCompetition=" + dateCompetition + ", dateMAJ="
				+ dateMAJ + ", heureMAJ=" + heureMAJ + "]";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5949232266657711640L;
	private String nomCompetition;
	private String dateCompetition;
	private String dateMAJ;
	private String heureMAJ;
	
	
	public   ArrayList<Categorie> listeCategorie=new ArrayList<Categorie>();
	public  ArrayList<Club> listClub=new ArrayList<Club>();
	
	public Competition(String nomCompetition, String dateCompetition) {
		super();
		this.nomCompetition = nomCompetition;
		this.dateCompetition = dateCompetition;
		this.setDate(null);
		this.setHeure(null);

		FileOutputStream fichier;
		try {
			fichier = new FileOutputStream(nomCompetition+dateCompetition+".ser");
			ObjectOutputStream oos = new ObjectOutputStream(fichier);
			oos.writeObject(this);
			oos.flush();
			oos.close();
			System.out.println("Sauvegarde effectué");
		} catch (java.io.IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	}

	public Competition() {
		// TODO Auto-generated constructor stub
	}

	public  ArrayList<Categorie> getListeCategorie() {
		return this.listeCategorie;
	}
	
	public  void listeCategorieToString(){
		for(Categorie cat :this.listeCategorie){
			System.out.println(cat.getNom());
		}
	}

	public  void setListeCategorie(ArrayList<Categorie> listeCategorie) {
		this.listeCategorie = listeCategorie;
	}

	public  ArrayList<Club> getListClub() {
		return this.listClub;
	}

	public  void setListClub(ArrayList<Club> listClub) {
		this.listClub = listClub;
	}

	public String getDateCompetition() {
		return dateCompetition;
	}
	public void setDateCompetition(String dateCompetition) {
		this.dateCompetition = dateCompetition;
	}
	public String getNomCompetition() {
		return nomCompetition;
	}
	public void setNomCompetition(String nomCompetition) {
		this.nomCompetition = nomCompetition;
	}

	public String getDate() {
		return dateMAJ;
	}

	public void setDate(String date) {
		this.dateMAJ = date;
	}

	public String getHeure() {
		return heureMAJ;
	}

	public void setHeure(String heure) {
		this.heureMAJ = heure;
	}
	


}
