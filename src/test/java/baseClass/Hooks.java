package baseClass;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import utils.CommonUtils;

public class Hooks extends CommonUtils {

	public static ThreadLocal<AppiumDriver> driver=new ThreadLocal<>();
	@BeforeTest(alwaysRun = true)
	public void ConfigureAppium() throws MalformedURLException {
		extentReports();
		DesiredCapabilities options=new DesiredCapabilities();
		options.setCapability("appium:udid","emulator-5554");
		options.setCapability("appium:automationName", "UiAutomator2");
		options.setCapability("appium:platformName", "Android");
		options.setCapability("appium:unicodeKeyboard", true); //hiding the keyboard
		//options.setApp("/Users/kondurikiran/Desktop/CromaCampus/AppiumAutomationProject/src/test/resources/resources/General-Store.apk");	
		//options.setCapability("emulator-5554");
		//options.setCapability("com.flipkart.android.activity.HomeFragmentHolderActivity");
		//options.setCapability("com.flipkart.android");
		//options.setCapability("browserName", "Chrome");
		//options.setCapability("chromedriverExecutable", "//opt//homebrew//bin//chromedriver");
		//options.setNoReset(true);
		driver.set(new AndroidDriver(new URL("http://0.0.0.0:4723"), options));
		driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterSuite
	public void tearDown() {
		extent.flush();
		driver.get().quit();
	}
    public static AppiumDriver getDriver()
    {
		return driver.get();
    	
    }
}
