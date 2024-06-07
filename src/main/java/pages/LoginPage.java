package pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import elements.HomePageElements;
import elements.LoginPageElements;

public class LoginPage extends BasePage {

	private LoginPageElements loginPageElements;
	private HomePageElements homePageElements;

	public LoginPage() {
		super();
		loginPageElements = new LoginPageElements();
		homePageElements = new HomePageElements();
	}
	
	public void approveBanner() {
		waitForElementClickable(loginPageElements.getCookieBannerOKButton());
		driver.findElement(loginPageElements.getCookieBannerOKButton()).click();
	}

	public void populateEmail(String email) {
		waitForElementClickable(loginPageElements.getEmailInputField());
		driver.findElement(loginPageElements.getEmailInputField()).clear();
		driver.findElement(loginPageElements.getEmailInputField()).sendKeys(email);
	}

	public void populatePassword(String password) {
		waitForElementClickable(loginPageElements.getPasswordInputField());
		driver.findElement(loginPageElements.getPasswordInputField()).clear();
		driver.findElement(loginPageElements.getPasswordInputField()).sendKeys(password);
	}

	public void clickLoginButton() {
		waitForElementClickable(loginPageElements.getLoginButton());
		driver.findElement(loginPageElements.getLoginButton()).click();
	}

	public void verifySuccessfullLogin() {
		waitForElementVisible(homePageElements.getHomePageUserIcon());
		String url = driver.getCurrentUrl();
		Assert.assertTrue(url.contains("/en/listings?page"));
	}
	
	private String loginPageUrl = "https://st.storabble.etondigital.com/en/login";
	
	/**
	 * Method is verifying error message after entering incorect data
	 * @param param indicates on which element we are focusing
	 * if param is empty, assert is inspecting email and password error messages
	 */
	public void verifyUnsuccessfullLogin(String param) {
		if(param.equals("email")) {
			waitForElementVisible(loginPageElements.getEmailErrorMessage());
			assertEquals(driver.findElement(loginPageElements.getEmailErrorMessage()).getText(), "Please enter a valid email address.");
			assertTrue(driver.getCurrentUrl().contains("/en/login"), "Url should be: " + loginPageUrl);
		}else if(param.equals("password")) {
			waitForElementVisible(loginPageElements.getPasswordErrorMessage());
			assertEquals(driver.findElement(loginPageElements.getPasswordErrorMessage()).getText(), "Your password is incorrect.");
			assertTrue(driver.getCurrentUrl().contains("/en/login"), "Url should be: " + loginPageUrl);
		}else if(param.equals("")){
			waitForElementVisible(loginPageElements.getEmailErrorMessage());
			waitForElementVisible(loginPageElements.getPasswordErrorMessage());
			assertEquals(driver.findElement(loginPageElements.getEmailErrorMessage()).getText(), "Please enter a valid email address.");
			assertEquals(driver.findElement(loginPageElements.getPasswordErrorMessage()).getText(), "Your password is incorrect.");
			assertTrue(driver.getCurrentUrl().contains("/en/login"), "Url should be: " + loginPageUrl);
		}else {
			System.out.println("Unknown element");
		}		
	}
	
	/**
	 * Method is verifying error message after leaving input fields empty
	 * @param param indicates on which element we are focusing
	 * if param is empty, assert is inspecting email and password error messages
	 */
	public void verifyUnsuccessfullLoginWithEmptyFields(String param) {
		if(param.equals("email")) {
			waitForElementVisible(loginPageElements.getEmailErrorMessage());
			assertEquals(driver.findElement(loginPageElements.getEmailErrorMessage()).getText(), "This field is required.");
			assertTrue(driver.getCurrentUrl().contains("/en/login"), "Url should be: " + loginPageUrl);
		}else if(param.equals("password")) {
			waitForElementVisible(loginPageElements.getPasswordErrorMessage());
			assertEquals(driver.findElement(loginPageElements.getPasswordErrorMessage()).getText(), "This field is required.");
			assertTrue(driver.getCurrentUrl().contains("/en/login"), "Url should be: " + loginPageUrl);
		}else if(param.equals("")){
			waitForElementVisible(loginPageElements.getEmailErrorMessage());
			waitForElementVisible(loginPageElements.getPasswordErrorMessage());
			assertEquals(driver.findElement(loginPageElements.getEmailErrorMessage()).getText(), "This field is required.");
			assertEquals(driver.findElement(loginPageElements.getPasswordErrorMessage()).getText(), "This field is required.");
			assertTrue(driver.getCurrentUrl().contains("/en/login"), "Url should be: " + loginPageUrl);
		}else {
			System.out.println("Unknown element");
		}		
	}

