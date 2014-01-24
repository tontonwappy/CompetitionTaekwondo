import java.io.Serializable;


public class Combat  implements  Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6610523947725260495L;
	private Categorie categorie;
	private Competiteur premierCombattant;
	private Competiteur deuxiemeCombattant;
	private String genre;
	
	
	public Combat(Categorie categorie, Competiteur premierCombattant,
			Competiteur deuxiemeCombattant, String genre) {
		super();
		this.categorie = categorie;
		this.premierCombattant = premierCombattant;
		this.deuxiemeCombattant = deuxiemeCombattant;
		this.genre = genre;
	}

	public Combat() {
		// TODO Auto-generated constructor stub
	}

	public Competiteur getPremierCombattant() {
		return premierCombattant;
	}
	public void setPremierCombattant(Competiteur premierCombattant) {
		this.premierCombattant = premierCombattant;
	}
	public Competiteur getDeuxiemeCombattant() {
		return deuxiemeCombattant;
	}
	public void setDeuxiemeCombattant(Competiteur deuxiemeCombattant) {
		this.deuxiemeCombattant = deuxiemeCombattant;
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
}
