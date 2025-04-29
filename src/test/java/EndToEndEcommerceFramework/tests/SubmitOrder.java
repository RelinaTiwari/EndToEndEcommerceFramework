package EndToEndEcommerceFramework.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import EndToEndEcommerceFramework.Base.BaseClass;
import EndToEndEcommerceFramework.PageObjectModel.Cart;
import EndToEndEcommerceFramework.PageObjectModel.CheckoutPage;
import EndToEndEcommerceFramework.PageObjectModel.ConfirmationPage;
import EndToEndEcommerceFramework.PageObjectModel.OrdersPage;
import EndToEndEcommerceFramework.PageObjectModel.ProductCatalogue;
import EndToEndEcommerceFramework.PurchaseData.DataProviderClass;

public class SubmitOrder extends BaseClass {

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		String productName = "ZARA COAT 3";
		String countrynametoselect = "India";

		ProductCatalogue productcatalogue = landingpage.login(input.get("email"), input.get("password"));

		// ProductCatalogue Page
		List<WebElement> products = productcatalogue.getProductList();
		productcatalogue.addProductToCart(productName);
		Cart cart = productcatalogue.goToCart();
		Boolean match = cart.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutpage = cart.goToCheckout();
		checkoutpage.selectCountry(countrynametoselect);
		ConfirmationPage confirmationpage = checkoutpage.placeOrder();

		String confirmmessage = confirmationpage.getConfirmationText();
		System.out.println(confirmmessage);
		Assert.assertTrue(confirmmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = "submitOrder")
	public void validateOrder() throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		String productName = "ZARA COAT 3";

		ProductCatalogue productcatalogue = landingpage.login("relinatiwari.rt@gmail.com", "Relina12@#");
		OrdersPage orderspage = productcatalogue.goToOrders();
		Boolean match = orderspage.validateOrderisPresent(productName);
		Assert.assertTrue(match);

	}

	@DataProvider
	public Object[][] getData() throws IOException {
		// return new Object[][] {{"relinatiwari.rt@gmail.com" , "Relina12@#"} ,
		// {"relinatiwari.rt@gmail.com" , "Relina12@#"}};
		/*
		 * HashMap<String,String> map = new HashMap(); map.put("email",
		 * "relinatiwari.rt@gmail.com"); map.put("password", "Relina12@#");
		 * 
		 * HashMap<String,String> map1 = new HashMap(); map1.put("email",
		 * "relinatiwari.rt@gmail.com"); map1.put("password", "Relina12@#"); return new
		 * Object[][] {{map} , {map1}};
		 */

		DataProviderClass dataprovider = new DataProviderClass();
		List<HashMap<String, String>> data = dataprovider.getJsonToMap();
		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

}
