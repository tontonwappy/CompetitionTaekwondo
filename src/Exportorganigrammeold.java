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

public class Exportorganigrammeold {

	public static void exportExcel() {

		try {
			WritableWorkbook workbook = Workbook.createWorkbook(new File(
					"sortie.xls"));

			for (ListeCombat li : Controleur.listCategorieCombat) {
				System.out.println("+++++++++++++++++++++"
						+ li.getCategorie().getNom());
				if (li.getCategorie().getNom().equals("Poussins 2")) {
					System.out.println("test");
				}
				if (li.getGenre().equals("H")) {
					WritableSheet sheet = workbook.createSheet(li
							.getCategorie().getNom() + " Masculin", 0);
					WritableCellFormat cadre1 = new WritableCellFormat();

					Label nom1 = new Label(6, 1, "CATEGORIE : "
							+ li.getCategorie().getNom() + " Masculin", cadre1);
					((WritableSheet) sheet).addCell(nom1);

					int i = 0;
					int tailleliste = li.getListeCombat().size();
					int typeArbre = tailleliste % 4;
					System.out.println(li.getCategorie().getNom() + " "
							+ li.getGenre() + " " + typeArbre);

					for (Combat comb : li.getListeCombat()) {
						ecrireSimpleArbre(sheet, i,
								comb.getPremierCombattant(),
								comb.getDeuxiemeCombattant());
						i = i + 8;
					}
					int nbOccurence = ecrireTier2et3(sheet, typeArbre,
							tailleliste);
					gestiontier4(sheet, 0, tailleliste);

				}

				else if (li.getGenre().equals("Mixte")) {

					WritableSheet sheet = workbook.createSheet(li
							.getCategorie().getNom() + " Mixte", 0);
					WritableCellFormat cadre1 = new WritableCellFormat();

					Label nom1 = new Label(6, 1, "CATEGORIE : "
							+ li.getCategorie().getNom() + " Mixte", cadre1);
					((WritableSheet) sheet).addCell(nom1);
					int i = 0;
					int tailleliste = li.getListeCombat().size();
					int typeArbre = tailleliste % 4;
					System.out.println(li.getCategorie().getNom() + " "
							+ li.getGenre() + " " + typeArbre);
					for (Combat comb : li.getListeCombat()) {
						ecrireSimpleArbre(sheet, i,
								comb.getPremierCombattant(),
								comb.getDeuxiemeCombattant());
						i = i + 8;
					}
					int nbOccurence = ecrireTier2et3(sheet, typeArbre,
							tailleliste);
					gestiontier4(sheet, 0, tailleliste);

				} else {
					WritableSheet sheet = workbook.createSheet(li
							.getCategorie().getNom() + " Feminin", 0);
					WritableCellFormat cadre1 = new WritableCellFormat();

					Label nom1 = new Label(6, 1, "CATEGORIE : "
							+ li.getCategorie().getNom() + " Feminin", cadre1);
					((WritableSheet) sheet).addCell(nom1);
					int i = 0;
					int tailleliste = li.getListeCombat().size();
					int typeArbre = tailleliste % 4;
					System.out.println(li.getCategorie().getNom() + " "
							+ li.getGenre() + " " + typeArbre);
					for (Combat comb : li.getListeCombat()) {

						ecrireSimpleArbre(sheet, i,
								comb.getPremierCombattant(),
								comb.getDeuxiemeCombattant());
						i = i + 8;
					}

					int nbOccurence = ecrireTier2et3(sheet, typeArbre,
							tailleliste);
					gestiontier4(sheet, 0, tailleliste);

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

	public static void ecrireSimpleArbre(Sheet sheet, int i, Competiteur comp1,
			Competiteur comp2) throws WriteException {
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
		Label nom1 = new Label(1, 1 + i, comp1.getClub().getNom(), cadre1);
		Label nomsuite1 = new Label(1, 2 + i, comp1.getNom() + " "
				+ comp1.getPrenom(), cadre1);
		Label ligneH = new Label(2, 1 + i, "", ligneHorizontale);
		Label ligneV1 = new Label(2, 2 + i, "", ligneVerticale);
		Label ligneV2 = new Label(2, 3 + i, "", ligneVerticale);
		Label ligneV3 = new Label(2, 4 + i, "", ligneVerticale);
		Label ligneV4 = new Label(2, 5 + i, "", ligneVerticale);
		Label ligneH2 = new Label(2, 6 + i, "", ligneHorizontaleBis);
		Label case2 = new Label(1, 5 + i, "", cadre1);
		Label case2bis = new Label(1, 6 + i, "", cadre1);
		if (comp2 != null) {
			case2 = new Label(1, 5 + i, comp2.getClub().getNom(), cadre1);
			case2bis = new Label(1, 6 + i, comp2.getNom() + " "
					+ comp2.getPrenom(), cadre1);
		}

		Label ligneH3 = new Label(3, 3 + i, "", ligneHorizontale);

		// Ajout des cellules
		// ((WritableSheet) sheet).addCell(label);
		((WritableSheet) sheet).addCell(nom1);
		((WritableSheet) sheet).addCell(ligneH);
		((WritableSheet) sheet).addCell(nomsuite1);
		((WritableSheet) sheet).addCell(ligneV1);
		((WritableSheet) sheet).addCell(ligneV2);
		((WritableSheet) sheet).addCell(ligneV3);
		((WritableSheet) sheet).addCell(ligneV4);
		((WritableSheet) sheet).addCell(ligneH2);
		((WritableSheet) sheet).addCell(case2);
		((WritableSheet) sheet).addCell(case2bis);
		((WritableSheet) sheet).addCell(ligneH3);
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
		int nbOccurence = 0;

		if (typeArbre == 0) {
			int taille = tailleliste / 2;
			int z = 0;
			int e = 0;
			while (z < taille) {
				try {
					ecrireArbreSecondTour4(sheet, e);
					nbOccurence = nbOccurence + 1;
				} catch (WriteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e = e + 16;
				z = z + 1;
			}
		}

		if (typeArbre == 1) {
			int e = 0;
			try {
				int taille = tailleliste / 2;
				int z = 0;

				while (z < taille - 1) {
					ecrireArbreSecondTour4(sheet, e);
					e = e + 16;
					z = z + 1;
				}
			} catch (WriteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				ecrireArbreSecondTour3(sheet, e);
				nbOccurence = nbOccurence + 2;
			} catch (WriteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

		if (typeArbre == 2) {
			int taille = tailleliste / 2;
			int z = 0;
			int e = 0;
			while (z < taille) {
				try {
					ecrireArbreSecondTour4(sheet, e);
					nbOccurence = nbOccurence + 1;
				} catch (WriteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e = e + 16;
				z = z + 1;

			}
		}

		if (typeArbre == 3) {
			// int taille=tailleliste/3;
			int x = tailleliste - typeArbre;
			int e = 0;
			int z = 0;
			while (z < x) {
				try {
					ecrireArbreSecondTour4(sheet, e);
					nbOccurence = nbOccurence + 1;
				} catch (WriteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				z = z + 2;
				e = e + 16;
			}
			try {
				ecrireArbreSecondTour3(sheet, e);
				nbOccurence = nbOccurence + 2;
			} catch (WriteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return nbOccurence;
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
}
