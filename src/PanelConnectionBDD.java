import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;









import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.taekwondo.webservice.ClientLourd;
import com.taekwondo.webservice.ClientLourdImplService;


public class PanelConnectionBDD extends JPanel {
	private static final long serialVersionUID = 1L;

	JButton boutonEnvoie= new JButton("OK");
	JButton boutonSynchro= new JButton("Synchroniser avec la Base");
	JLabel titre = new JLabel("Connection à la base de donnée");
	JTextArea zonetext=new JTextArea();



	PanelConnectionBDD(){

		/** Initialisation des panels **/   
		this.setLayout(new BorderLayout());
		JPanel contenuHaut = new JPanel();
		JPanel contenuCenter = new JPanel();
		this.add(contenuHaut,BorderLayout.NORTH);	
		
		DefaultListModel<String> listModelCompetiteur = new DefaultListModel<String>();
		JList<String> listCompetiteur = new JList<String>(listModelCompetiteur);

		this.add(contenuCenter,BorderLayout.CENTER);	
		/*Couleur*/

		contenuCenter.setBackground(new Color(193,205,205));

		/** Placement des composants : titre et bouton **/
		contenuHaut.add(titre);		


		zonetext.setText("En attente de connection à la base de donnée");
		
		
		listCompetiteur.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		listCompetiteur.setVisibleRowCount(-1);
		listCompetiteur.setLayoutOrientation(JList.VERTICAL); 
		JScrollPane listScroller2 = new JScrollPane(listCompetiteur);
		listScroller2.setPreferredSize(new Dimension(200,250));

		contenuHaut.add(boutonEnvoie);
		contenuHaut.add(boutonSynchro);
		contenuHaut.add(zonetext);
		contenuCenter.add(listScroller2);
		
		
		
		

		boutonEnvoie.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				GestionBdd.initConnection();
				if(!(GestionBdd.service==null)){
					zonetext.setText("connection reussie");
					GestionBdd.remplirTabSynchCompetition();
				}
				else{
					zonetext.setText("echec de la connection à la base de donnée");
				}
			}
		});

		boutonSynchro.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				System.out.println(Controleur.getCompetitionEnCours());
				
				if(Controleur.getCompetitionEnCours().getNomCompetition()==null){
					JOptionPane.showMessageDialog(Fenetre.PanelSynchronisation,"Selectionner une competition dans la page d'accueil","Erreur",JOptionPane.ERROR_MESSAGE);	
				}
				
				else if((GestionBdd.service==null) ){
					JOptionPane.showMessageDialog(Fenetre.PanelSynchronisation,"Connectez vous avant","Erreur",JOptionPane.ERROR_MESSAGE);	
					
				}
				else{
					
					GestionBdd.verificationDonneesBDDetAjout();
				}
				
				//GestionBdd.ajoutCompetiteursDeLaBdd(Controleur.competitionEnCours.getNomCompetition());
			
			}

		});
	}

}	