
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
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
	DefaultTableModel model = new DefaultTableModel(); //définir le tableau
	JTable tableau = new JTable(model);
	private JComboBox<String> comboGenre = new JComboBox<String>(); //définir fille ou garçon
	int age;
	int anneeNaissance;
	int ligneSelectionTableau;
	private LinkedList<Competiteur> listCompetiteurTableau=new LinkedList<Competiteur>();
	DefaultListModel<String> listModel = new DefaultListModel<String>();
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
		//contenuHaut.setBackground(Color.blue);
		JPanel contenuCenter = new JPanel();
		contenuCenter.setBackground(Color.gray);
		JPanel contenuBas = new JPanel();
		//contenuBas.setBackground(Color.orange);
		JPanel contenuIN = new JPanel();
		//contenuIN.setBackground(Color.yellow);
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
		//contenuINHaut.add(case5);
			
		
	
		
		
		contenuTableau.add(tableau.getTableHeader(),BorderLayout.NORTH);
		contenuTableau.add(tableau,BorderLayout.CENTER);
		contenuTableau.add(contenuTableauBot,BorderLayout.SOUTH);
		contenuTableauBot.add(info);
	
	/***/
		
		
		areaScrollPane = new JScrollPane(contenuTableau);
		areaScrollPane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		areaScrollPane.setPreferredSize(new Dimension(550, 400));
		
		contenuINCenter.add(areaScrollPane);	
		
		/****/
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
		//case5.add(labelClub);
		//case5.add(comboClub);

		contenuBas.add(boutonEnvoie);
		contenuBas.add(boutonSupprimer);
		contenuBas.add(boutonListe);	


		tableau.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				ligneSelectionTableau = tableau.getSelectedRow();

			}
		});
