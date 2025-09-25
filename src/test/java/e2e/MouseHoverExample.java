package e2e;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class MouseHoverExample {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.flipkart.com");

        Actions actions = new Actions(driver);

        // Hover on "Fashion"
        WebElement fashionMenu = driver.findElement(By.xpath("//span[text()='Fashion']"));
        actions.moveToElement(fashionMenu).perform();
        Thread.sleep(5000); // Wait for submenu to appear
        
        // Hover on "Men's T-Shirts"
        WebElement tshirts = driver.findElement(By.xpath("//a[text()=\"Men's T-Shirts\"]"));
        actions.moveToElement(tshirts).click().perform();
        Thread.sleep(5000); // Wait for submenu to appear
        driver.quit();
    }
}
