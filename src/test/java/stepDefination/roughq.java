package stepDefination;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class roughq {
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static JavascriptExecutor js;

	public static void main(String[] args) throws InterruptedException, IOException, TesseractException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		// options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		/*
		 * options.addArguments("--incognito"); // DesiredCapabilities object
		 * DesiredCapabilities c = DesiredCapabilities.chrome(); // set capability to
		 * c.setCapability(ChromeOptions.CAPABILITY, options);
		 */

		driver = new ChromeDriver(options);

		driver.manage().window().maximize();

		// driver.get(AppURL);
		driver.manage().timeouts().implicitlyWait(9000, TimeUnit.MILLISECONDS);
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 30);
		js = (JavascriptExecutor) driver;

		driver.get("https://www.irctc.co.in/nget/train-search");
		Thread.sleep(3000);

		WebElement login = driver.findElement(By.xpath("//a[@aria-label='Click here to Login in application']"));
		js.executeScript("arguments[0].click();", login);

		Thread.sleep(3000);
		List <WebElement> data = driver.findElements(By.xpath("//img[@border = '0']"));
		Thread.sleep(3000);
		
		File scr = data.get(1).getScreenshotAs(OutputType.FILE);
		String captchaImg = "capthaimg" + ".png";
		String path = System.getProperty("user.dir") + "/screenshots/" + captchaImg;
		FileHandler.copy(scr, new File(path));
		ITesseract img = new Tesseract();
		String imgtext = img.doOCR(new File(scr.toString()));
		WebElement captchaTextBox = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id = 'nlpAnswer']")));
		captchaTextBox.sendKeys(imgtext);

	}

}
