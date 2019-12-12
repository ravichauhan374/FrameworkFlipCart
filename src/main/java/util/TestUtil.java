package util;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import dataProvider.ConfigFileReader;


public class TestUtil {
	ConfigFileReader configFileReader;
	Logger log = Logger.getLogger(TestUtil.class);
	WebDriver driver;
	
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
	
	public WebElement findElementCSS(WebDriver driver,By csselement){
		WebElement element=null;
		try{
			if(driver.findElement(csselement).isDisplayed()){
				element= driver.findElement(csselement);
			}
			element= fluentWait(driver,csselement);
			}catch(Exception e){
				log.error("Unable to launch URL: " + e.getMessage());
				Assert.assertNull(e);
				}
		return element;
		}
	
	
	@SuppressWarnings("deprecation")
	public WebElement fluentWait(WebDriver driver,By csselement){
		WebElement element=null;
		try{
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)							
					.withTimeout(30, TimeUnit.SECONDS) 			
					.pollingEvery(5, TimeUnit.SECONDS) 			
					.ignoring(NoSuchElementException.class);
			
			element = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					WebElement element = driver.findElement(csselement);
					if(element.isDisplayed())
						{
						return element;
						}
					return element=null;
					}
				});
				}catch(Exception e){
					log.error("Unable to find element"+ csselement.toString() +" after 30 second: " + e.getMessage());
					Assert.assertNull(e);
		}
		return element;
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
	
	//JavaScript
	public void javaScriptExecutorClick(WebElement element){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", element);
	}
	
	public void javaScriptExecutorScroll(){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,600)");	
	}
	
	
}
