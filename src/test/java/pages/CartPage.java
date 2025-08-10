package pages;
import utils.CommonUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class CartPage extends CommonUtils {
private final AppiumDriver driver;
public CartPage(AppiumDriver driver)
{
	this.driver=driver;
	PageFactory.initElements(new AppiumFieldDecorator(driver), this);
}
@iOSXCUITFindBy(xpath="Cart")
@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc='Cart']")
private WebElement cart;

@iOSXCUITFindBy(xpath="myCart")
@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.flipkart.android:id/title_action_bar']")
private WebElement myCart;

@iOSXCUITFindBy(xpath="cartItems")
@AndroidFindBy(xpath="//android.widget.TextView[@text='Missing Cart items?']")
private WebElement cartItems;

public void clickOnCart()
{
	tapOnElement(cart,"Cart");
}
public void getTextOfMyCart()
{
	textOfElement(myCart,"myCart");
}
public void getTextOfMissingCartitems()
{
	textOfElement(cartItems,"Missing Cart items");
}
}
