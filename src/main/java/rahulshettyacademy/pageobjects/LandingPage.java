package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "#userEmail")
	WebElement username;

	@FindBy(css = "#userPassword")
	WebElement passcode;

	@FindBy(css = ".btn")
	WebElement submit;
	
	//div[class='ng-tns-c4-5 toast-message ng-star-inserted']
	
	@FindBy(css="div[aria-label='Incorrect email or password.']")
	WebElement errormsgs;

	public productscatalogue loginapplication(String usernames, String passcod) {

		username.sendKeys(usernames);
		passcode.sendKeys(passcod);
		submit.click();
		productscatalogue productcatalogue = new productscatalogue(driver);
		return productcatalogue;

	}
	
	
	
	public String errormsg() {
		
		waitForElementToAppears(errormsgs);
		return errormsgs.getText();
		
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}

}
