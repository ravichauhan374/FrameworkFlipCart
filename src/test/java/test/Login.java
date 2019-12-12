package test;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.LoginPage;


public class Login extends TestBase{
	LoginPage loginPage;
	Logger log = Logger.getLogger(Login.class);
	@Test
	public void testLogin(){
		boolean istestLogin = false;
		loginPage = new LoginPage(driver);
		try{
			if(loginPage.enterLoginCred()){
				istestLogin = true;
			}
			
		}catch(Exception e){
			log.error("Unable to Login to the Application", e);
		}
		Assert.assertTrue(istestLogin, "Login Test Passed");
	}
}
