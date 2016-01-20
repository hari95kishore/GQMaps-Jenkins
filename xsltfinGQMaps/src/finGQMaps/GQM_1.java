package finGQMaps;





import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import testDataBase.DBDataExtractor;
import TestDataExcel.readExcel;



public class GQM_1 extends DBDataExtractor {
	
	
	

	WebDriver driver;
	
	
    @BeforeSuite
	public void deleteCookies() throws MalformedURLException
	{
		
		
	}
	
	@Test
	public void loginInvalidUser() throws IOException, ClassNotFoundException, SQLException
	{
		System.setProperty("newlogfile.name", "GQM_1");
		Logger logger=Logger.getLogger("GQM_1");
		PropertyConfigurator.configure("log4j.properties");
		Properties properties = new Properties();
		FileInputStream fis= new FileInputStream("C:\\GQMaps_workspace\\xsltfinGQMaps\\globalProperties.properties");
		properties.load(fis);
		if(properties.getProperty("mode").equalsIgnoreCase("grid") && properties.getProperty("browser").equalsIgnoreCase("firefox"))
			driver = new FirefoxDriver();
		driver.manage().deleteAllCookies();
		//File file= new File(properties.getProperty("excelSheetsPath")+"\\LoginModuleData.xlsx");
		//String sheetname="LoginModuleData";
		//readExcel rd  = new readExcel(file,sheetname);
		String username=null,password=null;
		ResultSet res=DBConnection("GQM_1");
		while(res.next())
		{
			
			username=res.getString("username");
			System.out.println(username);
			password=res.getString("password");
			System.out.println(password);
		}
		
		
		logger.info("launching GQMaps");
		driver.get(properties.getProperty("url"));
		try
		{
		driver.findElement(By.xpath(".//*[@id='txtUserId']")).sendKeys(username);
		logger.info("username web element is present");
		}
		catch(Exception e)
		{
			logger.error("failed");
		}
		try
		{
		driver.findElement(By.xpath(".//*[@id='pwdPassword']")).sendKeys(password);
		logger.info("password webelement is present");
		}
		catch(Exception e)
		{
			logger.info("password webelement is not present");
		}
		driver.findElement(By.xpath(".//*[@id='submitLogin']")).click();
		Alert a= driver.switchTo().alert();
		System.out.println(a.getText());
		driver.close();
	
	}
}
