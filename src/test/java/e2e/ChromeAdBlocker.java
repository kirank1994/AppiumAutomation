package e2e;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeAdBlocker {

    public static void main(String[] args) throws InterruptedException {
        ChromeOptions options = new ChromeOptions();

//        // Disable automation-controlled detection
//        options.addArguments("--disable-blink-features=AutomationControlled");
//
//        // Block ads / popups
//        options.addArguments("--disable-popup-blocking");
//        options.addArguments("--disable-extensions");
//        options.addArguments("--disable-notifications");
//
//        // Experimental - disable ad-related services
//        options.addArguments("--disable-features=InterestCohort,AdTagging,HeavyAdIntervention");

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.managed_default_content_settings.ads", 2); // block ads
        options.setExperimentalOption("prefs", prefs);
        
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/select-menu");

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
