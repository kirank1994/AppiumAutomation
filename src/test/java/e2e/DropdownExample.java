package e2e;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class DropdownExample {
    public static void main(String[] args) throws InterruptedException {
        // Set path for ChromeDriver (if required in your system)
        // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Launch browser
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Open demo dropdown page
        driver.get("https://demoqa.com/select-menu");

        // Locate dropdown element
        WebElement dropdown = driver.findElement(By.id("oldSelectMenu"));

        // Create Select class object
        Select select = new Select(dropdown);

        // 1. Select by visible text
        select.selectByVisibleText("Yellow");
        Thread.sleep(2000);

        // 2. Select by value attribute
        select.selectByValue("2");  // Value for "Blue"
        Thread.sleep(2000);

        // 3. Select by index (index starts at 0)
        select.selectByIndex(4); // Will select "Purple"
        Thread.sleep(2000);

        // Print the selected option
        System.out.println("Currently selected: " + select.getFirstSelectedOption().getText());

        // Close browser
        driver.quit();
    }
}
