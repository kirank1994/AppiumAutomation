package testSuite;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import baseClass.Hooks;
import io.appium.java_client.AppiumDriver;
import pages.HomePage;

public class HomePageTestClass extends Hooks {
public AppiumDriver driver;
private HomePage homePage;
public static ExtentTest test=extent.createTest(message("Home Page-navigation"));
@BeforeClass
public void setup()
{
 driver=Hooks.getDriver();	
 homePage=new HomePage(driver);
}
	
@Test
	public void ScrollExample() {
		node = test.createNode(message("Scrolling until 'Sponsored'"));
		//Change-1
		//driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='Home']")).click();
		//node.info("Clicked on Home");
		
		//Change-2
		//tapOnElement(driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='Home']")), "Home");
		
		//Changes-3
		homePage.clickOnHome();
		homePage.scrollUntilTheElement("Sponsored");
		
	}

	

}
