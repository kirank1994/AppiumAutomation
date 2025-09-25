package e2e;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;
import java.util.List;

public class GesturesDemo2 {

    // Popup/iframe ad handler
    public static void handlePopup(WebDriver driver) {
        try {
            // Look inside iframes too
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

            // Also check in main DOM
            List<WebElement> closeBtn = driver.findElements(By.xpath("//button[@class='close' or contains(text(),'✕')]"));
            if (!closeBtn.isEmpty() && closeBtn.get(0).isDisplayed()) {
                closeBtn.get(0).click();
                System.out.println("Popup closed successfully.");
            }
        } catch (Exception e) {
            driver.switchTo().defaultContent();
            System.out.println("No popup found.");
        }
    }

    public static void main(String[] args) throws InterruptedException {
       // WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        WebDriver driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        Actions actions = new Actions(driver);

        try {
            // 4. Drag & Drop
            driver.get("https://demoqa.com/droppable");
            handlePopup(driver);
            WebElement source = driver.findElement(By.id("draggable"));
            WebElement target = driver.findElement(By.id("droppable"));
            actions.dragAndDrop(source, target).perform();

            // 5. Scroll into View
            driver.get("https://demoqa.com/automation-practice-form");
            handlePopup(driver);
            WebElement submitBtn = driver.findElement(By.id("submit"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitBtn);

            // 6. File Upload & Download
            driver.get("https://demoqa.com/upload-download");
            handlePopup(driver);
            WebElement uploadBtn = driver.findElement(By.id("uploadFile"));
            uploadBtn.sendKeys(System.getProperty("user.dir") + "/sample.txt"); // ensure sample.txt exists
            driver.findElement(By.id("downloadButton")).click();

            // 7. Resize
            driver.get("https://jqueryui.com/resizable/");
            driver.switchTo().frame(0);
            WebElement resizeHandle = driver.findElement(By.xpath("//div[@id='resizable']/div[3]"));
            actions.clickAndHold(resizeHandle).moveByOffset(100, 50).release().perform();
            driver.switchTo().defaultContent();

            // 8. Zoom In (CTRL + '+')
            actions.keyDown(Keys.CONTROL).sendKeys(Keys.ADD).keyUp(Keys.CONTROL).perform();
            Thread.sleep(2000);

            // 9. Zoom Out (CTRL + '-')
            actions.keyDown(Keys.CONTROL).sendKeys(Keys.SUBTRACT).keyUp(Keys.CONTROL).perform();

        } finally {
            driver.quit();
        }
    }
}