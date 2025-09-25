package e2e;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Set;

public class WindowHandlingExample {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get("https://seleniumpractise.blogspot.com/2017/");

        // Parent window
        String parent = driver.getWindowHandle();

        // Open multiple windows
        driver.findElement(By.xpath("//a[normalize-space()='Click here for Google']")).click();
        driver.findElement(By.xpath("//a[normalize-space()='Click here for Facebook']")).click();
        driver.findElement(By.xpath("//a[normalize-space()='Click here for Gmail']")).click();

        // Switch to Facebook window
        Set<String> allWindows = driver.getWindowHandles();

        for (String window : allWindows) {
            driver.switchTo().window(window);
            String currentUrl = driver.getCurrentUrl();
            
            if (currentUrl.contains("facebook")) {
                System.out.println("✅ Switched to Facebook");
                break;
            }
        }

        // Back to parent window
        driver.switchTo().window(parent);
        System.out.println("🔙 Back to Parent window: " + driver.getTitle());

        driver.quit();
    }
}