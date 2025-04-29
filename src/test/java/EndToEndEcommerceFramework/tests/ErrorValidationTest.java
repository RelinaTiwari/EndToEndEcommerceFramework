package EndToEndEcommerceFramework.tests;

import java.io.IOException;
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
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;



import EndToEndEcommerceFramework.Base.BaseClass;
import EndToEndEcommerceFramework.Base.Retry;
import EndToEndEcommerceFramework.PageObjectModel.Cart;
import EndToEndEcommerceFramework.PageObjectModel.CheckoutPage;
import EndToEndEcommerceFramework.PageObjectModel.ConfirmationPage;
import EndToEndEcommerceFramework.PageObjectModel.LandingPage;
import EndToEndEcommerceFramework.PageObjectModel.ProductCatalogue;

public class ErrorValidationTest extends BaseClass {

	@Test(groups = {"Error Validation"} , retryAnalyzer=Retry.class)
	public void invalidCredentials() throws InterruptedException, IOException {
		

		landingpage.login("relinatiwari.rt@gmail.com", "Relina12");
		String errormsg = landingpage.getErrorMessage();
		Assert.assertEquals("Incorrect email orrr password.", errormsg);
		
		
	}
	
	@Test
	public void invalidProduct() throws InterruptedException
	{
		String productName = "ZARA COAT 3";
		ProductCatalogue productcatalogue = landingpage.login("relinatiwari.rt@gmail.com", "Relina12@#");
		List<WebElement> products = productcatalogue.getProductList();
		productcatalogue.addProductToCart(productName);
		Cart cart = productcatalogue.goToCart();
		Boolean match = cart.verifyProductDisplay("Zara coat");
		Assert.assertFalse(match);
	}

}
