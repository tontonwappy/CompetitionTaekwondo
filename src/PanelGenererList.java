import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import jxl.write.biff.RowsExceededException;


public class PanelGenererList extends JPanel{

	private static final long serialVersionUID = 1L;
	JLabel labelTitre=new JLabel("Generer la liste des combats : ");
	DefaultListModel<String> listModel = new DefaultListModel<String>();
	JList<String> list = new JList<String>(listModel); 
	DefaultTableModel model = new DefaultTableModel(); //définir le tableau
	JTable tableau = new JTable(model);
	JButton boutonGenerer=new JButton("Generer");
	JButton boutonExporter=new JButton("Exporter Organigramme");
	JButton boutonExporterCategorie=new JButton("Exporter Categorie");
	JButton boutonExporterListClub=new JButton("Exporter liste Club");
	public PanelGenererList() {

		/** Initialisation des panels **/   
		this.setLayout(new BorderLayout());
		JPanel contenuHaut = new JPanel();
		final JPanel contenuCenter = new JPanel();
		JPanel contenuDroite = new JPanel();
		//JPanel panelinDroite = new JPanel();
		this.add(contenuHaut,BorderLayout.NORTH);	
		//this.add(contenuDroite,BorderLayout.EAST);
		this.add(contenuCenter,BorderLayout.CENTER);	



		/*Couleur*/
		contenuDroite.setBackground(Color.GRAY);
		contenuCenter.setBackground(new Color(193,205,205));


		/** Placement des composants : titre et bouton **/
		contenuHaut.add(labelTitre);	
		contenuHaut.add(boutonGenerer);
		contenuHaut.add(boutonExporter);
		contenuHaut.add(boutonExporterListClub);
		contenuHaut.add(boutonExporterCategorie);
		


		final JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);


		JScrollPane areaScrollPane = new JScrollPane(textArea);
		areaScrollPane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		areaScrollPane.setPreferredSize(new Dimension(550, 600));
		contenuCenter.add(areaScrollPane);

		boutonExporter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
		
