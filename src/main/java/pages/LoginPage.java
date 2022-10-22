package pages;


import base.Page;

public class LoginPage extends Page{
	
	
	
	public  AppLoginPage doLogin(String username, String password) {
		typeDP("emailLocator", username);
		typeDP("passwordLocator", password);
		click("signInBtn");
		click("okFirstLoginBtn");
		return new AppLoginPage();
	
	}

	
}
