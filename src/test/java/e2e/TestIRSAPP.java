package e2e;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;

import java.util.Iterator;

import java.util.Set;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.chrome.ChromeOptions;

import org.testng.Assert;

public class TestIRSAPP {

	public static void main(String args[])

	{

		WebDriver driver = new ChromeDriver();

		// System.getProperty("user.dir",
		// "C:\\Users\\ASUA\\eclipse-workspace\\Demo\\Resources\\chromedriver.exe");

		driver.get("https://www.irs.gov/");

		driver.manage().window().maximize();

		WebElement FormsAndInstructions = driver.findElement(By.xpath("//a[@id='accessible-megamenu-nav-item-4']"));

		FormsAndInstructions.click();

		WebElement pdf1040 = driver.findElement(By.xpath("//b[contains(text(),'Form 1040')]"));

		pdf1040.click();

		// It will return the parent window name as a String

		String parent = driver.getWindowHandle();

		Set<String> s = driver.getWindowHandles();

		// Now iterate using Iterator

		Iterator<String> I1 = s.iterator();

		while (I1.hasNext())

		{

			String child_window = I1.next();

			if (!parent.equals(child_window))

			{

				driver.switchTo().window(child_window);

				System.out.println(driver.switchTo().window(child_window).getTitle());

			}

			driver.navigate().to("https://www.irs.gov/pub/irs-pdf/f1040.pdf");

			ChromeOptions options = new ChromeOptions();

			System.out.print("Test1");

			HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();

			System.out.print("Test2");

			chromeOptionsMap.put("plugins.plugins_disabled", new String[] { "Chrome PDF Viewer" });

			chromeOptionsMap.put("plugins.always_open_pdf_externally", true);

			chromeOptionsMap.put("download.default_directory", "C:\\Users\\ASUA\\eclipse-workspace\\Demo\\Resources");

			options.setExperimentalOption("prefs", chromeOptionsMap);

			options.addArguments("--remote-allow-origins=*");

			WebDriver driver1 = new ChromeDriver(options);

			String pdfUrl = "https://www.irs.gov/pub/irs-pdf/f1040.pdf";

			String pdfContent = getPdfContent(pdfUrl);
			System.out.println("Extracted PDF Content:\n" + pdfContent);
			Assert.assertTrue(pdfContent.contains("Preparer’s name"));
			Assert.assertTrue(pdfContent.contains("Preparer’s signature"));
			Assert.assertTrue(pdfContent.contains("Date"));
			Assert.assertTrue(pdfContent.contains("PTIN"));
			Assert.assertTrue(pdfContent.contains("Check if:"));
			Assert.assertTrue(pdfContent.contains("Self-employed"));
			Assert.assertTrue(pdfContent.contains("Firm’s name"));
			Assert.assertTrue(pdfContent.contains("Phone no."));
			Assert.assertTrue(pdfContent.contains("Firm’s address"));
			Assert.assertTrue(pdfContent.contains("Firm’s EIN"));
			Assert.assertTrue(
					pdfContent.contains("Go to www.irs.gov/Form1040 for instructions and the latest information."));
			Assert.assertTrue(pdfContent.contains("Form 1040 (2024)"));

		}

	}

// Utility method to download and extract text from a PDF URL
	public static String getPdfContent(String pdfUrl) {
		try (InputStream in = new URL(pdfUrl).openStream(); PDDocument document = PDDocument.load(in)) {
			PDFTextStripper pdfStripper = new PDFTextStripper();
			return pdfStripper.getText(document);
		} catch (Exception e) {
			throw new RuntimeException("Failed to extract PDF content: " + e.getMessage(), e);
		}
	}
}