					Exportorganigramme.exportExcel();
				
			}
		});
		
		boutonExporterCategorie.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				ExportListCategorie.exportExcel();
				textArea.setText("");
				textArea.append("********************************************************"+"\n");
				textArea.append("********************************************************"+"\n");
				textArea.append("********************************************************"+"\n");
				textArea.append("--------->LISTE DES CATEGORIES EXPORTEE"+"\n");
				textArea.append("********************************************************"+"\n");
				textArea.append("********************************************************"+"\n");
				textArea.append("********************************************************"+"\n");
			}
		});
		
		boutonExporterListClub.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				ExportListClub.exportExcel();
				textArea.setText("");
				textArea.append("********************************************************"+"\n");
				textArea.append("********************************************************"+"\n");
				textArea.append("********************************************************"+"\n");
				textArea.append("--------->LISTE DES CLUBS EXPORTEE"+"\n");
				textArea.append("********************************************************"+"\n");
				textArea.append("********************************************************"+"\n");
				textArea.append("********************************************************"+"\n");
			}
		});
		
		boutonGenerer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				
				Controleur.generationListCombat();			
				Controleur.insertionCombattantListCombat();
				System.out.println("------------test--------------");
				for(ListeCombat li : Controleur.listCategorieCombat){
					for(Combat cb :li.getListeCombat()){
						if(cb.getDeuxiemeCombattant()!=null){
							System.out.println("*******Combat***********");
							System.out.println(cb.getPremierCombattant().getNom());
							System.out.println(cb.getDeuxiemeCombattant().getNom());
						}
						else{
							System.out.println("*******Combat***********");
							System.out.println(cb.getPremierCombattant().getNom());
						}
					}
				}									

				textArea.setText("");
				int numeroCompTotal =0;
				for(ListeCombat li : Controleur.listCategorieCombat){
					if (li.getGenre().equals("H")){
						textArea.append("********************************************************"+"\n");
						textArea.append("**************"+li.getCategorie().getNom()+"*****MASCULIN*********"+"\n");
						textArea.append("********************************************************"+"\n \n");	
						for(Combat cb :li.getListeCombat()){
							if(cb.getDeuxiemeCombattant()!=null){
								textArea.append("         |--"+cb.getPremierCombattant().getClub().getNom()+" :: "+cb.getPremierCombattant().getPrenom()+" "+cb.getPremierCombattant().getNom()+"\n");
								textArea.append("-------| \n");
								textArea.append("         |--"+cb.getDeuxiemeCombattant().getClub().getNom()+" :: "+cb.getDeuxiemeCombattant().getPrenom()+" "+cb.getDeuxiemeCombattant().getNom()+"\n");														
								textArea.append("******************************************************* \n");
								 numeroCompTotal=numeroCompTotal+2;
							}
							else{
								textArea.append(cb.getPremierCombattant().getClub().getNom()+" :: "+cb.getPremierCombattant().getPrenom()+" "+cb.getPremierCombattant().getNom()+"\n");
								numeroCompTotal=numeroCompTotal+1;
							}
						}
						textArea.append("\n \n \n");
					}
					
					else if (li.getGenre().equals("Mixte")){

						textArea.append("********************************************************"+"\n");
						textArea.append("**************"+li.getCategorie().getNom()+"*****Mixte*********"+"\n");
						textArea.append("********************************************************"+"\n \n");	
						for(Combat cb :li.getListeCombat()){
							if(cb.getDeuxiemeCombattant()!=null){
								
								textArea.append("         |--"+cb.getPremierCombattant().getClub().getNom()+" :: "+cb.getPremierCombattant().getPrenom()+" "+cb.getPremierCombattant().getNom()+"\n");
								textArea.append("-------| \n");
								textArea.append("         |--"+cb.getDeuxiemeCombattant().getClub().getNom()+" :: "+cb.getDeuxiemeCombattant().getPrenom()+" "+cb.getDeuxiemeCombattant().getNom()+"\n");															
								textArea.append("******************************************************* \n");
								numeroCompTotal=numeroCompTotal+2;
							}
							else{
								textArea.append(cb.getPremierCombattant().getClub().getNom()+" :: "+cb.getPremierCombattant().getPrenom()+" "+cb.getPremierCombattant().getNom()+"\n");
								numeroCompTotal=numeroCompTotal+1;
							}
						}
						textArea.append("\n \n \n");
					
						
					}
					else{
						textArea.append("********************************************************"+"\n");
						textArea.append("**************"+li.getCategorie().getNom()+"*****FEMININ*********"+"\n");
						textArea.append("********************************************************"+"\n \n");		
						for(Combat cb :li.getListeCombat()){
							if(cb.getDeuxiemeCombattant()!=null){
								textArea.append("         |--"+cb.getDeuxiemeCombattant().getClub().getNom()+" :: "+cb.getDeuxiemeCombattant().getPrenom()+" "+cb.getDeuxiemeCombattant().getNom()+"\n");
								textArea.append("-------| \n");
								textArea.append("         |--"+cb.getPremierCombattant().getClub().getNom()+" :: "+cb.getPremierCombattant().getPrenom()+" "+cb.getPremierCombattant().getNom()+"\n");
								textArea.append("******************************************************* \n");
								numeroCompTotal=numeroCompTotal+2;
							}
							else{
								textArea.append(cb.getPremierCombattant().getClub().getNom()+" :: "+cb.getPremierCombattant().getPrenom()+" "+cb.getPremierCombattant().getNom()+"\n");
								numeroCompTotal=numeroCompTotal+1;
							}
							
						}
						textArea.append("\n \n \n");
						
					}

				}
				textArea.append("\n \n \n");
				textArea.append("Nombre total de participants dans la liste =" + numeroCompTotal);
			}
		});

	}
}

