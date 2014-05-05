import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicArrowButton;

import java.util.ArrayList;
import java.util.List;

//import com.taekwondo.exporttomcat.HelloWorld;
//import com.taekwondo.exporttomcat.HelloWorldImplService;


public class PanelAjoutEtSupp extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JButton boutonEnvoie= new JButton("Rafraichir");
	JButton boutonConfirmer= new JButton("Confirmer l'envoie");
	JLabel titre = new JLabel("Synchronisation avec la base de donnée");
	static DefaultListModel<String> listModelClub = new DefaultListModel<String>();
	JList<String> listClub = new JList<String>(listModelClub);

	static DefaultListModel<String> listModelCompetiteur = new DefaultListModel<String>();
	JList<String> listCompetiteur = new JList<String>(listModelCompetiteur);


	static DefaultListModel<String> listModelCategorie = new DefaultListModel<String>();
	JList<String> listCategorie = new JList<String>(listModelCategorie);

	JButton boutonDroite= new JButton(">>");
	JButton boutonGauche= new JButton("<<");


	public static ArrayList<Competiteur> listeCompetiteurNonMaj=new ArrayList<Competiteur>();
	public static ArrayList<Categorie> listeCategorieNonMaj=new ArrayList<Categorie>();
	public static ArrayList<Club> listeClubNonMaj=new ArrayList<Club>();
	public static ArrayList<Club> listeClubMaj=new ArrayList<Club>();

	PanelAjoutEtSupp(){

		/** Initialisation des panels **/   
		this.setLayout(new BorderLayout());
		JPanel contenuHaut = new JPanel();
		JPanel contenuCenter = new JPanel();
		//JPanel panelinDroite = new JPanel();
		this.add(contenuHaut,BorderLayout.NORTH);	
		this.add(contenuCenter,BorderLayout.CENTER);	

		listClub.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		listClub.setVisibleRowCount(-1);
		listClub.setLayoutOrientation(JList.VERTICAL); 
		JScrollPane listScroller = new JScrollPane(listClub);
		listScroller.setPreferredSize(new Dimension(200,250));

		listCompetiteur.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		listCompetiteur.setVisibleRowCount(-1);
		listCompetiteur.setLayoutOrientation(JList.VERTICAL); 
		JScrollPane listScroller2 = new JScrollPane(listCompetiteur);
		listScroller2.setPreferredSize(new Dimension(200,250));

		listCategorie.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		listCategorie.setVisibleRowCount(-1);
		listCategorie.setLayoutOrientation(JList.VERTICAL); 
		JScrollPane listScrollerCategorie = new JScrollPane(listCategorie);
		listScrollerCategorie.setPreferredSize(new Dimension(200,250));

		Controleur.remplirComboFichierSerialize();

		/** Placement des composants : titre et bouton **/
		contenuHaut.add(titre);		

		//contenuCenter.add(boutonEnvoie);
		//
		contenuCenter.add(boutonEnvoie);
		contenuCenter.add(listScroller);
		//contenuCenter.add(boutonGauche);
		//contenuCenter.add(boutonDroite);
		contenuCenter.add(listScroller2);
		contenuCenter.add(listScrollerCategorie);
		contenuCenter.add(boutonConfirmer);


		//On part du principe que la compétition est deja stocké dans la BDD
		//TODO il faut rajouter une option qui détecte si la compétiton est stocké sinon on ne propose pas ce menu
		//on part du principe que chaque compétition à un nom différent

		boutonEnvoie.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(GestionBdd.verificationDonneesBDDetAjout()){


					listModelClub.clear();
					listModelCategorie.clear();
					listModelCompetiteur.clear();
					GestionBdd.service.connectionBdd();
					int competitionId=GestionBdd.service.recuperationCompetition(Controleur.competitionEnCours.getNomCompetition());
					List<com.taekwondo.webservice.Club> listeClub=GestionBdd.service.recuperationListeClub(competitionId);
					List<com.taekwondo.webservice.Categorie> listeCategorie=GestionBdd.service.recuperationListeCategorie(competitionId);
					List<com.taekwondo.webservice.Competiteur> listCompetiteur=GestionBdd.service.recuperationTOUTCompetiteurT();
					boolean clubtrouve=false;

					boolean categorietrouve=false;
					for(Club cl:Controleur.competitionEnCours.getListClub()){
						clubtrouve=false;				
						for(com.taekwondo.webservice.Club clWeb:listeClub){		
							if(cl.getNom().equals(clWeb.getNom())){						
								clubtrouve=true;
							}
						}

						for(Competiteur competiteurFichier:cl.getListCompetiteur()){
							boolean competiteurtrouve=false;
							for(com.taekwondo.webservice.Competiteur comp:listCompetiteur){
								if((comp.getNom().equals(competiteurFichier.getNom()))){					
									competiteurtrouve=true;
								}
							}
							if(competiteurtrouve==false){
								listeCompetiteurNonMaj.add(competiteurFichier);
							}
						}
						if(clubtrouve==false && !(listeClubNonMaj.contains(cl))){				
							listeClubNonMaj.add(cl);					
						}	
					}
					for(Categorie cat:Controleur.competitionEnCours.getListeCategorie()){
						categorietrouve=false;				
						for(com.taekwondo.webservice.Categorie catWeb:listeCategorie){		
							if(cat.getNom().equals(catWeb.getNom())){						
								categorietrouve=true;

							}
						}
						if(categorietrouve==false && !(listeCategorieNonMaj.contains(cat))){				
							listeCategorieNonMaj.add(cat);					
						}				
					}



					int result =JOptionPane.showConfirmDialog(Fenetre.PanelSynchronisation,"Vous allez ajouter : \n "+listeClubNonMaj.size()+" Club(s) \n"+listeCategorieNonMaj.size()+" Categorie(s) \n"+listeCompetiteurNonMaj.size()+" Combattant(s) \n"+"Et supprimer :"+GestionBdd.competiteurPasListWeb(Controleur.getCompetitionEnCours().getNomCompetition()).size()+"Competiteur","Attention",JOptionPane.WARNING_MESSAGE);	
					if (result == JOptionPane.OK_OPTION) {		
						for(Club cl:listeClubNonMaj){
							listModelClub.addElement(cl.getNom());
						}
						for(Categorie cat:listeCategorieNonMaj){
							listModelCategorie.addElement(cat.getNom());
						}
						for(Competiteur c:listeCompetiteurNonMaj){
							listModelCompetiteur.addElement(c.getNom() + " "+c.getPrenom() + " de " + c.getClub().getNom());
						}

						if(GestionBdd.verificationDonneesBDDetAjout()){
							if(GestionBdd.competiteurPasListWeb(Controleur.getCompetitionEnCours().getNomCompetition()).size()>0){
								listModelCompetiteur.addElement("***********Suppression************");
							}

							for(Integer id:GestionBdd.competiteurPasListWeb(Controleur.getCompetitionEnCours().getNomCompetition())){
								for(com.taekwondo.webservice.Competiteur comp:listCompetiteur){
									if(id==comp.getId()){
										listModelCompetiteur.addElement(comp.getNom() + " "+comp.getPrenom() );
									}
								}
							}
						}
		
					} else {
						System.out.println("Cancelled");
					}

					GestionBdd.service.closeBdd();
				}
			}		
		});
		boutonConfirmer.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!(listeClubNonMaj.size()==0) || !(listeCategorieNonMaj.size()==0) || !(listeCompetiteurNonMaj.size()==0)){
					int result =JOptionPane.showConfirmDialog(Fenetre.PanelSynchronisation,"vous etes sur le point d'envoyer des données au serveur","Attention",JOptionPane.WARNING_MESSAGE);	
					if (result == JOptionPane.OK_OPTION) {
						GestionBdd.envoieAjoutACompetition(listeClubNonMaj,listeCategorieNonMaj,listeCompetiteurNonMaj);
					} else {
						System.out.println("Cancelled");
					}
				}
				else{
					JOptionPane.showMessageDialog(Fenetre.PanelSynchronisation,"Il n'y a rien à envoyer","Erreur",JOptionPane.ERROR_MESSAGE);		
				}

			}});
	}



}	