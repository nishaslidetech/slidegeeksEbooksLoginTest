package stepDefination;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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

public class StripeCheckout extends SetUPClass {

	@Given("^Go to the geeks Home page$")
	public void go_to_the_geeks_Home_page() throws Throwable {
		driver.get(AppURL);
		// ClearBrowserCache();
	}

	@Then("^click on signup button$")
	public void click_on_signup_button() throws Throwable {
		Thread.sleep(1000);
		Thread.sleep(3000);
		try {

			WebElement Sign_Up = driver.findElement(By.xpath("//a[@class='signupclass']"));
			Thread.sleep(1000);
			Sign_Up.click();
			Thread.sleep(1000);
			log.info("It's opening the website URL and redirect user to sign up page");
			driver.get("https://www.slidegeeks.com/register?222=111");
		} catch (NoSuchElementException popup) {
		}

	}

	@Then("^User creates a new account$")
	public void User_creates_a_new_account() throws Throwable {
		Thread.sleep(5000);
		WebElement name = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#register_name")));
		Thread.sleep(3000);
		name.sendKeys("Automated Program");
		Thread.sleep(3000);
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		String generatedString = buffer.toString();

		System.out.println(generatedString);

		String signup_email = generatedString;
		String full_email = "selenium.testing." + generatedString + "@gmail.com";
		System.out.println(full_email);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(2000);

		WebElement new_email = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#register_email")));
		new_email.clear();
		new_email.sendKeys(full_email);
		Thread.sleep(3000);

		WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#register_password")));
		password.clear();
		password.sendKeys("Geeks@123");
		Thread.sleep(3000);

		WebElement confirm_passwoed = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#register_password2")));
		// Thread.sleep(3000);
		confirm_passwoed.sendKeys("Geeks@123");
		Thread.sleep(3000);

		/*
		 * WebElement captcha =
		 * wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(
		 * "#captchtext"))); // Thread.sleep(3000); captcha.sendKeys("Y3Tt6bfwI");
		 * Thread.sleep(3000);
		 */
		WebElement register_btn = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".pg-register-button-new")));
		// Thread.sleep(3000);
		register_btn.click();
		Thread.sleep(5000);
	}

	@Then("^verify that user is successfully signup$")
	public void verify_that_user_is_successfully_signup() throws Throwable {

	}

	@Then("^click on ebooks button$")
	public void click_on_ebooks_button() throws Throwable {
		try {
			WebElement eBooks = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Ebooks']")));
			Thread.sleep(3000);
			eBooks.click();
			Thread.sleep(4000);

			try {
				List<WebElement> buyNow = driver.findElements(By.xpath("//input[@value = 'BUY NOW']"));
				buyNow.get(3).click();
				Thread.sleep(3000);
			} catch (WebDriverException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Thread.sleep(7000);
		} catch (WebDriverManagerException e) {

			e.printStackTrace();
		}
	}

	@Then("^verify the checkout process via stripe$")
	public void verify_the_checkout_process_via_stripe() throws Throwable {
		try {
			boolean cp_btn = wait
					.until(ExpectedConditions
							.elementToBeClickable(By.xpath("//label[@for='payment_radio_1_2__stripe_2']")))
					.isSelected();
			System.out.println(cp_btn + "= value");
			Thread.sleep(3000);
			if (cp_btn == true) {

				WebElement place_order_btn = wait
						.until(ExpectedConditions.elementToBeClickable(By.id("hikabtn_checkout_next")));
				Thread.sleep(3000);
				js.executeScript("arguments[0].click();", place_order_btn);

			} else {
				WebElement stripe = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//label[@for='payment_radio_1_2__stripe_2']")));
				Thread.sleep(3000);
				js.executeScript("arguments[0].click();", stripe);

				Thread.sleep(3000);

				WebElement place_order_btn = wait
						.until(ExpectedConditions.elementToBeClickable(By.id("hikabtn_checkout_next")));
				Thread.sleep(3000);
				js.executeScript("arguments[0].click();", place_order_btn);
				Thread.sleep(6000);

				// verify the page and navigate back to account page

				String pp_page_title = driver.getTitle();

				System.out.println("Title of the Page is --> " + pp_page_title);

				Assert.assertTrue("title does not matched", driver.getTitle().contains("SlideTeam Geeks Inc"));

				Thread.sleep(4000);

				// verify price
				String verifyPrice = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'$19.99')]")))
						.getText();
				System.out.println("verifyPrice = " + verifyPrice);

				Assert.assertTrue("Your are not on paypal page", verifyPrice.contentEquals("$19.99"));

				WebElement Stripe_back1 = driver
						.findElement(By.xpath("//div[@class='Header-backArrowContainer']//*[name()='svg']"));
				Thread.sleep(3000);
				Stripe_back1.click();
				// js.executeScript("agruments[0].click();", Stripe_back1);
				Thread.sleep(4000);

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^delete the account$")
	public void delete_the_account() throws Throwable {
		Thread.sleep(2000);

		WebElement Account = driver.findElement(By.xpath("//a[normalize-space()='Account']"));
		Thread.sleep(3000);
		Account.click();
		Thread.sleep(3000);
		WebElement Delete_Account = driver.findElement(By.xpath("//a[normalize-space()='Delete Account']"));
		Thread.sleep(3000);
		js.executeScript("arguments[0].scrollIntoView();", Delete_Account);
		Thread.sleep(3000);
		Delete_Account.click();
		Thread.sleep(3000);
		WebElement Delete_Account_reason = driver.findElement(By.cssSelector("#only-free-download-product"));
		Thread.sleep(3000);
		Delete_Account_reason.click();
		Thread.sleep(3000);
		WebElement Delete_Profile = driver.findElement(By.xpath("//button[@id='delete_profile']"));
		Thread.sleep(3000);
		Delete_Profile.click();
		Thread.sleep(3000);
		WebElement No_Delete = driver.findElement(By.xpath("//button[@class='btn btn-default button_2']"));
		Thread.sleep(3000);
		No_Delete.click();
		Thread.sleep(7000);

	}

	@Then("^verify the account is successfully deleted$")
	public void verify_the_account_is_successfully_deleted() throws Throwable {
		// verify that wether the account is deleted or not?
		String verifyDeleteAccountMessage = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='alert-message login-sucesmsg']")))
				.getText();
		System.out.println("verifyText1 = " + verifyDeleteAccountMessage);

		Assert.assertTrue("Account is not deleted successfully",
				verifyDeleteAccountMessage.contentEquals("Your Account has been deleted successfully."));
		Thread.sleep(3000);
	}

}
