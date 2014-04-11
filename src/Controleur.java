import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;


import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;


public class Controleur {
	//Met en cache les competiteurs supprimés durant le commencement de la suppression a la mise a jour de la base
	//Si un utilisateur met a jour la base pendant entre la suppression et le MAJ, le competiteur supprimer ne le sera pas
	//pour la suppression on regarde la difference entre le client et la base a condition que le client soit a jour, puis on supprime les combattants qui ne sont plus dans le client
	//mais si un utilisateur MAJ la base en meme tps, le numero de version sera different et on devra re telecharger les differences de la base vers le client, hors les combattants supprimé ne seront plus dans le client
	//et le systeme va par conséquent re téléchargé les combattants précedements supprimé
	public static ArrayList<Competiteur> ListeCacheSuppresssionComp=new ArrayList<Competiteur>();
	public static ArrayList<Integer> parcourAgeManquant=new ArrayList<Integer>();
	public static ArrayList<ListeCombat> listCategorieCombat = new ArrayList<ListeCombat>();
	static Competition competitionEnCours=new Competition();
	static boolean fichierSelection=false; //si aucun fichier n'est selectionné, aucun menu peut être selectionné

	public static Competition getCompetitionEnCours() {
		return competitionEnCours;
	}



	public static void setCompetitionEnCours(Competition competitionEnCours) {
		Controleur.competitionEnCours = competitionEnCours;
	}

	


	public static void listeCategorieToString(){
		for(Categorie cat :competitionEnCours.listeCategorie){
			System.out.println(cat.getNom());
		}
	}



	public static ArrayList<ListeCombat> getListCategorieCombat() {
		return listCategorieCombat;
	}

	public static void setListCategorieCombat(
			ArrayList<ListeCombat> listCategorieCombat) {
		Controleur.listCategorieCombat = listCategorieCombat;
	}


	public static Boolean inserCompetiteur(Competiteur competiteur,Club c){
		c.getListCompetiteur().add(competiteur);
		return true;
	}

	public static Categorie inserCombattantCategorie(Competiteur competiteur){
		for(Categorie cat : competitionEnCours.listeCategorie){

			if(cat.getAgeMini()<=competiteur.getAge() && cat.getAgeMaxi()>=competiteur.getAge()){
				competiteur.setCategorie(cat);
				System.out.println("Nouveau competiteur ajoutï¿½ dans :" + cat.getNom());
				return cat;
			}
		}
		
		return null;
	}


	//Verifie si une des Categories comporte l'age
	public static Boolean verifInsertionCategorie(int age){
		for(Categorie cat : competitionEnCours.listeCategorie){
			if(cat.getAgeMini()<=age && cat.getAgeMaxi()>=age){
				return true;
			}
		}
		return false;
	}

	//Retrouve une Categorie avec son nom
	public static Categorie retrouveCategorie(String categorieString){
		for(Categorie cat: competitionEnCours.listeCategorie){
			if (cat.getNom().equals(categorieString))
				return cat;
		}
		return null;
	}

	//Verifie la conformite d'une nouvelle categorie, elle ne doit pas chevaucher une autre
	public static Boolean VerifCreationCategorie(int ageMini,int ageMaxi){
		for(Categorie cat : competitionEnCours.listeCategorie){
			if(cat.getAgeMini()<=ageMini && cat.getAgeMaxi()> ageMini ){
				return false;
			}
			else if(cat.getAgeMini()<=ageMaxi && cat.getAgeMaxi()> ageMaxi ){
				return false;
			}
			else if(cat.getAgeMini()<=ageMini && cat.getAgeMaxi()>=ageMaxi){
				return false;
			}
			else if(cat.getAgeMini()>=ageMini && cat.getAgeMaxi()<=ageMaxi){
				return false;
			}

		}
		return true;
	}

	//Verifie si l'age entre dans une des categories
	public static Boolean  verifAgeDansCategorie(int age,Categorie cat){
		Boolean agebool=false;
		if((cat.getAgeMini()<=age) && (age<=cat.getAgeMaxi())){
			agebool=true;
		}	
		return agebool;	
	}

