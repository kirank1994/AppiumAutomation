package testSuite;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import baseClass.Hooks;
import io.appium.java_client.AppiumDriver;
import pages.CategoriesPage;

public class CategoriesPageTestClass extends Hooks {
public AppiumDriver driver;
private CategoriesPage categoriesPage;
public static ExtentTest test=extent.createTest(message("CategoriesPage-navigation"));
@BeforeClass
public void setup()
{
 driver=Hooks.getDriver();	
 categoriesPage=new CategoriesPage(driver);
}
	
@Test
public void Scenario2() throws InterruptedException {
	// Click on Categories->Search- for Phones->Click On Back->Categories
	node = test.createNode(message("Verifiying the Search Options on Categories"));
	categoriesPage.clickOnCategories();
	categoriesPage.clickOnSearch();
	categoriesPage.sendKeysOnElement();
	categoriesPage.clearOnElement();
	categoriesPage.clickOnBack();
}
@AfterMethod
public void tearDown(ITestResult result) {
    testStatus(result);
}

}

