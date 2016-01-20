package TestDataExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class readExcel {
	
	Properties prop= new Properties();
	
	public XSSFWorkbook workbook;
	public  XSSFSheet sheet;
	
	
 public readExcel(File file,String sheetName) throws IOException
 {
	 FileInputStream fis= new FileInputStream(file);
	 workbook= new XSSFWorkbook(fis);
	 sheet = workbook.getSheet(sheetName);
 }
 
 public ArrayList<String> readData(String tc_id) throws FileNotFoundException
 {
	 ArrayList<String> data= new ArrayList<String>();
	
	 for(int i=0;i<sheet.getPhysicalNumberOfRows();i++)
	 {
		 int j=0;
		 Row row= sheet.getRow(i);
		 if(row.getCell(j).getStringCellValue().equalsIgnoreCase(tc_id))
		 {
			 j=1;
			 System.out.println(tc_id+" is present in excel and data provided is as follows:");
			 for(j=1;j<row.getLastCellNum();j++)
			 {
				 data.add(row.getCell(j).getStringCellValue());
				 System.out.println(data.get(j-1));
				
			 }
			break;
		 }
		 
	 }
	 return data;
 }
 
}
