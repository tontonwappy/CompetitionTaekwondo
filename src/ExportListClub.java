import java.io.File;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


public class ExportListClub {





	public static void exportExcel() {


		try {
			WritableWorkbook workbook = Workbook.createWorkbook(new File("sortieListClub.xls"));


			for(Club cl : Controleur.listClub){
				WritableSheet sheet = workbook.createSheet(cl.getNom(), 0);
				int widthInChars = 20;
				int col = 2;
				sheet.setColumnView(col, widthInChars);
				widthInChars = 20;
				col = 1;
				sheet.setColumnView(col, widthInChars);
				ecriretete(sheet);
				int i=0;
				for(Competiteur comp : cl.getListCompetiteur()){
					ecrireCompetiteur(sheet, i, comp);
					i=i+1;
				}			
			}
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

	public static void ecrireCompetiteur(Sheet sheet,int i,Competiteur comp1) throws WriteException{
		WritableCellFormat cadre1 = new WritableCellFormat();

		cadre1.setBorder(Border.ALL, BorderLineStyle.THIN);

		//Crée un label à la ligne 2, colonne 0 sans style prédéfini
		Label club = new Label(1, 2+i, comp1.getClub().getNom(),cadre1);
		Label nom = new Label(2, 2+i,comp1.getNom(),cadre1);
		Label prenom = new Label(3, 2+i,comp1.getPrenom(),cadre1);
		Label genre = new Label(4, 2+i,comp1.getGenre(),cadre1);
		Label age = new Label(5, 2+i,String.valueOf(comp1.getAge()) ,cadre1);
		Label categorie = new Label(6, 2+i,comp1.getCategorie().getNom(),cadre1);
		Label presence = new Label(7, 2+i," ",cadre1);
		//Ajout des cellules
		//((WritableSheet) sheet).addCell(label);
		((WritableSheet) sheet).addCell(club);
		((WritableSheet) sheet).addCell(nom);
		((WritableSheet) sheet).addCell(prenom);
		((WritableSheet) sheet).addCell(genre);
		((WritableSheet) sheet).addCell(age);
		((WritableSheet) sheet).addCell(categorie);
		((WritableSheet) sheet).addCell(presence);


	}

	public static void ecriretete(Sheet sheet){
		WritableCellFormat cadre1 = new WritableCellFormat();


		try {
			cadre1.setBorder(Border.ALL, BorderLineStyle.THICK);
			
			Label club = new Label(1, 1, "CLUB",cadre1);
			Label nom = new Label(2, 1,"Nom",cadre1);
			Label prenom = new Label(3, 1,"Prenom",cadre1);
			Label genre = new Label(4, 1,"Genre",cadre1);
			Label age = new Label(5, 1,"Age",cadre1);
			Label categorie = new Label(6, 1,"Categorie",cadre1);
			Label presence = new Label(7, 1,"Présence",cadre1);
			
			((WritableSheet) sheet).addCell(club);
			((WritableSheet) sheet).addCell(nom);
			((WritableSheet) sheet).addCell(prenom);
			((WritableSheet) sheet).addCell(genre);
			((WritableSheet) sheet).addCell(age);
			((WritableSheet) sheet).addCell(categorie);
			((WritableSheet) sheet).addCell(presence);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}


}
