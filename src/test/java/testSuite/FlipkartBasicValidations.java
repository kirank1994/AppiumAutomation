package testSuite;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import baseClass.Hooks;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class FlipkartBasicValidations extends Hooks {

	@Test
	public void Scenario1() throws InterruptedException {
		// Click on Home->Play->Categories->Account->Cart->Home
		test = extent.createTest(message("Bottom-navigation Bar"));
		node = test.createNode(message("Verifiying the Bottom navigation Icons"));
		driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='Home']")).click();
		node.info("Clicked on Home");
		driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='Play']")).click();
		node.info("Clicked on Play");
		driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='Categories']")).click();
		node.info("Clicked on Categories");
		driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='Account']")).click();
		node.info("Clicked on Account");
		driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='Cart']")).click();
		node.info("Clicked on Cart");
		driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='Home']")).click();
		node.pass("✅ Clicked on Home Again");
	}

	@Test
	public void Scenario2() throws InterruptedException {
		// Click on Categories->Search- for Phones->Click On Back->Categories
		node = test.createNode(message("Verifiying the Search Options on Categories"));
		driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='Categories']")).click();
		node.info("Clicked on Categories");
		driver.findElement(By.xpath("//android.widget.ImageView[@content-desc='Search']")).click();
		node.info("Clicked on Search");
		driver.findElement(By.xpath(
				"//android.widget.FrameLayout/child::android.view.ViewGroup[1]/descendant::android.widget.EditText"))
				.sendKeys("phones");
		node.info("Clicked on Send Keys");
		driver.findElement(By.xpath(
				"//android.widget.FrameLayout/child::android.view.ViewGroup[1]/descendant::android.widget.EditText"))
				.clear();
		node.info("Clicked on Clear");
		driver.findElement(By.xpath(
				"//android.widget.FrameLayout/child::android.view.ViewGroup[1]/descendant::android.widget.EditText/ancestor::android.view.ViewGroup[3]/child::android.view.ViewGroup[1]"))
				.click();
		// failure("❌ fail to validate");
		node.skip("Test Case Skiped");
	}

	@Test
	public void Scenario3() throws InterruptedException {
		// Click on Cart->getText "MyCart" ->Print it
		test = extent.createTest(message("MyCart-navigation"));
		node = test.createNode(message("Extracting Text from the MyCart"));
		driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='Cart']")).click();
		node.info("Clicked on Cart");
		String text = driver
				.findElement(
						By.xpath("//android.widget.TextView[@resource-id='com.flipkart.android:id/title_action_bar']"))
				.getText();
		node.info("MyCart Text is : " + text);
	}

	@Test
	public void Scenario4() throws InterruptedException {
		// Click on Cart->getText "MyCart" ->Print it
		node = test.createNode(message("Extracting Text from the Grocery"));
		driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='Cart']")).click();
		node.info("Clicked on Cart");
		driver.findElement(By.xpath("//android.widget.TextView[@text='Grocery']")).click();
		node.info("Clicked on Grocery");
		String text = driver.findElement(By.xpath("//android.widget.TextView[@text='Your basket is empty!']"))
				.getText();
		node.info("Grocery Text is : " + text);
	}

	public static String message(String message) {
		return "<b>" + message + "</b>";
	}

	@Test
	public void Scenario5() {
		test = extent.createTest(message("MyCart-navigation-Text-verification"));
		node = test.createNode(message("Extracting Text from the MyCart"));
		driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='Cart']")).click();
		node.info("Clicked on Cart");
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
		driver.findElement(By.xpath("//android.widget.TextView[@text='Continue Shopping ']")).click();
		node.info("Clicked on Continue Shoping");
	}

	@Test
	public void ScrollExample() {
		test = extent.createTest(message("Scroll Example - Home"));
		node = test.createNode(message("Scrolling until 'Sponsored'"));
		driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='Home']")).click();
		node.info("Clicked on Home");
		// driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new
		// UiSelector().scrollable(true))" +".scrollIntoView(new UiSelector().text(More
		// on Flipkart))"));
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
				+ ".scrollIntoView(new UiSelector().text(\"Sponsored\"));"));
	}

	@Test
	public void SwapExample() {
		System.out.println("Going Swipe function");
		swipeByCoordinates((AndroidDriver) driver, 738, 624, 350, 624);
		System.out.println("Ending Swipe function");

	}
}
