package e2e;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class StaticDropDown {
    static WebDriver driver;

    public static void main(String[] args) throws IOException {
        driver = new ChromeDriver();
        driver.get("https://testautomationpractice.blogspot.com/");
        driver.manage().window().maximize();

        // Dropdown locator
        By dropdownLocator = By.id("dropdown-class-example");

        // Select by visible text
        SelectDropdown(dropdownLocator, "text", "Option1");

        // Select by index
        SelectDropdown(dropdownLocator, "index", "2");

        // Select by value attribute
        SelectDropdown(dropdownLocator, "value", "option3");

        driver.quit();
    }

    // Utility method to get element
    public static WebElement getElement(By locator) {
        return driver.findElement(locator);
    }

    // Utility method to select dropdown dynamically
    public static void SelectDropdown(By locator, String type, String value) {
        Select s = new Select(getElement(locator));

        switch (type.toLowerCase()) {
            case "index":
                s.selectByIndex(Integer.parseInt(value));
                break;
            case "value":
                s.selectByValue(value);
                break;
            case "text":
                s.selectByVisibleText(value);
                break;
            default:
                System.out.println("Invalid selection type provided: " + type);
                break;
        }
    }
}
