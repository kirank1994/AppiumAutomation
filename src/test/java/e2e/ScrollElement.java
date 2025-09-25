package e2e;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ScrollElement {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().window().maximize();

        WebElement input = driver.findElement(By.xpath("//button[@id='mousehover']"));

        // Call the ScrollIntoView method
        ScrollIntoView(input, driver);
        ClickElement(input, driver);
        AddBorderLine(input, driver);
        Thread.sleep(10000); // Wait for 2 seconds to see the effect
        driver.close();
    }

    public static void ScrollIntoView(WebElement element, WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        System.out.println("✅ Scrolled to element: " + element.getText());
    }
    public static void ClickElement(WebElement element, WebDriver driver) {
        ((org.openqa.selenium.JavascriptExecutor) driver)
            .executeScript("arguments[0].click();", element);
    }

    public static void AddBorderLine(WebElement element, WebDriver driver) {
        ((org.openqa.selenium.JavascriptExecutor) driver)
            .executeScript("arguments[0].style.border='3px solid red';", element);
    }
}