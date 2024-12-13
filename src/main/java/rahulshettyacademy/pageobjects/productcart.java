package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class productcart extends AbstractComponent {

	WebDriver driver;

	public productcart(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "div[class='cartSection'] h3")
	List<WebElement> cart;
	@FindBy(xpath = "//*[text()='Checkout']")
	WebElement checkout;
	// List<WebElement> cart=
	// driver.findElements(By.cssSelector("div[class='cartSection'] h3"));
	// boolean match =cart.stream().anyMatch(s->s.getText().equalsIgnoreCase("ADIDAS
	// ORIGINAL"));

	public boolean cartpage(String productname) {

		boolean match = cart.stream().anyMatch(s -> s.getText().equalsIgnoreCase(productname));
		return match;

		// driver.findElement(By.xpath("//*[text()='Checkout']")).click();

	}

	public CheckoutPage gotocheckout() {
		checkout.click();
		CheckoutPage checkoutpage = new CheckoutPage(driver);
		return checkoutpage;

	}

}
