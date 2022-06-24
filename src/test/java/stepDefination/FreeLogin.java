package stepDefination;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import BaseClass.SetUPClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.config.WebDriverManagerException;

public class FreeLogin extends SetUPClass {
	String actualUrl = "https://www.slidegeeks.com/ebooks/checkout";

	@Given("^User is on geeks Home Page$")
	public void user_is_on_geeks_Home_Page() throws Throwable {
		driver.get(AppURL);
		ClearBrowserCache();
	}

	@Then("click on Sign in button")
	public void click_on_sign_in_button() throws InterruptedException {
		try {
			WebElement login = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Login']")));
			login.click();
			Thread.sleep(3000);
		} catch (WebDriverException e) {

			e.printStackTrace();
		}
	}

	@Then("Enter valid user name and password")
	public void enter_valid_user_name_and_password() throws InterruptedException {
		try {

			// without login pop-up

			WebElement email_Address = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='E-mail Address']")));
			Thread.sleep(2000);
			email_Address.clear();
			email_Address.sendKeys("qaslidegeeks@gmail.com");

			WebElement password = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Password']")));
			password.clear();
			password.sendKeys("Qwerty@1");

			WebElement login_btn = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='Submit']")));
			// js.executeScript("arguments[0].click();", login_btn );
			login_btn.click();
			Thread.sleep(3000);

		} catch (NoSuchElementException e) {

		}

	}

	@Then("^Click on Ebooks$")
	public void click_on_Ebooks() throws Throwable {
		try {
			WebElement eBooks = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("EBOOKS")));
			eBooks.click();
			Thread.sleep(3000);
		} catch (WebDriverManagerException e) {

			e.printStackTrace();
		}
	}

	@Then("^Click on Buy Now button$")
	public void click_on_Buy_Now_button() throws Throwable {

		try {
			List<WebElement> buyNow = driver.findElements(By.xpath("//input[@value='BUY NOW']"));
			buyNow.get(2).click();
			Thread.sleep(3000);
		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// verify that user is on shoping cart page
	}

	@Then("^verify the checkout process$")
	public void verify_the_checkout_process() throws Throwable {
		try {

			// select paypal option
			String currenturl = driver.getCurrentUrl();
			System.out.println("url = " + currenturl);
			Assert.assertTrue("user is not logout from the application", currenturl.equals(actualUrl));

			Thread.sleep(2000);

			WebElement selectPaypal = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='payment_radio_1_2__paypal_1']")));
			Thread.sleep(2000);

			js.executeScript("arguments[0].click();", selectPaypal);

			WebElement proceedToCheckout1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.id("hikabtn_checkout_next")));
			Thread.sleep(2000);

			js.executeScript("arguments[0].click();", proceedToCheckout1);
			Thread.sleep(6000);

			// verify that user is on paypal page

			WebElement payWithCreditAndDebit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='createAccount']")));
			Thread.sleep(2000);

			payWithCreditAndDebit.click();
			Thread.sleep(3000);

			String verifyPrice = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='css-ltr-10fi8ut']")))
					.getText();
			Thread.sleep(2000);

			System.out.println("Text = " + verifyPrice);

			driver.get(actualUrl);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^user logout from the application$")
	public void user_logout_from_the_application() throws Throwable {

		try {
			Thread.sleep(3000);
			WebElement logout = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("LOGOUT")));
			js.executeScript("arguments[0].click();", logout);
			Thread.sleep(3000);
		} catch (NoSuchElementException e) {

		}
		String getUrl = driver.getCurrentUrl();

		Assert.assertTrue("Your are not on paypal page", actualUrl.contentEquals(getUrl));
		Thread.sleep(3000);
	}

}
