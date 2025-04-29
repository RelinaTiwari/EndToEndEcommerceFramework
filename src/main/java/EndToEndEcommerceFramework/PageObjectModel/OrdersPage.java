package EndToEndEcommerceFramework.PageObjectModel;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import EndToEndEcommerceFramework.AbstractComponents.AbstractComponents;

public class OrdersPage extends AbstractComponents{
	
	WebDriver driver;

	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> products;
	
	public Boolean validateOrderisPresent(String productName)
	{
		Boolean match = products.stream().anyMatch(prod -> prod.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	
	
}
