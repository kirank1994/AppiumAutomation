package baseClass;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import utils.CommonUtils;

public class Hooks extends CommonUtils {

	public static AppiumDriver driver;

	@BeforeMethod
	public void ConfigureAppium() throws MalformedURLException {
		extentReports();
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("Pixel 7 pro");
		//options.setApp("/Users/kondurikiran/Desktop/CromaCampus/AppiumAutomationProject/src/test/resources/resources/General-Store.apk");	
		options.setUdid("emulator-5554");
		//options.setAppActivity("");
		//options.setAppPackage("");
		//options.setCapability("browserName", "Chrome");
		//options.setCapability("chromedriverExecutable", "//opt//homebrew//bin//chromedriver");

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterSuite
	public void tearDown() {
		extent.flush();
		driver.quit();
	}

}
