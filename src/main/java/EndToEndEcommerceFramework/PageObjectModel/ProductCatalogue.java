package EndToEndEcommerceFramework.PageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import EndToEndEcommerceFramework.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {

	WebDriver driver;
	
	By productContainer = By.xpath("//div[contains(@class,'mb-3')]");
	By addToCartButton = By.xpath(".//button[text()=' Add To Cart']");
	By tosterMessage = By.xpath("//div[@id ='toast-container']/div/div");

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[contains(@class,'mb-3')]")
	List<WebElement> products;
	
	@FindBy(css = ".ng-animating")
	WebElement spinner;

	public List<WebElement> getProductList() {
		waitForElementToBeVisible(productContainer);
		return products;
	}
	
	
	public WebElement getProductByName(String productName)
	{
		WebElement product = getProductList().stream().filter(prod -> prod.findElement(By.cssSelector("b"))
				.getText().equalsIgnoreCase(productName )).findFirst().orElse(null);
		return product;
		
	}
	
	public void addProductToCart(String productName) throws InterruptedException
	{
		getProductByName(productName).findElement(addToCartButton).click();
		Thread.sleep(2000);
		waitForElementToBeVisible(tosterMessage);
		waitForElementToBeInVisible(spinner);
	}
	
	

}