	public void verifyErrorMessageFormat() {
		String expectedFontColor = "rgba(214, 48, 36, 1)";
		String expectedFontSize = "14px";
		String expectedFontWeight = "400";
		
		waitForElementVisible(loginPageElements.getEmailErrorMessage());
		waitForElementVisible(loginPageElements.getPasswordErrorMessage());
		
        assertEquals(driver.findElement(loginPageElements.getEmailErrorMessage()).getCssValue("color"), expectedFontColor, "Email error message font color should be: " + expectedFontColor);
        assertEquals(driver.findElement(loginPageElements.getEmailErrorMessage()).getCssValue("font-size"), expectedFontSize, "Email error message font size should be: " + expectedFontSize);
        assertEquals(driver.findElement(loginPageElements.getEmailErrorMessage()).getCssValue("font-weight"), expectedFontWeight, "Email error message font weight should be: " + expectedFontWeight);
        
        assertEquals(driver.findElement(loginPageElements.getPasswordErrorMessage()).getCssValue("color"), expectedFontColor, "Password error message font color should be: " + expectedFontColor);
        assertEquals(driver.findElement(loginPageElements.getPasswordErrorMessage()).getCssValue("font-size"), expectedFontSize, "Password error message font size should be: " + expectedFontSize);
        assertEquals(driver.findElement(loginPageElements.getPasswordErrorMessage()).getCssValue("font-weight"), expectedFontWeight, "Password error message font weight should be: " + expectedFontWeight);
	}
	
	public void verifyThatScriptIsNotExecuted() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Boolean isXSSExecuted = (Boolean) js.executeScript("return window.alert.toString().indexOf('XSS') > -1;");
        Assert.assertFalse(isXSSExecuted, "XSS script should not be executed.");
	}
	
	public void verifyCSRFExistance() {
	    WebElement csrfToken = driver.findElement(By.name("_csrf"));
	    Assert.assertNotNull(csrfToken, "CSRF token should be present in the login form.");
	}
	
	public void removeCSRF() {
		((JavascriptExecutor) driver).executeScript("document.querySelector('[name=\"_csrf\"]').remove();");
	}
	
	public void verifyCSRFErrorMessage() {
	    WebElement errorMessageElement = driver.findElement(By.cssSelector(".invalid-msg"));
	    Assert.assertTrue(errorMessageElement.isDisplayed(), "Error message should be displayed when CSRF token is missing.");
	}
	
	public void attemptBruteforceLogin(String email, String password) {
		for(int i = 0; i < 10; i++) {
			populateEmail(email);
			populatePassword(password);
			clickLoginButton();
		}
		
	    WebElement captcha = driver.findElement(By.id("captcha"));
	    Assert.assertTrue(captcha.isDisplayed(), "Captcha should be displayed after multiple failed login attempts.");

	}
	
	public void verifyNonExposureOfSensitiveData(String email, String password) {
		waitForElementVisible(loginPageElements.getEmailErrorMessage());
		waitForElementVisible(loginPageElements.getPasswordErrorMessage());
		Assert.assertFalse(driver.findElement(loginPageElements.getEmailErrorMessage()).getText().contains(email), "Error message should not contain email address.");
		Assert.assertFalse(driver.findElement(loginPageElements.getPasswordErrorMessage()).getText().contains(password), "Error message should not contain password.");
		assertTrue(driver.getCurrentUrl().contains("/en/login"), "Url should be: " + loginPageUrl);

	    Assert.assertFalse(driver.getCurrentUrl().contains(email), "URL should not contain email address.");
	    Assert.assertFalse(driver.getCurrentUrl().contains(password), "URL should not contain password.");
	
	}
	
	public void verifyCookies() {
		waitForElementVisible(homePageElements.getHomePageUserIcon());
	    Set<Cookie> cookies = driver.manage().getCookies();
	    for (Cookie cookie : cookies) {
	        Assert.assertTrue(cookie.isHttpOnly(), "Cookie should be HttpOnly: " + cookie.getName());
	        Assert.assertTrue(cookie.isSecure(), "Cookie should be secure: " + cookie.getName());
	    }
	}
	
	
	/**
	 * Prepared method for successfull login: populate fields > click login button,
	 * verify
	 */
	public void loginUserToTheSystem(String email, String password) {
		populateEmail(email);
		populatePassword(password);
		clickLoginButton();
		verifySuccessfullLogin();
	}
}
