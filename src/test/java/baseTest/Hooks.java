package baseTest;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Hooks {

	// public static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
	public static AppiumDriver driver;

	@SuppressWarnings("deprecation")
	@Test
	public void SetUp() throws MalformedURLException {
//		DesiredCapabilities capabilities = new DesiredCapabilities();
//		capabilities.setCapability("automationName", "UiAutomator2"); // XCUITest - iOS
//		capabilities.setCapability("platformName", "Android"); // iOS
//		capabilities.setCapability("deviceName", "Pixel 7 Pro"); // This is for iOS
//		capabilities.setCapability("appium:udid", "emulator-5554"); // Android only
//		capabilities.setCapability("appium:autoDismissKeyboard", true); // iOS
//		capabilities.setCapability("appium:unicodeKeyboard", true); // Android
//		capabilities.setCapability("noReset", true);
//		capabilities.setCapability("appium:appWaitForLaunch", true);
// driver.set(new AndroidDriver(new URL("http://0.0.0.0:4723/"), capabilities));
		
		UiAutomator2Options capabilities = new UiAutomator2Options();
        capabilities.setDeviceName("RahulPhone");                  
        capabilities.setUdid("emulator-5554"); //To run automation on Android device/emulator this field setup is mandatory                    
        capabilities.setPlatformName("Android"); 
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), capabilities);
		System.out.println("Driver lanched succssfully");
	}

}
