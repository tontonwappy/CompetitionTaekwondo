
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class PanelAjoutCompetiteur extends JPanel  {
	JButton boutonEnvoie= new JButton("OK");
	JButton boutonSupprimer= new JButton("Supprimer");
	JButton boutonListe= new JButton("Importer une liste");
	JLabel titre = new JLabel("Gestion des compétiteurs");
	JLabel labelNom = new JLabel("Nom :");
	JLabel labelPrenom = new JLabel("Prenom :");
	JLabel labeldateNaissance = new JLabel("Age :");
	JLabel labelAge = new JLabel("(jj/mm/aaaa)");
	JLabel labelClub = new JLabel("Choisissez un club");
	public JComboBox<String> comboClub = new JComboBox<String>();
	final JTextField dateNaissance = new JTextField();
	final JTextField nom = new JTextField();
	final JTextField prenom = new JTextField();
	final JTextField agePersonne = new JTextField();
	DefaultTableModel model = new DefaultTableModel();
	JTable tableau = new JTable(model);
	private JComboBox<String> comboGenre = new JComboBox<String>(); //définir fille ou garçon
	int age;
	int anneeNaissance;
	int ligneSelectionTableau;
	private LinkedList<Competiteur> listCompetiteurTableau=new LinkedList<Competiteur>();
	static DefaultListModel<String> listModel = new DefaultListModel<String>();
	JList<String> list = new JList<String>(listModel); 
	private  static JScrollPane areaScrollPane=new JScrollPane();

	private static final long serialVersionUID = 1L;
	PanelAjoutCompetiteur(){
		//rend le panel focusable pour utiliser les keylisnters
		this.setFocusable(true);
		this.requestFocus();

		this.setLayout(new BorderLayout());
		JPanel contenuWest = new JPanel();
		contenuWest.setBackground(new Color(193,205,205));
		JPanel contenuHaut = new JPanel();
		JPanel contenuCenter = new JPanel();
		contenuCenter.setBackground(Color.gray);
		JPanel contenuBas = new JPanel();
		JPanel contenuIN = new JPanel();
		JPanel contenuINHaut = new JPanel();
		contenuINHaut.setBackground(Color.green);
		JPanel contenuINCenter = new JPanel();
		contenuINHaut.setBackground(new Color(193,205,205));
		final JPanel contenuTableau = new JPanel();
		final JLabel info = new JLabel();
		final JPanel contenuTableauBot = new JPanel();
		JPanel case1 = new JPanel();
		JPanel case2 = new JPanel();
		JPanel case3 = new JPanel();
		JPanel case4 = new JPanel();	
		JPanel case5 = new JPanel();
		contenuIN .setLayout(new BorderLayout());
		contenuTableau .setLayout(new BorderLayout());
		contenuCenter.setBorder(BorderFactory.createLineBorder(Color.black));
		contenuINHaut.setBorder(BorderFactory.createLineBorder(Color.black));
		case1.setBorder(BorderFactory.createLineBorder(Color.black));
		case2.setBorder(BorderFactory.createLineBorder(Color.black));
		case3.setBorder(BorderFactory.createLineBorder(Color.black));
		case4.setBorder(BorderFactory.createLineBorder(Color.black));
		case5.setBorder(BorderFactory.createLineBorder(Color.black));
		this.add(contenuWest,BorderLayout.WEST);
		this.add(contenuHaut,BorderLayout.NORTH);	
		this.add(contenuCenter,BorderLayout.CENTER);	
		this.add(contenuBas,BorderLayout.SOUTH);	
		contenuCenter.add(contenuIN,BorderLayout.CENTER);
		contenuIN.add(contenuINHaut,BorderLayout.NORTH);
		contenuIN.add(contenuINCenter,BorderLayout.CENTER);
		contenuINHaut.add(case1);
		contenuINHaut.add(case2);
		contenuINHaut.add(case3);
		contenuINHaut.add(case4);
		contenuTableau.add(tableau.getTableHeader(),BorderLayout.NORTH);
		contenuTableau.add(tableau,BorderLayout.CENTER);
		contenuTableau.add(contenuTableauBot,BorderLayout.SOUTH);
		contenuTableauBot.add(info);

		areaScrollPane = new JScrollPane(contenuTableau);
		areaScrollPane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		areaScrollPane.setPreferredSize(new Dimension(550, 400));
		contenuINCenter.add(areaScrollPane);	

		//Ajout du genre
		comboGenre.addItem("H");
		comboGenre.addItem("F");

		// Ajouter des colonnes au tableau
		model.addColumn("Nom");
		model.addColumn("Prenom");
		model.addColumn("Age");
		model.addColumn("Année de naissance");
		model.addColumn("Genre");
		model.addColumn("Categorie");
		model.addColumn("Club");

		//Liste de club
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setVisibleRowCount(-1);
		list.setLayoutOrientation(JList.VERTICAL); 
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(200,250));
		contenuWest.add(listScroller);

		/** Placement des composants : titre et bouton **/
		contenuHaut.add(titre);		
		nom.setColumns(10);
		prenom.setColumns(10);
		agePersonne.setColumns(2);
		dateNaissance.setColumns(4);
		case1.add(labelNom);
		case1.add(nom);
		case2.add(labelPrenom);
		case2.add(prenom);
		case3.add(labeldateNaissance);
		case3.add(agePersonne);
		case3.add(new JLabel("ans OU  année naissance"));
		case3.add(dateNaissance);
		case4.add(new JLabel("Genre"));
		case4.add(comboGenre);
		contenuBas.add(boutonEnvoie);
		contenuBas.add(boutonSupprimer);
		contenuBas.add(boutonListe);	


		tableau.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				ligneSelectionTableau = tableau.getSelectedRow();

			}
		});

		new Controleur.threadCheckListpanelCompetiteur().start();	

		list.addMouseListener(new MouseListener() {

			//Recherche le club dans la liste grace au clique puis remplit la liste de combat
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				areaScrollPane.repaint();
				Club selectionClub=Controleur.rechercheClub(list.getSelectedValue());
				int i=Controleur.remplirCombattantPanelCompetiteur(selectionClub,model);		
				info.setText("Nombre  :"+i);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}


		});

		boutonEnvoie.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{

				if(nom.getText().equals("")){
					JOptionPane.showMessageDialog(getParent(),
							"Nom manquant");				
				}
				else if(prenom.getText().equals("")){
					JOptionPane.showMessageDialog(getParent(),
							"Prenom manquant");				
				}
				else if(((dateNaissance.getText().equals("")) && (agePersonne.getText().equals("")))  ){
					JOptionPane.showMessageDialog(getParent(),
							"Age ou Date de naissance manquant");				
				}

				else if (  !agePersonne.getText().matches("[0-9]*" )){
					JOptionPane.showMessageDialog(getParent(),
							"l'age doit être un nombre");				
				}
				else if ( !dateNaissance.getText().matches("[0-9]*" )){
					JOptionPane.showMessageDialog(getParent(),
							"l'age doit être un nombre");				
				}

				else if ( list.getSelectedValue()==null){
					JOptionPane.showMessageDialog(getParent(),
							"choisissez un club");				
				}
				else{
					if(!dateNaissance.getText().equals("") && !agePersonne.getText().equals("")){
						if(Integer.parseInt(dateNaissance.getText())<1950){
							JOptionPane.showMessageDialog(getParent(),
									"entez une date à 4 chiffre supérieur a 1950");		
							return;
						}
						int age=Controleur.calculAge(Integer.parseInt(dateNaissance.getText()));

						if(age!=Integer.parseInt(dateNaissance.getText())){
							JOptionPane.showMessageDialog(getParent(),
									"la date de naissance et l'age ne concorde pas");		
							return;
						}
					}	
					if(!dateNaissance.getText().equals("")){
						age=Controleur.calculAge(Integer.parseInt(dateNaissance.getText()));
						anneeNaissance=Integer.parseInt(dateNaissance.getText());
					}
					else if(!agePersonne.getText().equals("")){
						age=Integer.parseInt(agePersonne.getText());
						anneeNaissance=Controleur.calculAnnee(Integer.parseInt(agePersonne.getText()));
					}
					for(Club c : Controleur.listClub){
						if(c.getNom().equals(list.getSelectedValue())){
							if (!Controleur.verifInsertionCategorie(age)){
								JOptionPane.showMessageDialog(getParent(),
										"L'age ne correspond pas à une categorie, veuillez en créer une");		
								return;
							}
							Competiteur nouveauCompetiteur=new Competiteur(nom.getText(),prenom.getText(),age,comboGenre.getSelectedItem().toString(),c);
							Controleur.inserCompetiteur(nouveauCompetiteur,c);
							Categorie insertionCategorieCombattant= Controleur.inserCombattantCategorie(nouveauCompetiteur);
							if (insertionCategorieCombattant==null){
								JOptionPane.showMessageDialog(getParent(),
										"Erreur lors de l'insertion dans la categorie");		
								return;
							}
							listCompetiteurTableau.add(nouveauCompetiteur);
							model.addRow(new Object[]{nom.getText(),prenom.getText(),age,anneeNaissance,comboGenre.getSelectedItem().toString(),insertionCategorieCombattant.getNom(),c.getNom()});
							break;
						}	
					}
					nom.setText("");
					prenom.setText("");
					agePersonne.setText("");
					dateNaissance.setText("");
				}
			}
		});

		boutonSupprimer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{				
				if(!(list.getSelectedValue()==null)){				
					Club recupClub=Controleur.rechercheClub(list.getSelectedValue());	
					Controleur.supprimCompetiteurClub(recupClub, ligneSelectionTableau);
					model.removeRow(ligneSelectionTableau);	
				}
				else{
					JOptionPane.showMessageDialog(getParent(),
							"Selectionne d'abord le club");				
				}
			}
		});

		boutonListe.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				Controleur.importDonnee("Competiteur");
			}
		});
	}
	






}	