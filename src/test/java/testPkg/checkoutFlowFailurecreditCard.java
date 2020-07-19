/**
	 * This test case tests the error message when payment is done through the credit card through redirection 
	 * and entering wrong credit card number .
	 * @author Vikram.Kulkarni
	 *
	 */
package testPkg;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import seleniumMethods.ApplicationMethods;

public class checkoutFlowFailurecreditCard extends ApplicationMethods {

	public static WebDriver driver = null;
	public String browser = "chrome";
	public String url = "https://demo.midtrans.com/";
	public String cvv = "123", colorCode, expectedColorCodeInRGB, curTitle, actualTitle;
	public String maxlength, toggleBox;
	boolean flag = false;
	public WebElement checkOutBtn, cardNumber, expiryDate, securityCode;

	@Test
	public void checkOutFFailure() {
		driver = driverInitialization("chrome");
		// open the link. Pass driver and url to be opened
		openApplication(driver, url);
		assertEqualStrings("Sample Store", driver.getTitle());
		// Clicking on Buy Now Button
		driver.findElement(By.cssSelector("a.btn.buy")).click();
		System.out.println("Clicked on by now button");
		// Clicking on Checkout Button
		checkOutBtn = driver.findElement(By.cssSelector("div.cart-checkout"));
		if (checkOutBtn.isEnabled()) {
			System.out.println("Checkout Button Is enabled proceed further : SETTINGS");
			driver.findElement(By.cssSelector("div.cart-checkout-settings")).click();
			System.out.println("selecting Set advanced rules");
			driver.findElement(By.cssSelector("label")).click();
			System.out.println("selecting Redirection");
			driver.findElement(By.id("is_snap_pop_up:false")).click();
			System.out.println("selecting save button");
			driver.findElement(By.cssSelector("a.btn.btn-primary")).click();
			System.out.println("selecting save button - END");
		} else
			System.out.println("Checkout Button Is Disabled");

		driver.findElement(By.cssSelector("div.switch.switch-center.small")).click();
		driver.findElement(By.cssSelector("div.list-title.text-actionable-bold")).click();
		cardNumber = driver.findElement(By.xpath("//div //input[@name='cardnumber']"));
		cardNumber.click();
		cardNumber.sendKeys("4811 1111 1111 1114");
		System.out.println("Before Expiry date");
		expiryDate = driver.findElement(By.cssSelector("div.input-group.col-xs-7 input"));
		expiryDate.click();
		expiryDate.clear();
		expiryDate.sendKeys("05/20");
		System.out.println("Entered wrong Expiry date");
		// Get value of CSS property border-bottom-color which will be returned
		// in RGB format.
		colorCode = expiryDate.getCssValue("border-bottom");
		System.out.println("Color code in RGB" + colorCode);
		expectedColorCodeInRGB = "rgba(255, 255, 255)";
		if (!(expectedColorCodeInRGB.equals(colorCode))) {
			System.out.println("Color codes are not equal. Wrong input might be entered");
			flag = false;
		} else
			flag = true;
		// Asserting actual and expected color codes
		assertFalse(flag, "Data entered is incorrect for expiry month.Please change and try agian");
		curTitle = driver.getTitle();
		actualTitle = "COCO";
		assertEqualStrings(curTitle, actualTitle);
		closeBrowser();
		System.out.println("Closing the browser. Test Execution finished for test case");
	}

}
