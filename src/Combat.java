import java.io.Serializable;


public class Combat  implements  Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6610523947725260495L;

	private Competiteur premierCombattant;
	private Competiteur deuxiemeCombattant;

	
	public Combat(Competiteur premierCombattant,
			Competiteur deuxiemeCombattant) {
		super();

		this.premierCombattant = premierCombattant;
		this.deuxiemeCombattant = deuxiemeCombattant;

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


}
