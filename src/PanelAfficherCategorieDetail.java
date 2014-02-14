import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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

	private static final long serialVersionUID = 1L;
	JLabel labelTitre=new JLabel("Affichage des categories");
	JLabel labelInfo=new JLabel();
	static DefaultListModel<String> listModel = new DefaultListModel<String>();
	JList<String> list = new JList<String>(listModel); 
	static DefaultTableModel model = new DefaultTableModel(); //définir le tableau
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
		panelHaut.add(labelTitre);

		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {	
				areaScrollPane.repaint();
				Categorie selectioncategorie=Controleur.retrouveCategorie(list.getSelectedValue());
				if(selectioncategorie!=null){
				int nbComp=Controleur.remplirCombattant(selectioncategorie,model);		
				labelInfo.setText("Categorie :"+selectioncategorie.getNom()+"    Nombre de compétiteurs :"+nbComp);
				}
			}});		
	}



	


}
