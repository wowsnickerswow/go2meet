package testCases;

import org.testng.annotations.AfterSuite;

import base.Page;


public class BaseTest {
	
	@AfterSuite
	public void tearDown() {
		Page.quite();
		
	}

}
