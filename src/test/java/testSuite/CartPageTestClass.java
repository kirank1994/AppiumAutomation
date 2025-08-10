package testSuite;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import baseClass.Hooks;
import io.appium.java_client.AppiumDriver;
import pages.CartPage;

public class CartPageTestClass extends Hooks {
public AppiumDriver driver;
private CartPage cartPage;
public static ExtentTest test=extent.createTest(message("Cart Page-navigation"));
@BeforeClass
public void setup()
{
 driver=Hooks.getDriver();	
 cartPage=new CartPage(driver);
}
	
	@Test
	public void Scenario3() throws InterruptedException {
		// Click on Cart->getText "MyCart" ->Print it
		node = test.createNode(message("Extracting Text from the MyCart"));
		cartPage.clickOnCart();
		cartPage.getTextOfMyCart();
	}

	@Test
	public void Scenario4() throws InterruptedException {
		// Click on Cart->getText "MissingCartitems" ->Print it
		node = test.createNode(message("Extracting Text from the MissingCartitems"));
		cartPage.clickOnCart();
		cartPage.getTextOfMissingCartitems();

	}

}
