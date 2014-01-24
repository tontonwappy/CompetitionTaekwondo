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

public class Exportorganigramme {

	public static void exportExcel() {

		try {
			int nombreParticipant=0;
			WritableWorkbook workbook = Workbook.createWorkbook(new File(
					"sortie.xls"));

			for (ListeCombat li : Controleur.listCategorieCombat) {
	
				
				int tailleliste = li.getListeCombat().size();
				int typeArbre = tailleliste % 4;
				int i = 0;
				int d=0;
				if (li.getGenre().equals("H")) {
					/*************************************************/	
					WritableSheet sheet = workbook.createSheet("Masculin"+li.getCategorie().getNom() , 0);			
					WritableCellFormat CadreCategorie = new WritableCellFormat();
					Label Categorie = new Label(3, 0, "CATEGORIE : "+ li.getCategorie().getNom()+"Masculin" , CadreCategorie);	
				
					((WritableSheet) sheet).addCell(Categorie);
				

					sheet.setColumnView(0, 2);
					sheet.setColumnView(1, 35);
					sheet.setColumnView(2, 5);
					sheet.setColumnView(4, 25);
					sheet.setColumnView(5, 5);
					sheet.setColumnView(7, 25);
					int col = 1;
					int widthInChars = 20;
					sheet.setColumnView(col, widthInChars);

					/*************************************************/

					nombreParticipant=nombreParticipant+ecrirePremierTier(li,sheet,i);
					if(tailleliste>0){
					if(tailleliste>2){
						 d=ecrireTier2et3(sheet, typeArbre,tailleliste);
					}
					else if(tailleliste==2){
						d=15;
					}
					
					else {
						d=6;
					}
					/*****************************Ajout du tableau des médailles**********************************/
					WritableCellFormat cadre = new WritableCellFormat();
					try {
						cadre.setBorder(Border.ALL, BorderLineStyle.THIN);
						int var=d+2;
						Label tableau1 = new Label(3, var, "Or ",cadre);
						Label tableau2 = new Label(3, var+1, "Argent ",cadre);
						Label tableau3 = new Label(3, var+2, "Bronze ",cadre);
						Label nom1 = new Label(4, var, " ",cadre);
						Label nom2 = new Label(4, var+1, " ",cadre);
						Label nom3 = new Label(4, var+2, " ",cadre);
						((WritableSheet) sheet).addCell(tableau1);
						((WritableSheet) sheet).addCell(tableau2);
						((WritableSheet) sheet).addCell(tableau3);
						((WritableSheet) sheet).addCell(nom1);
						((WritableSheet) sheet).addCell(nom2);
						((WritableSheet) sheet).addCell(nom3);
					} catch (WriteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					/*****************************Ajout du tableau des médailles**********************************/
					}
					//gestiontier4(sheet, 0, tailleliste);

				}

				else if (li.getGenre().equals("Mixte")) {
					/*************************************************/	
					WritableSheet sheet = workbook.createSheet("Mixte"+li.getCategorie().getNom() , 0);								
					WritableCellFormat cadre1 = new WritableCellFormat();
					Label Categorie = new Label(3, 0, "CATEGORIE : "+ li.getCategorie().getNom()+ " Mixte", cadre1);
			
					((WritableSheet) sheet).addCell(Categorie);		
	

					sheet.setColumnView(0, 2);
					sheet.setColumnView(1, 35);
					sheet.setColumnView(2, 5);
					sheet.setColumnView(4, 25);
					sheet.setColumnView(5, 5);
					sheet.setColumnView(7, 25);

					int col = 1;
					int widthInChars = 20;
					sheet.setColumnView(col, widthInChars);
					/*************************************************/	
					nombreParticipant=nombreParticipant+ecrirePremierTier(li,sheet,i);
					if(tailleliste>0){
					if(tailleliste>2){
						d=ecrireTier2et3(sheet, typeArbre,
								tailleliste);
					}
					//	gestiontier4(sheet, 0, tailleliste);
					else if(tailleliste==2){
						d=15;
					}
					
					else {
						d=6;
					}
					
					/*****************************Ajout du tableau des médailles**********************************/
					WritableCellFormat cadre = new WritableCellFormat();
					try {
						cadre.setBorder(Border.ALL, BorderLineStyle.THIN);
						int var=d+2;
						Label tableau1 = new Label(3, var, "Or ",cadre);
						Label tableau2 = new Label(3, var+1, "Argent ",cadre);
						Label tableau3 = new Label(3, var+2, "Bronze ",cadre);
						Label nom1 = new Label(4, var, " ",cadre);
						Label nom2 = new Label(4, var+1, " ",cadre);
						Label nom3 = new Label(4, var+2, " ",cadre);
						((WritableSheet) sheet).addCell(tableau1);
						((WritableSheet) sheet).addCell(tableau2);
						((WritableSheet) sheet).addCell(tableau3);
						((WritableSheet) sheet).addCell(nom1);
						((WritableSheet) sheet).addCell(nom2);
						((WritableSheet) sheet).addCell(nom3);
					} catch (WriteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					/*****************************Ajout du tableau des médailles**********************************/
					}
				} else {
					/*************************************************/	
					WritableSheet sheet = workbook.createSheet("Feminin"+li.getCategorie().getNom() , 0);								
					WritableCellFormat cadre1 = new WritableCellFormat();
					Label Categorie = new Label(3, 0, "CATEGORIE : "+ li.getCategorie().getNom()+ " Feminin", cadre1);
			
					((WritableSheet) sheet).addCell(Categorie);		
	

					sheet.setColumnView(0, 2);
					sheet.setColumnView(1, 35);
					sheet.setColumnView(2, 5);
					sheet.setColumnView(4, 25);
					sheet.setColumnView(5, 5);
					sheet.setColumnView(7, 25);

					int col = 1;
					int widthInChars = 20;
					sheet.setColumnView(col, widthInChars);
					/*************************************************/	
					nombreParticipant=nombreParticipant+ecrirePremierTier(li,sheet,i);
					if(tailleliste>0){
					if(tailleliste>2){
						d=ecrireTier2et3(sheet, typeArbre,
								tailleliste);
					}
					else if(tailleliste==2){
						d=15;
					}
					
					else {
						d=6;
					}
					
					/*****************************Ajout du tableau des médailles**********************************/
					WritableCellFormat cadre = new WritableCellFormat();
					try {
						cadre.setBorder(Border.ALL, BorderLineStyle.THIN);
						int var=d+2;
						Label tableau1 = new Label(3, var, "Or ",cadre);
						Label tableau2 = new Label(3, var+1, "Argent ",cadre);
						Label tableau3 = new Label(3, var+2, "Bronze ",cadre);
						Label nom1 = new Label(4, var, " ",cadre);
						Label nom2 = new Label(4, var+1, " ",cadre);
						Label nom3 = new Label(4, var+2, " ",cadre);
						((WritableSheet) sheet).addCell(tableau1);
						((WritableSheet) sheet).addCell(tableau2);
						((WritableSheet) sheet).addCell(tableau3);
						((WritableSheet) sheet).addCell(nom1);
						((WritableSheet) sheet).addCell(nom2);
						((WritableSheet) sheet).addCell(nom3);
					} catch (WriteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					/*****************************Ajout du tableau des médailles**********************************/
					//	gestiontier4(sheet, 0, tailleliste);
					}
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
		} finally {
			System.out
					.println("Le fichier \"sortie.xls\" à été généré correctement.");
		}
	}
	public static int ecrirePremierTier(ListeCombat li,Sheet sheet,int i){
		int nbParticipant=0;
		for (Combat comb : li.getListeCombat()) {
			try {
				nbParticipant=nbParticipant+ecritureArbreSimpleNiveau1(sheet, i,comb.getPremierCombattant(),comb.getDeuxiemeCombattant(),4);
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			i = i + 8;}
		return nbParticipant;
	}


	public static void ecrireArbreSecondTour4(Sheet sheet, int i)
			throws WriteException {
		WritableCellFormat cadre1 = new WritableCellFormat();
		WritableCellFormat cadre2 = new WritableCellFormat();
		WritableCellFormat ligneHorizontale = new WritableCellFormat();
		WritableCellFormat ligneVerticale = new WritableCellFormat();
		WritableCellFormat ligneHorizontaleBis = new WritableCellFormat();
		cadre1.setBorder(Border.TOP, BorderLineStyle.MEDIUM);
		cadre1.setBorder(Border.LEFT, BorderLineStyle.MEDIUM);
		cadre1.setBorder(Border.RIGHT, BorderLineStyle.MEDIUM);
		cadre1.setBorder(Border.BOTTOM, BorderLineStyle.MEDIUM);
		cadre2.setBorder(Border.BOTTOM, BorderLineStyle.MEDIUM);
		cadre2.setBorder(Border.LEFT, BorderLineStyle.MEDIUM);
		cadre2.setBorder(Border.RIGHT, BorderLineStyle.MEDIUM);
		ligneHorizontale.setBorder(Border.BOTTOM, BorderLineStyle.MEDIUM);
		ligneVerticale.setBorder(Border.RIGHT, BorderLineStyle.MEDIUM);
		ligneHorizontaleBis.setBorder(Border.TOP, BorderLineStyle.MEDIUM);

		// Crée un label à la ligne 2, colonne 0 sans style prédéfini
		Label nom1 = new Label(4, 3 + i, "", cadre1);
		Label nomsuite1 = new Label(4, 4 + i, "", cadre1);
		Label ligneH = new Label(5, 3 + i, "", ligneHorizontale);
		Label ligneV1 = new Label(5, 4 + i, "", ligneVerticale);
		Label ligneV2 = new Label(5, 5 + i, "", ligneVerticale);
		Label ligneV3 = new Label(5, 6 + i, "", ligneVerticale);
		Label ligneV4 = new Label(5, 7 + i, "", ligneVerticale);
		Label ligneV5 = new Label(5, 8 + i, "", ligneVerticale);
		Label ligneV6 = new Label(5, 9 + i, "", ligneVerticale);
		Label ligneV7 = new Label(5, 10 + i, "", ligneVerticale);
		Label ligneV8 = new Label(5, 11 + i, "", ligneVerticale);
		Label ligneH2 = new Label(5, 12 + i, "", ligneHorizontaleBis);
		Label case2 = new Label(4, 11 + i, "", cadre1);
		Label case2bis = new Label(4, 12 + i, "", cadre1);
		Label ligneH3 = new Label(6, 7 + i, "", ligneHorizontale);
		Label case3 = new Label(7, 7 + i, "", cadre1);
		Label case3bis = new Label(7, 8 + i, "", cadre1);
		Label ligneH3bis = new Label(8, 7 + i, "", ligneHorizontale);

		// Ajout des cellules
		// ((WritableSheet) sheet).addCell(label);
		((WritableSheet) sheet).addCell(nom1);
		((WritableSheet) sheet).addCell(ligneH);
		((WritableSheet) sheet).addCell(nomsuite1);
		((WritableSheet) sheet).addCell(ligneV1);
		((WritableSheet) sheet).addCell(ligneV2);
		((WritableSheet) sheet).addCell(ligneV3);
		((WritableSheet) sheet).addCell(ligneV4);
		((WritableSheet) sheet).addCell(ligneV5);
		((WritableSheet) sheet).addCell(ligneV6);
		((WritableSheet) sheet).addCell(ligneV7);
		((WritableSheet) sheet).addCell(ligneV8);
		((WritableSheet) sheet).addCell(ligneH2);
		((WritableSheet) sheet).addCell(case2);
		((WritableSheet) sheet).addCell(case2bis);
		((WritableSheet) sheet).addCell(ligneH3);
		((WritableSheet) sheet).addCell(case3);
		((WritableSheet) sheet).addCell(case3bis);
		((WritableSheet) sheet).addCell(ligneH3bis);
	}

	public static void ecrireArbreSecondTour3(Sheet sheet, int i)
			throws WriteException {
		WritableCellFormat cadre1 = new WritableCellFormat();
		WritableCellFormat cadre2 = new WritableCellFormat();
		WritableCellFormat ligneHorizontale = new WritableCellFormat();
		WritableCellFormat ligneVerticale = new WritableCellFormat();
		WritableCellFormat ligneHorizontaleBis = new WritableCellFormat();
		cadre1.setBorder(Border.TOP, BorderLineStyle.MEDIUM);
		cadre1.setBorder(Border.LEFT, BorderLineStyle.MEDIUM);
		cadre1.setBorder(Border.RIGHT, BorderLineStyle.MEDIUM);
		cadre1.setBorder(Border.BOTTOM, BorderLineStyle.MEDIUM);
		cadre2.setBorder(Border.BOTTOM, BorderLineStyle.MEDIUM);
		cadre2.setBorder(Border.LEFT, BorderLineStyle.MEDIUM);
		cadre2.setBorder(Border.RIGHT, BorderLineStyle.MEDIUM);
		ligneHorizontale.setBorder(Border.BOTTOM, BorderLineStyle.MEDIUM);
		ligneVerticale.setBorder(Border.RIGHT, BorderLineStyle.MEDIUM);
		ligneHorizontaleBis.setBorder(Border.TOP, BorderLineStyle.MEDIUM);

		// i=i+8;
		// Crée un label à la ligne 2, colonne 0 sans style prédéfini

		Label club1 = new Label(4, 3 + i, "", cadre1);
		Label nom1 = new Label(4, 4 + i, "", cadre1);
		Label ligneResultat1 = new Label(5, 3 + i, "", ligneHorizontale);
		Label ligneResultat2 = new Label(6, 3 + i, "", ligneHorizontale);
		Label club2 = new Label(4, 11 + i, "", cadre1);
		Label nom2 = new Label(4, 12 + i, "", cadre1);
		Label club3 = new Label(4, 19 + i, "", cadre1);
		Label nom3 = new Label(4, 20 + i, "", cadre1);
		Label ligneH = new Label(5, 11 + i, "", ligneHorizontale);
		Label ligneV1 = new Label(5, 12 + i, "", ligneVerticale);
		Label ligneV2 = new Label(5, 13 + i, "", ligneVerticale);
		Label ligneV3 = new Label(5, 14 + i, "", ligneVerticale);
		Label ligneV4 = new Label(5, 15 + i, "", ligneVerticale);
		Label ligneV5 = new Label(5, 16 + i, "", ligneVerticale);
		Label ligneV6 = new Label(5, 17 + i, "", ligneVerticale);
		Label ligneV7 = new Label(5, 18 + i, "", ligneVerticale);
		Label ligneV8 = new Label(5, 19 + i, "", ligneVerticale);
		Label ligneH2 = new Label(5, 20 + i, "", ligneHorizontaleBis);
		Label ligneH3 = new Label(6, 15 + i, "", ligneHorizontale);

		Label resultatclub1 = new Label(7, 3 + i, "", cadre1);
		Label resulatnomnom1 = new Label(7, 4 + i, "", cadre1);
		Label resultatclub2 = new Label(7, 15 + i, "", cadre1);
		Label resulatnomnom2 = new Label(7, 16 + i, "", cadre1);

		Label ligneresultat1 = new Label(8, 4 + i, "", ligneHorizontaleBis);
		Label ligneresultat2 = new Label(8, 15 + i, "", ligneHorizontale);

		// Ajout des cellules
		// ((WritableSheet) sheet).addCell(label);
		((WritableSheet) sheet).addCell(club2);
		((WritableSheet) sheet).addCell(ligneH);
		((WritableSheet) sheet).addCell(nom2);
		((WritableSheet) sheet).addCell(ligneV1);
		((WritableSheet) sheet).addCell(ligneV2);
		((WritableSheet) sheet).addCell(ligneV3);
		((WritableSheet) sheet).addCell(ligneV4);
		((WritableSheet) sheet).addCell(ligneV5);
		((WritableSheet) sheet).addCell(ligneV6);
		((WritableSheet) sheet).addCell(ligneV7);
		((WritableSheet) sheet).addCell(ligneV8);
		((WritableSheet) sheet).addCell(ligneH2);
		((WritableSheet) sheet).addCell(club3);
		((WritableSheet) sheet).addCell(nom3);
		((WritableSheet) sheet).addCell(ligneH3);
		((WritableSheet) sheet).addCell(club1);
		((WritableSheet) sheet).addCell(nom1);
		((WritableSheet) sheet).addCell(ligneResultat1);
		((WritableSheet) sheet).addCell(ligneResultat2);
		((WritableSheet) sheet).addCell(resultatclub1);
		((WritableSheet) sheet).addCell(resulatnomnom1);
		((WritableSheet) sheet).addCell(resultatclub2);
		((WritableSheet) sheet).addCell(resulatnomnom2);
		((WritableSheet) sheet).addCell(ligneresultat1);
		((WritableSheet) sheet).addCell(ligneresultat2);
	}

	public static int ecrireTier2et3(Sheet sheet, int typeArbre, int tailleliste) {

		int type=tailleliste%2;
		int restListe=tailleliste;
		int d=0;
		if(type==1){

			 d=8;
			try {
				ecritureUneCelluleSansNom(sheet,2,7);
				restListe=restListe-1;
				while(restListe!=0){
					ecritureArbreSimpleNiveau2(sheet,2+d,null,null,8);
					d=d+16;
					restListe=restListe-2;
				}
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		if(type==0){

			 d=0;
			try {
				while(restListe!=0){
					ecritureArbreSimpleNiveau2(sheet,2+d,null,null,8);
					d=d+16;
					restListe=restListe-2;
				}
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		
		return d;
	
	}

	public static void ecrireTier4(Sheet sheet, int i) throws WriteException {
		WritableCellFormat cadre1 = new WritableCellFormat();
		WritableCellFormat ligneHorizontale = new WritableCellFormat();
		WritableCellFormat ligneVerticale = new WritableCellFormat();
		WritableCellFormat ligneHorizontaleBis = new WritableCellFormat();
		cadre1.setBorder(Border.TOP, BorderLineStyle.MEDIUM);
		cadre1.setBorder(Border.LEFT, BorderLineStyle.MEDIUM);
		cadre1.setBorder(Border.RIGHT, BorderLineStyle.MEDIUM);
		cadre1.setBorder(Border.BOTTOM, BorderLineStyle.MEDIUM);
		ligneHorizontale.setBorder(Border.BOTTOM, BorderLineStyle.MEDIUM);
		ligneVerticale.setBorder(Border.RIGHT, BorderLineStyle.MEDIUM);
		ligneHorizontaleBis.setBorder(Border.TOP, BorderLineStyle.MEDIUM);

		for (int nb = 0; nb < 16; nb++) {
			Label ligneH = new Label(8, nb + 8 + i, "", ligneVerticale);
			((WritableSheet) sheet).addCell(ligneH);
		}
		Label ligneH2 = new Label(8, 16 + 8 + i, "", ligneHorizontaleBis);
		Label ligneH = new Label(9, 15 + i, "", ligneHorizontale);
		Label club1 = new Label(10, 15 + i, "", cadre1);
		Label nom1 = new Label(10, 16 + i, "", cadre1);
		Label ligneH3 = new Label(11, 16 + i, "", ligneHorizontaleBis);

		((WritableSheet) sheet).addCell(ligneH2);
		((WritableSheet) sheet).addCell(ligneH);

		((WritableSheet) sheet).addCell(club1);
		((WritableSheet) sheet).addCell(nom1);
		((WritableSheet) sheet).addCell(ligneH3);

	}

	public static void ecrireTier4bis3(Sheet sheet, int i)
			throws WriteException {
		WritableCellFormat cadre1 = new WritableCellFormat();
		WritableCellFormat ligneHorizontale = new WritableCellFormat();
		WritableCellFormat ligneVerticale = new WritableCellFormat();
		WritableCellFormat ligneHorizontaleBis = new WritableCellFormat();
		cadre1.setBorder(Border.TOP, BorderLineStyle.MEDIUM);
		cadre1.setBorder(Border.LEFT, BorderLineStyle.MEDIUM);
		cadre1.setBorder(Border.RIGHT, BorderLineStyle.MEDIUM);
		cadre1.setBorder(Border.BOTTOM, BorderLineStyle.MEDIUM);
		ligneHorizontale.setBorder(Border.BOTTOM, BorderLineStyle.MEDIUM);
		ligneHorizontale.setBorder(Border.LEFT, BorderLineStyle.MEDIUM);
		ligneVerticale.setBorder(Border.LEFT, BorderLineStyle.MEDIUM);
		ligneHorizontaleBis.setBorder(Border.TOP, BorderLineStyle.MEDIUM);
		i=i-4;
		for (int nb = 0; nb < 12; nb++) {
			Label ligneH = new Label(9, nb + 8 + i, "", ligneVerticale);
			((WritableSheet) sheet).addCell(ligneH);
		}

		Label ligneH = new Label(9, 13 + i, "", ligneHorizontale);
		Label club1 = new Label(10, 13 + i, "", cadre1);
		Label nom1 = new Label(10, 14 + i, "", cadre1);
		Label ligneH3 = new Label(11, 14 + i, "", ligneHorizontaleBis);


		((WritableSheet) sheet).addCell(ligneH);

		((WritableSheet) sheet).addCell(club1);
		((WritableSheet) sheet).addCell(nom1);
		((WritableSheet) sheet).addCell(ligneH3);

	}

	@SuppressWarnings("unused")
	private static void gestiontier4(Sheet sheet,int occLeft,int tailleliste) throws WriteException{
		int incrementation=0;	
		while(tailleliste>0){

			if(((tailleliste-4)>=0 || tailleliste%4==0) && tailleliste!=5){			
				ecrireTier4(sheet,incrementation);

			}
			else if(tailleliste%3==0){
				ecrireTier4bis3(sheet,incrementation);	
			}

			else if(tailleliste%5==0){
				ecrireTier4bis3(sheet,incrementation+16);	
			}

			tailleliste=tailleliste-4;
			incrementation=incrementation+32;

		}			

	}

	/* distance case est la distance entre deux case d'un meme arbre*/
	private static int ecritureArbreSimpleNiveau1(Sheet sheet,int i, Competiteur comp1,Competiteur comp2,int largeurLigneVertical)throws WriteException{
		int participant = 0;
		participant=participant+ecritureUneCelluleAvecNom(sheet,i,comp1);//ecriture de la 1ere cellule avec le 1er competiteur
		participant=participant+ecritureUneCelluleAvecNom(sheet,i+4,comp2);//ecriture de la 2nd cellule avec le 2nd competiteur avec un decalage vers le bas		


		/*lignes*/
		WritableCellFormat HorizontaleCoteBas = new WritableCellFormat();
		WritableCellFormat HorizontaleCoteHaut = new WritableCellFormat();
		WritableCellFormat Verticale = new WritableCellFormat();
		Label ligneH3 = new Label(3, 3 + i, "", HorizontaleCoteBas);
		ecritureUneCelluleSansNom(sheet, 2+i,4);

		/* definition des limites des lignes */
		HorizontaleCoteBas.setBorder(Border.BOTTOM, BorderLineStyle.MEDIUM);
		Verticale.setBorder(Border.RIGHT, BorderLineStyle.MEDIUM);
		HorizontaleCoteHaut.setBorder(Border.TOP, BorderLineStyle.MEDIUM);


		/* Construction des lignes verticales*/
		for(int x=0;x<largeurLigneVertical;x++){
			((WritableSheet) sheet).addCell(new Label(2, 2 + i + x, "", Verticale));
		}
		
		Label ligneH2 = new Label(2, 6 + i, "", HorizontaleCoteHaut);


		((WritableSheet) sheet).addCell(ligneH2);
		((WritableSheet) sheet).addCell(ligneH3);
		return participant;
	}

	private static void ecritureArbreSimpleNiveau2(Sheet sheet,int i, Competiteur comp1,Competiteur comp2,int largeurLigneVertical)throws WriteException{

		//ecritureUneCelluleSansNom(sheet, i, 7);//ecriture de la 1ere cellule avec le 1er competiteur
		//ecritureUneCelluleSansNom(sheet,i+8,7);//ecriture de la 2nd cellule avec le 2nd competiteur avec un decalage vers le bas		


		/*lignes*/
		WritableCellFormat HorizontaleCoteBas = new WritableCellFormat();
		WritableCellFormat HorizontaleCoteHaut = new WritableCellFormat();
		WritableCellFormat Verticale = new WritableCellFormat();
		//Label ligneH3 = new Label(6, 3 + i, "", HorizontaleCoteBas);
		ecritureUneCelluleSansNom(sheet, 4+i,7);

		/* definition des limites des lignes */
		HorizontaleCoteBas.setBorder(Border.BOTTOM, BorderLineStyle.MEDIUM);
		Verticale.setBorder(Border.RIGHT, BorderLineStyle.MEDIUM);
		HorizontaleCoteHaut.setBorder(Border.TOP, BorderLineStyle.MEDIUM);


		/* Construction des lignes verticales*/
		for(int x=0;x<largeurLigneVertical;x++){
			((WritableSheet) sheet).addCell(new Label(5, 2 + i + x, "", Verticale));
		}
		((WritableSheet) sheet).addCell(new Label(5, 10 + i ,"", HorizontaleCoteHaut));

	}


	/* Sert exclusivement pour le tier 1 */
	private static int ecritureUneCelluleAvecNom(Sheet sheet,int i, Competiteur comp)throws WriteException{
		int participant=0;
		/* cellule*/
		WritableCellFormat cadre = new WritableCellFormat();
		WritableCellFormat HorizontaleCoteBas = new WritableCellFormat();

		/* Limite  */
		HorizontaleCoteBas.setBorder(Border.BOTTOM, BorderLineStyle.MEDIUM);
		cadre.setBorder(Border.TOP, BorderLineStyle.MEDIUM);
		cadre.setBorder(Border.LEFT, BorderLineStyle.MEDIUM);
		cadre.setBorder(Border.RIGHT, BorderLineStyle.MEDIUM);
		cadre.setBorder(Border.BOTTOM, BorderLineStyle.MEDIUM);

		/* Insertion des parametres et construction */
		Label club = new Label(1, 1 + i, "", cadre);
		Label competiteur = new Label(1, 2 + i, "", cadre);
		if(comp!=null){ //si ne tombe pas sur un combat où il manque une personne
			club = new Label(1, 1 + i, comp.getClub().getNom(), cadre);
			competiteur = new Label(1, 2 + i, comp.getNom() + " "+ comp.getPrenom(), cadre);
			participant=1;
		}
		Label ligneCase = new Label(2, 1 + i, "", HorizontaleCoteBas);

		/* application a la feuille */
		((WritableSheet) sheet).addCell(club);
		((WritableSheet) sheet).addCell(competiteur);
		((WritableSheet) sheet).addCell(ligneCase);
		return participant;
	}

	/* reutilisable - Construit une arbre simple */
	private static void ecritureUneCelluleSansNom(Sheet sheet,int i,int x)throws WriteException{
		/* cellule*/
		WritableCellFormat cadre = new WritableCellFormat();
		WritableCellFormat HorizontaleCoteBas = new WritableCellFormat();

		/* Limite  */
		HorizontaleCoteBas.setBorder(Border.BOTTOM, BorderLineStyle.MEDIUM);
		cadre.setBorder(Border.TOP, BorderLineStyle.MEDIUM);
		cadre.setBorder(Border.LEFT, BorderLineStyle.MEDIUM);
		cadre.setBorder(Border.RIGHT, BorderLineStyle.MEDIUM);
		cadre.setBorder(Border.BOTTOM, BorderLineStyle.MEDIUM);


		/* Insertion des parametres et construction */
		Label club = new Label(x, 1 + i, "", cadre);
		Label competiteur = new Label(x, 2 + i, "", cadre);

		Label ligneCase = new Label(x+1, 1 + i, "", HorizontaleCoteBas);

		/* pour ecrire une cellule complete avec la tete et la queue */
		if(x!=1){
			Label ligneCaseAvantCase = new Label(x-1, 1 + i, "", HorizontaleCoteBas);
			((WritableSheet) sheet).addCell(ligneCaseAvantCase);
		}

		/* application a la feuille */
		((WritableSheet) sheet).addCell(club);
		((WritableSheet) sheet).addCell(competiteur);
		((WritableSheet) sheet).addCell(ligneCase);
	}

}
