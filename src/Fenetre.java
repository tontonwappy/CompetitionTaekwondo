

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
	JMenuItem menuAccueil,menuItem,club,competiteur,categorie,boutonCategorie,genererList,sauvegarde,chargement;
	JRadioButtonMenuItem rbMenuItem;
	JCheckBoxMenuItem cbMenuItem;
	JMenu accueil,gestion,afficher,generer,gestionSauvegarde;
	JPanel currentPanel;

	public Fenetre(){
		this.setTitle("Gestionnaire de  compétitions");
		this.setSize(1100, 700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             
		this.setVisible(true);	 
		menuBar = new JMenuBar();

		accueil = new JMenu("Accueil ");
		menuBar.add(accueil);
		menuAccueil = new JMenuItem("Accueil");
		accueil.add(menuAccueil);

		gestion = new JMenu("Gestion ");
		menuBar.add(gestion);
		generer = new JMenu("Génerer ");
		menuBar.add(generer);
		gestionSauvegarde = new JMenu("Sauvegarde ");
		menuBar.add(gestionSauvegarde);
		club = new JMenuItem("Clubs");
		competiteur = new JMenuItem("Compétiteurs");
		categorie = new JMenuItem("Catégories");
		genererList = new JMenuItem("generer la liste des combats / clubs");
		gestion.add(club);
		gestion.add(competiteur);
		gestion.add(categorie); 
		generer.add(genererList);
		afficher = new JMenu("Afficher");
		menuBar.add(afficher);
		boutonCategorie = new JMenuItem("Afficher tous les compétiteurs");
		afficher.add(boutonCategorie);	     
		sauvegarde = new JMenuItem("Sauvegarder");
		gestionSauvegarde.add(sauvegarde);
		//chargement = new JMenuItem("Chargement");
		//gestionSauvegarde.add(chargement);
		this.setJMenuBar(menuBar);
		final PanelAjoutClub panelAjoutClub = new PanelAjoutClub();
		final PanelAccueil panelAccueil = new PanelAccueil();
		final PanelAjoutCategorie panelAjoutCategorie = new PanelAjoutCategorie();
		final PanelAjoutCompetiteur panelAjoutCompetiteur = new PanelAjoutCompetiteur();
		final PanelGenererList  panelGenererList= new PanelGenererList();
		final PanelAfficherCategorieDetail panelAfficherCompetiteur = new PanelAfficherCategorieDetail();
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
