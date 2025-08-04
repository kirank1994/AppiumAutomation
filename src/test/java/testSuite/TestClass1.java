package testSuite;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import baseClass.Hooks;

public class TestClass1 extends Hooks {
	@Test
	public void display() throws InterruptedException {
		// 1.⁠ ⁠Tap on dropdown - com.androidsample.generalstore:id/spinnerCountry
		// -//android.widget.EditText[@resource-id="com.androidsample.generalstore:id/nameField"]
		driver.findElement(
				By.xpath("//android.widget.Spinner[@resource-id=\"com.androidsample.generalstore:id/spinnerCountry\"]"))
				.click();
		System.out.println("1.⁠ ⁠Tap on dropdown");
		
		// 2.⁠ ⁠select different country
		List<WebElement> countries = driver.findElements(By.id("android:id/text1"));
		for (WebElement country : countries) {
		    if (country.getText().equals("India")) {
		        country.click();
		        break;
		    }
		}
		System.out.println(" 2.⁠ ⁠select different country");
		//driver.pressKey(new KeyEvent(AndroidKey.BACK));
		
		// 3.⁠ ⁠Enter Your Name -com.androidsample.generalstore:id/nameField
		// -//android.widget.EditText[@resource-id="com.androidsample.generalstore:id/nameField"]
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("kiran");
		System.out.println("3.⁠ ⁠Enter Your Name");
		
		// 4.⁠ ⁠Select Gender -com.androidsample.generalstore:id/radioFemale
		// -//android.widget.RadioButton[@resource-id="com.androidsample.generalstore:id/radioFemale"]
		driver.findElement(By.id("com.androidsample.generalstore:id/radioMale")).click();
		System.out.println("4.⁠ ⁠Select Gender");
		
		// 5.⁠ ⁠Tap on Lets Shop button -com.androidsample.generalstore:id/btnLetsShop -
		// //android.widget.Button[@resource-id="com.androidsample.generalstore:id/btnLetsShop"]
		driver.findElement(
				By.xpath("//android.widget.Button[@resource-id=\"com.androidsample.generalstore:id/btnLetsShop\"]"))
				.click();
		System.out.println("5. Tap on Lets Shop button ");

		Thread.sleep(5000);
	}

}
