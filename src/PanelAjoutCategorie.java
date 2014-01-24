import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;



import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
		
		/*
		contenuCentre.add(nomCategorie);
		contenuCentre.add(labelAgeMini);
		contenuCentre.add(ageMiniField);
		contenuCentre.add(labelAgeMaxi);
		contenuCentre.add(ageMaxiField);
		contenuCentre.add(boutonEnvoie);
		contenuCentre.add(boutonSupprimer);*/
		
		
		
		
		

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

					for(int i=Integer.parseInt(ageMiniField.getText());i<=Integer.parseInt(ageMaxiField.getText());i++){
						int x=0;
						int indexAge=0;
						boolean ageTrouve=false;
						for(int age:ageManquant){
							if(age==i ){
								ageTrouve=true;
								indexAge=x;
							}
							x=x+1;
						}

						if(ageTrouve)
							ageManquant.remove(indexAge);

					}
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
						Categorie nouvelleCategorie=new Categorie(nomCategorie.getText(), Integer.parseInt(ageMiniField.getText()),Integer.parseInt(ageMaxiField.getText()));
						Controleur.listeCategorie.add(nouvelleCategorie);					
						model.addRow(new Object[]{nomCategorie.getText(),Integer.parseInt(ageMiniField.getText()),Integer.parseInt(ageMaxiField.getText())});					
						nomCategorie.setText("");
						ageMaxiField.setText("");
						ageMiniField.setText("");
						listModel.addElement(nouvelleCategorie.getNom());
						for(Club club : Controleur.listClub){
							for(Competiteur comp :club.getListCompetiteur()){

								System.out.println(comp.getCategorie());
								if(comp.getCategorie()==null ){
									System.out.println(Controleur.verifAgeDansCategorie(comp.getAge(),nouvelleCategorie));

									if (Controleur.verifAgeDansCategorie(comp.getAge(),nouvelleCategorie)){

										comp.setCategorie(nouvelleCategorie);
									}
								}
							}
						}
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
				refreshCheckBox(contenuBas);
			}

		});

		/** Met l'attribut de la categorie de chaque compétiteurs à null quand on supprime une catégorie**/
		boutonSupprimer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				for(Club cl :Controleur.listClub){
					for(Competiteur comp : cl.getListCompetiteur()){					
						if(comp.getCategorie()!=null){
							System.out.println(comp.getCategorie().getNom()+" "+comp.getNom()+" "+ comp.getPrenom());
							if (comp.getCategorie().getNom().equals(Controleur.listeCategorie.get(ligneSelectionTableau).getNom())){
								System.out.println(comp.getNom()+ comp.getPrenom());
								comp.setCategorie(null);
							}
						}
					}
				}

				/** TEST **/
				ArrayList<Integer> parcourAgeManquant=new ArrayList<Integer>();

				for (Club cl:Controleur.listClub)
				{
					for(Competiteur comp : cl.getListCompetiteur()){
						if(comp.getCategorie()==null){
							if(!testValeurTableau(parcourAgeManquant, comp.getAge())){
								parcourAgeManquant.add(comp.getAge());
							}
						}
					}
				}


				/** Message d'alerte quand il manque des catégories associé aux attributs de chaques compétiteurs**/
				//int ageMin=Controleur.listeCategorie.get(ligneSelectionTableau).getAgeMini();
				//int ageMax=Controleur.listeCategorie.get(ligneSelectionTableau).getAgeMaxi();
				labelAlerte.setForeground(Color.RED);
				String msgalerte = "Veuillez recreer les categories pour les ages suivant :";
				//System.out.println(ageMin);
				//System.out.println(ageMax);
				//for (int i=ageMin;i<=ageMax;i++){
				//	ageManquant.add(i);
				//}
				//ageManquant=parcourAgeManquant;
				ageManquant=triBulleCroissant(parcourAgeManquant);
				for(int age :ageManquant){
					msgalerte=msgalerte+Integer.toString( age)+" ;"; 
				}
				if(ageManquant.size()>0)
					labelAlerte.setText(msgalerte);

				contenuHaut.add(labelAlerte);
				int numeroligne=ligneSelectionTableau;
				Controleur.supprimCategorie(numeroligne);

				model.removeRow(ligneSelectionTableau);	
				refreshCheckBox(contenuBas);
			}
		});



	}


	public static void refreshCheckBox(JPanel panel){
		panel.removeAll();
		panel.repaint();
		JLabel infoMixte=new JLabel("Categorie mixte : ");
		panel.add(infoMixte);	
		for(Categorie cat : Controleur.listeCategorie){			
			final JCheckBox nvlCheckBox=new JCheckBox(cat.getNom());	
			panel.add(nvlCheckBox);		
			if(cat.isCategorieMixt()){
				nvlCheckBox.setSelected(true);
			}

			nvlCheckBox.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Categorie cat = Controleur.retrouveCategorie(nvlCheckBox.getText());
					if(nvlCheckBox.isSelected())
						cat.setCategorieMixt(true);
					else
						cat.setCategorieMixt(false);	
					System.out.println(nvlCheckBox.getText()+" : "+ nvlCheckBox.isSelected());
					System.out.println(cat.isCategorieMixt());

				}});

		}
	}





	private boolean testValeurTableau(ArrayList<Integer> tableau, Integer i){
		for(Integer tab:tableau){
			if(i==tab)
				return true;
		}
		
		return false;

	}
	
	public static ArrayList<Integer> triBulleCroissant(ArrayList<Integer> tableau) {
		int longueur = tableau.size();
		int tampon = 0;
		boolean permut;
 
		do {
			// hypothèse : le tableau est trié
			permut = false;
			for (int i = 0; i < longueur - 1; i++) {
				// Teste si 2 éléments successifs sont dans le bon ordre ou non
				if (tableau.get(i) > tableau.get(i+1)) {
					// s'ils ne le sont pas, on échange leurs positions
					tampon = tableau.get(i);
					tableau.set(i, tableau.get(i+1));
					tableau.set(i+1, tampon) ;
					permut = true;
				}
			}
		} while (permut);
		return tableau;
	}

}
//	private void remplirListCategorie(){
//		
//		for(Categorie cat : Controleur.listeCategorie){
//			//listModel.addElement(cat.getNom());
//			model.addRow(new Object[]{cat.getNom(),cat.getAgeMini(),cat.getAgeMaxi()});					
//		}
//	}
//	
//	public class threadCheckList extends Thread {
//
//		public void run() {
//			
//			while(true){
//				System.out.println("test");
//				//if(listModel.size()!=Controleur.listeCategorie.size()){
//					listModel.removeAllElements();
//					remplirListCategorie();
//					
//				//}
//				try {
//					sleep(1000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}	
//			}
//		}
//
//	}
//}
