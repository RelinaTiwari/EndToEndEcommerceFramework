package EndToEndEcommerceFramework.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import EndToEndEcommerceFramework.PageObjectModel.LandingPage;

public class BaseClass {

	public WebDriver driver;
	public LandingPage landingpage;

	public WebDriver initializeBrowser() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\EndToEndEcommerceFramework\\resources\\Global.properties");
		prop.load(fis);
		System.out.println("Hello");

		String browser = prop.getProperty("browser");

		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		return driver;
	}
	
	@BeforeMethod (alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		initializeBrowser();
		landingpage = new LandingPage(driver);
		landingpage.goToWebsite();
		return landingpage;
	}
	
	@AfterMethod (alwaysRun = true)
	public void tearDown()
	{
		driver.quit();
	}
	
	
	public String getScreenShot(String testcaseName , WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"//reports//"+testcaseName+".png";
		File dest = new File(path);
		FileUtils.copyFile(source, dest);
		return path;
	}

}
