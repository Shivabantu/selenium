package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class orderpage extends AbstractComponent {
	WebDriver driver;

	public orderpage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> orderli;

	@FindBy(css = "button[routerlink*='/dashboard/myorders']")
	WebElement order;

	public boolean orderlis(String productname) {

		boolean match = orderli.stream().anyMatch(s -> s.getText().equalsIgnoreCase(productname));
		return match;
	}

}
