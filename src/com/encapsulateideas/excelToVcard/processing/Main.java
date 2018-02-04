package com.encapsulateideas.excelToVcard.processing;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Main {
	public static boolean convert(File excel, File vcard,int namecell, int numbercell){
		AppendVCard avc = new AppendVCard(vcard.getAbsolutePath());
	      FileInputStream fIP;
		try {
			  FileInputStream fis = new FileInputStream(excel);
		      XSSFWorkbook workbook = new XSSFWorkbook(fis);
		      XSSFSheet spreadsheet = workbook.getSheetAt(0);
		      FormulaEvaluator objFormulaEvaluator = new XSSFFormulaEvaluator(workbook);
		      DataFormatter objDefaultFormat = new DataFormatter();
		      Iterator < Row >  rowIterator = spreadsheet.iterator();
		      while (rowIterator.hasNext()) {
		    	  Row row = rowIterator.next();
		    	  String name = row.getCell(namecell-1).toString();
		    	  Cell cellValue = row.getCell(numbercell-1);
		    	  objFormulaEvaluator.evaluate(cellValue); 
		    	  String number = objDefaultFormat.formatCellValue(cellValue,objFormulaEvaluator);
		    	  avc.appendFile(name, number);
		    	 /* System.out.println(name+"-->"+number);*/
		      }
		System.out.println("Done!!!");
		return true;
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
}
