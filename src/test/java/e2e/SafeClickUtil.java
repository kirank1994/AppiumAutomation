package e2e;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class SafeClickUtil {

    public static void safeClick(WebDriver driver, By locator) {
        try {
            // Try normal Selenium click
            WebElement element = driver.findElement(locator);
            element.click();
            System.out.println("Clicked using normal Selenium click.");
        } catch (ElementClickInterceptedException e) {
            System.out.println("Click intercepted by ad/iframe. Retrying with JS click...");

            // Fallback to JavaScript click
            WebElement element = driver.findElement(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

            System.out.println("Clicked using JavaScript click.");
        }
    }

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/select-menu");

        // Use safeClick for dropdown
        safeClick(driver, By.id("selectOne"));

        driver.quit();
    }
}


