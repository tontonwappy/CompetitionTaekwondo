import java.io.File;
import java.io.IOException;

import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.ScriptStyle;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


public class ExportExcel {




	public static void main(String[] args) {
		try {
			WritableWorkbook workbook = Workbook.createWorkbook(new File("sortie.xls"));
			WritableSheet sheet = workbook.createSheet("Premier classeur", 0);
			//Crée le format d’une cellule
			WritableFont arial10font = new WritableFont(WritableFont.ARIAL, 20,WritableFont.BOLD, true, UnderlineStyle.NO_UNDERLINE,Colour.BLUE, ScriptStyle.NORMAL_SCRIPT);
			WritableCellFormat arial10format = new WritableCellFormat(arial10font);


			WritableCellFormat cadre1 = new WritableCellFormat();
			WritableCellFormat cadre2 = new WritableCellFormat();
			WritableCellFormat ligneHorizontale = new WritableCellFormat();
			cadre1.setBorder(Border.TOP, BorderLineStyle.MEDIUM);
			cadre1.setBorder(Border.LEFT, BorderLineStyle.MEDIUM);
			cadre1.setBorder(Border.RIGHT, BorderLineStyle.MEDIUM);
			cadre2.setBorder(Border.BOTTOM, BorderLineStyle.MEDIUM);
			cadre2.setBorder(Border.LEFT, BorderLineStyle.MEDIUM);
			cadre2.setBorder(Border.RIGHT, BorderLineStyle.MEDIUM);



			ligneHorizontale.setBorder(Border.BOTTOM, BorderLineStyle.MEDIUM);
			//Crée un label à la ligne 0, colonne 0 avec le format spécifique
			Label label = new Label(0, 0, "Arial 10 point label",arial10format);


			//Crée un label à la ligne 2, colonne 0 sans style prédéfini
			Label nom1 = new Label(4, 4, "nom1",cadre1);
			Label nomsuite1 = new Label(4, 5, "nomsuite1 aaaaaaaaaaaa",cadre2);
			Label nom2 = new Label(4, 8, "nom2",cadre1);
			Label ligne1 = new Label(5, 4, "",ligneHorizontale);


			//Ajout des cellules
			sheet.addCell(label);
			sheet.addCell(nom1);
			sheet.addCell(nom2);
			sheet.addCell(ligne1);
			sheet.addCell(nomsuite1);



			//Ajout d’une cellule ligne 2, colonne 1
			Number number = new Number(1, 2, 3.1459);
			sheet.addCell(number);
			//Ajout d’une image ligne 4, colonne 0
			//Taille de l’image : 6 lignes et 2 colonnes
			//WritableImage image = new WritableImage(0, 4, 2, 6,new File("Logo-Labo-Sun.png"));
			//sheet.addImage(image);
			//Ecriture et fermeture du classeur
			workbook.write();
			workbook.close();
		} catch (RowsExceededException e1) {
			e1.printStackTrace();
		} catch (WriteException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			System.out.println("Le fichier \"sortie.xls\" à été généré correctement.");
		}
	} 


}
