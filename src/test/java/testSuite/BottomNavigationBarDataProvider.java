package testSuite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import baseClass.Hooks;
import io.appium.java_client.AppiumDriver;

public class BottomNavigationBarDataProvider extends Hooks {
	public AppiumDriver driver;
	public static ExtentTest test=extent.createTest(message("Bottom-navigation Bar"));
	
	@BeforeClass
	public void setup() {
		driver = Hooks.getDriver();
	}

	@DataProvider(name = "testData")
	public Object[][] getData() 
	{
		return new Object[][] 
            { 
			    { "//android.view.ViewGroup[@content-desc='Home']", "Home" },
				{ "//android.view.ViewGroup[@content-desc='Play']", "Play" },
				{ "//android.view.ViewGroup[@content-desc='Categories']", "Categories" },
				{ "//android.view.ViewGroup[@content-desc='Account']", "Account" },
				{ "//android.view.ViewGroup[@content-desc='Cart']", "Cart" },
				{ "//android.view.ViewGroup[@content-desc='Home']", "Home" } 
			 };
	 }

	@Test(dataProvider = "testData")
	public void testMethod(String elementLocator, String testName) {
		node = test.createNode(message("Verifiying the Bottom navigation Icons"));
		WebElement element = driver.findElement(By.xpath(elementLocator));
		tapOnElement(element, testName);
	}
	@AfterMethod
	public void tearDown(ITestResult result) {
	    testStatus(result);
	}
}
