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

public class FacebookLogin extends SetUPClass {

	@Given("^Go to the Home page$")
	public void go_to_the_Home_page() throws Throwable {
		ClearfacebookCache();
		driver.get(AppURL);

		Thread.sleep(3000);
	}

	@Then("^Click on ebook button$")
	public void click_on_ebook_button() throws Throwable {
		try {
			WebElement eBooks = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("EBOOKS")));
			Thread.sleep(3000);
			eBooks.click();
			Thread.sleep(3000);
		} catch (WebDriverManagerException e) {

			e.printStackTrace();
		}
	}

	@Then("^Click on Buynow option$")
	public void click_on_Buynow_option() throws Throwable {

		try {
			List<WebElement> buyNow = driver.findElements(By.linkText("BUY NOW"));
			buyNow.get(1).click();
			Thread.sleep(3000);
		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Then("^Click on facebook option$")
	public void click_on_facebook_option() throws Throwable {

		try {
			WebElement facebook = SetUPClass
					.elementToBeClickable(By.xpath("//form[@id='site_signup_form']//img[@id='facebook-signin-btn']"));
			Thread.sleep(2000);
			facebook.click();
			Thread.sleep(3000);
		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Then("^Enter valid credentials$")
	public void enter_valid_credentials() throws Throwable {

		driver.manage().window().maximize();
		// String handle = " ";
		String currentWindow = driver.getWindowHandle();
		String popupWindowHandle = null;

		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(currentWindow)) {

				popupWindowHandle = handle;
				driver.switchTo().window(popupWindowHandle);
				// driver.manage().window().maximize();

				Thread.sleep(4000);

				WebElement fb_email = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='email']")));
				fb_email.clear();
				fb_email.sendKeys("sumit.kumar@slidetech.in");
				Thread.sleep(3000);
				WebElement fb_pass = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='pass']")));

				fb_pass.clear();
				fb_pass.sendKeys("redhat2090");
				Thread.sleep(3000);
				try {
					if (!driver.findElements(By.xpath("//input[@value='Log In']")).isEmpty()) {
						driver.findElement(By.xpath("//input[@value='Log In']")).click();
					} else {
						WebElement fb_login = wait.until(
								ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='loginbutton']")));

						fb_login.click();
						Thread.sleep(2000);
					}
				} catch (NoSuchElementException e) {

				}
				if (!driver.findElements(By.xpath("//div[@class='login-attempt-popup']")).isEmpty()) {
					WebElement approve = wait
							.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='confirm-approve']")));
					approve.click();
				}
				// to verify the continue button
				// button[normalize-space()='Yes, Continue']

			}
		}
		driver.switchTo().window(currentWindow);
		Thread.sleep(6000);

	}

	@Then("^Verify that user is successfully logged in$")
	public void verify_that_user_is_successfully_logged_in() throws Throwable {
		Thread.sleep(1000);
	}

	@Then("^Download the selected ebbok$")
	public void download_the_selected_ebbok() throws Throwable {
		
		System.out.println("url after facebook signup = "+ driver.getCurrentUrl());
		Thread.sleep(2000);
		try {
			WebElement download = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='download_8']")));
			js.executeScript("arguments[0].scrollIntoView(true);",download);
			Thread.sleep(2000);
			download.click();
			Thread.sleep(3000);
		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Then("^Logout from the application$")
	public void logout_from_the_application() throws Throwable {
		try {
			Thread.sleep(3000);
			WebElement sign_Out = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("LOGOUT")));
			js.executeScript("arguments[0].click();", sign_Out);
			Thread.sleep(3000);
		} catch (NoSuchElementException e) {

		}

		// verify the logout

		String getUrl = driver.getCurrentUrl();

		Assert.assertTrue("Your are not on paypal page", actualUrl.contentEquals(getUrl));
		Thread.sleep(3000);

	}

}
