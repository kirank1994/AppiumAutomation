package e2e;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Brokenlink {
	public static void main(String[] args) throws IOException {
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.deadlinkcity.com/");
		driver.manage().window().maximize();
// Step-1 we identify broken links by fetching all anchor tags (<a>), 
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println("Total Links: " + links.size());

		for (WebElement link : links) {
// Step-2 extracting the href
			String linkurl = link.getAttribute("href");

			if (linkurl == null || linkurl.isEmpty()) {
				continue; // Skip if no href
			}

			try {
                //URLConnection = generic parent class (for all protocols).
				URL url = new URL(linkurl);
// Step-3 and then validating the response code using HttpURLConnection(HttpURLConnection = specialized subclass) (for HTTP/HTTPS).
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setConnectTimeout(5000);
				conn.connect();

				int statusCode = conn.getResponseCode();

// Step-4 If the code is 200, the link is valid; otherwise, it’s a broken link
				if (statusCode >= 400) {
					System.out.println(linkurl + " -> Broken (Status: " + statusCode + ")");
				} else {
					System.out.println(linkurl + " -> Valid (Status: " + statusCode + ")");
				}
			} catch (Exception e) {
				System.out.println(linkurl + " -> Exception: " + e.getMessage());
			}
		}

		driver.quit();
	}
}