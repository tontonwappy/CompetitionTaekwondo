

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;


public class Fenetre extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem menuAccueil,menuItem,club,competiteur,categorie,boutonCategorie,genererList,sauvegarde,chargement,connection,synchronisation,AjoutetsuppWeb;
	JRadioButtonMenuItem rbMenuItem;
	JCheckBoxMenuItem cbMenuItem;
	JMenu accueil,gestion,afficher,generer,gestionSauvegarde,BDD;
	JPanel currentPanel;
	final static PanelAjoutClub panelAjoutClub = new PanelAjoutClub();
	final static PanelAccueil panelAccueil = new PanelAccueil();
	final static PanelAjoutCategorie panelAjoutCategorie = new PanelAjoutCategorie();
	final static PanelAjoutCompetiteur panelAjoutCompetiteur = new PanelAjoutCompetiteur();
	final static PanelGenererList  panelGenererList= new PanelGenererList();
	final static PanelAfficherCategorieDetail panelAfficherCompetiteur = new PanelAfficherCategorieDetail();
	final static PanelConnectionBDD panelCOnnectionBdd = new PanelConnectionBDD();
	final static PanelSynchronisation PanelSynchronisation = new PanelSynchronisation();
	final static PanelAjoutEtSupp PanelAjoutEtSupp = new PanelAjoutEtSupp();


	public Fenetre(){
		this.setTitle("Gestionnaire de  compétitions");
		this.setSize(1100, 700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             
		this.setVisible(true);	 
		menuBar = new JMenuBar();

		/*************MENU******************/
		accueil = new JMenu("Accueil ");
		menuBar.add(accueil);
		gestion = new JMenu("Gestion ");
		menuBar.add(gestion);	
		generer = new JMenu("Génerer ");
		menuBar.add(generer);		
		afficher = new JMenu("Afficher");
		menuBar.add(afficher);		
		gestionSauvegarde = new JMenu("Sauvegarde ");
		menuBar.add(gestionSauvegarde);		
		BDD = new JMenu("Base de donnee ");
		menuBar.add(BDD);	

		/*************ITEM******************/
		menuAccueil = new JMenuItem("Accueil");	
		club = new JMenuItem("Clubs");
		competiteur = new JMenuItem("Compétiteurs");
		categorie = new JMenuItem("Catégories");	
		genererList = new JMenuItem("generer la liste des combats / clubs");	
		boutonCategorie = new JMenuItem("Afficher tous les compétiteurs");	
		sauvegarde = new JMenuItem("Sauvegarder");
		connection = new JMenuItem("connection à la Base de données");
		synchronisation = new JMenuItem("Ajout/Récupération de compétition");
		AjoutetsuppWeb = new JMenuItem("Mise à jour de la base de données");

		/*************ADD ITEM TO MENU******************/
		accueil.add(menuAccueil);
		gestion.add(club);
		gestion.add(competiteur);
		gestion.add(categorie); 
		generer.add(genererList);
		afficher.add(boutonCategorie);	     
		gestionSauvegarde.add(sauvegarde);
		BDD.add(connection);
		BDD.add(synchronisation);
		BDD.add(AjoutetsuppWeb);

		//chargement = new JMenuItem("Chargement");
		//gestionSauvegarde.add(chargement);
		this.setJMenuBar(menuBar);


		final String erreur="Selectionner une compétition";
		final String titreerreur="erreur";
		setPanel(panelAccueil);
		currentPanel=panelAccueil;



		menuAccueil.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				setPanel(panelAccueil);
				currentPanel=panelAccueil;
			}
		});


		club.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(Controleur.fichierSelection==false){
					JOptionPane.showMessageDialog(panelAfficherCompetiteur,erreur,titreerreur,JOptionPane.ERROR_MESSAGE);		
				}
				else{
					setPanel(panelAjoutClub);
					currentPanel=panelAjoutClub;
				}
			}
		});

		competiteur.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(Controleur.fichierSelection==false){
					JOptionPane.showMessageDialog(panelAfficherCompetiteur,erreur,titreerreur,JOptionPane.ERROR_MESSAGE);		
				}
				else{
					setPanel(panelAjoutCompetiteur);
					currentPanel=panelAjoutCompetiteur;
				}
			}
		});

		categorie.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(Controleur.fichierSelection==false){
					JOptionPane.showMessageDialog(panelAfficherCompetiteur,erreur,titreerreur,JOptionPane.ERROR_MESSAGE);		
				}
				else{
					setPanel(panelAjoutCategorie);
					currentPanel=panelAjoutCategorie;
				}
			}
		});

		genererList.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(Controleur.fichierSelection==false){
					JOptionPane.showMessageDialog(panelAfficherCompetiteur,erreur,titreerreur,JOptionPane.ERROR_MESSAGE);		
				}
				else{
					setPanel(panelGenererList);
					currentPanel=panelGenererList;
				}
			}
		});

		sauvegarde.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(Controleur.fichierSelection==false){
					JOptionPane.showMessageDialog(panelAfficherCompetiteur,erreur,titreerreur,JOptionPane.ERROR_MESSAGE);		
				}
				else{
					Controleur.sauvegarde();
				}
			}
		});

		connection.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{		
				setPanel(panelCOnnectionBdd);
				currentPanel=panelCOnnectionBdd;			
			}
		});
		synchronisation.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{	
				if(GestionBdd.service==null){
					JOptionPane.showMessageDialog(panelAfficherCompetiteur,"connectez vous avant",titreerreur,JOptionPane.ERROR_MESSAGE);		
				}
				else{
					setPanel(PanelSynchronisation);
					currentPanel=PanelSynchronisation;		
				}
			}
		});


		AjoutetsuppWeb.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{	
				if(Controleur.fichierSelection==false ){
					JOptionPane.showMessageDialog(panelAfficherCompetiteur,erreur,titreerreur,JOptionPane.ERROR_MESSAGE);		
				}
				else if(GestionBdd.service==null){
					JOptionPane.showMessageDialog(panelAfficherCompetiteur,"connectez vous avant",titreerreur,JOptionPane.ERROR_MESSAGE);		
				}
				else{
					setPanel(PanelAjoutEtSupp);
					currentPanel=PanelAjoutEtSupp;	
				}
			}
		});

		boutonCategorie.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(Controleur.fichierSelection==false){
					JOptionPane.showMessageDialog(panelAfficherCompetiteur,erreur,titreerreur,JOptionPane.ERROR_MESSAGE);		
				}
				else{
					Controleur.refreshListModelCategorie(PanelAfficherCategorieDetail.listModel);
					if(PanelAjoutCategorie.ageManquant.size()==0){
						setPanel(panelAfficherCompetiteur);
						currentPanel=panelAfficherCompetiteur;
					}
					else{
						JOptionPane.showMessageDialog(panelAfficherCompetiteur,"Avant de pouvoir afficher les categories veuillez remplir els categories manquante : ","Titre : exception",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		this.setVisible(true);
	}

	private void setPanel(JPanel panel){
		if(currentPanel!=null){
			this.remove(currentPanel);	
		}
		this.add(panel);
		this.revalidate();
		this.repaint();

	}



}
