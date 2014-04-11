import java.awt.BorderLayout;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
//import java.awt.Color;
//import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.taekwondo.webservice.*;
import com.taekwondo.webservice.Categorie;
import com.taekwondo.webservice.Competition;


public class PanelAccueil extends JPanel {
	/**
	 * 1 ere fenetre lors de l'ouverture du logiciel
	 */
	private static final long serialVersionUID = 1L;
	JLabel labelInfo=new JLabel();
	JLabel labelFichier=new JLabel("Séléctionner le fichier à afficher");
	JLabel labelCreer=new JLabel("Si aucun fichier n'est présent ou si vous voulez créer une compétition");
	JButton boutonCreerCategorie=new JButton("Creer une compétition");
	JButton boutonSynchronisation=new JButton("Synchroniser les données");

	JPanel contenuCentre = new JPanel();
	static JComboBox<String> combo = new JComboBox<String>();


	PanelAccueil(){

		combo.addItem("");
		this.setLayout(new BorderLayout());
		JPanel panel=new JPanel();
		labelInfo.setText("Outil de gestion de l'USSE taekwondo spécial Arbre de Noël");
		panel.add(labelInfo);	
		this.add(panel,BorderLayout.NORTH);
		this.add(contenuCentre,BorderLayout.CENTER);	

		contenuCentre.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		contenuCentre.add(labelCreer,gbc);
		gbc.insets.left = 15;
		gbc.gridx = 1;
		gbc.gridy = 0;
		contenuCentre.add(boutonCreerCategorie,gbc);
		gbc.insets.top = 15;
		gbc.gridx = 0;
		gbc.gridy = 1;
		contenuCentre.add(labelFichier,gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		contenuCentre.add(combo,gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		contenuCentre.add(boutonSynchronisation,gbc);

		Controleur.remplirComboFichier();

		boutonCreerCategorie.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				display();
			}
		});


		boutonSynchronisation.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				//GestionBdd.service.connectionBdd();
				//System.out.println(GestionBdd.competiteurPasListWeb(Controleur.competitionEnCours.getNomCompetition()));
				//GestionBdd.service.closeBdd();
				
				
				//ClientLourdImplService helloWorldService = new ClientLourdImplService();  
				//final ClientLourd service = helloWorldService.getClientLourdImplPort(); 
				//service.ajoutCompetition("aaaaa", "bbbbcccc");
				//service.ajoutBddCategorie("comp2","cadet", 13, 15);
				// service.ajoutClub("comp2", "lokoa");
				/*************************************************/
				//		        List<Competition> list= service.recuperationListeCompetition();
				//		        for(Competition c : list){
				//		        	 System.out.println(c.getId()+c.getNomCompetition()+c.getDateCompetition());
				//		        }
				/*************************************************/
				//		        List<com.taekwondo.webservice.Club> list= service.recuperationListeClub(10);
				//		        for(com.taekwondo.webservice.Club c : list){
				//		        	 System.out.println(c.getNom());
				//		        }
				/*************************************************/
				//		        List<com.taekwondo.webservice.Competiteur> list= service.recuperationListeCompetiteur(3);
				//		        for(com.taekwondo.webservice.Competiteur c : list){
				//		        	 System.out.println(c.getNom());
				//		        }	
				/*************************************************/
				//		        List<com.taekwondo.webservice.Categorie> list= service.recuperationListeCategorie(11);
				//		        for(Categorie c : list){
				//		        	 System.out.println(c.getNom());
				//		        }	      
			}
		});

		combo.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				//La methode se déclanche à chaque fois qu'on ajoute un objet
				//on s'assure que la méthode ne se déclanche pas si il n'y a rien
				if(!arg0.getItem().toString().equals("")){
					if(arg0.getStateChange() == ItemEvent.SELECTED) {
						Controleur.fichierSelection=true;
						Controleur.setCompetitionEnCours(Deserialisation.rechercheCompetitionDansFichier(arg0.getItem().toString()));
						Controleur.changementFichier();			
					}
				}
			}
		});


	}



	private static void display() {
		JTextField field1 = new JTextField();
		JTextField field2 = new JTextField();
		JPanel panel = new JPanel(new GridLayout(0, 1));
		panel.add(new JLabel("Nom de la compétition"));
		panel.add(field1);
		panel.add(new JLabel("date de la compétition (jj/mm/aaaa)"));
		panel.add(field2);
		int result = JOptionPane.showConfirmDialog(null, panel, "Test",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION) {		
			combo.removeAllItems();
			Controleur.ajoutCompetition(field1.getText(), field2.getText());
			Controleur.remplirComboFichier();
		} else {
			System.out.println("Cancelled");
		}
	}
}
