package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.LoginPage;

public class LoginTest extends BaseTest{

	private LoginPage loginPage;
	
	@BeforeMethod(alwaysRun = true)
	public void setUp() {
		loginPage = new LoginPage();
	}
	
	@AfterMethod
	public void tearDown() {
		quitBrowser();
	}
	
	@Test(description = "")
	public void TestSuccessfullLogin() {
		pause(5000);
	}
}
