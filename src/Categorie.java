import java.io.Serializable;




public class Categorie implements  Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6003726445798208474L;
	private String nom;
	private int ageMini;
	private int ageMaxi;
	private boolean categorieMixt=false;
	//private  ArrayList<Competiteur> listeCompetiteur=new ArrayList<Competiteur>();
	
	Categorie(String nom, int ageMini, int ageMaxi){
		this.nom=nom;
		this.ageMini=ageMini;
		this.ageMaxi=ageMaxi;
		this.categorieMixt=false;
	}
	
	public boolean isCategorieMixt() {
		return categorieMixt;
	}

	public void setCategorieMixt(boolean categorieMixt) {
		this.categorieMixt = categorieMixt;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getAgeMini() {
		return ageMini;
	}
	public void setAgeMini(int ageMini) {
		this.ageMini = ageMini;
	}
	public int getAgeMaxi() {
		return ageMaxi;
	}
	public void setAgeMaxi(int ageMaxi) {
		this.ageMaxi = ageMaxi;
	}


	

}
