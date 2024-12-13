package shivakumar.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class basetest {
	private static final String always = null;
	public static WebDriver driver;
	public LandingPage landingpage;

	public WebDriver initialization() throws IOException {

		Properties prop = new Properties();
		FileInputStream fil = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\rahulshettyacademy\\resouces\\GlobalData.Properties ");
		prop.load(fil);
		String browserName = System.getProperty("browser")!= null ? System.getProperty("browser") :prop.getProperty("browser");
		// TODO Auto-generated method stub
	//	String browerName = prop.getProperty("brower");

		if (browserName.contains("Chrome")) {
			
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless")) {
				options.addArguments("headless");
			}
			System.getProperty("webdriver.chrome.driver",
					"D:\\Shiva Kumar Bantu\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().setSize(new Dimension(1440, 990));
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException  {
		
		
		String jsonContent = FileUtils.readFileToString(new File(filepath));

		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {});
		
		return data;
		
	}
	
	public  String getscreenshot(String testCaseName, WebDriver driver ) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		 File source =  ts.getScreenshotAs(OutputType.FILE);
		 File fil = new File("C:\\Users\\Admin\\eclipse-workspace\\seleniumframework\\reportsinde\\ss.png");
		 FileUtils.copyFile(source, fil);
		 return "C:\\Users\\Admin\\eclipse-workspace\\seleniumframework\\reportsinde\\ss.png";
		 
		
	
		
	}

	@BeforeMethod(alwaysRun = true)

	public LandingPage lanuchApplication() throws IOException {
		driver = initialization();
		landingpage = new LandingPage(driver);
		landingpage.goTo();
		return landingpage;

	}
	@AfterMethod(alwaysRun = true)
	
	public void typeclose() {
		driver.close();
	}
}
