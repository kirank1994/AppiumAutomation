package e2e;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.nio.file.Paths;

public class HiddenFileUpload {

    public static void main(String[] args) throws Exception {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.foundit.in/upload/");
//<input data-v-40a72a81="" id="file-upload" name="file" type="file">
        // ✅ Locate the hidden <input type="file">
        WebElement fileInput = driver.findElement(By.cssSelector("input[type='file']"));

        // ✅ Use JavaScript to make it visible
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.display='block';", fileInput);

        // ✅ Prepare file path (Mac style)
        String filePath = Paths.get("/Users/kondurikiran/Desktop/usage.txt").toAbsolutePath().toString();

        // ✅ Now sendKeys works
        fileInput.sendKeys(filePath);

        System.out.println("File uploaded successfully via sendKeys on hidden input!");

        driver.quit();
    }
}