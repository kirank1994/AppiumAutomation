package utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class CommonUtils {
	public static AppiumDriver driver;
	public static ExtentReports extent = new ExtentReports();
	public static ExtentSparkReporter spark;
	public static String reportFolderPath = "reports";
	public static ExtentTest test;
	public static ExtentTest node;
	public static void extentReports() {
//      spark.config().setTimelineEnabled(true);
      spark = new ExtentSparkReporter(reportFolderPath + "/"+ "flipkart" + getCurrentDateTime() + ".html");
      spark.config().setCss(
              ".header { animation: zoomIn 0.5s; }" +
                      ".test.pass { animation: fadeIn 0.5s ease-in-out; }" +
                      ".test.fail { animation: shake 0.5s; }" +
                      ".test-summary { animation: pulse 1.5s infinite; }" +
                      ".test:hover { background-color: #f0f8ff; transform: scale(1.02); transition: 0.3s ease-in-out; }" +
                      "@keyframes fadeIn { from { opacity: 0; } to { opacity: 1; } }" +
                      "@keyframes shake { 0%, 100% { transform: translateX(0); } 25% { transform: translateX(-5px); } 50% { transform: translateX(5px); } 75% { transform: translateX(-5px); } }" +
                      "@keyframes zoomIn { from { transform: scale(0.5); } to { transform: scale(1); } }" +
                      "@keyframes pulse { 0% { transform: scale(1); } 50% { transform: scale(1.05); } 100% { transform: scale(1); } }"
      );
      spark.config().setDocumentTitle("flipkart Automation Report");
      spark.config().setReportName(" flipkart");
      extent.attachReporter(spark);
     
  }
	public static String getCurrentDateTime() {
	      
        LocalDateTime currentDateTime = LocalDateTime.now();
      
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH:mm:ss");
     
        return currentDateTime.format(formatter);
    }
	public static String getBase64(AppiumDriver driver) {
        String base64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        return base64;
    }
	public void failure(String message)
	{
		node.log(Status.FAIL, message, MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64(driver), "Failed image").build());

	}
	
	public void swipeByCoordinates(AndroidDriver driver, int startX, int startY, int endX, int endY) {
	    PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");

	    Sequence swipe = new Sequence(finger, 1);
	    swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
	    swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
	    swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), endX, endY));
	    swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

	    driver.perform(Arrays.asList(swipe));
	}
}
