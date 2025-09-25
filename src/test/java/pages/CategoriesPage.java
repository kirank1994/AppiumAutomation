package pages;
import utils.CommonUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class CategoriesPage extends CommonUtils {
private final AppiumDriver driver;
public CategoriesPage(AppiumDriver driver)
{
	this.driver=driver;
	PageFactory.initElements(new AppiumFieldDecorator(driver), this);
}
@iOSXCUITFindBy(xpath="Categories")
@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc='Categories']")
private WebElement categories;

@iOSXCUITFindBy(xpath="Search")
@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='Search']")
private WebElement search;

@iOSXCUITFindBy(xpath="Back")
@AndroidFindBy(xpath="//android.widget.FrameLayout/child::android.view.ViewGroup[1]/descendant::android.widget.EditText/ancestor::android.view.ViewGroup[3]/child::android.view.ViewGroup[1]")
private WebElement back;

@iOSXCUITFindBy(xpath="Back")
@AndroidFindBy(xpath="//android.widget.FrameLayout/child::android.view.ViewGroup[1]/descendant::android.widget.EditText")
private WebElement textBox;


public void clickOnCategories()
{
	tapOnElement(categories,"Categories");
}
public void clickOnSearch() {
	tapOnElement(search,"Search");	
}
public void clickOnBack() {
	tapOnElement(back,"Back");	
}
public void sendKeysOnElement() {
	String value=configReader(configFilePath, "InputSearchBar");
	System.out.println("Value from properties file: " + value);
	sendKeysOnElement(textBox, value, "Search Bar");
	
}
public void clearOnElement() {
	clearOnElement(textBox,"Clear");
	
}
}
