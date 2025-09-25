package e2e;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;
import java.util.List;

public class GesturesDemo1 {
    
    public static void handlePopup(WebDriver driver) {
        try {
            // Close any popups inside iframes
            List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
            for (WebElement iframe : iframes) {
                driver.switchTo().frame(iframe);
                List<WebElement> closeBtn = driver.findElements(By.xpath("//button | //span[text()='✕']"));
                if (!closeBtn.isEmpty()) {
                    closeBtn.get(0).click();
                    System.out.println("Popup closed inside iframe.");
                }
                driver.switchTo().defaultContent();
            }
        } catch (Exception e) {
            driver.switchTo().defaultContent();
            System.out.println("No popup found.");
        }
    }

    public static void main(String[] args) {
        //WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        WebDriver driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        try {
            // 1. Clicks
            driver.get("https://demoqa.com/buttons");
            handlePopup(driver);
            Actions actions = new Actions(driver);

            WebElement doubleClickBtn = driver.findElement(By.id("doubleClickBtn"));
            WebElement rightClickBtn = driver.findElement(By.id("rightClickBtn"));
            WebElement clickMeBtn = driver.findElement(By.xpath("//button[text()='Click Me']"));

            actions.doubleClick(doubleClickBtn).perform();
            actions.contextClick(rightClickBtn).perform();
            actions.click(clickMeBtn).perform();

            // 2. Hover
            driver.get("https://demoqa.com/menu");
            handlePopup(driver);
            WebElement menuItem = driver.findElement(By.xpath("//a[text()='Main Item 2']"));
            actions.moveToElement(menuItem).perform();

            // 3. Keyboard
            driver.get("https://demoqa.com/text-box");
            handlePopup(driver);
            WebElement inputBox = driver.findElement(By.id("userName"));
            inputBox.sendKeys("Hello World");
            actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
            actions.sendKeys(Keys.DELETE).perform();
        } 
        finally {
            driver.quit();
        }
    }
}