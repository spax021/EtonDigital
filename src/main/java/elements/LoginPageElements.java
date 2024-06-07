package elements;

import org.openqa.selenium.By;

public class LoginPageElements {
	
	private By emailInputField = By.id("email");
	private By emailErrorMessage = By.cssSelector(".c-input-field.invalid-input + .invalid-msg");
	private By passwordInputField = By.id("password");
	private By passwordErrorMessage = By.cssSelector(".c-pass-input + .invalid-msg");
	private By showPasswordButton = By.cssSelector(".eye-icon-closed");
	private By forgotPasswordButton = By.cssSelector(".c-sign-in__form--reset-pass");
	private By loginButton = By.cssSelector("[type='submit']");
	private By signUpButton = By.linkText("Sign up");
	private By cookieBannerOKButton = By.cssSelector(".cookie-banner .btn");
	
	public By getEmailInputField() {
		return emailInputField;
	}
	public By getEmailErrorMessage() {
		return emailErrorMessage;
	}
	public By getPasswordInputField() {
		return passwordInputField;
	}
	public By getPasswordErrorMessage() {
		return passwordErrorMessage;
	}
	public By getShowPasswordButton() {
		return showPasswordButton;
	}
	public By getForgotPasswordButton() {
		return forgotPasswordButton;
	}
	public By getLoginButton() {
		return loginButton;
	}
	public By getSignUpButton() {
		return signUpButton;
	}
	public By getCookieBannerOKButton() {
		return cookieBannerOKButton;
	}
}
