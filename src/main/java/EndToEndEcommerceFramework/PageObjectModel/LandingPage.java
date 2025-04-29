package EndToEndEcommerceFramework.PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import EndToEndEcommerceFramework.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents{
	
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver , this);
	}
	
	@FindBy(id="userEmail")
	WebElement useremail;
	
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement login;
	
	@FindBy(css = "[class*='toast-message']")
	WebElement errorToast;
	
	public void goToWebsite()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}

	

	public ProductCatalogue login(String email, String password) {
		useremail.sendKeys(email);
		userPassword.sendKeys(password);
		login.click();
		
		ProductCatalogue productcatalogue = new ProductCatalogue(driver);
		return productcatalogue;
	}
	
	public String getErrorMessage()
	{
		waitForElementToBeVisible(errorToast);
		return errorToast.getText();
		
	}
	
	

}
