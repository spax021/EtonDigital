package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;

import config.PropertiesFile;
import pages.BasePage;

public class BaseTest  {

	protected static WebDriver webDriver;
	private ChromeOptions option;
	protected BasePage basePage;

	@BeforeMethod
	public void lounchApplication(ITestContext context) {
		setChromeDriverProperty();

		webDriver = new ChromeDriver();
		context.setAttribute("driver", webDriver);
		
		basePage = new BasePage();
		basePage.setWebDriver(webDriver);

		webDriver.manage().deleteAllCookies();
		webDriver.manage().deleteCookieNamed("sessionKey");
		webDriver.manage().window().maximize();
		webDriver.get( "https://" + PropertiesFile.getBasicAuth() + PropertiesFile.getBaseUrl()); 

	}

	public void quitBrowser() {
		if (webDriver != null) {
			webDriver.quit();
		}
	}

	private void setChromeDriverProperty() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		System.setProperty("webdriver.chrome.verboseLogging", "true");
	}

	public WebDriver getDriver() {
		return this.webDriver;
	}

	public void pause(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
