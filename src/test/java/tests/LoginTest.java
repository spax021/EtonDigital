package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import config.PropertiesFile;
import pages.LoginPage;

@Listeners(common.Listeners.class)
public class LoginTest extends BaseTest{

	private LoginPage loginPage;
	private String email = PropertiesFile.getEmail();
	private String password = PropertiesFile.getPassword();
	private String incorectEmail = PropertiesFile.getInvalidUEmail();
	private String incorectPassword = PropertiesFile.getInvalidPassword();
	
	@BeforeMethod(alwaysRun = true)
	public void setUp() {
		loginPage = new LoginPage();
		loginPage.approveBanner();
//		loginPage.loginUserToTheSystem(email, password);
	}
	
	@AfterMethod
	public void tearDown() {
		quitBrowser();
	}
	
	@Test(description = "Test Case 1: Valid Login")
	public void TestValidLogin() {
		loginPage.populateEmail(email);
		loginPage.populatePassword(password);
		loginPage.clickLoginButton();
		loginPage.verifySuccessfullLogin();
	}
	
	@Test(description = "Test Case 2: Invalid Email")
	public void TestInvalidEmail() {
		loginPage.populateEmail(incorectEmail);
		loginPage.populatePassword(password);
		loginPage.clickLoginButton();
		loginPage.verifyUnsuccessfullLogin("email");
	}
	
	@Test(description = "Test Case 3: Invalid Password")
	public void TestInvalidPassword() {
		loginPage.populateEmail(email);
		loginPage.populatePassword(incorectPassword);
		loginPage.clickLoginButton();
		loginPage.verifyUnsuccessfullLogin("password");
	}
	
	@Test(description = "Test Case 4: Both Email and Password Invalid")
	public void TestInvalidEmailAndPassword() {
		loginPage.populateEmail(incorectEmail);
		loginPage.populatePassword(incorectPassword);
		loginPage.clickLoginButton();
		loginPage.verifyUnsuccessfullLogin("");
	}
	
	@Test(description = "Test Case 5: Empty Fields")
	public void TestEmptyFields() {
		loginPage.populateEmail("");
		loginPage.populatePassword("");
		loginPage.clickLoginButton();
		loginPage.verifyUnsuccessfullLoginWithEmptyFields("");
		
	}
	
	@Test(description = "Test Case 6: Only Email Populated")
	public void TestOnlyEmailPopulated() {
		loginPage.populateEmail(email);
		loginPage.populatePassword("");
		loginPage.clickLoginButton();
		loginPage.verifyUnsuccessfullLoginWithEmptyFields("password");
	}
	
	@Test(description = "Test Case 7: Only Password Populated")
	public void TestOnlyPasswordPopulated() {
		loginPage.populateEmail("");
		loginPage.populatePassword(password);
		loginPage.clickLoginButton();
		loginPage.verifyUnsuccessfullLoginWithEmptyFields("email");
	}
	
	@Test(description = "Test Case 8: Error Message Format")
	public void TestErrorMessageFormat() {
		loginPage.populateEmail(incorectEmail);
		loginPage.populatePassword(incorectPassword);
		loginPage.clickLoginButton();
		loginPage.verifyErrorMessageFormat();
		
	}
	
	@Test(description = "Test Case 9: SQL Injection")
	public void TestSQLInjection() {
		loginPage.populateEmail("' OR '1'='1");
		loginPage.populatePassword("' OR '1'='1");
		loginPage.clickLoginButton();
		loginPage.verifyUnsuccessfullLogin("email");
	}
	
	@Test(description = "Test Case 10: XSS Vulnerability")
	public void TestXSSVulnerability() {
		loginPage.populateEmail("<script>alert('XSS');</script>");
		loginPage.populatePassword("<script>alert('XSS');</script>");
		loginPage.clickLoginButton();
		loginPage.verifyUnsuccessfullLogin("email");
		loginPage.verifyThatScriptIsNotExecuted();
	}
	
	@Test(description = "Test Case 11: CSRF (Cross-Site Request Forgery) Protection")
	public void TestCSRFProtection() {
		loginPage.populateEmail(email);
		loginPage.populatePassword(password);
		loginPage.verifyCSRFExistance();
		loginPage.removeCSRF();
		loginPage.clickLoginButton();
		loginPage.verifyCSRFErrorMessage();
	}
	
	@Test(description = "Test Case 12: Brute Force Protection")
	public void TestBruteForce() {
		loginPage.attemptBruteforceLogin(email, incorectPassword);
		loginPage.verifyUnsuccessfullLogin("password");
	}
	
	@Test(description = "Test Case 13: Sensitive Data Exposure")
	public void TestSensitiveExposure() {
		loginPage.populateEmail(incorectEmail);
		loginPage.populatePassword(incorectPassword);
		loginPage.clickLoginButton();
		loginPage.verifyNonExposureOfSensitiveData(incorectEmail, incorectPassword);
	}
	
	@Test(description = "Test Case 14: Secure Cookies")
	public void TestSecureCookies() {
		loginPage.populateEmail(email);
		loginPage.populatePassword(password);
		loginPage.clickLoginButton();
		loginPage.verifyCookies();
	}
	
}