	//Recherche un club avec son nom
	public static Club rechercheClub(String club){
		Club recupclub=null;
		if(competitionEnCours.listClub.size()!=0){
			for(Club cl : competitionEnCours.listClub){
				if(cl.getNom().equals(club)){
					recupclub=cl;
				}		
			}
		}
		return recupclub;
	}

	//Supprime un club avec son nom
	public static boolean supprimClub(String club){
		boolean supprim = true;
		Club rechercheClub=rechercheClub(club);
		for(Competiteur comp : rechercheClub.getListCompetiteur()){
			if(comp.getClub().getNom().equals(club)){
				supprim=false;
			}
		}
		if(supprim){
			Club recupClub=rechercheClub(club);
			if(recupClub!=null){
				competitionEnCours.listClub.remove(recupClub);
			}
		}
		return supprim;
	}

	//Supprime un competiteur avec sa position dans l'array
	public static boolean supprimCompetiteurClub(Club club,int numeroDsArray){	
		int tailleAvant=club.getListCompetiteur().size();
		Controleur.ListeCacheSuppresssionComp.add(club.getListCompetiteur().get(numeroDsArray));
		club.getListCompetiteur().remove(numeroDsArray);
		if(tailleAvant==club.getListCompetiteur().size()){
			return false;
		}
		else{
			return true;
		}
	}

	//supprime une categorie avec sa position dans l'array
	public static void supprimCategorie(int numeroDsArray){
		competitionEnCours.listeCategorie.remove(numeroDsArray);
	}

	//affiche tout les competiteurs pour tous les clubs
	public static void afficheToutCompetiteur(){
		for(Club cl : competitionEnCours.listClub){
			for(Competiteur comp :cl.getListCompetiteur()){
				System.out.println("-----");			
				comp.toString();
				System.out.println("-----");
			}
		}
	}

	//genere une liste de combat avec pour chaque combat, 2 combatants, si ce n'est pas possible cree un combat avec 1 combattant
	public static void generationListCombat(){
		listCategorieCombat.clear();
		for(Categorie cat : competitionEnCours.listeCategorie){
			if(cat.isCategorieMixt()){
				ListeCombat nvlListeCombatH=new ListeCombat(cat, "Mixte");
				listCategorieCombat.add(nvlListeCombatH);
				for(Club cl : competitionEnCours.listClub){
					for(Competiteur comp : cl.getListCompetiteur()){
						//System.out.println(comp.getNom());
						if(comp.getCategorie().getNom().equals(cat.getNom())){
							nvlListeCombatH.getListeCombattant().add(comp);
						}
					}
				}
			}
			else{
				ListeCombat nvlListeCombatH=new ListeCombat(cat, "H");
				ListeCombat nvlListeCombatF=new ListeCombat(cat, "F");
				listCategorieCombat.add(nvlListeCombatH);
				listCategorieCombat.add(nvlListeCombatF);	
				for(Club cl : competitionEnCours.listClub){
					for(Competiteur comp : cl.getListCompetiteur()){

						if(comp.getCategorie().getNom().equals(cat.getNom())){
							if(comp.getGenre().equals("H")){
								nvlListeCombatH.getListeCombattant().add(comp);
							}

							else{
								nvlListeCombatF.getListeCombattant().add(comp);
							}
						}
					}
				}
			}
		}
	}

