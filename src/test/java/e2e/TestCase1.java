package e2e;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class TestCase1 {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		UiAutomator2Options options=new UiAutomator2Options();
		options.setAutomationName("UiAutomator2");
		options.setDeviceName("emulator-5554");
		options.setPlatformName("Android");

		
		options.withBrowserName("Chrome");
		options.setCapability("chromedriverExecutable", "//opt//homebrew//bin//chromedriver");
		AndroidDriver driver=new AndroidDriver(new URL("http://127.0.0.1:4723/"),options);
		driver.get("https://m.facebook.com/");
		driver.findElement(By.xpath("//*[@id='m_login_email']")).sendKeys("kiran");
		Thread.sleep(5000);
        System.out.println("Entered Text");
	}

}
