import java.awt.BorderLayout;
//import java.awt.Color;
//import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class PanelAccueil extends JPanel {
	/**
	 * 1 ere fenetre lors de l'ouverture du logiciel
	 */
	private static final long serialVersionUID = 1L;
	JLabel labelInfo=new JLabel();
	PanelAccueil(){
		this.setLayout(new BorderLayout());
		JPanel panel=new JPanel();
		labelInfo.setText("Outil de gestion de l'USSE taekwondo spécial Arbre de Noël");
		panel.add(labelInfo);
		this.add(panel,BorderLayout.NORTH);
	}
	
}
