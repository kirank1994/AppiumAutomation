package testSuite;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import baseClass.Hooks;
import io.appium.java_client.AppiumDriver;
import pages.PlayPage;

public class PlayPageTestClass extends Hooks {
public AppiumDriver driver;
private PlayPage playPage;
public static ExtentTest test=extent.createTest(message("Play-navigation"));
@BeforeClass
public void setup()
{
 driver=Hooks.getDriver();	
 playPage=new PlayPage(driver);
}
	
	@Test
	public void Scenario3() throws InterruptedException {
		// Click on Cart->getText "MyCart" ->Print it
		node = test.createNode(message("Extracting Text from the Play"));
		playPage.clickOnPlay();
		playPage.getTextOfPlay();
	}

}
