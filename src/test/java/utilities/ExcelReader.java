package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Log4j2.LoggerLoad;

public class ExcelReader {

    public static String xlsxFilePath= "src\\test\\resources\\orangeLogin.xlsx";
    int totalRow;
   // G:\My Drive\SDET-146-Assignments-Projects\LMS_TestData.xlsx
    
	public List<Map<String,String>> getTestDataFromSheet(String sheetName) throws IOException
	{
		File file = new File(xlsxFilePath);
		XSSFWorkbook workbook = null;
		XSSFSheet sheet = null;
		try{
		FileInputStream inputStream = new FileInputStream(file);
		workbook = new XSSFWorkbook(inputStream);		   
	    sheet = workbook.getSheet(sheetName);		  
			
		} catch(Exception e) {
			LoggerLoad.error("Error while reading test data from excel file");
		} finally {
			if(workbook != null)
				workbook.close();
		}
		return  readSheet(sheet);
	}
	
	private List<Map<String,String>> readSheet(XSSFSheet sheet) {
	    
		
	  int totalRow=sheet.getPhysicalNumberOfRows();
			
	  List<Map<String,String>> excelRows = new ArrayList<Map<String,String>>();
			
	  for(int currentRow=1; currentRow <= totalRow; currentRow++)
			{
				XSSFRow row=sheet.getRow(currentRow);
				if(null != row) {
					int totalCol = row.getLastCellNum();
					
					Map<String,String> colMapData = new HashMap<String,String>();
					
					for(int currentCol=0; currentCol < totalCol; currentCol++)
					{						
						String colHeaderName= sheet.getRow(sheet.getFirstRowNum()).getCell(currentCol).getStringCellValue();
						XSSFCell cell=row.getCell(currentCol);
						if(cell!=null) {							
							if(cell.getStringCellValue() instanceof String) {
								colMapData.put(colHeaderName, cell.getStringCellValue());
							} else {
								colMapData.put(colHeaderName, cell.getNumericCellValue()+"");
							}
							
						} else {
							colMapData.put(colHeaderName, "");
						}												
				    }				    
					excelRows.add(colMapData);
				}
				
			}
			return excelRows;
			
	}
	
	
	
}
		
		
		
	



