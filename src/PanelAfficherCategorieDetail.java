import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Calendar;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


public class PanelAfficherCategorieDetail extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel labelTitre=new JLabel("Affichage des categories");
	JLabel labelInfo=new JLabel();
	static DefaultListModel<String> listModel = new DefaultListModel<String>();
	JList<String> list = new JList<String>(listModel); 
	DefaultTableModel model = new DefaultTableModel(); //définir le tableau
	JTable tableau = new JTable(model);
	JButton boutontest=new JButton("Ok");
	private  static JScrollPane areaScrollPane=new JScrollPane();
	JButton boutonRefresh=new JButton("Refraichir la liste");

	PanelAfficherCategorieDetail(){

		// Ajouter des colonnes au tableau
		model.addColumn("Club");
		model.addColumn("Nom");
		model.addColumn("Prenom");
		model.addColumn("Age");
		model.addColumn("Année de naissance");
		model.addColumn("Genre");
		model.addColumn("Categorie");


		this.setLayout(new BorderLayout());
		JPanel panelEst=new JPanel();
		JPanel panelCentre=new JPanel();
		JPanel panelHaut=new JPanel();
		JPanel panelBas=new JPanel();

		panelEst.setBackground(Color.gray);
		panelCentre.setBackground(new Color(193,205,205));


		this.add(panelEst,BorderLayout.WEST);
		this.add(panelCentre,BorderLayout.CENTER);
		this.add(panelHaut,BorderLayout.NORTH);
		this.add(panelBas,BorderLayout.SOUTH);

		//panelBas.add(boutonRefresh);


		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setVisibleRowCount(-1);
		list.setLayoutOrientation(JList.VERTICAL); 
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(200,250));
		panelEst.add(listScroller);

		areaScrollPane = new JScrollPane(tableau);
		areaScrollPane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		areaScrollPane.setPreferredSize(new Dimension(550, 550));


		panelCentre.add(tableau.getTableHeader());
		panelCentre.add(areaScrollPane);

		panelBas.add(labelInfo);

		remplirListClub();

		System.out.println(list.getSelectedValue());



		panelHaut.add(labelTitre);


		//new threadCheckList().start();	
		//Thread thread=new threadCheckList();
		//thread.start();

		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
				
				areaScrollPane.repaint();
				Categorie selectioncategorie=Controleur.retrouveCategorie(list.getSelectedValue());
				if(selectioncategorie!=null){
				System.out.println(list.getSelectedValue());
					System.out.println(list.getSelectedValue());
					System.out.println("categorie"+selectioncategorie);
				int nbComp=remplirCombattant(selectioncategorie);		
				
				System.out.println(list.getSelectedValue());
				labelInfo.setText("Categorie :"+selectioncategorie.getNom()+"    Nombre de compétiteurs :"+nbComp);
				}
		
			}});
	
			
	}



	private void remplirListClub(){
		for(Club cl : Controleur.listClub){
			listModel.addElement(cl.getNom());
		}

	}
	private static void remplirListCategorie(){
		for(Categorie cat : Controleur.listeCategorie){
			listModel.addElement(cat.getNom());
			System.out.println("test");
		}

	}


	private int remplirCombattant(Categorie categorie){
		model.getDataVector().removeAllElements();
		int i=0;
		for(Club cl : Controleur.listClub){
			System.out.println("--------");
			for(Competiteur comp : cl.getListCompetiteur()){
			//	System.out.println(comp.getNom()+" ---"+comp.getCategorie().getNom()+"---"+categorie.getNom());
				if (comp.getCategorie().getNom().equals(categorie.getNom()) && comp.getGenre().equals("H")){
					i=i+1;
					System.out.println("insertion "+comp.toString());
					model.addRow(new Object[]{cl.getNom(),comp.getNom(),comp.getPrenom(),comp.getAge(),calculAnnee(comp.getAge()),comp.getGenre(),comp.getCategorie().getNom(),Controleur.listClub.get(0).getNom()});
				}
			}
		}		
		model.addRow(new Object[]{"***","***","***","***","***","***","***","***"});
		//***************************
		for(Club cl : Controleur.listClub){
			System.out.println("--------");
			for(Competiteur comp : cl.getListCompetiteur()){
				System.out.println(comp.getNom()+" ---"+comp.getCategorie().getNom()+"---"+categorie.getNom());
				if (comp.getCategorie().getNom().equals(categorie.getNom())&& comp.getGenre().equals("F")){
					i=i+1;
					System.out.println("insertion "+comp.toString());
					model.addRow(new Object[]{cl.getNom(),comp.getNom(),comp.getPrenom(),comp.getAge(),calculAnnee(comp.getAge()),comp.getGenre(),comp.getCategorie().getNom(),Controleur.listClub.get(0).getNom()});
				}
			}
		}
		return i;
	}

	private int calculAnnee(int age){
		Calendar c = Calendar.getInstance();
		int annee = c.get(Calendar.YEAR);
		return annee-age;
	}

	

	public static void refresh(){
		/**erreur **/
		//if(listModel.size()!=Controleur.listeCategorie.size()){
			//if(PanelAjoutCategorie.ageManquant.size()==0){
				listModel.removeAllElements();
				remplirListCategorie();				
			//}	
		//}
	}


	public class threadCheckList extends Thread {
		public void run() {
			while(true){
				if(listModel.size()!=Controleur.listeCategorie.size()){
					if(PanelAjoutCategorie.ageManquant.size()==0){
						listModel.removeAllElements();
						remplirListCategorie();				
					}					
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
}
