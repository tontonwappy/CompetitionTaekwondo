import java.awt.BorderLayout;
import java.awt.Color;



import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class PanelAccueil extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel labelInfo=new JLabel();
	PanelAccueil(){
		//setBackground(Color.RED);
		this.setLayout(new BorderLayout());
		JPanel panel=new JPanel();
		labelInfo.setText("Outil de gestion de l'USSE taekwondo spécial Arbre de Noël");
		panel.add(labelInfo);
		this.add(panel,BorderLayout.NORTH);
		ImageIcon image = new ImageIcon("arbre.jpg");
		JLabel label = new JLabel("", image, JLabel.CENTER);
		this.add( label, BorderLayout.CENTER );


	}
	
}
