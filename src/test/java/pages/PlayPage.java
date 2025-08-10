package pages;
import utils.CommonUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class PlayPage extends CommonUtils {
private final AppiumDriver driver;
public PlayPage(AppiumDriver driver)
{
	this.driver=driver;
	PageFactory.initElements(new AppiumFieldDecorator(driver), this);
}
@iOSXCUITFindBy(xpath="Play")
@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc='Play']")
private WebElement play;

@iOSXCUITFindBy(xpath="Play")
@AndroidFindBy(xpath="//android.widget.TextView[@text='Play ']")
private WebElement playText;

public void clickOnPlay()
{
	tapOnElement(play,"Play");
}
public void getTextOfPlay()
{
	textOfElement(playText,"Play");
}

}
