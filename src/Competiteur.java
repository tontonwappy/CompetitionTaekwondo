import java.io.Serializable;


public class Competiteur  implements  Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1119009845909241085L;
	private int id;
	private String nom;
	private String prenom;
	private int age;
	private String genre;
	private Club club;
	private Categorie categorie;
	private boolean dansListCombat=false;
	
	Competiteur( String nom, String prenom, int age,String genre,Club club){
		this.nom=nom;
		this.prenom=prenom;
		this.age=age;
		this.genre=genre;
		this.setClub(club);	
		this.categorie=null;
	}
	
	public Competiteur() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}
	public String toString(){
		return("** nom :"+this.getNom()+" //prenom :"+this.getPrenom()+"//age :"+this.getAge()+"//club :"+this.getClub().getNom()+"// Categorie"+this.getCategorie().getNom()+"**");
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public boolean isDansListCombat() {
		return dansListCombat;
	}

	public void setDansListCombat(boolean dansListCombat) {
		this.dansListCombat = dansListCombat;
	}
	
}

