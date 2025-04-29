package EndToEndEcommerceFramework.tests;

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

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriver driver  = new ChromeDriver();
		String productName = "ZARA COAT 3";
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("relinatiwari.rt@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Relina12@#");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'mb-3')]")));
		List<WebElement> products = driver.findElements(By.xpath("//div[contains(@class,'mb-3')]"));
		
		WebElement product = products.stream().filter(prod -> prod.findElement(By.cssSelector("b"))
				.getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		product.findElement(By.xpath(".//button[text()=' Add To Cart']")).click();
		Thread.sleep(2000);
	
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id ='toast-container']/div/div")));
	    
		String actualText = driver.findElement(By.xpath("//div[@id ='toast-container']/div/div")).getText();
		System.out.println(actualText);
		String expectedText = "Product Added To Cart";
		Assert.assertEquals(expectedText, actualText);
		
		driver.findElement(By.xpath("(//button[contains(text(),'Cart')])[1]")).click();
		
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class = 'cart']//li")));
		List<WebElement> cartproducts = driver.findElements(By.xpath("//div[@class = 'cart']//li"));
		int l = products.size();
		System.out.println(l);
		for(WebElement cartproduct : cartproducts)
		{
			WebElement getcartproduct =  cartproduct.findElement(By.xpath(".//div[@class='cartSection']/h3"));
			String cartproductName = getcartproduct.getText();
			System.out.println(cartproductName);
			
			if(cartproductName.equals("ZARA COAT 3"))
			{
				Thread.sleep(5000);
				Assert.assertEquals(cartproductName, "ZARA COAT 3");
			}
			
		}
		
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		
		
		
		WebElement selectCountry = driver.findElement(By.xpath("//div[@class='form-group']/child::input"));
		Actions action = new Actions(driver);
		action.sendKeys(selectCountry,"Ind").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section[class*='ta-results']")));	
		
		List<WebElement> countryList = driver.findElements(By.xpath("//section/button[@class='ta-item list-group-item ng-star-inserted']"));
		for(WebElement countrynames: countryList)
		{
			String countryname = countrynames.findElement(By.xpath("./span")).getText();
			System.out.println(countryname);
			if(countryname.equalsIgnoreCase("India"))
			{
				countrynames.click();
				break;
			}
		}
		

		driver.findElement(By.xpath("//div[@class = 'actions']/a")).click();
		
		
		String confirmmessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		System.out.println(confirmmessage);
		Assert.assertTrue(confirmmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		
		
		driver.quit();
		
		

	}

}
