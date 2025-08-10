package testSuite;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import baseClass.Hooks;
import io.appium.java_client.AppiumDriver;
import pages.AccountPage;

public class AccountPageTestClass extends Hooks {
public AppiumDriver driver;
private AccountPage accountPage;
public static ExtentTest test=extent.createTest(message("Account-navigation"));
@BeforeClass
public void setup()
{
 driver=Hooks.getDriver();	
 accountPage=new AccountPage(driver);
}
	
	@Test
	public void Scenario3() throws InterruptedException {
		// Click on Cart->getText "Account" ->Print it
		node = test.createNode(message("Extracting Text from the Account"));
		accountPage.clickOnAccount();
		accountPage.getTextOfAccount();
	}
}
