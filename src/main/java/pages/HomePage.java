package pages;


import org.openqa.selenium.By;

import base.Page;

public class HomePage extends Page{
	
	
	public void goToSignUp() {
		driver.findElement(By.xpath("//a[normalize-space()='Registration']")).click();

	}
	
	public LoginPage goToLogin() {
		System.out.println("ligin");
		System.out.println("ligin");	
		click("okWelcomeBtn");
		

		click("loginButton");
		return new LoginPage();
		
	}
	
	public void goToZohoEdu() {
		
	}
	
	public void goTOLearnMore() {
		
	}
	
	public void validateFooter() {
		
	}

}
