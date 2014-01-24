/*
 * FileChooser.java
 *
 * Exemple d'utilisation de JFileChooser
 *
 * GM/2008-04-03/
 */

 
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
 
/**
 *
 * @author gmonard
 */
public class FileChooser extends JFrame implements ActionListener
{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//JLabel texte;
   JTextField fichier; /* nom du fichier choisit
                        * il apparait dans un JTextField non editable
                        * (fichier.setEditable(false))
                        */
   JButton choisir;    /* bouton qui permet d'appeler le JFileChooser
                        */
   JButton quitter;
   JButton voir;       /* bouton qui appelle une autre fenetre 
                        * pour afficher le contenu du fichier
                        * Au debut, le bouton n'est pas cliquable 
                        * (voir.setEnabled(falste)). Il ne le devient
                        * que quand un fichier a été choisi
                        */
   JFileChooser choixfichier; /* La fenêtre pour choisir un fichier
                               */
   String nomfichier;   /* le nom du fichier choisi
                         */
   private String typedonnee;
 
   public FileChooser(String typedonnee)
   {
      super("Exemple de choix d'un fichier");
      this.setSize(400,100);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.typedonnee=typedonnee;
 
      BorderLayout agencement = new BorderLayout();
      this.setLayout(agencement);
 
      JPanel partiemilieu = new JPanel();
      JPanel partiebasse = new JPanel();
 
      /* on utilise un FlowLayout pour mettre les trois composants
       * les uns a la suite des autres (JLabel, JTextField, et JButton)
       */
      FlowLayout centre = new FlowLayout(FlowLayout.CENTER);
      partiemilieu.setLayout(centre);
 
      JLabel texte = new JLabel("Fichier:");
      partiemilieu.add(texte);
 
      fichier = new JTextField("",20);
      fichier.setEditable(false);         /* attention, non editable
                                           * on doit passer par le JFileChooser
                                           */
      partiemilieu.add(fichier);
 
      choisir = new JButton("Choisir ...");
      partiemilieu.add(choisir);
      choisir.addActionListener(this);
 
      voir = new JButton("Importer");
      voir.setEnabled(false);
      partiebasse.add(voir);
      voir.addActionListener(this);
 
      quitter = new JButton("Quitter");
      partiebasse.add(quitter);
      quitter.addActionListener(this);
 
      this.add(partiemilieu);
      this.add(partiebasse, BorderLayout.SOUTH);
 
      this.setVisible(true); // toujours en dernier ...
   }
 
   public void actionPerformed(ActionEvent evenement)
   {
      Object source = evenement.getSource();
 
      if (source == choisir)
      {
         /* appel au JFileChooser
          */
  
    	  File directory = new File (".");
    	  try {
			choixfichier = new JFileChooser(directory.getCanonicalPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  FiltreExtension extension =new FiltreExtension("xls", "Fichier excel");
          choixfichier.addChoosableFileFilter(extension);
         /* que s'est-il passe ?
          */
         int returnVal = choixfichier.showOpenDialog(this);
         /* si on a choisit quelque chose,
          * alors on recupere le nom du fichier
          * (attention, cette partie n'est pas complete,
          *  il faudrait verifier qu'on a bien choisit un
          *  fichier et non un repertoire ...)
          */
         if(returnVal == JFileChooser.APPROVE_OPTION)
         {
            // recuperation du nom du fichier
            nomfichier = choixfichier.getSelectedFile().getAbsolutePath();
            // affichage du nom du fichier dans le JTextField
            fichier.setText(nomfichier);
            extension.getDescription();
            // maintenant, on peut cliquer sur le JButton voir
            voir.setEnabled(true);
         }
 
      }
      else if (source == voir)
      {
         // affichage du contenu du fichier dans une autre fenetre
         //ShowFile popup = new ShowFile(this);
    	  System.out.println(nomfichier);
    	  System.out.println(choixfichier.getSelectedFile().getName());
    	  ExtractExcel.exctractData(choixfichier.getSelectedFile().getName(),this.typedonnee);
      }
      else if (source == quitter)
      {
         // fin du programme, on ferme la fenetre
         // plus beau qu'un System.exit(0) ...
         this.dispose();
      }
      else
      {
         // rien
      }
   }
 
   /*
    * lireFichier() est inspire de l'exemple de lecture d'un fichier
    * caractere par caractere
    * Ici, on retourne le contenu entier du fichier sous forme d'une chaine
    * de caractere
    */
   public String lireFichier()
   {
 
      StringBuffer contenu = new StringBuffer("");
      FileReader fichier;
 
      try
      {
         /* ouverture du fichier */
         fichier = new FileReader(nomfichier);
         /* lecture du fichier + ecriture de tous les caracteres rencontres */
         int inByte;
         do
         {
            inByte = fichier.read();
            if (inByte != -1)
            {
               // ajout du caractere lu a contenu
               contenu.append((char)inByte);
            }
         }
         while (inByte != -1);
 
      }
      catch (FileNotFoundException fnfe)
      {
         System.out.println("Probleme a l'ouverture du fichier");
         System.exit(1);
      }
      catch (IOException ioe)
      {
         System.out.printf("Erreur de lecture");
         System.exit(1);
      }
      return contenu.toString();
   }
 
   /*
    * point d'entre du programme
    */
//   public static void main(String[] arguments)
//   {
//      FileChooser exemple = new FileChooser();
//   }
   
   
  
}
 
/*
 * fenetre d'affichage du contenu du fichier
 *
 * Deux morceaux: le contenu du fichier (avec une barre de defilement si besoin)
 * et un bouton OK pour fermer la fenetre
// */
//class ShowFile extends JFrame implements ActionListener
//{
//   FileChooser pere;
//   JButton ok;
// 
//   public ShowFile(FileChooser pere)
//   {
//      this.pere = pere;
//      this.setSize(700,700);
//      this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
// 
//      BorderLayout agencement = new BorderLayout();
//      this.setLayout(agencement);
// 
//      JPanel partiebasse = new JPanel();
//      JPanel partiehaute = new JPanel();
// 
//      ok = new JButton("OK");
//      ok.addActionListener(this);
// 
//      JTextArea contenu = new JTextArea(40,60);
//      // le contenu du fichier n'est pas editable
//      contenu.setEditable(false);
// 
//      /* Pour avoir une barre de defilement on englobe
//       * le JTextArea dand un JScrollPane
//       */
//      JScrollPane defilement = new JScrollPane(contenu);//,
// 
//      String texte = pere.lireFichier();
//      contenu.setText(texte);
// 
//      partiehaute.add(defilement); /* on ajoute le JScrollPane qui contient
//                                    * le JTextField
//                                    * (et non le JTextField)
//                                    */
//      partiebasse.add(ok);
// 
//      this.add(partiehaute, BorderLayout.CENTER);
//      this.add(partiebasse, BorderLayout.SOUTH);
// 
//      this.setVisible(true);
//   }
// 
//   public void actionPerformed(ActionEvent evenement)
//   {
//      Object source = evenement.getSource();
//      if (source == ok)
//      {
//         // si on clique sur OK, on ferme la fenetre
//         this.dispose();
//      }
//      else
//      {
//         // rien
//      }
//   }
//}