	public static void insertionCombattantListCombat(){
		resetCombattant();
		for(ListeCombat li : listCategorieCombat){
			Collections.shuffle(li.getListeCombattant());	
			for(Competiteur comp1 :li.getListeCombattant()){
				if(!comp1.isDansListCombat()){
					for(Competiteur comp2 :li.getListeCombattant()){
						if(comp1.getClub()!=comp2.getClub() && !comp2.isDansListCombat()  && comp1!=comp2){
							Combat nouveauCombat =new Combat(comp1,comp2);				
							li.getListeCombat().add(nouveauCombat);
							comp1.setDansListCombat(true);
							comp2.setDansListCombat(true);
							break;
						}


					}
				}
			}
			for(Competiteur comp1 :li.getListeCombattant()){
				if(!comp1.isDansListCombat()){
					for(Competiteur comp2 :li.getListeCombattant()){
						if(!comp2.isDansListCombat() && comp1!=comp2){
							Combat nouveauCombat =new Combat(comp1,comp2);				
							li.getListeCombat().add(nouveauCombat);
							comp1.setDansListCombat(true);
							comp2.setDansListCombat(true);
							break;
						}


					}
				}

			}

			if (li.getListeCombattant().size() % 2==1){
				for(Competiteur comp1 :li.getListeCombattant()){
					if(!comp1.isDansListCombat()){
						Combat nouveauCombat =new Combat(comp1,null);				
						li.getListeCombat().add(nouveauCombat);
						comp1.setDansListCombat(true);
					}
				}
			}

		}
		for(ListeCombat li : listCategorieCombat){
			for(Combat combat : li.getListeCombat()){
				if(combat.getDeuxiemeCombattant()!=null && combat.getPremierCombattant().getClub().getNom().equals(combat.getDeuxiemeCombattant().getClub().getNom())){			
					for(Combat listeCombattant2 : li.getListeCombat()){
						System.out.println("***");
						System.out.println(combat.getPremierCombattant());
						System.out.println(combat.getDeuxiemeCombattant());
						if(listeCombattant2.getDeuxiemeCombattant()!=null){
							if(!(combat.getPremierCombattant().getClub().getNom().equals(listeCombattant2.getPremierCombattant().getClub().getNom())) && !(combat.getPremierCombattant().getNom().equals(listeCombattant2.getDeuxiemeCombattant().getClub().getNom()))){


								System.out.println(listeCombattant2.getPremierCombattant());
								System.out.println(listeCombattant2.getDeuxiemeCombattant());
								Competiteur competetiteurSave=combat.getPremierCombattant();
								combat.setPremierCombattant(listeCombattant2.getPremierCombattant());															
								listeCombattant2.setPremierCombattant(competetiteurSave);

							}
						}
					}

				}
			}
		}

		for(ListeCombat li : listCategorieCombat){
			for(Combat combat : li.getListeCombat()){
				if(combat.getDeuxiemeCombattant()!=null && combat.getPremierCombattant().getClub().getNom().equals(combat.getDeuxiemeCombattant().getClub().getNom())){			


					for(Combat listeCombattant2 : li.getListeCombat()){
						System.out.println("***");
						System.out.println(combat.getPremierCombattant());
						System.out.println(combat.getDeuxiemeCombattant());
						if(listeCombattant2.getDeuxiemeCombattant()!=null){
							if(!(combat.getPremierCombattant().getClub().getNom().equals(listeCombattant2.getPremierCombattant().getClub().getNom())) && !(combat.getPremierCombattant().getNom().equals(listeCombattant2.getDeuxiemeCombattant().getClub().getNom()))){


								System.out.println(listeCombattant2.getPremierCombattant());
								System.out.println(listeCombattant2.getDeuxiemeCombattant());
								Competiteur competetiteurSave=combat.getPremierCombattant();
								combat.setPremierCombattant(listeCombattant2.getPremierCombattant());															
								listeCombattant2.setPremierCombattant(competetiteurSave);

							}
						}
					}

				}
			}
		}

	}

	public static void chargementCategorie(){
		for(Categorie cat : competitionEnCours.listeCategorie){
			PanelAjoutCategorie.listModel.addElement(cat.getNom());
			PanelAjoutCategorie.model.addRow(new Object[]{cat.getNom(),cat.getAgeMini(),cat.getAgeMaxi()});			
			
		}

	}

	public static void resetCombattant(){
		for(Club cl : competitionEnCours.listClub){
			for(Competiteur comp : cl.getListCompetiteur()){
				comp.setDansListCombat(false);
			}
		}
	}
	public static void importDonnee(String typeImport){
		@SuppressWarnings("unused")
		FileChooser fentreImport= new FileChooser(typeImport);
	}

	public static void ajoutClub(String club){
		Club nouveauClub=new Club(1,club);
		competitionEnCours.listClub.add(nouveauClub);
	}

