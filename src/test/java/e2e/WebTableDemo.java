package e2e;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

public class WebTableDemo {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://testautomationpractice.blogspot.com/");
        driver.manage().window().maximize();

        // Count rows and columns
        List<WebElement> rows = driver.findElements(By.xpath("//table[@name='BookTable']/tbody/tr"));
        int rowCount = rows.size();

        List<WebElement> cols = driver.findElements(By.xpath("//table[@name='BookTable']/tbody/tr[1]/th"));
        int colCount = cols.size();

        System.out.println("Rows: " + rowCount);
        System.out.println("Columns: " + colCount);

        // Print header row
        System.out.print("Headers: ");
        for (WebElement col : cols) {
            System.out.print(col.getText() + " | ");
        }
        System.out.println();

        // Print table data
        for (int r = 2; r <= rowCount; r++) { // start from 2 because row 1 = header
            for (int c = 1; c <= colCount; c++) {
                String cellValue = driver.findElement(
                        By.xpath("//table[@name='BookTable']/tbody/tr[" + r + "]/td[" + c + "]")
                ).getText();
                System.out.print(cellValue + " | ");
            }
            System.out.println();
        }

        driver.quit();
    }
}