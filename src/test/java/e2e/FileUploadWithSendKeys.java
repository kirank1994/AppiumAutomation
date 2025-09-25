package e2e;

import java.nio.file.Paths;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FileUploadWithSendKeys {
    public static void main(String[] args) throws Exception {
        // Launch Chrome
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Navigate to demo file upload page
        driver.get("https://the-internet.herokuapp.com/upload");

        // Locate the file input element (standard <input type="file">)
        WebElement uploadInput = driver.findElement(By.id("file-upload"));

        // Provide file path directly with sendKeys()
        String filePath = Paths.get("/Users/kondurikiran/Desktop/usage.txt").toAbsolutePath().toString();

        // Click the Upload button
        driver.findElement(By.id("file-submit")).click();

        // Verify success message
        String message = driver.findElement(By.tagName("h3")).getText();
        System.out.println("Upload result: " + message);

        driver.quit();
    }
}