package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage{
	@FindBy(xpath="//a[contains(@href,'login?')]")
	private WebElement loginButton;
	@FindBy(xpath="//div[@class='_1XBjg- row']//div[@class='_39M2dM JB4AMj']/input[@type='text']")
	private WebElement loginTextField;
	@FindBy(xpath="//div[@class='_1XBjg- row']//div[@class='_39M2dM JB4AMj'][2]/input[@type='password']")
	private WebElement passwordTextField;
	@FindBy(xpath="//div[@class='_1XBjg- row']//button[@type='submit']")
	private WebElement button;

	@FindBy(xpath="//div[@class='_1XBjg- row']")
	private WebElement containerBox;
	
	Logger log = Logger.getLogger(HomePage.class);
	WebDriver driver;
	public HomePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public boolean enterLoginCred() throws Exception{
		boolean isenterLoginCred = false;
		try{
			if(!(containerBox.isDisplayed())) {
				loginButton.click();
			}
			if(loginTextField.isDisplayed()) {
				loginTextField.click();
				loginTextField.sendKeys("hghfhgf");
				log.info("Entered emailID/Phone Number successfully");
				if(passwordTextField.isDisplayed()) {
					passwordTextField.click();
					passwordTextField.sendKeys("jhj");
					log.info("Entered Password successfully");
					if(button.isEnabled()) {
						button.click();
						log.info("clicked Login Button successfully");
						isenterLoginCred = true;
					}
				}
			}
		}catch(Exception e){
			log.error("Unable to enter Login Cred", e);
			throw new Exception(e);
		}
		return isenterLoginCred;
	}
	
}