	/* Recherche tout les competiteurs qui n'ont pas de contegorie et l'ajoute dans cette categorie*/
	public static void ajoutToutCompCategorie(Categorie categorie){
		for(Club club : competitionEnCours.listClub){
			for(Competiteur comp :club.getListCompetiteur()){
				if(comp.getCategorie()==null ){
					if (Controleur.verifAgeDansCategorie(comp.getAge(),categorie)){
						comp.setCategorie(categorie);
					}
				}
			}
		}

	}
	public static Categorie ajoutNvlCategorie(String nom,int ageMini,int ageMaxi){
		Categorie nouvelleCategorie=new Categorie(nom, ageMini,ageMaxi);
		competitionEnCours.listeCategorie.add(nouvelleCategorie);
		return nouvelleCategorie;
	}

	/* pour une categorie donnee met a null l'attribut categorie des combatants present dans celle ci*/
	public static void supprimCombattantCategorie(String categorie){
		for(Club cl :competitionEnCours.listClub){
			for(Competiteur comp : cl.getListCompetiteur()){					
				if(comp.getCategorie()!=null){
					if (comp.getCategorie().getNom().equals(categorie)){
						comp.setCategorie(null);
					}
				}
			}
		}
	}

	public static void ajoutAgeManquantDansCategorie(){
		for (Club cl:competitionEnCours.listClub)
		{
			for(Competiteur comp : cl.getListCompetiteur()){
				if(comp.getCategorie()==null){
					if(!testValeurTableau(parcourAgeManquant, comp.getAge())){
						parcourAgeManquant.add(comp.getAge());
					}
				}
			}
		}
	}


	static boolean testValeurTableau(ArrayList<Integer> tableau, Integer i){
		for(Integer tab:tableau){
			if(i==tab)
				return true;
		}
		return false;

	}

	public static ArrayList<Integer> triBulleCroissant(ArrayList<Integer> tableau) {
		int longueur = tableau.size();
		int tampon = 0;
		boolean permut;

		do {
			// hypothèse : le tableau est trié
			permut = false;
			for (int i = 0; i < longueur - 1; i++) {
				// Teste si 2 éléments successifs sont dans le bon ordre ou non
				if (tableau.get(i) > tableau.get(i+1)) {
					// s'ils ne le sont pas, on échange leurs positions
					tampon = tableau.get(i);
					tableau.set(i, tableau.get(i+1));
					tableau.set(i+1, tampon) ;
					permut = true;
				}
			}
		} while (permut);
		return tableau;
	}
	public static ArrayList<Integer> getAgeManquantDsCategorie(){
		return triBulleCroissant(Controleur.parcourAgeManquant);
	}


	static int calculAge(int dateAnneeNaissance){	
		Calendar c = Calendar.getInstance();
		int annee = c.get(Calendar.YEAR);
		int age=annee-dateAnneeNaissance;
		return age;
	}

	static int calculAnnee(int age){
		Calendar c = Calendar.getInstance();
		int annee = c.get(Calendar.YEAR);
		return annee-age;
	}

	public static void remplirListClub(DefaultListModel<String> listModel){
		listModel.clear();
		for(Club cl : competitionEnCours.listClub){
			listModel.addElement(cl.getNom());
		}
	}
	
	public static int remplirCombattantPanelCompetiteur(Club club, DefaultTableModel model){
		model.getDataVector().removeAllElements();
		int i =0;
		for(Competiteur comp : club.getListCompetiteur()){
			//if (comp.getClub()==club){
				i=i+1;
				try {
					model.addRow(new Object[]{comp.getNom(),comp.getPrenom(),comp.getAge(),Controleur.calculAnnee(comp.getAge()),comp.getGenre(),comp.getCategorie().getNom(),comp.getClub().getNom()});
				} catch (Exception e) {
					System.out.println("marche pas");
				}
				
			//}
		}
		return i;
	}
	
	public static void remplirListCategorie(DefaultListModel<String> listModel){
		for(Categorie cat : competitionEnCours.listeCategorie){
			listModel.addElement(cat.getNom());
		}
	}
	
	public static void refreshListModelCategorie(DefaultListModel<String> listModel){
		listModel.removeAllElements();
		Controleur.remplirListCategorie(listModel);				
}
	
