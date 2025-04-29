package EndToEndEcommerceFramework.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import EndToEndEcommerceFramework.PageObjectModel.Cart;
import EndToEndEcommerceFramework.PageObjectModel.OrdersPage;

public class AbstractComponents {

	WebDriver driver;

	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver , this);
	}
	
	@FindBy(xpath = "(//button[contains(text(),'Cart')])[1]")
	WebElement cart;
	
	@FindBy(xpath = "//button[contains(text(),'ORDERS')]")
	WebElement Orders;
	
	public void waitForElementToBeVisible(By locator)
	{
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void waitForElementToBeVisible(WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(1));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementToBeInVisible(WebElement spinner)
	{
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(1));
		wait.until(ExpectedConditions.invisibilityOf(spinner));
	}

	public Cart goToCart()
	{
		cart.click();
		Cart cart = new Cart(driver);
		return cart;
		
	}
	
	public OrdersPage goToOrders()
	{
		Orders.click();
		OrdersPage orderspage = new OrdersPage(driver);
		return orderspage;
		 
	}

}
