package EndToEndEcommerceFramework.PageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import EndToEndEcommerceFramework.AbstractComponents.AbstractComponents;


public class CheckoutPage extends AbstractComponents {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		
	}

	By countriesNameResult = By.cssSelector("section[class*='ta-results']");
	
	@FindBy(xpath = "//div[@class='form-group']/child::input")
	WebElement selectCountry;
	
	@FindBy(xpath = "//section/button[@class='ta-item list-group-item ng-star-inserted']")
	List<WebElement> countryList;
	
	@FindBy(xpath = "//div[@class = 'actions']/a")
	WebElement placeOrderButton;
	
	
	public void selectCountry(String countrynametoselect)
	{
		Actions action = new Actions(driver);
		action.sendKeys(selectCountry, countrynametoselect).build().perform();
		waitForElementToBeVisible(countriesNameResult);
		

		for (WebElement countrynames : countryList) {
			String countryname = countrynames.findElement(By.xpath("./span")).getText();
			System.out.println(countryname);
			if (countryname.equalsIgnoreCase("India")) {
				countrynames.click();
				break;
			}
		}
	}
	
	public ConfirmationPage placeOrder()
	{
		placeOrderButton.click();
		ConfirmationPage confirmationpage = new ConfirmationPage(driver);
		return confirmationpage;
	}

	

}
