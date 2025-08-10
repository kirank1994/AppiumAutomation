package pages;
import utils.CommonUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class HomePage extends CommonUtils {
private final AppiumDriver driver;
public HomePage(AppiumDriver driver)
{
	this.driver=driver;
	PageFactory.initElements(new AppiumFieldDecorator(driver), this);
}
@iOSXCUITFindBy(xpath="Home")
@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc='Home']")
private WebElement home;

@iOSXCUITFindBy(xpath="myCart")
@AndroidFindBy(xpath="//android.widget.TextView[@text='Sponsored']")
private WebElement sponsored;

public void clickOnHome() 
{
	tapOnElement(home, "Home");
}

public void scrollUntilTheElement(String element)
{
	scrollUntilElement(element,sponsored,"Sponsored");
}



}
