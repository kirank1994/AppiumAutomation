package e2e;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.nio.file.Paths;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class UploadTheFile {

    public static void main(String[] args) throws Exception {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.foundit.in/upload/");

        // Click on the Upload Resume button
        WebElement uploadButton = driver.findElement(By.xpath("//div[contains(text(),'Upload Resume')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", uploadButton);

        // ✅ Copy file path into clipboard (Mac style)
        String filePath = Paths.get("/Users/kondurikiran/Desktop/usage.txt").toAbsolutePath().toString();
        StringSelection selection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

        // ✅ Use Robot to paste (⌘ + V) + press Enter on Mac
        Robot robot = new Robot();
        robot.delay(2000);

        robot.keyPress(KeyEvent.VK_META);   // Command key on Mac
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_META);

        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        System.out.println("File uploaded successfully!");
    }
}