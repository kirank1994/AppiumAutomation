package testSuite;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import baseClass.Hooks;
import io.appium.java_client.android.AndroidDriver;
import pages.AccountPage;

public class AccountPageTestClass extends Hooks {
public AndroidDriver driver;
private AccountPage accountPage;
public static ExtentTest test=extent.createTest(message("Account-navigation"));
@BeforeClass
public void setup()
{
 driver=Hooks.getAndroidDriver();	
 accountPage=new AccountPage(driver);
}
	
	@Test
	public void Scenario3() throws InterruptedException {
		// Click on Cart->getText "Account" ->Print it
		node = test.createNode(message("Extracting Text from the Account"));
		accountPage.clickOnAccount();
		accountPage.getTextOfAccount();
	}
	@AfterMethod
	public void tearDown(ITestResult result) {
	    testStatus(result);
	}
}
