package e2e;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class flipcartValidation {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.flipkart.com/");

        Actions actions = new Actions(driver);

        // Hover on "Login"
        WebElement loginButton=driver.findElement(By.xpath("//span[text()='Login'] | //div[text()='Login']"));
        actions.moveToElement(loginButton).pause(Duration.ofMillis(1000)).perform();

        // Wait for "Orders" link to be visible and click
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement ordersLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//li[@class='AT0fUR'][text()='Orders']")));
        ordersLink.click();
        Thread.sleep(5000); // Wait for the page to load

        driver.quit();
    }
}