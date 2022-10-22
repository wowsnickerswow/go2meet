package pages;


import base.Page;

public class AppLoginPage extends Page{
	
	public void createHangout(String title, String descriptoin) {
		click("createHangoutBtn");
		click("eventContainerModal");
		click("titleInput");
		typeDP("titleInput",title);
		typeDP("descriptionInput", descriptoin);
		click("createBtn");

		System.out.println("TestCreate event DONE");
	}

}