	public static int remplirCombattant(Categorie categorie, DefaultTableModel model){
		model.getDataVector().removeAllElements();
		int i=0;
		for(Club cl : competitionEnCours.listClub){
			for(Competiteur comp : cl.getListCompetiteur()){	
				if (comp.getCategorie().getNom().equals(categorie.getNom()) && comp.getGenre().equals("H")){
					i=i+1;
					model.addRow(new Object[]{cl.getNom(),comp.getNom(),comp.getPrenom(),comp.getAge(),Controleur.calculAnnee(comp.getAge()),comp.getGenre(),comp.getCategorie().getNom(),competitionEnCours.listClub.get(0).getNom()});
				}
			}
		}		
		model.addRow(new Object[]{"***","***","***","***","***","***","***","***"});
		for(Club cl : competitionEnCours.listClub){
			for(Competiteur comp : cl.getListCompetiteur()){
				if (comp.getCategorie().getNom().equals(categorie.getNom())&& comp.getGenre().equals("F")){
					i=i+1;
					model.addRow(new Object[]{cl.getNom(),comp.getNom(),comp.getPrenom(),comp.getAge(),Controleur.calculAnnee(comp.getAge()),comp.getGenre(),comp.getCategorie().getNom(),competitionEnCours.listClub.get(0).getNom()});
				}
			}
		}
		return i;
	}

	public static void refreshCheckBox(JPanel panel){
		panel.removeAll();
		panel.repaint();
		JLabel infoMixte=new JLabel("Categorie mixte : ");
		panel.add(infoMixte);	
		for(Categorie cat : competitionEnCours.listeCategorie){			
			final JCheckBox nvlCheckBox=new JCheckBox(cat.getNom());	
			panel.add(nvlCheckBox);		
			if(cat.isCategorieMixt()){
				nvlCheckBox.setSelected(true);
			}
			nvlCheckBox.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Categorie cat = Controleur.retrouveCategorie(nvlCheckBox.getText());
					if(nvlCheckBox.isSelected())
						cat.setCategorieMixt(true);
					else
						cat.setCategorieMixt(false);	
				}});
		}
	}
	
	public static void verifAge(String ageMini,String ageMaxi){
		for(int i=Integer.parseInt(ageMini);i<=Integer.parseInt(ageMaxi);i++){
			int x=0;
			int indexAge=0;
			boolean ageTrouve=false;
			for(int age:PanelAjoutCategorie.ageManquant){
				if(age==i ){
					ageTrouve=true;
					indexAge=x;
				}
				x=x+1;
			}
			if(ageTrouve)
				PanelAjoutCategorie.ageManquant.remove(indexAge);
		}
	}

	
	public static class threadCheckListpanelCompetiteur extends Thread {
		public void run() {
			while(true){
				if(PanelAjoutCompetiteur.listModel.size()!=competitionEnCours.listClub.size()){
					PanelAjoutCompetiteur.listModel.removeAllElements();
					Controleur.remplirListClub(PanelAjoutCompetiteur.listModel);
				}
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		}
	}
	
	public static Competition ajoutCompetition(String nom, String date){
		Competition competition=new Competition(nom,date);
		return competition;
	}
	
	public static void sauvegarde(){
		Serialization.serialise(competitionEnCours);
	}
	
	public static void changementFichier(){
		PanelAjoutClub.listModel.clear();
		remplirListClub(PanelAjoutClub.listModel);
		PanelAjoutCompetiteur.listModel.clear();
		PanelAjoutCategorie.model.getDataVector().removeAllElements();
		PanelAfficherCategorieDetail.model.getDataVector().removeAllElements();
		PanelAjoutCompetiteur.model.getDataVector().removeAllElements();
		Controleur.refreshCheckBox(PanelAjoutCategorie.contenuBas);
		chargementCategorie();	
	}
	
	public static void remplirComboFichier(){
		PanelAccueil.combo.removeAllItems();
		PanelAccueil.combo.addItem("");
		File dir = new File(".");
		File [] files = dir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".ser");
			}
		});
		for (File serFiles : files) {
			PanelAccueil.combo.addItem((serFiles.getName()));
		}
	}
	
	public static void remplirComboFichierSerialize(){
		PanelSynchronisation.listModelCompetitionSer.clear();
		File dir = new File(".");
		File [] files = dir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".ser");
			}
		});
		for (File serFiles : files) {
			
			String fichier=serFiles.getName();
			String str[]=fichier.split("\\.");
			PanelSynchronisation.listModelCompetitionSer.addElement(str[0]);


			
		}
	}
	
	
}