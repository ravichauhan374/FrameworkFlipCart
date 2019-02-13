package util;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import dataProvider.ConfigFileReader;


public class BeforeAfterTest {
	ConfigFileReader configFileReader;
	static public WebDriver driver;
	Logger log = Logger.getLogger(BeforeAfterTest.class);
	
	@BeforeMethod
	public void beforeMethod() {
		
			 configFileReader= new ConfigFileReader();
			 System.setProperty("webdriver.chrome.driver", configFileReader.getDriverPath());
			 driver = new ChromeDriver();
			 log.info("chrome Browser is up and Running");
			 driver.manage().window().maximize();
			 driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
			 driver.get(configFileReader.getApplicationUrl());
			 log.info("launching the URL");
		}
	
	@AfterMethod
	public void afterMethod() {
			driver.close();
			log.info("chrome Browser is closed successfully");
		}

}
