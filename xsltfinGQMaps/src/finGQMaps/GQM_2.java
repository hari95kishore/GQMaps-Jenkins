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

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.lf5.LogLevel;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import testDataBase.DBDataExtractor;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;











import ExtentReports.ExtentReportManager;
import ExtentReports.NewLogForEachRunFileAppender;
import TestDataExcel.readExcel;

public class GQM_2 extends DBDataExtractor{
	WebDriver driver;
	
		@Test
	public void loginInvalidPass() throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
			System.setProperty("newlogfile.name", "GQM_2");
			String className = this.getClass().getSimpleName();
			System.out.println(className);
		Properties properties = new Properties();
		FileInputStream fis= new FileInputStream("C:\\GQMaps_workspace\\xsltfinGQMaps\\globalProperties.properties");
		properties.load(fis);
		if(properties.getProperty("mode").equalsIgnoreCase("grid") && properties.getProperty("browser").equalsIgnoreCase("firefox"))
		{
			
			driver = new FirefoxDriver();
		}
		 ExtentReports extent = ExtentReportManager.getInstance();
		 ExtentTest test;
		 Logger logger=Logger.getLogger("GQM_2");
		 PropertyConfigurator.configure("log4j.properties");
		// NewLogForEachRunFileAppender nfl =new NewLogForEachRunFileAppender();
		// nfl.setFile(className);

test=extent.startTest("GQM_2", "monitoring check in GQMaps");
		
		driver.manage().deleteAllCookies();
		File file= new File(properties.getProperty("excelSheetsPath")+"\\LoginModuleData.xlsx");
		String username=null,password=null,asset_id=null,date=null;
		ResultSet res=DBConnection("GQM_2");
		while(res.next())
		{
			username=res.getString("username");
			System.out.println(username);
			password=res.getString("password");
			System.out.println(password);
			asset_id=res.getString("assetid");
			System.out.println(asset_id);
			date=res.getString("date");
			System.out.println(date);
		}
	    test.log(LogStatus.INFO, "Launching GQMaps");
		driver.get(" http://52.74.175.175:8080/GQMapsCustomerUI/");
		logger.info("launching GQMaps");
		if(driver.findElement(By.xpath(".//*[@id='txtUserId']")).isDisplayed())
		{
			driver.findElement(By.xpath(".//*[@id='txtUserId']")).sendKeys(username);
			logger.info("username web element is present-passed");
			test.log(LogStatus.PASS, "Verifying username present", "username web element is present");
		}
		else
		{
			test.log(LogStatus.FAIL, "Verifying username present", "username web element is not present");
			logger.error("username web element is not present");
		}
		
		if(driver.findElement(By.xpath(".//*[@id='pwdPassword']")).isDisplayed())
		{
			driver.findElement(By.xpath(".//*[@id='pwdPassword']")).sendKeys(password);
			logger.info("password web element is present-passed");
			test.log(LogStatus.PASS, "Verifying password present", "password web element is present");
		}
		else
		{
			test.log(LogStatus.FAIL, "Verifying password present", "password web element is not present");
			logger.error("password web element is not present");
		}
			
		//driver.findElement(By.xpath(".//*[@id='pwdPassword']")).sendKeys(password);
		driver.findElement(By.xpath(".//*[@id='submitLogin']")).click();
		//Thread.sleep(1000);
		try
		{
			driver.findElement(By.xpath(".//*[@id='cmbGoals']/li[2]/a")).click();
			logger.info("monitoring web element is present-passed");
			test.log(LogStatus.PASS, "Verifying Monitoring present", "monitoring web element is present");
		}
		catch(Exception e)
		{ 
			test.log(LogStatus.FAIL, "Verifying monitoring present", e.toString());
			logger.error("monitoring web element is not present");
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			// Now you can do whatever you need to do with it, for example copy somewhere
			FileUtils.copyFile(scrFile, new File("C:\\GQMaps_workspace\\xsltfinGQMaps\\ExtentReports\\Screenshots\\screenshot.png"));
		test.addScreenCapture("C:\\GQMaps_workspace\\xsltfinGQMaps\\ExtentReports\\Screenshots\\screenshot.png");
		}
		//driver.findElement(By.xpath(".//*[@id='cmbGoals']/li[2]/a")).click();
		try
		{
		driver.findElement(By.xpath(".//*[@id='AssetId']")).sendKeys(asset_id);
		logger.info("asset_id webelement is present");
		}
		catch(Exception e)
		{
			logger.error("asset_id web element is not present");
		}
		driver.findElement(By.xpath(".//*[@id='Date']")).sendKeys(date);
		driver.findElement(By.xpath(".//*[@id='btnAsset']")).click();
		driver.close();
		 driver.quit();
	        extent.endTest(test);
	        extent.flush();
	}
}
