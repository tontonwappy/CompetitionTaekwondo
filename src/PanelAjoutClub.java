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
import javax.swing.JTextField;


public class PanelAjoutClub extends JPanel {
	private static final long serialVersionUID = 1L;

	JButton boutonEnvoie= new JButton("OK");
	JButton boutonSupprimer= new JButton("Supprimer");
	JLabel titre = new JLabel("Gestion des clubs");
	JLabel labelAjoutClub = new JLabel("Entrez le nom d'un club à ajouter");
	static DefaultListModel<String> listModel = new DefaultListModel<String>();
	JList<String> list = new JList<String>(listModel); 
	final JTextField nomClub = new JTextField();

	PanelAjoutClub(){

		/** Initialisation des panels **/   
		this.setLayout(new BorderLayout());
		JPanel contenuHaut = new JPanel();
		JPanel contenuCenter = new JPanel();
		JPanel contenuDroite = new JPanel();
		//JPanel panelinDroite = new JPanel();
		this.add(contenuHaut,BorderLayout.NORTH);	
		this.add(contenuDroite,BorderLayout.EAST);
		this.add(contenuCenter,BorderLayout.CENTER);	
		/*Couleur*/
		contenuDroite.setBackground(Color.GRAY);
		contenuCenter.setBackground(new Color(193,205,205));
			
		/** Placement des composants : titre et bouton **/
		contenuHaut.add(titre);		
		nomClub.setColumns(20);
		contenuCenter.add(labelAjoutClub);
		contenuCenter.add(nomClub);
		contenuCenter.add(boutonEnvoie);
		contenuCenter.add(boutonSupprimer);

		/** Liste contenant les noms des clubs **/
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setVisibleRowCount(-1);
		list.setLayoutOrientation(JList.VERTICAL); 
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(200,250));
		contenuDroite.add(listScroller);

		
		boutonEnvoie.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				String clubtext=nomClub.getText();
				if (Controleur.rechercheClub(clubtext)==null){
					Controleur.ajoutClub(clubtext);
					nomClub.setText("");
					listModel.addElement(clubtext);
				}
				else{
					JOptionPane.showMessageDialog(getParent(),
							"Le club existe deja");		
					return;			
				}
			}
		});

		boutonSupprimer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(list.getSelectedIndex()!=-1){
					if(!Controleur.supprimClub(listModel.get(list.getSelectedIndex()))){
						JOptionPane.showMessageDialog(getParent(),
								"Impossible la catégorie contient des compétiteurs");		
						return;
					}
					else{
						listModel.remove(list.getSelectedIndex());
						JOptionPane.showMessageDialog(getParent(),
								"Club supprimé");		
						return;
					}
				}
			}
			
		});
	}

}	