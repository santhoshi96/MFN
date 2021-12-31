package launchCleanUp;

import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FetchExcelData {

	public static String[][] FdExcelData(String sheetName) throws InvalidFormatException, IOException {
		
		File excelFilename = new File("./ExcelData/MFNaccount.xlsx");
		XSSFWorkbook wB = new XSSFWorkbook(excelFilename);
		
		XSSFSheet sheet = wB.getSheet(sheetName);
		
		int rowCount = sheet.getLastRowNum();
		int columnCount = sheet.getRow(0).getLastCellNum();
		
		String[][] data = new String[rowCount][columnCount];
		
		for (int i = 1; i <=rowCount; i++) {
			XSSFRow row = sheet.getRow(i);
		for (int j = 0; j <columnCount; j++) {
			XSSFCell cell = row.getCell(j);
			System.out.println(cell.getStringCellValue());
			data[i-1][j]= cell.getStringCellValue();
		}
		}
		return data;
		
	}

	}


