/**
	 * This test case tests the end to end scenario for checking out a pillow, while paying through 
	 * the credit card through redirection .
	 * @author Vikram.Kulkarni
	 *
	 */
package testPkg;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import seleniumMethods.ApplicationMethods;

public class checkoutFlowcreditCard extends ApplicationMethods {
	public static WebDriver driver = null;
	public String browser = "chrome";
	public String url = "https://demo.midtrans.com/";
	public String cvv = "123", newAmount, curTitle, actualTitle;
	public String maxlength, toggleBox;
	boolean flag = false;
	public WebElement checkOutBtn, cardNumber, expiryDate, securityCode, promo, discountAmount;
	public WebElement otp, phoneNo, submitTransaction;
	public WebDriverWait wait;

	@Test
	public void checkOutSuccess() throws InterruptedException {

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
			// checkOutBtn.click();
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
		expiryDate.sendKeys("09/20");
		System.out.println("After Expiry date");
		securityCode = driver.findElement(By.cssSelector("div.input-group.col-xs-5 input"));
		securityCode.sendKeys(cvv);
		System.out.println("Entered the CVV as: " + cvv);
		maxlength = securityCode.getAttribute("maxlength");
		assertEqualStrings(maxlength, "6");
		System.out.println("Verified that max length is 6");
		// verify that the toggle button for option Save payment details for
		// future transactions is defaulted to 'No'
		toggleBox = driver.findElement(By.cssSelector("div.toggle.toggle-right input")).getAttribute("value");
		assertEqualStrings(toggleBox, "on");
		System.out.println("Verified that toggle box is defaulted to ON value");
		// verify that the check-box is unchecked
		promo = driver.findElement(By.xpath("//label[contains(text(),'Potongan 10% - Demo Promo Engine')] //input"));
		flag = promo.isSelected();
		assertEqualBooleanValue(true, flag, "Checkbox is unchecked");
		System.out.println("checked the check box..");
		// validate that amount is reduced by 200 Rp
		discountAmount = driver.findElement(By.cssSelector(".text-amount-amount"));
		newAmount = discountAmount.getText();
		assertEqualStrings(newAmount, "18,000");
		// click on PAY button
		driver.findElement(By.className("text-button-main")).click();
		// switch to frame
		WebElement frame = driver.findElement(By.cssSelector("div.page-container.scroll iframe"));
		driver.switchTo().frame(frame);
		System.out.println("Switched to frame");
		// enter the OTP
		Thread.sleep(7000);
		otp = driver.findElement(By.xpath("//*[@id='PaRes']"));
		otp.click();
		otp.sendKeys("112233");
		System.out.println("Entered the OTP");
		// click OK
		submitTransaction = driver.findElement(By.name("ok"));
		submitTransaction.isEnabled();
		assertEqualBooleanValue(true, flag, "OK button is enabled");
		submitTransaction.click();
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.titleIs("COCO"));
		curTitle = driver.getTitle();
		actualTitle = "COCO";
		assertEqualStrings(curTitle, actualTitle);
		closeBrowser();
		System.out.println("Closing the browser. Test Execution finished for test case");
	}
}
