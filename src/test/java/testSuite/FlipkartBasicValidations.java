package testSuite;

import org.openqa.selenium.By;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import baseClass.Hooks;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class FlipkartBasicValidations extends Hooks {
public AppiumDriver driver;

	@Test
	public void Scenario1() throws InterruptedException {
		// Click on Home->Play->Categories->Account->Cart->Home
		test = extent.createTest(message("Bottom-navigation Bar"));
		node = test.createNode(message("Verifiying the Bottom navigation Icons"));
		tapOnElement(driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='Home']")), "Home");
		tapOnElement(driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='Play']")), "Play");
		tapOnElement(driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='Categories']")), "Categories");
		tapOnElement(driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='Account']")), "Account");
		tapOnElement(driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='Cart']")), "Cart");
		tapOnElement(driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='Home']")), "Home");
	}

	//@Test
	public void Scenario2() throws InterruptedException {
		// Click on Categories->Search- for Phones->Click On Back->Categories
		node = test.createNode(message("Verifiying the Search Options on Categories"));
		tapOnElement(driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='Categories']")), "Categories");
		tapOnElement(driver.findElement(By.xpath("//android.widget.ImageView[@content-desc='Search']")), "Search");
		sendKeysOnElement(driver.findElement(By.xpath("//android.widget.FrameLayout/child::android.view.ViewGroup[1]/descendant::android.widget.EditText")), "phones", "Send Keys");
		clearOnElement(driver.findElement(By.xpath("//android.widget.FrameLayout/child::android.view.ViewGroup[1]/descendant::android.widget.EditText")), "Clear");
		tapOnElement(driver.findElement(By.xpath("//android.widget.FrameLayout/child::android.view.ViewGroup[1]/descendant::android.widget.EditText/ancestor::android.view.ViewGroup[3]/child::android.view.ViewGroup[1]")), "Skiped");		
	}

	//@Test
	public void Scenario3() throws InterruptedException {
		// Click on Cart->getText "MyCart" ->Print it
		test = extent.createTest(message("MyCart-navigation"));
		node = test.createNode(message("Extracting Text from the MyCart"));
		tapOnElement(driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='Cart']")), "Cart");
		textOfElement(driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.flipkart.android:id/title_action_bar']")),"MyCart");
	}

	//@Test
	public void Scenario4() throws InterruptedException {
		// Click on Cart->getText "MyCart" ->Print it
		node = test.createNode(message("Extracting Text from the Grocery"));
		//driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='Cart']")).click();
		//node.info("Clicked on Cart");
		tapOnElement(driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='Cart']")), "Cart");
		
		//driver.findElement(By.xpath("//android.widget.TextView[@text='Grocery']")).click();
		//node.info("Clicked on Grocery");
		tapOnElement(driver.findElement(By.xpath("//android.widget.TextView[@text='Grocery']")), "Grocery");
		
		String text = driver.findElement(By.xpath("//android.widget.TextView[@text='Your basket is empty!']"))
				.getText();
		node.info("Grocery Text is : " + text);
	}

	public static String message(String message) {
		return "<b>" + message + "</b>";
	}

	//@Test
	public void Scenario5() {
		test = extent.createTest(message("MyCart-navigation-Text-verification"));
		node = test.createNode(message("Extracting Text from the MyCart"));
		//driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='Cart']")).click();
		//node.info("Clicked on Cart");
		tapOnElement(driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='Cart']")), "Cart");
		
		String text = driver
				.findElement(
						By.xpath("//android.widget.TextView[@resource-id='com.flipkart.android:id/title_action_bar']"))
				.getText();
		if (text.equals("My Cart1")) {
			node.pass("✅ MyCart Text is matched");
		} else {
			failure("❌ MyCart Text is not matched");
			// node.fail("");
		}
		// node.skip("skiped the login"); keep in TearDown
		//driver.findElement(By.xpath("//android.widget.TextView[@text='Continue Shopping ']")).click();
		//node.info("Clicked on Continue Shoping");
		tapOnElement(driver.findElement(By.xpath("//android.widget.TextView[@text='Continue Shopping ']")), "Continue Shoping");
	}

	//@Test
	public void ScrollExample() {
		test = extent.createTest(message("Scroll Example - Home"));
		node = test.createNode(message("Scrolling until 'Sponsored'"));
		//driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='Home']")).click();
		//node.info("Clicked on Home");
		tapOnElement(driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='Home']")), "Home");
		// driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new
		// UiSelector().scrollable(true))" +".scrollIntoView(new UiSelector().text(More
		// on Flipkart))"));
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
				+ ".scrollIntoView(new UiSelector().text(\"Sponsored\"));"));
	}

	//@Test
	public void SwapExample() {
		swipeByCoordinates((AndroidDriver) driver, 738, 624, 350, 624);
	}
	@AfterMethod
	public void tearDown(ITestResult result) {
	    testStatus(result);
	}
}
