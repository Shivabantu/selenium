package shivakumar.tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import dev.failsafe.internal.util.Durations;
import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.orderpage;
import rahulshettyacademy.pageobjects.productcart;
import rahulshettyacademy.pageobjects.productscatalogue;
import shivakumar.TestComponents.basetest;

public class submitordertest<TakeScreenshot> extends basetest {
	//String productname = "ADIDAS ORIGINAL";

	@Test(dataProvider= "getdata", groups = {"purchase"})
	public void submitorder(HashMap<String, String> input) throws InterruptedException, IOException {

		// String productname = "ADIDAS ORIGINAL";
		productscatalogue productcatalogue = landingpage.loginapplication(input.get("Email"), input.get("password"));
		List<WebElement> products = productcatalogue.getProductList();
		productcatalogue.addtocart(input.get("productname"));
		productcart productcart = productcatalogue.gotocart();
		// productcart productcart = new productcart(driver);
		boolean match = productcart.cartpage(input.get("productname"));
		AssertJUnit.assertTrue(match);
		CheckoutPage checkoutPage = productcart.gotocheckout();
		checkoutPage.selectCountry("india");
		Thread.sleep(2000);
		ConfirmationPage confirmationPage = CheckoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		AssertJUnit.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods= {"submitorder"})

	public void orderlist() throws InterruptedException {

		productscatalogue productcatalogue = landingpage.loginapplication("shiva169521@gmail.com", "Shiva@123");
		orderpage orderpage = productcatalogue.gotocarts();
		Assert.assertTrue(orderpage.orderlis("ADIDAS ORIGINAL"));

	}
	

	
	@DataProvider
	
	public Object[][] getdata() throws IOException {
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\shivakumar\\data\\purchaseorder.json");
		return new Object[][] {{data.get(0)},{ data.get(1)}};
		
		 
		
	}
	
	
	/*{
		return new Object[][] {{"shiva169521@gmail.com","Shiva@123","ADIDAS ORIGINAL" },{"shiva16952@gmail.com","Shiva@123","ZARA COAT 3" }
		};
	} */
		
	/*	@DataProvider
		//HashMap
		public Object[][] getdata() {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("Email", "shiva169521@gmail.com");
			map.put("password", "Shiva@123");
			map.put("productname", "ADIDAS ORIGINAL");
			
			HashMap<String, String> map1 = new HashMap<String, String>();
			map1.put("Email", "shiva16952@gmail.com");
			map1.put("password", "Shiva@123");
			map1.put("productname", "ZARA COAT 3");
			
			return new Object[][] {{map},{map1}};
			
			*/
	//Jsondata to Map 
	
	
	
	
	
		}
		
		
	
	

