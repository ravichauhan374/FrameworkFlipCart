package test;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import util.TestUtil;

public class TestBase {
	Logger log = Logger.getLogger(TestBase.class);
	WebDriver driver=null;
	TestUtil ut= new TestUtil();
	
	@BeforeMethod
	public void beforeMethod(){
				
	//Launch the driver or browser	
	try{
			driver=ut.chrome();
		}catch(Exception e){
			log.error("driver not initilase: " + e.getMessage());
			Assert.assertNotEquals(null, driver);
		}
	
	//Launch the URL	
		try{
				driver=ut.launchUrl(driver);
			}catch(Exception e){
				log.error("Unable to launch URL: " + e.getMessage());
				Assert.assertNull(e);
			}	
		ut.maximize(driver);
		ut.implicitlyWait(driver);
		}
	
//	@AfterMethod
//	public void afterMethod(){
//		ut.close(driver);
//	}
}