//		/* INIT*/
//				Competiteur initCompetiteur = new Competiteur("robinson","crusoe",5,"H",Controleur.rechercheClub("USSE"));
//				Controleur.inserCompetiteur(initCompetiteur,Controleur.rechercheClub("USSE"));	
//				Controleur.inserCombattantCategorie(initCompetiteur);
//				initCompetiteur = new Competiteur("arsen","lupin",6,"H",Controleur.rechercheClub("USSE"));
//				Controleur.inserCompetiteur(initCompetiteur,Controleur.rechercheClub("USSE"));
//				Controleur.inserCombattantCategorie(initCompetiteur);
//				initCompetiteur = new Competiteur("jo","lafiotte",5,"H",Controleur.rechercheClub("USSE"));
//				Controleur.inserCompetiteur(initCompetiteur,Controleur.rechercheClub("USSE"));
//				Controleur.inserCombattantCategorie(initCompetiteur);
//				initCompetiteur = new Competiteur("mario","ludgi",5,"H",Controleur.rechercheClub("USSE"));
//				Controleur.inserCompetiteur(initCompetiteur,Controleur.rechercheClub("USSE"));
//				Controleur.inserCombattantCategorie(initCompetiteur);
//				initCompetiteur = new Competiteur("fiora","fetch",5,"H",Controleur.rechercheClub("USSE"));
//				Controleur.inserCompetiteur(initCompetiteur,Controleur.rechercheClub("USSE"));
//				Controleur.inserCombattantCategorie(initCompetiteur);
//				initCompetiteur = new Competiteur("john","malokvich",5,"H",Controleur.rechercheClub("USSE"));
//				Controleur.inserCompetiteur(initCompetiteur,Controleur.rechercheClub("USSE"));
//				Controleur.inserCombattantCategorie(initCompetiteur);
//				initCompetiteur = new Competiteur("lenni","simpson",6,"F",Controleur.rechercheClub("USSE"));
//				Controleur.inserCompetiteur(initCompetiteur,Controleur.rechercheClub("USSE"));
//				Controleur.inserCombattantCategorie(initCompetiteur);
//				
//				initCompetiteur = new Competiteur("marius","lepecheur",5,"H",Controleur.rechercheClub("Dauphine"));
//				Controleur.inserCompetiteur(initCompetiteur,Controleur.rechercheClub("Dauphine"));
//				Controleur.inserCombattantCategorie(initCompetiteur);
//				initCompetiteur = new Competiteur("dora","lexporatrice",5,"H",Controleur.rechercheClub("Dauphine"));
//				Controleur.inserCompetiteur(initCompetiteur,Controleur.rechercheClub("Dauphine"));
//				Controleur.inserCombattantCategorie(initCompetiteur);
//				
//								
//				remplirCombattant(Controleur.rechercheClub("USSE"));	


		/* INIT*/
		new threadCheckList().start();	

		list.addMouseListener(new MouseListener() {


			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				areaScrollPane.repaint();
				Club selectionClub=Controleur.rechercheClub(list.getSelectedValue());
				int i=remplirCombattant(selectionClub);		
				System.out.println(list.getSelectedValue());
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
						int age=calculAge(Integer.parseInt(dateNaissance.getText()));

						if(age!=Integer.parseInt(dateNaissance.getText())){
							JOptionPane.showMessageDialog(getParent(),
									"la date de naissance et l'age ne concorde pas");		
							return;
						}


					}	
					if(!dateNaissance.getText().equals("")){
						age=calculAge(Integer.parseInt(dateNaissance.getText()));
						anneeNaissance=Integer.parseInt(dateNaissance.getText());
					}
					else if(!agePersonne.getText().equals("")){
						age=Integer.parseInt(agePersonne.getText());
						anneeNaissance=calculAnnee(Integer.parseInt(agePersonne.getText()));
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
					comboClub.setSelectedIndex(1);

				}
			}
		});
		
		

		boutonSupprimer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				
				if(!(list.getSelectedValue()==null)){
				System.out.println("----++++++++++++++----");
				for(Club cl : Controleur.listClub){
					for(Competiteur comp : cl.getListCompetiteur()){				
						System.out.println(comp.toString());
						if(comp.getClub().getNom().equals(list.getSelectedValue())){

						}
					}
					System.out.println(cl.getListCompetiteur().size());
				}
			
				System.out.println("----*******----");
				Club recupClub=Controleur.rechercheClub(list.getSelectedValue());	
				Controleur.supprimCompetiteurClub(recupClub, ligneSelectionTableau);
				//listCompetiteurTableau.remove(ligneSelectionTableau);	

				model.removeRow(ligneSelectionTableau);	
				for(Club cl : Controleur.listClub){
					for(Competiteur comp : cl.getListCompetiteur()){				
						System.out.println(comp.toString());
						if(comp.getClub().getNom().equals(list.getSelectedValue())){

						}
					}
					System.out.println(cl.getListCompetiteur().size());
				}

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
	
	


	public Object GetData(JTable table, int row_index, int col_index){
		return table.getModel().getValueAt(row_index, col_index);
	}  

	public void refreshComboClub(){
		comboClub.removeAllItems();
		comboClub.addItem("");
		for(Club c : Controleur.listClub){
			comboClub.addItem(c.getNom());
		}
	}

	private int calculAge(int dateAnneeNaissance){	
		Calendar c = Calendar.getInstance();
		int annee = c.get(Calendar.YEAR);
		int age=annee-dateAnneeNaissance;
		return age;
	}

	private int calculAnnee(int age){
		Calendar c = Calendar.getInstance();
		int annee = c.get(Calendar.YEAR);
		return annee-age;
	}

	public class threadCheckList extends Thread {

		public void run() {

			while(true){
				if(listModel.size()!=Controleur.listClub.size()){
					listModel.removeAllElements();
					remplirListClub();
				}
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		}

	}

	private void remplirListClub(){
		for(Club cl : Controleur.listClub){
			listModel.addElement(cl.getNom());
		}

	}
	private int remplirCombattant(Club club){
		model.getDataVector().removeAllElements();
		boolean aUnCompetiteur=false;
		int i =0;
		for(Competiteur comp : club.getListCompetiteur()){
			System.out.println(comp.toString());
			if (comp.getClub()==club){
				i=i+1;
				//System.out.println(comp.toString());
				model.addRow(new Object[]{comp.getNom(),comp.getPrenom(),comp.getAge(),calculAnnee(comp.getAge()),comp.getGenre(),comp.getCategorie().getNom(),comp.getClub().getNom()});
				aUnCompetiteur=true;
			}
		}
		if (!aUnCompetiteur){
			//model.addRow(new Object[]{});
		}
		return i;
	}



}	