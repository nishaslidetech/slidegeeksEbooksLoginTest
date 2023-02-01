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

public class PaidLogin extends SetUPClass {

	@Given("^User is on geeks Home Pageii$")
	public void user_is_on_geeks_Home_Pageii() throws Throwable {
		driver.get(AppURL);
		ClearBrowserCache();
	}

	@Then("click on signin butoon")
	public void click_on_signin_butoon() throws InterruptedException {
		try {
			WebElement login = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Login']")));
			login.click();
			Thread.sleep(3000);
		} catch (WebDriverException e) {

			e.printStackTrace();
		}
	}

	@Then("enter vaild paid credentials")
	public void enter_vaild_paid_credentials() throws InterruptedException {
		try {

			WebElement email_Address = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='E-mail Address']")));
			Thread.sleep(2000);
			email_Address.clear();
			email_Address.sendKeys("sumit.kumar@slidetech.in");

			WebElement password = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Password']")));
			password.clear();
			password.sendKeys("redhat2090");

			WebElement login_btn = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='Submit']")));
			// js.executeScript("arguments[0].click();", login_btn );
			login_btn.click();
			Thread.sleep(4000);

		} catch (NoSuchElementException e) {

		}

	}

	@Then("^Click on Ebooksii$")
	public void click_on_Ebooksii() throws Throwable {
		try {
			WebElement eBooks = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Ebooks']")));
			eBooks.click();
			Thread.sleep(3000);
		} catch (WebDriverManagerException e) {

			e.printStackTrace();
		}
	}

	@Then("^Click on Download option$")
	public void click_on_Download_option() throws Throwable {
		try {
			WebElement download = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='download_8']")));
			Thread.sleep(2000);
			download.click();
			Thread.sleep(3000);
		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Then("^User logout from the application$")
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
