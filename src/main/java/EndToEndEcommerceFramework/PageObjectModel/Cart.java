package EndToEndEcommerceFramework.PageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import EndToEndEcommerceFramework.AbstractComponents.AbstractComponents;

public class Cart extends AbstractComponents{

	
	WebDriver driver;
	
	By cartlist = By.xpath("//div[@class = 'cart']//li");
	
	
	public Cart(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@class = 'cart']//li")
	List<WebElement> cartProducts;
	
	@FindBy(xpath = "//button[text()='Checkout']")
	WebElement checkoutbutton;
	
	public List<WebElement> getCartProducts()
	{
		waitForElementToBeVisible(cartlist);
		return cartProducts;
	}
	
	public boolean verifyProductDisplay(String productName)
	{
		Boolean match = getCartProducts().stream().anyMatch(prod -> prod.findElement(By.cssSelector("h3")).getText().equalsIgnoreCase(productName));
		System.out.println(match);
		return match;
	}
	
	public CheckoutPage goToCheckout()
	{
		checkoutbutton.click();
		CheckoutPage checkoutpage = new CheckoutPage(driver);
		return checkoutpage;
		
	}
	
	
	

}
