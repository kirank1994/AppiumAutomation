package testSuite;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import baseClass.Hooks;

public class TestClass2 extends Hooks{
@Test
public void TeseCaseValidation()
{
	driver.findElement(By.xpath("//android.widget.TextView[@text=\"Brand Mall \"]/../following-sibling::android.view.ViewGroup[1]")).click();
	driver.findElement(By.xpath("//android.widget.FrameLayout/child::android.view.ViewGroup[1]/descendant::android.widget.EditText")).sendKeys("phone");
	driver.findElement(By.xpath("//android.widget.FrameLayout/child::android.view.ViewGroup[1]/descendant::android.widget.EditText")).clear();
	
	driver.findElement(By.xpath("//android.widget.FrameLayout/child::android.view.ViewGroup[1]/descendant::android.widget.EditText/ancestor::android.view.ViewGroup[3]/child::android.view.ViewGroup[1]")).click();
	//driver.findElement(By.xpath("//android.widget.FrameLayout/child::android.view.ViewGroup[1]/descendant::android.widget.EditText/ancestor::android.view.ViewGroup[3]/child::android.view.ViewGroup[1]")).click();
	//Scenario-1 
}
}
