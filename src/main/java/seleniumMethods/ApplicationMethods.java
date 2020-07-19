/**
	 * This class contains Selenium specific methods compatible with any given browsers.
	 * @author Vikram.Kulkarni
	 */
package seleniumMethods;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.Assert;

public class ApplicationMethods {

	public static WebDriver driver = null;
	public static WebElement element;

	/**
	 * This method instantiates the driver the URL for any given browsers.
	 *
	 * @author Vikram.Kulkarni
	 * @param takes
	 *            browser type as input
	 * @return driver object
	 */
	public WebDriver driverInitialization(String browsertype) {

		if (browsertype.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
			driver = new ChromeDriver();

		} else if (browsertype.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\geckodriver.exe");
			driver = new FirefoxDriver();

		} else if (browsertype.equalsIgnoreCase("internetexplorer")) {
			System.setProperty("webdriver.ie.driver", "src\\main\\resources\\internetexplorerdriver.exe");
			driver = new FirefoxDriver();
		}

		return driver;
	}

	/**
	 * This method open the URL for any given browsers.
	 *
	 * @author Vikram.Kulkarni
	 * @param takes
	 *            driver and URL as input
	 * @return nothing
	 */
	public void openApplication(WebDriver driver, String url) {
		// GUI Link opening
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println("Page opened is " + driver.getCurrentUrl());
	}

	/**
	 * This method assertsEquals the actual and expected strings.
	 *
	 * @author Vikram.Kulkarni
	 * @param takes
	 *            expected and actual string as input
	 * @return nothing
	 */
	public void assertEqualStrings(String exString, String actualString) {
		Assert.assertEquals(exString, actualString);
	}

	/**
	 * This method assertsEquals the actual and expected boolean. and prints the
	 * message to console
	 * 
	 * @author Vikram.Kulkarni
	 * @param takes
	 *            expected and actual boolean value as input
	 * @return nothing
	 */
	public void assertEqualBooleanValue(boolean expVal, boolean actVal, String msg) {
		Assert.assertEquals(expVal, actVal, msg);
	}

	/**
	 * This method closes the browser.
	 *
	 * @author Vikram.Kulkarni
	 * @param nothing
	 * @return nothing
	 */
	public void closeBrowser() {
		driver.close();
	}

	/**
	 * This method assertFalse the boolean value and prints string message.
	 *
	 * @author Vikram.Kulkarni
	 * @param takes
	 *            values and message to be printed
	 * @return nothing
	 */

	public void assertFalse(boolean val, String msg) {
		Assert.assertFalse(val, msg);
	}
}
