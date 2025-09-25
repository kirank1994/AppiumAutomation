package e2e;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CustomDropdownExample {
    public static void main(String[] args) throws InterruptedException {
        // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
    	//ChromeOptions options = new ChromeOptions();
//    	options.addArguments("--disable-blink-features=AutomationControlled");
//    	options.addArguments("--disable-popup-blocking");
//    	options.addArguments("--disable-notifications");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Open a demo page with custom dropdown
        driver.get("https://demoqa.com/select-menu");

        // Example: "Select One" is a custom dropdown (not a <select>)
        // Step 1: Click the dropdown to expand options
      
        // way-1 Scroll element into view before clicking
//        WebElement dropdown = driver.findElement(By.id("selectOne"));
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdown);
//        dropdown.click();
        
        // way-2 Use JavaScript Click (bypasses overlays like ads)
//        WebElement dropdown = driver.findElement(By.id("selectOne"));
//        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdown);
//        dropdown.click();
        
         //way-3 Wait until element is clickable (avoids race condition)
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("selectOne")));
//        dropdown.click();
        
        // way-4 using chrome options to disable adblocker
        WebElement dropdown = driver.findElement(By.id("selectOne"));
        dropdown.click();
        Thread.sleep(2000);

        // Step 2: Select option by visible text using XPath
        WebElement option = driver.findElement(By.xpath("//div[contains(@id,'react-select-3-option-0-1')]"));
        option.click();
        Thread.sleep(2000);

        System.out.println("Custom dropdown option selected successfully!");

        driver.quit();
    }
}