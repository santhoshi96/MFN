package launchCleanUp;

import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ScanExcelData {
 
	public static String[] readMID(String sheetName) throws InvalidFormatException, IOException {
		File excelFilename = new File("./ExcelData/Merchant_id.xlsx");
		XSSFWorkbook wB = new XSSFWorkbook(excelFilename);
		
		XSSFSheet sheet = wB.getSheet(sheetName);
		
		int rowCount = sheet.getLastRowNum();
		int columnCount = sheet.getRow(0).getLastCellNum();
		
		String[] data = new String[rowCount];
		
		for (int i = 1; i <=rowCount; i++) {
			XSSFRow row = sheet.getRow(i);
		//for (int j = 0; j <columnCount; j++) {
			XSSFCell cell = row.getCell(0);
			//System.out.println(cell.getStringCellValue());
			data[i-1]= cell.getStringCellValue();
		}
		
		return data;
		
	}

}
