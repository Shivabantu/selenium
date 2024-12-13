package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class productscatalogue extends AbstractComponent {


	WebDriver driver;
	public productscatalogue (WebDriver driver){
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
		
		
	}
	
	//List<WebElement> productname= driver.findElements(By.cssSelector("div[class*='col-lg-4']"));
	@FindBy(css="div[class*='col-lg-4']")
	List<WebElement> products;
	@FindBy(css=".ng-animating")
	WebElement spinner;
	@FindBy(css="button[routerlink='/dashboard/cart']")
	WebElement gotocartt;
	
	By productsBy = By.cssSelector("div[class*='col-lg-4']");
	By addtocart = By.cssSelector(".card-body button:last-of-type");
	By visibleof = By.cssSelector("#toast-container");
	
	
	
	public List<WebElement> getProductList(){
		waitForElementToAppear(productsBy);
		return products;
		
	}
	
	//WebElement prod = productname.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals("ADIDAS ORIGINAL")).findFirst().orElse(null);
	//prod.findElement(By.cssSelector("div button:last-of-type")).click();
	
	public WebElement getProductName(String productname) {
		
		WebElement prod = getProductList().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);
		return prod;
		
	}
	
	public void addtocart(String productname) throws InterruptedException {
		WebElement prod = getProductName(productname);
		prod.findElement(addtocart).click();
		waitForElementToAppear(visibleof);
		waitforElemenetDisappear(spinner);
	}
	//	driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
	public productcart gotocart() {
		gotocartt.click();
		productcart productcart = new productcart(driver);
		return productcart;
		
		
	}


}
