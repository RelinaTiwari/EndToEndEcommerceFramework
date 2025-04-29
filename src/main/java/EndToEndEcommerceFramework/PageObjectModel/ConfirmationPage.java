package EndToEndEcommerceFramework.PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import EndToEndEcommerceFramework.AbstractComponents.AbstractComponents;

public class ConfirmationPage extends AbstractComponents {
	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		
		
	}

	@FindBy(css = ".hero-primary")
	WebElement confirmationText;
	
	
	public String getConfirmationText()
	{
		return confirmationText.getText();
		
	}
	
}
