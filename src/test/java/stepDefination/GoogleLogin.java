package stepDefination;

import java.io.File;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;

import BaseClass.SetUPClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.config.WebDriverManagerException;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

public class GoogleLogin extends SetUPClass {

	@Given("^user is on Home Page$")
	public void user_is_on_Home_Page() throws Throwable {
		ClearGoggleCache();
		driver.get(AppURL);
		Thread.sleep(3000);

	}

	@Then("^click on Ebook button$")
	public void click_on_Ebook_button() throws Throwable {
		try {
			WebElement eBooks = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("EBOOKS")));
			eBooks.click();
			Thread.sleep(3000);
		} catch (WebDriverManagerException e) {

			e.printStackTrace();
		}
	}

	@Then("^click on Buynow option$")
	public void click_on_Buynow_option() throws Throwable {

		try {
			List<WebElement> buyNow = driver.findElements(By.linkText("BUY NOW"));
			buyNow.get(1).click();
			Thread.sleep(5000);
		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Then("^click on google option$")
	public void click_on_google_option() throws Throwable {
		try {
			WebElement google = SetUPClass
					.elementToBeClickable(By.xpath("//form[@id='site_signup_form']//img[@id='google-signin-btn']"));
			Thread.sleep(2000);
			js.executeScript("arguments[0].click();", google);
			Thread.sleep(3000);
		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Then("^enter valid credentials$")
	public void enter_valid_credentials() throws Throwable {
		driver.manage().window().maximize();

		// Store the CurrentWindow for future reference
		// String handle = " ";
		String currentWindow = driver.getWindowHandle();
		String popupWindowHandle = null;

		// Switch To Popup Window

		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(currentWindow)) {

				popupWindowHandle = handle;
				driver.switchTo().window(popupWindowHandle);
				driver.manage().window().maximize();

				Thread.sleep(5000);

				if (!driver.findElements(By.xpath("//div[@class='BHzsHc']")).isEmpty()) {
					WebElement another_btn = wait.until(
							ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Use another account']")));
					another_btn.click();
				}

				WebElement g_email = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
						"/html/body/div[1]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[1]/div/form/span/section/div/div/div[1]/div/div[1]/div/div[1]/input")));
				g_email.sendKeys("slidetech.qa@gmail.com");
				Thread.sleep(3000);

				WebElement g_login_btn1 = wait.until(ExpectedConditions
						.elementToBeClickable(By.cssSelector("#identifierNext > div > button > span")));
				g_login_btn1.click();
				Thread.sleep(3000);

				if (!driver.findElements(By.xpath("//input[@type = 'text']")).isEmpty()) {
					Thread.sleep(3000);
					
					File scr = wait
							.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@id = 'captchaimg']")))
							.getScreenshotAs(OutputType.FILE);
					String captchaImg = "capthaimg" + ".jpg";
					String path = System.getProperty("user.dir") + "/screenshots/" + captchaImg;
					FileHandler.copy(scr, new File(path));
					ITesseract img = new Tesseract();
					String imgtext = img.doOCR(new File(path));
					WebElement captchaTextBox = wait
							.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type = 'text']")));
					captchaTextBox.sendKeys(imgtext);

					WebElement next = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Next")));
					next.click();

				}

				Thread.sleep(90000000);
				WebElement g_pass = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
						"/html/body/div[1]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[1]/div/form/span/section/div/div/div[1]/div[1]/div/div/div/div/div[1]/div/div[1]/input")));

				g_pass.sendKeys("Himanshi@123");

				Thread.sleep(3000);
				WebElement g_login_btn2 = wait.until(
						ExpectedConditions.elementToBeClickable(By.cssSelector("#passwordNext > div > button > span")));
				g_login_btn2.click();

				/*
				 * if (!driver.findElements(By.xpath("//div[@class='login-attempt-popup']")).
				 * isEmpty()) { WebElement approve = wait
				 * .until(ExpectedConditions.elementToBeClickable(By.xpath(
				 * "//input[@id='confirm-approve']"))); approve.click(); }
				 */
			}
		}
		driver.switchTo().window(currentWindow);
		Thread.sleep(7000);

	}

	@Then("^verify that user is successfully logged in$")
	public void verify_that_user_is_successfully_logged_in() throws Throwable {

	}

	@Then("^download the selected ebbok$")
	public void download_the_selected_ebbok() throws Throwable {
		WebElement download = SetUPClass.elementToBeClickable(By.xpath("//a[@id='download_8']"));
		Thread.sleep(3000);
		download.click();
		Thread.sleep(3000);

	}

	@Then("^logout from the application$")
	public void logout_from_the_application() throws Throwable {
		try {
			Thread.sleep(3000);
			WebElement logout = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("LOGOUT")));
			js.executeScript("arguments[0].click();", logout);
			Thread.sleep(3000);
		} catch (NoSuchElementException e) {

		}
	}

	@Then("^verify that user is successfully logged out$")
	public void verify_that_user_is_successfully_logged_out() throws Throwable {

		String getUrl = driver.getCurrentUrl();
		System.out.println(getUrl + "=getUrl");
		Assert.assertTrue("Your are not on paypal page", actualUrl.contentEquals(getUrl));
		Thread.sleep(3000);
	}

}
