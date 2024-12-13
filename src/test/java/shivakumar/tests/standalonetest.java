package shivakumar.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import dev.failsafe.internal.util.Durations;
import io.github.bonigarcia.wdm.WebDriverManager;

public class standalonetest {

	public static void main(String[] args) throws InterruptedException {
		
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#userEmail")));
		driver.findElement(By.cssSelector("#userEmail")).sendKeys("shiva169521@gmail.com");
		driver.findElement(By.cssSelector("#userPassword")).sendKeys("Shiva@123");
		driver.findElement(By.cssSelector(".btn ")).click();
		List<WebElement> productname= driver.findElements(By.cssSelector("div[class*='col-lg-4']"));
		WebElement prod = productname.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals("ADIDAS ORIGINAL")).findFirst().orElse(null);
		prod.findElement(By.cssSelector("div button:last-of-type")).click();
		//WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		Thread.sleep(2000);
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
		List<WebElement> cart= driver.findElements(By.cssSelector("div[class='cartSection'] h3"));
		boolean  match =cart.stream().anyMatch(s->s.getText().equalsIgnoreCase("ADIDAS ORIGINAL"));
		Assert.assertTrue(match);
		driver.findElement(By.xpath("//*[text()='Checkout']")).click();
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "India").build().perform();		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.cssSelector("button[class*='ta-item']:nth-child(2)")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".action__submit")).click();
		String confirm = driver.findElement(By.cssSelector(".hero-primary")).getText();
		System.out.println(confirm);
		Assert.assertTrue(confirm.equalsIgnoreCase("Thankyou for the order."));
		
	}

}
