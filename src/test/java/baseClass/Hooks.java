package baseClass;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import utils.CommonUtils;

public class Hooks extends CommonUtils {

	public static ThreadLocal<AppiumDriver> driver=new ThreadLocal<>();
	@BeforeTest(alwaysRun = true)
	public void ConfigureAppium() throws MalformedURLException {
		extentReports();
		DesiredCapabilities options = new DesiredCapabilities();
		String platform = System.getProperty("platform", "Android"); // Default to Android
		if (platform.equalsIgnoreCase("Android")) {
			options.setCapability("appium:udid", "emulator-5554");
			options.setCapability("appium:automationName", "UiAutomator2");
			options.setCapability("appium:platformName", "Android");
			options.setCapability("appium:unicodeKeyboard", true);
			options.setCapability("appium:autoAcceptAlerts", true);
			driver.set(new AndroidDriver(new URL("http://0.0.0.0:4723"), options));
		} else if (platform.equalsIgnoreCase("IOS")) {
			options.setCapability("appium:platformName", "iOS");
			options.setCapability("appium:automationName", "XCUITest");
			options.setCapability("appium:deviceName", "iPhone 15");
			options.setCapability("appium:platformVersion", "18.6");
			options.setCapability("appium:udid", "00008120-000855C221F1A01E");
			options.setCapability("appium:bundleId", "com.appflipkart.flipkart");
			options.setCapability("appium:xcodeSigningId", "iPhone Developer");
			options.setCapability("appium:updatedWDABundleId", "com.kiran.WebDriverAgentRunner");
			driver.set(new IOSDriver(new URL("http://0.0.0.0:4723"), options));
		}
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
    public static AndroidDriver getAndroidDriver()
    {
		return (AndroidDriver) driver.get();
    }
    public static IOSDriver getIOSDriver()
    {
		return (IOSDriver) driver.get();
    }
}