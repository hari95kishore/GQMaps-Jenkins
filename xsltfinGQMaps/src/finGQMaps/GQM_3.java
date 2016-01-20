package finGQMaps;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import TestDataExcel.readExcel;

public class GQM_3 {
	
	WebDriver driver;
	 DesiredCapabilities capabilities= DesiredCapabilities.firefox();

	@Test
	public void loginValid() throws InterruptedException, IOException
	{
		Properties properties = new Properties();
		FileInputStream fis= new FileInputStream("C:\\GQMaps_workspace\\xsltfinGQMaps\\globalProperties.properties");
		properties.load(fis);
		if(properties.getProperty("mode").equalsIgnoreCase("grid") && properties.getProperty("browser").equalsIgnoreCase("firefox"))
			driver = new RemoteWebDriver(new java.net.URL("http://192.168.8.81:5566/wd/hub"), DesiredCapabilities.firefox());
		
		driver.manage().deleteAllCookies();
		File file= new File(properties.getProperty("excelSheetsPath")+"\\LoginModuleData.xlsx");
		String sheetname="LoginModuleData";
		readExcel rd  = new readExcel(file,sheetname);
		ArrayList<String> testData= rd.readData("GQM_3");
	    String username=testData.get(0);
	    String password= testData.get(1);
		driver.get(" http://52.74.175.175:8080/GQMapsCustomerUI/");
		driver.findElement(By.xpath(".//*[@id='txtUserId']")).sendKeys(username);
		driver.findElement(By.xpath(".//*[@id='pwdPassword']")).sendKeys(password);
		driver.findElement(By.xpath(".//*[@id='submitLogin']")).click();
		Thread.sleep(4000);
		if(driver.findElement(By.xpath(".//*[@id='entpName']")).isDisplayed())
		System.out.println(driver.getCurrentUrl());
		driver.close();
	}


}
