package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utilities.ExcelReader;
import utilities.ExtentManager;
import utilities.Utilities;

public class Page {

	public static WebDriver driver;

	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoytLogger");
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "/src/test/resources/com/excel/testData.xlsx");
	public static WebDriverWait wait;
	public ExtentReports rep = ExtentManager.getInstance();
	public static String browser;
	public static ExtentTest test;

	public Page() {

		if (driver == null) {

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "/src/test/resources/com/properties/Config.properties");
			} catch (FileNotFoundException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			try {
				config.load(fis);
				log.debug("Config file loaded");
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "/src/test/resources/com/properties/OR.properties");
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				OR.load(fis);
				log.debug("OR file loaded");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Jenkins browser filter configurations

			if (System.getenv("browser") != null && System.getenv("browser").isEmpty()) {
				browser = System.getenv("browser");
			} else {
				browser = config.getProperty("browser");
			}

			config.setProperty("browser", browser);

			if (config.getProperty("browser").equals("Chrome")) {
				System.out.println("Chrome started");

				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "/src/test/resources/com/executables/chromedriver");
				driver = new ChromeDriver();
			}

			driver.get(config.getProperty("testURL"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		}

	}
	
	//Common keywords
	
	public static void quite() {
		driver.quit();
	}

	public void click(String locator) {
//		if(locator.endsWith("_XPATH")) {
//			driver.findElement(By.xpath(OR.getProperty(locator))).click();
//		} else if(locator.endsWith("_CSS")) {
//			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
//		}
		driver.findElement(By.xpath(OR.getProperty(locator))).click();
	}

	public void typeOR(String locator, String value) {
		driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(OR.getProperty(value));
		System.out.println((LogStatus.INFO + "Typing in :" + locator + " entered value: " + OR.getProperty(value)));

	}

	public void typeDP(String locator, String value) {
		driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		System.out.println((LogStatus.INFO + "Typing in :" + locator + " entered value: " + OR.getProperty(value)));

	}

	public boolean IsElementPresent(By by) {
		// TODO Auto-generated method stub

		try {
			driver.findElement(By.xpath(OR.getProperty("createHangoutBtn")));
			return true;

		} catch (NoSuchElementException e) {
			return false;

		}

	}

	static WebElement dropdown;

	public void select(String locator, String value) {
		if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		}

		Select select = new Select(dropdown);
		select.selectByVisibleText(value);
		test.log(LogStatus.INFO, "" + ":" + locator + " entered value as" + value);

	}

	public static void verifyEquals(String expected, String actual) throws IOException {
		try {
			Assert.assertEquals(actual, expected);
		} catch (Throwable t) {
			Utilities.captureScreenShot();
			// ReportNG
			Reporter.log("Capturing screenshot");
			Reporter.log("<br>+" + "Verification failure" + t.getMessage() + "<br>");
			Reporter.log("<a target = \"blank\" href=" + Utilities.ScreenshotName + "> ScreenShot<a>");
			// Extent Reports
			test.log(LogStatus.FAIL, " verification failed with exception: " + t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(Utilities.ScreenshotName));

		}
	}
}
