package pages;
import utils.CommonUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class AccountPage extends CommonUtils {
private final AppiumDriver driver;
public AccountPage(AppiumDriver driver)
{
	this.driver=driver;
	PageFactory.initElements(new AppiumFieldDecorator(driver), this);
}

@iOSXCUITFindBy(xpath="Account")
@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc='Account']")
private WebElement account;

@iOSXCUITFindBy(xpath="Account")
@AndroidFindBy(xpath="(//android.widget.TextView[@text='Account'])[1]")
private WebElement accountText;

public void clickOnAccount()
{
	tapOnElement(account,"Account");
}
public void getTextOfAccount()
{
	textOfElement(accountText,"Account");
}
}
