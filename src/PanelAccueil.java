import java.awt.BorderLayout;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FilenameFilter;

import javax.swing.JButton;
import javax.swing.JComboBox;
//import java.awt.Color;
//import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class PanelAccueil extends JPanel {
	/**
	 * 1 ere fenetre lors de l'ouverture du logiciel
	 */
	private static final long serialVersionUID = 1L;
	JLabel labelInfo=new JLabel();
	JButton boutonCreerCategorie=new JButton("Creer une compétition");

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
		contenuCentre.add(boutonCreerCategorie);
		contenuCentre.add(combo);

		Controleur.remplirComboFichier();

		boutonCreerCategorie.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				display();

			}
		});

		combo.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange() == ItemEvent.SELECTED) {
					Controleur.fichierSelection=true;
					Controleur.setCompetitionEnCours(Deserialisation.rechercheCompetitionDansFichier(arg0.getItem().toString()));
					Controleur.changementFichier();	
					
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
