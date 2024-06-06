package pages;

import elements.LoginPageElements;

public class LoginPage extends BasePage {

	private LoginPageElements loginPageElements;

	public LoginPage() {
		super();
		loginPageElements = new LoginPageElements();
	}

	public void populateEmailAndPassword() {

	}

	public void clickLoginButton() {

	}

	public void verifySuccessfullLogin() {

	}

	public void verifyUnsuccessfullLogin() {

	}

	/**
	 * Prepared method for successfull login
	 * populate fields, click login button, verify
	 */
	public void loginUserToTheSystem() {
		populateEmailAndPassword();
		clickLoginButton();
		verifySuccessfullLogin();
	}
}
