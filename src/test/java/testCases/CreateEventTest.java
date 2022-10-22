package testCases;
import static org.testng.Assert.fail;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.AppLoginPage;
import utilities.Utilities;

public class CreateEventTest extends BaseTest{

	@Test(dataProviderClass = Utilities.class, dataProvider = "dp", priority=2)
	public void createEventTest(Hashtable<String,String> data) throws InterruptedException {
		
		
		AppLoginPage barApp = new AppLoginPage();
		barApp.createHangout(data.get("title"), data.get("description"));
		
	


	}
	

		
	}


