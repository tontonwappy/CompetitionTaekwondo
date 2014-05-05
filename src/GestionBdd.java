

import java.awt.Panel;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.taekwondo.webservice.ClientLourd;
import com.taekwondo.webservice.ClientLourdImplService;


public class GestionBdd {
	static ClientLourd service=null;
	static List<com.taekwondo.webservice.Competition> recuperationlistCompetition;

	public static boolean initConnection(){
		boolean connection=false;
		ClientLourdImplService helloWorldService = new ClientLourdImplService();  
		GestionBdd.service = helloWorldService.getClientLourdImplPort(); 
		if(GestionBdd.service!=null)
			connection=true;
		return connection;	
	}

	public static boolean envoieCompetitionVersBDD(String nomCompetition){
		boolean competitionEnregistre=true;
		boolean competitiontrouvee=false;
		service.connectionBdd();
		List<com.taekwondo.webservice.Competition> list= service.recuperationListeCompetition();
		Competition comp=Deserialisation.rechercheCompetitionDansFichier(nomCompetition+".ser");
		for(com.taekwondo.webservice.Competition c : list){
			if((c.getNomCompetition()+c.getDateCompetition()).equals(comp.getNomCompetition()+comp.getDateCompetition()))
				competitiontrouvee=true;
		}
		if (competitiontrouvee==false){
			service.ajoutCompetition(comp.getNomCompetition(), comp.getDateCompetition());


			for(Categorie cat:comp.getListeCategorie()){
				service.ajoutCategorie(comp.getNomCompetition(),cat.getNom() , cat.getAgeMini(), cat.getAgeMaxi());
			}

			for(Club cl:comp.getListClub()){
				service.ajoutClub(comp.getNomCompetition(), cl.getNom());
				for(Competiteur compet:cl.getListCompetiteur()){
					int idCat=service.retrouveIdCategorie(compet.getCategorie().getNom());
					service.ajoutCompetiteur(comp.getNomCompetition(),cl.getNom(),compet.getNom(), compet.getPrenom(),compet.getAge(),compet.getGenre(),idCat);
				}
			}		
		}
		else{
			JOptionPane.showMessageDialog(Fenetre.PanelSynchronisation,"La competition existe deja","Erreur",JOptionPane.ERROR_MESSAGE);		
			competitionEnregistre=false;
		}
		service.closeBdd();
		return competitionEnregistre;
	}


	public static void remplirTabSynchCompetition(){
		GestionBdd.service.connectionBdd();
		if(GestionBdd.service!=null){
			recuperationlistCompetition= GestionBdd.service.recuperationListeCompetition();
			PanelSynchronisation.listModelCompetitionBdd.clear();
			for(com.taekwondo.webservice.Competition c : recuperationlistCompetition){
				PanelSynchronisation.listModelCompetitionBdd.addElement(c.getNomCompetition()+" / "+c.getDateCompetition());
			}
		}
		GestionBdd.service.closeBdd();

	}

