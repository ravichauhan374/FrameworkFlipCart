package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import dataProvider.ConfigFileReader;


public class TestUtil {
	ConfigFileReader configFileReader;
	Logger log = Logger.getLogger(TestUtil.class);
	
	//Launch driver for Chrome browser
	public WebDriver chrome(){
		 configFileReader= new ConfigFileReader();
		 System.setProperty("webdriver.chrome.driver", configFileReader.getDriverPath("chrome"));
		 WebDriver driver= new ChromeDriver();	
		 log.info("Chrome Browser is up and Running");
		 return driver;
	}
	
	//Launch driver for FireFox browser
	public WebDriver fireFox(){
		 configFileReader= new ConfigFileReader();
		 System.setProperty("webdriver.gecko.driver", configFileReader.getDriverPath("fireFox"));
		 WebDriver driver= new FirefoxDriver();	
		 log.info("Firefox Browser is up and Running");
		 return driver;
	}
	
	//Launch driver for InternetExplorer browser
	public WebDriver internetExplorer(){
		 configFileReader= new ConfigFileReader();
		 System.setProperty("webdriver.ie.driver", configFileReader.getDriverPath("internetExplorer"));
		 WebDriver driver= new InternetExplorerDriver();	
		 log.info("InternetExplorer Browser is up and Running");
		 return driver;
	}
	
	//Launch Url in the browser and it can be change from config file
	public WebDriver launchUrl(WebDriver driver){
		configFileReader= new ConfigFileReader();
		driver.get(configFileReader.getApplicationUrl());
		log.info("launching the URL");
		return driver;
	}
	
	//maximizing the browser application
	public WebDriver maximize(WebDriver driver){
		driver.manage().window().maximize();
		log.info("Maximize the browser window");
		return driver;
	}
	
	//closing the application
	public void close(WebDriver driver){
		driver.close();
		log.info("Driver is close");
	}
	
	//closing the application
	public void quit(WebDriver driver){
		driver.quit();
		log.info("Driver is quit");
	}
	
	//implicitlyWait set as company standards(It can be change in config file)
	public WebDriver implicitlyWait(WebDriver driver){
		configFileReader= new ConfigFileReader();
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		log.info("Using Implicit Wait for 10 seconds");
		return driver;
	}
	
	
	
	
	
	
	
	
	
	
	//for taking the screenshot
	public WebDriver captureScreenMethod(WebDriver driver){
		try{
		File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile, new File("D:\\SoftwareTestingMaterial.png"));
		}
		catch(IOException e){
			log.error("not able to generate file" + e.getMessage());
		}
		return driver;
	}
	
	//for reading data from excel sheet
	public String ReadExcel(int getSheet, int getRowData,int getCellData){
		String cellval = null;
		try{
			FileInputStream fis = new FileInputStream("D:\\Test.xlsx");
			HSSFWorkbook workbook = new HSSFWorkbook(fis);
			HSSFSheet sheet = workbook.getSheetAt(getSheet);
            Row row = sheet.getRow(getRowData);
			Cell cell = row.getCell(getCellData);
			cellval= cell.getStringCellValue();
			log.info("Data is read from excel: "+ cellval);
			}
			catch(IOException e){
				log.error("not able to found excel file" + e.getMessage());
			}
		return cellval;
	}
	
	//for writing data to excel sheet
	public void WriteExcel(String setSheet,int setRowData,int setCellData, String cellValue){
		try{
			FileInputStream fis = new FileInputStream("D:\\Test.xlsx");
			HSSFWorkbook workbook = new HSSFWorkbook(fis);
			HSSFSheet sheet = workbook.getSheet(setSheet);
			Row row = sheet.createRow(setRowData);
			Cell cell = row.createCell(setCellData);
			cell.setCellType(CellType.STRING);
			cell.setCellValue("SoftwareTestingMaterial.com");
			FileOutputStream fos = new FileOutputStream(cellValue);
			workbook.write(fos);
			fos.close();
			log.info("Data is Write in excel: "+ cellValue);
			}
			catch(IOException e){
				log.error("not able to found excel file" + e.getMessage());
			}
	}
}
