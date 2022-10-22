package utilities;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class TestExtentReports {

	public ExtentSparkReporter htmlReporter; // Create HTML file and add configuration;
	public ExtentReports extent; // Attaching Reporter, Creating test cases and other;
	public ExtentTest test; // maintaining the test cases. adding logs status pass;

	public void setReport() {
		
		htmlReporter = new ExtentSparkReporter("./reports/extent.html");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("g2m report");
		htmlReporter.config().setReportName("Smoke test");
		htmlReporter.config().setTheme(Theme.STANDARD);
		
		extent = new ExtentReports();

		extent.attachReporter(htmlReporter);
		
		extent.setSystemInfo("Automation Test", "Rahul Arora");
		extent.setSystemInfo("build No:", "21");

		
		}

	@Test
	public void doLogin() {

	}

	@AfterTest
	public void endReport() {
		extent.flush();

	}

}
