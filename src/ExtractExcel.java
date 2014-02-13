import java.io.File;
import java.io.IOException;

import jxl.*; 
import jxl.read.biff.BiffException;

public class ExtractExcel {

	public static void exctractData(String nomFichier, String typedonnee){
		try {
			Workbook workbook = Workbook.getWorkbook(new File(nomFichier));
			Sheet sheet = workbook.getSheet(0); 
			int i=3;	
			

		
			while(!(sheet.getCell(0,i).getContents().equals("fin")) ){
				boolean doublon=false;
				for(Club cl:Competition.listClub){
					for(Competiteur comp :cl.getListCompetiteur()){
						if((sheet.getCell(0,i).getContents().equals(comp.getNom()) && sheet.getCell(1,i).getContents().equals(comp.getPrenom())))					
								{
								doublon=true;
								}
					}
				}	
			if (!doublon){
				//System.out.println(sheet.getCell(1,0).getContents());
				Club club = Controleur.rechercheClub(sheet.getCell(1,0).getContents());
				//Club club = Controleur.rechercheClub("U.S.S.E");
				System.out.println(sheet.getCell(0,i).getContents());
				System.out.println(sheet.getCell(1,i).getContents());
				System.out.println(Integer.parseInt(sheet.getCell(2,i).getContents()));
				System.out.println(sheet.getCell(3,i).getContents());
				System.out.println(club.getNom());
				System.out.println("..................");
				Competiteur nouveauCompetiteur=new Competiteur(sheet.getCell(0,i).getContents(),sheet.getCell(1,i).getContents(),Integer.parseInt(sheet.getCell(2,i).getContents()),sheet.getCell(3,i).getContents(),club);
				Controleur.inserCompetiteur(nouveauCompetiteur,Controleur.rechercheClub(sheet.getCell(1,0).getContents()));
				@SuppressWarnings("unused")
				Categorie cat =Controleur.inserCombattantCategorie(nouveauCompetiteur);
				
		
			}
				i=i+1;
			}		
			
			System.out.println("***********************************----------------------******************************");
			for(Club cl:Competition.listClub){
				System.out.println(cl.getNom());
				for(Competiteur comp :cl.getListCompetiteur()){
					try{
					System.out.println(comp.toString());
					} catch (NullPointerException  e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("**********************");
			}	
			
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
