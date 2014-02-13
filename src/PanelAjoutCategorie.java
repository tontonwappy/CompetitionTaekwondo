import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;



public class PanelAjoutCategorie extends JPanel {

	private static final long serialVersionUID = 1L;
	JButton boutonEnvoie= new JButton("OK");
	JButton boutonSupprimer= new JButton("Supprimer");
	JLabel titre = new JLabel("Gestion des categories");
	JLabel labelAjoutCategorie = new JLabel("Nom de la categorie à ajouter");
	JLabel labelAgeMini = new JLabel("Age minimum de la categorie");
	JLabel labelAgeMaxi = new JLabel("Age maximum de la categorie");
	static DefaultListModel<String> listModel = new DefaultListModel<String>();
	JList<String> list = new JList<String>(listModel); 
	final JTextField nomCategorie = new JTextField();
	final JTextField ageMiniField = new JTextField();
	final JTextField ageMaxiField = new JTextField();
	static DefaultTableModel model = new DefaultTableModel(); //définir le tableau
	JTable tableau = new JTable(model);
	int ligneSelectionTableau;
	JLabel labelAlerte = new JLabel("");
	public static 	ArrayList<Integer> ageManquant=new ArrayList<>();

	public final static  JPanel contenuBas = new JPanel();
	PanelAjoutCategorie(){

		/** Initialisation des panels **/   
		this.setLayout(new BorderLayout());
		JPanel contenuCentre = new JPanel();
		JPanel contenuDroite = new JPanel();
		final JPanel contenuHaut = new JPanel();
		contenuDroite.setBackground(Color.gray);
		contenuCentre.setBackground(new Color(193,205,205));
		this.add(contenuHaut,BorderLayout.NORTH);
		this.add(contenuCentre,BorderLayout.CENTER);	
		this.add(contenuDroite,BorderLayout.EAST);	
		this.add(contenuBas,BorderLayout.SOUTH);	
		final JPanel contenuTableau = new JPanel();
		contenuHaut.add(titre);
		contenuDroite.add(contenuTableau);
		contenuTableau .setLayout(new BorderLayout());
		contenuTableau.add(tableau.getTableHeader(),BorderLayout.NORTH);
		contenuTableau.add(tableau,BorderLayout.CENTER);
		model.addColumn("Nom Categorie");
		model.addColumn("Age Min");
		model.addColumn("Age Max");
		tableau.getColumnModel().getColumn(0).setPreferredWidth(180);
		/** Placement des composants : titre et bouton **/
		contenuCentre.add(labelAjoutCategorie);		
		nomCategorie.setColumns(20);
		ageMaxiField.setColumns(2);
		ageMiniField.setColumns(2);
		contenuCentre.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;	
		contenuCentre.add(labelAjoutCategorie,gbc);		
		nomCategorie.setColumns(20);
		ageMaxiField.setColumns(2);
		ageMiniField.setColumns(2);
		gbc.gridx = 1;
		gbc.gridy = 0;	
		contenuCentre.add(nomCategorie,gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.insets.top = 15;
		contenuCentre.add(labelAgeMini,gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		contenuCentre.add(ageMiniField,gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		contenuCentre.add(labelAgeMaxi,gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		contenuCentre.add(ageMaxiField,gbc);
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.insets.top = 15;
		contenuCentre.add(boutonEnvoie,gbc);
		gbc.gridx = 1;
		gbc.gridy = 4;
		contenuCentre.add(boutonSupprimer,gbc);

		final JLabel infoMixte = new JLabel("test");
		contenuBas.add(infoMixte);

		tableau.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				ligneSelectionTableau = tableau.getSelectedRow();
			}
		});

		boutonEnvoie.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(ageManquant.size()>0){
					Controleur.verifAge(ageMiniField.getText(), ageMaxiField.getText());
					}

				String msgalerte = "Veuillez recreer les categories pour les ages suivant :";
				for(int age :ageManquant){
					msgalerte=msgalerte+Integer.toString( age)+" ;"; 
				}

				if(ageManquant.size()>0)
					labelAlerte.setText(msgalerte);
				else{
					labelAlerte.setText("");
				}

				if(!nomCategorie.getText().equals("") && !ageMiniField.getText().equals("") && !ageMaxiField.getText().equals("")){
					if(Integer.parseInt(ageMiniField.getText())>Integer.parseInt(ageMaxiField.getText()) ){
						JOptionPane.showMessageDialog(getParent(),
								"les categories sont incorrects");		
						return;
					}

					if(Controleur.VerifCreationCategorie(Integer.parseInt(ageMiniField.getText()), Integer.parseInt(ageMaxiField.getText()))){
						Categorie nouvelleCategorie=Controleur.ajoutNvlCategorie(nomCategorie.getText(), Integer.parseInt(ageMiniField.getText()),Integer.parseInt(ageMaxiField.getText()));
						model.addRow(new Object[]{nomCategorie.getText(),Integer.parseInt(ageMiniField.getText()),Integer.parseInt(ageMaxiField.getText())});					
						nomCategorie.setText("");
						ageMaxiField.setText("");
						ageMiniField.setText("");
						listModel.addElement(nouvelleCategorie.getNom());
						Controleur.ajoutToutCompCategorie(nouvelleCategorie);		
					}
					else{
						JOptionPane.showMessageDialog(getParent(),
								"La categorie est incorrecte");		
						return;
					}
				}
				else {
					JOptionPane.showMessageDialog(getParent(),
							"les champs ne sont pas tous remplis");	
				}
				Controleur.refreshCheckBox(contenuBas);
			}
		});

		/** Met l'attribut de la categorie de chaque compétiteurs à null quand on supprime une catégorie**/
		boutonSupprimer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				Controleur.supprimCombattantCategorie(Competition.listeCategorie.get(ligneSelectionTableau).getNom());
				Controleur.ajoutAgeManquantDansCategorie();
				/** Message d'alerte quand il manque des catégories associé aux attributs de chaques compétiteurs**/
				labelAlerte.setForeground(Color.RED);
				String msgalerte = "Veuillez recreer les categories pour les ages suivant :";
				ageManquant=Controleur.getAgeManquantDsCategorie();
				for(int age :Controleur.getAgeManquantDsCategorie()){
					msgalerte=msgalerte+Integer.toString( age)+" ;"; 
				}
				if(ageManquant.size()>0)
					labelAlerte.setText(msgalerte);
				contenuHaut.add(labelAlerte);
				int numeroligne=ligneSelectionTableau;
				Controleur.supprimCategorie(numeroligne);
				model.removeRow(ligneSelectionTableau);	
				Controleur.refreshCheckBox(contenuBas);
			}
		});


	}


}

