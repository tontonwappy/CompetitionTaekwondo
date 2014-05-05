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

import java.util.List;

//import com.taekwondo.exporttomcat.HelloWorld;
//import com.taekwondo.exporttomcat.HelloWorldImplService;


public class PanelSynchronisation extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JButton boutonEnvoie= new JButton("OK");
	JButton boutonSupprimer= new JButton("Supprimer");
	JLabel titre = new JLabel("Synchronisation avec la base de donnée");
	static DefaultListModel<String> listModelCompetitionBdd = new DefaultListModel<String>();
	JList<String> listCompetitionBdd = new JList<String>(listModelCompetitionBdd);
	
	static DefaultListModel<String> listModelCompetitionSer = new DefaultListModel<String>();
	JList<String> listCompetitionSer = new JList<String>(listModelCompetitionSer);
	
	JButton boutonDroite= new JButton(">>");
	JButton boutonGauche= new JButton("<<");

	PanelSynchronisation(){

		/** Initialisation des panels **/   
		this.setLayout(new BorderLayout());
		JPanel contenuHaut = new JPanel();
		JPanel contenuCenter = new JPanel();
		//JPanel panelinDroite = new JPanel();
		this.add(contenuHaut,BorderLayout.NORTH);	
		this.add(contenuCenter,BorderLayout.CENTER);	

		listCompetitionBdd.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		listCompetitionBdd.setVisibleRowCount(-1);
		listCompetitionBdd.setLayoutOrientation(JList.VERTICAL); 
		JScrollPane listScroller = new JScrollPane(listCompetitionBdd);
		listScroller.setPreferredSize(new Dimension(200,250));
		
		listCompetitionSer.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		listCompetitionSer.setVisibleRowCount(-1);
		listCompetitionSer.setLayoutOrientation(JList.VERTICAL); 
		JScrollPane listScrollerCompetitionSer = new JScrollPane(listCompetitionSer);
		listScrollerCompetitionSer.setPreferredSize(new Dimension(200,250));
		
		Controleur.remplirComboFichierSerialize();

		/** Placement des composants : titre et bouton **/
		contenuHaut.add(titre);		

		//contenuCenter.add(boutonEnvoie);
		//contenuCenter.add(boutonSupprimer);
		contenuCenter.add(listScroller);
		contenuCenter.add(boutonGauche);
		contenuCenter.add(boutonDroite);
		contenuCenter.add(listScrollerCompetitionSer);



		
			boutonSupprimer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)

			{}

		});
		boutonGauche.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				boolean envoiecompetition=GestionBdd.envoieCompetitionVersBDD(listCompetitionSer.getSelectedValue());
				if(envoiecompetition==false)
					JOptionPane.showMessageDialog(Fenetre.PanelSynchronisation,"La competition existe deja","Erreur",JOptionPane.ERROR_MESSAGE);				
				else
					GestionBdd.remplirTabSynchCompetition();
				
			}

		});
		boutonDroite.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				GestionBdd.recuperationCompetitionBDD(listCompetitionBdd.getSelectedIndex());							
			}
		});
	}



}	