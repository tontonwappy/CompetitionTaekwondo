import java.util.ArrayList;
import java.util.Collections;

import jxl.write.WriteException;




public class Controleur {
	public static  ArrayList<Categorie> listeCategorie=new ArrayList<Categorie>();
	public static ArrayList<Club> listClub=new ArrayList<Club>();
	public static ArrayList<Categorie> getListeCategorie() {
		return listeCategorie;
	}
	
	public static void listeCategorieToString(){
		for(Categorie cat :listeCategorie){
			System.out.println(cat.getNom());
		}
	}

	public static void setListeCategorie(ArrayList<Categorie> listeCategorie) {
		Controleur.listeCategorie = listeCategorie;
	}

	public static ArrayList<Club> getListClub() {
		return listClub;
	}

	public static void setListClub(ArrayList<Club> listClub) {
		Controleur.listClub = listClub;
	}

	public static ArrayList<ListeCombat> getListCategorieCombat() {
		return listCategorieCombat;
	}

	public static void setListCategorieCombat(
			ArrayList<ListeCombat> listCategorieCombat) {
		Controleur.listCategorieCombat = listCategorieCombat;
	}

	public static ArrayList<ListeCombat> listCategorieCombat = new ArrayList<ListeCombat>();


	public static Boolean inserCompetiteur(Competiteur competiteur,Club c){
		c.getListCompetiteur().add(competiteur);
		return true;
	}
	
	public static Categorie inserCombattantCategorie(Competiteur competiteur){
		for(Categorie cat : listeCategorie){

			if(cat.getAgeMini()<=competiteur.getAge() && cat.getAgeMaxi()>=competiteur.getAge()){
				competiteur.setCategorie(cat);
				System.out.println("Nouveau competiteur ajout� dans :" + cat.getNom());
				return cat;
			}
		}
		return null;
	}


	public static Boolean verifInsertionCategorie(int age){
		for(Categorie cat : listeCategorie){
			if(cat.getAgeMini()<=age && cat.getAgeMaxi()>=age){
				return true;
			}
		}
		return false;

	}


	public static Categorie retrouveCategorie(String categorieString){
		for(Categorie cat: listeCategorie){
			if (cat.getNom().equals(categorieString))
				return cat;
		}
		return null;
	}

	public static Boolean VerifCreationCategorie(int ageMini,int ageMaxi){
		for(Categorie cat : listeCategorie){
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

	public static Boolean  verifAgeDansCategorie(int age,Categorie cat){
		Boolean agebool=false;
		if((cat.getAgeMini()<=age) && (age<=cat.getAgeMaxi())){
			agebool=true;
		}
		
		return agebool;
		
		
		
	}
	public static Club rechercheClub(String club){
		Club recupclub=new Club();
		for(Club cl : listClub){
			if(cl.getNom().equals(club)){
				recupclub=cl;
			}		
		}
		return recupclub;
	}

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
				listClub.remove(recupClub);
			}
		}

		return supprim;
	}


	public static boolean supprimCompetiteurClub(Club club,int numeroDsArray){	
		int tailleAvant=club.getListCompetiteur().size();
		club.getListCompetiteur().remove(numeroDsArray);

		if(tailleAvant==club.getListCompetiteur().size()){
			return false;
		}
		else{
			return true;
		}
	}

	public static void supprimCategorie(int numeroDsArray){
		listeCategorie.remove(numeroDsArray);
	}


	public static void afficheToutCompetiteur(){
		for(Club cl : listClub){
			for(Competiteur comp :cl.getListCompetiteur()){
				System.out.println("-----");			
				comp.toString();
				System.out.println("-----");
			}
		}
	}

	public static void generationListCombat(){
		listCategorieCombat.clear();
		for(Categorie cat : listeCategorie){
			if(cat.isCategorieMixt()){
				ListeCombat nvlListeCombatH=new ListeCombat(cat, "Mixte");
				listCategorieCombat.add(nvlListeCombatH);
				for(Club cl : listClub){
					for(Competiteur comp : cl.getListCompetiteur()){
						System.out.println(comp.getNom());
						if(comp.getCategorie().getNom().equals(cat.getNom())){
							nvlListeCombatH.getListeCombattant().add(comp);

						}
					}
				}
			}

			else{
				//TODO  ne pas cr�er de doublons de categorie H et F
				ListeCombat nvlListeCombatH=new ListeCombat(cat, "H");
				ListeCombat nvlListeCombatF=new ListeCombat(cat, "F");
				listCategorieCombat.add(nvlListeCombatH);
				listCategorieCombat.add(nvlListeCombatF);	
				for(Club cl : listClub){
					for(Competiteur comp : cl.getListCompetiteur()){
						System.out.println(comp.getNom());
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
							Combat nouveauCombat =new Combat(li.getCategorie(),comp1,comp2,li.getGenre());				
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
							Combat nouveauCombat =new Combat(li.getCategorie(),comp1,comp2,li.getGenre());				
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
						Combat nouveauCombat =new Combat(li.getCategorie(),comp1,null,li.getGenre());				
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

		for(Categorie cat : Controleur.listeCategorie){
			PanelAjoutCategorie.listModel.addElement(cat.getNom());
			PanelAjoutCategorie.model.addRow(new Object[]{cat.getNom(),cat.getAgeMini(),cat.getAgeMaxi()});			

		}

	}

	public static void resetCombattant(){
		for(Club cl : listClub){
			for(Competiteur comp : cl.getListCompetiteur()){
				comp.setDansListCombat(false);
			}
		}
	}
	public static void importDonnee(String typeImport){
		@SuppressWarnings("unused")
		FileChooser fentreImport= new FileChooser(typeImport);
	}

}