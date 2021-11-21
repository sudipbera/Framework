package learnautomation.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataProvider {
	
	//This constructor will load the excel as soon as we create object of this class,
	//We dont have to provide excel path again and again
	
	XSSFWorkbook wb;
	public ExcelDataProvider()
	{
		
		File src=new File("./TestData/Data.xlsx");
		try {
			FileInputStream fis = new FileInputStream(src); //convert excel data to raw data
			
			wb = new XSSFWorkbook(fis);
		}  catch (Exception e) {
			
			System.out.println("Unable to read Excel File"+e.getMessage());
		}
		
	}
	
	public String getStringData(int sheetIndex, int row, int cell)
	{
		
		return wb.getSheetAt(sheetIndex).getRow(row).getCell(cell).getStringCellValue();
	}
	
	public String getStringData(String sheetName, int row, int cell)
	{
		
		return wb.getSheet(sheetName).getRow(row).getCell(cell).getStringCellValue();
	}
	
	public double getNumericData(String sheetName, int row, int cell)
	{
		
		return wb.getSheet(sheetName).getRow(row).getCell(cell).getNumericCellValue();
	}

}
