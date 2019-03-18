package test;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import util.TestUtil;

public class Login{
	Logger log = Logger.getLogger(Login.class);
	@Test
	public void login1(){
		WebDriver driver=null;
		TestUtil ut= new TestUtil();
		try{
			driver=ut.chrome();
		}catch(Exception e){
			log.error("driver not initilase: " + e.getMessage());
			Assert.assertNotEquals(null, driver);
		}
		
	}
}