	public static void recuperationCompetitionBDD(int selectedValue) {
		GestionBdd.service.connectionBdd();
		boolean competitionexistance=false;
		if(GestionBdd.service!=null){
			List<com.taekwondo.webservice.Categorie> listeCategorie=service.recuperationListeCategorie(recuperationlistCompetition.get(selectedValue).getId());

			File dir = new File(".");
			File [] files = dir.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return name.endsWith(".ser");
				}
			});
			for (File serFiles : files) {			
				Competition comp=Deserialisation.rechercheCompetitionDansFichier(serFiles.getName());
				if(comp.getNomCompetition().equals(recuperationlistCompetition.get(selectedValue).getNomCompetition())){
					competitionexistance=true;

				}
			}

			if(competitionexistance==false){
				Competition competition=new Competition(recuperationlistCompetition.get(selectedValue).getNomCompetition(),recuperationlistCompetition.get(selectedValue).getDateCompetition());
				for(com.taekwondo.webservice.Categorie cat:listeCategorie){
					competition.getListeCategorie().add(new Categorie(cat.getNom(), cat.getAgeMini(), cat.getAgeMaxi()));
				}

				List<com.taekwondo.webservice.Club> listeClub=service.recuperationListeClub(recuperationlistCompetition.get(selectedValue).getId());
				for(com.taekwondo.webservice.Club club:listeClub){
					Club newClub=new Club(1,club.getNom());
					competition.getListClub().add(newClub);
					for(com.taekwondo.webservice.Competiteur comp : service.recuperationListeCompetiteur(club.getId())){
						Competiteur newCompetiteur=new Competiteur(comp.getNom(),comp.getPrenom(),comp.getAge(),comp.getGenre(),newClub);
						newClub.getListCompetiteur().add(newCompetiteur);		
						String nomCategorieCompetiteur=service.retourneNomCategorie(comp.getCategorie());	
						for(Categorie cat:competition.getListeCategorie()){
							if(cat.getNom().equals(nomCategorieCompetiteur)){
								newCompetiteur.setCategorie(cat);					
							}
						}
					}
				}
				Serialization.serialise(competition);
				Controleur.remplirComboFichier();
				Controleur.remplirComboFichierSerialize();
			}
			else{
				JOptionPane.showMessageDialog(Fenetre.PanelSynchronisation,"La competition existe deja","Erreur",JOptionPane.ERROR_MESSAGE);	
			}
			GestionBdd.service.closeBdd();
		}

	}

	public static void envoieAjoutACompetition(ArrayList<Club> listeClubNonMaj,
			ArrayList<Categorie> listeCategorieNonMaj,
			ArrayList<Competiteur> listeCompetiteurNonMaj) {
		GestionBdd.service.connectionBdd();
		if(listeClubNonMaj.size()>0){
			for(Club cl:listeClubNonMaj){
				GestionBdd.service.ajoutClub(Controleur.competitionEnCours.getNomCompetition(), cl.getNom());
			}

			PanelAjoutEtSupp.listeClubNonMaj.clear();
			PanelAjoutEtSupp.listModelClub.clear();

		}
		if(listeCategorieNonMaj.size()>0){
			for(Categorie cat:listeCategorieNonMaj){
				service.ajoutCategorie(Controleur.competitionEnCours.getNomCompetition(),cat.getNom() , cat.getAgeMini(), cat.getAgeMaxi());

			}
			PanelAjoutEtSupp.listeCategorieNonMaj.clear();
			PanelAjoutEtSupp.listModelCategorie.clear();
		}
		if(listeCompetiteurNonMaj.size()>0){
			for(Competiteur comp:listeCompetiteurNonMaj){
				int idCat=service.retrouveIdCategorie(comp.getCategorie().getNom());
				service.ajoutCompetiteur(Controleur.competitionEnCours.getNomCompetition(),comp.getClub().getNom(),comp.getNom(), comp.getPrenom(),comp.getAge(),comp.getGenre(),idCat);
			}
			PanelAjoutEtSupp.listeCompetiteurNonMaj.clear();
			PanelAjoutEtSupp.listModelCompetiteur.clear();
		}

		GestionBdd.service.closeBdd();
	}

	//verifie si il le client lourd est a jour
	public static boolean verificationDonneesBDDetAjout(){
		GestionBdd.service.connectionBdd();
		//Controleur.competitionEnCours.setDate("2014-03-27");
		//Controleur.competitionEnCours.setHeure("14:51");

		boolean uptodate=GestionBdd.service.isUpToDate(Controleur.competitionEnCours.getNomCompetition(), Controleur.competitionEnCours.getDate(), Controleur.competitionEnCours.getHeure());

		if(uptodate==false){
			int result =JOptionPane.showConfirmDialog(Fenetre.panelAccueil,"Attention il y a des données à télécharger \n vous devez les faire pour pouvoir continuer","Attention",JOptionPane.WARNING_MESSAGE);	
			if (result == JOptionPane.OK_OPTION) {
				GestionBdd.ajoutCompetiteursDeLaBdd(Controleur.competitionEnCours.getNomCompetition());

			} else {
				System.out.println("Cancelled");
			}
		}

		//GestionBdd.service.closeBdd();
		return uptodate;
	}


	public static void ajoutCompetiteursDeLaBdd(String nomCompetition){
		System.out.println("ajout de la bdd");
		ArrayList<Competiteur> listeNouveauAjoutComp=new ArrayList<Competiteur>();
		GestionBdd.service.connectionBdd();
		int idcompetition=GestionBdd.service.recuperationCompetition(nomCompetition);
		ArrayList<Competiteur> listeCompetiteurWeb=new ArrayList<Competiteur>();
		/*recuperation de la liste des clubs de la base de donées*/
		List<com.taekwondo.webservice.Club> listeClub=GestionBdd.service.recuperationListeClub(idcompetition);
		/* on compare si les clubs dans le client lourd sont les meme que la basee de données*/
		for(com.taekwondo.webservice.Club club:listeClub){
			boolean clubTrouve=false;
			for(Club clubControleur:Controleur.getCompetitionEnCours().getListClub()){	
				if(clubControleur.getNom().equals(club.getNom())){
					clubTrouve=true;
				}
			}
			/* si on a pas trouvé le club on l'ajoute au client lourd*/
			if(!clubTrouve){
				Controleur.getCompetitionEnCours().getListClub().add(new Club(1,club.getNom()));
				Controleur.remplirListClub(PanelAjoutClub.listModel);
			}
			
			
			Club newClub=new Club(1,club.getNom());
			/* on récupere la liste des clubs de la base de données*/
			List<com.taekwondo.webservice.Competiteur> listcompetiteurWeb=GestionBdd.service.recuperationListeCompetiteur(club.getId());
			for(com.taekwondo.webservice.Competiteur comp:listcompetiteurWeb){
				/* on instantie un nouveaux competiteur avec les données de la bdd pour pouvoir le comparer*/
				Competiteur newcomp=new Competiteur(comp.getNom(),comp.getPrenom(),comp.getAge(),comp.getGenre(),newClub);
				/* on retrouve la categorie du comattant de la bdd*/
				String nomCategorieCompetiteur=service.retourneNomCategorie(comp.getCategorie());	
				
				/* on ajoute les combattans de la base de données a un array contenant tous les combattants de la bdd*/
				for(Categorie cat:Controleur.competitionEnCours.getListeCategorie()){
					if(cat.getNom().equals(nomCategorieCompetiteur)){
						newcomp.setCategorie(cat);		
						listeCompetiteurWeb.add(newcomp);
					}
				}
			}			
		}
		for (Club cl:Controleur.competitionEnCours.getListClub()){
			@SuppressWarnings("unused")
			boolean comptrouve=false;
			for(Competiteur compControleur:cl.getListCompetiteur()){
				for(Competiteur comp:listeCompetiteurWeb){	
					if((comp.getNom().equals(compControleur.getNom()))&&(comp.getPrenom().equals(compControleur.getPrenom()))){
						comptrouve=true;
					}
				}
			}
		}
		/* on recherche si le combattant existe*/
		for(Competiteur comp:listeCompetiteurWeb){
			boolean comptrouve=false;
			for (Club cl:Controleur.competitionEnCours.getListClub()){
				for(Competiteur compControleur:cl.getListCompetiteur()){
					if((comp.getNom().equals(compControleur.getNom()))&&(comp.getPrenom().equals(compControleur.getPrenom()))){
						comptrouve=true;
					}
				}
			}
			if(comptrouve==false){
				System.out.println(comp.toString());
				ArrayList<Club> club=Controleur.competitionEnCours.getListClub();
				for(Club cl :club){
					if(cl.getNom().equals(comp.getClub().getNom())){

						boolean compCacheTrouve=false;
						for(Competiteur compCache:Controleur.ListeCacheSuppresssionComp){
							if(comp.getNom().equals(compCache.getNom()) && (comp.getPrenom().equals(compCache.getPrenom()))){
								compCacheTrouve=true;
							}
						}

						if(!compCacheTrouve){
							cl.getListCompetiteur().add(comp);
							listeNouveauAjoutComp.add(comp);
						}
					}
				}

			}
		}
		String listeComp = " : ";
		for(Competiteur comp:listeNouveauAjoutComp){
			listeComp=listeComp+"\n"+comp.getNom()+" "+comp.getPrenom() + " / " + comp.getAge() +"ans / " + comp.getClub().getNom();
		}
		List<String>heuredate=GestionBdd.recupSplitDate();
		Controleur.competitionEnCours.setDate(heuredate.get(0));
		Controleur.competitionEnCours.setHeure(heuredate.get(1));
		JOptionPane.showMessageDialog(Fenetre.panelAccueil,"vous avez ajoutez"+listeComp,"Information",JOptionPane.INFORMATION_MESSAGE);		
		GestionBdd.service.closeBdd();

	}


	public static ArrayList<Integer> competiteurPasListWeb(String nomCompetition){

		GestionBdd.service.connectionBdd();
		ArrayList<Integer> listIdCompetiteur=new ArrayList<Integer>();
		int idcompetition=GestionBdd.service.recuperationCompetition(nomCompetition);	
		List<com.taekwondo.webservice.Club> listeClub=GestionBdd.service.recuperationListeClub(idcompetition);
		for(com.taekwondo.webservice.Club club:listeClub){						
			List<com.taekwondo.webservice.Competiteur> listcompetiteurWeb=GestionBdd.service.recuperationListeCompetiteur(club.getId());
			for(com.taekwondo.webservice.Competiteur comp:listcompetiteurWeb){
				boolean comptrouve=false;
				for (Club cl:Controleur.competitionEnCours.getListClub()){			
					for(Competiteur compControleur:cl.getListCompetiteur()){
						if((comp.getNom().equals(compControleur.getNom()))&&(comp.getPrenom().equals(compControleur.getPrenom()))){
							comptrouve=true;
						}
					}

				}
				if(comptrouve==false){
					listIdCompetiteur.add(comp.getId());
				}

			}			
		}

		return listIdCompetiteur;
	}

	public static List<String> recupSplitDate(){
		GestionBdd.service.connectionBdd();
		List<String> dateHeure=GestionBdd.service.getHeureDate(Controleur.competitionEnCours.getNomCompetition());
		return dateHeure;

	}

}
