package com.tau;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class TauTest {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:/PortableProgramFiles/chromedriver_win32/chromedriver.exe");
		driver = new ChromeDriver();
		baseUrl = "http://automationpractice.com";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

//	@Test
	public void test() throws Exception {

		driver.get(baseUrl + "/");
		String expectedTxt = "Sign in";
		String actualTxt = driver.findElement(By.className("login")).getText();

		assertEquals("czy strona zawiera pole Sign in", expectedTxt.toLowerCase(), actualTxt.toLowerCase());

		// driver.findElement(By.linkText("MyBookmarks")).click();
		// driver.findElement(By.id("bookmarksFilter")).clear();
		// driver.findElement(By.id("bookmarksFilter")).sendKeys("c++");
//		assertEquals("wandbox", driver
//				.findElement(By.cssSelector("div.row:nth-child(9) > div:nth-child(1) > a:nth-child(1)")).getText());
//		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//		FileUtils.copyFile(scrFile, new File("target/SomeSiteTest1.png"));
	}

//	@Test 
	// problem z opoznieniem

	public void validationHelper(Dimension d) throws Exception {

		driver.manage().window().setSize(d);

		driver.get(baseUrl + "/index.php?controller=authentication&back=my-account");
		WebElement emailInput = driver.findElement(By.id("email_create"));
		emailInput.clear();
		emailInput.sendKeys("abc2.pl@");
		driver.findElement(By.id("SubmitCreate")).click();
		// driver.wait(5000);
		Thread.sleep(5000);
		String expectedTxt = "Invalid email address.";
		String actualTxt = driver.findElement(By.id("create_account_error")).getText();

		assertEquals("strona waliduje skladnie adres email", expectedTxt, actualTxt);

	}

	//@Test
	public void successfulRegistration() throws Exception {
		driver.get(baseUrl + "/index.php?controller=authentication&back=my-account");
		WebElement emailInput = driver.findElement(By.id("email_create"));
		emailInput.clear();
		long t = System.currentTimeMillis();
		emailInput.sendKeys( "abc" + t + "@op.pl");
		driver.findElement(By.id("SubmitCreate")).click();
		WebElement firstName = driver.findElement(By.id("customer_firstname"));
		firstName.sendKeys("Mateusz");
		WebElement lastName = driver.findElement(By.id("customer_lastname"));
		lastName.sendKeys("Kowalski");
		WebElement password = driver.findElement(By.id("passwd"));
		password.sendKeys("1234");
		WebElement address = driver.findElement(By.id("address1"));
		address.sendKeys("Aleje ujazdowskie");
		WebElement city = driver.findElement(By.id("city"));
		city.sendKeys("Warszawa");
		new Select(driver.findElement(By.id("id_state"))).selectByVisibleText("Mazowieckie");
		WebElement zip = driver.findElement(By.id("postcode"));
		zip.sendKeys("22939");
		new Select(driver.findElement(By.id("id_country"))).selectByVisibleText("United States");
		WebElement phone = driver.findElement(By.id("phone_mobile"));
		phone.sendKeys("8498394039");
		WebElement alias = driver.findElement(By.id("alias"));
		alias.clear();
		alias.sendKeys("ktostam");
		driver.findElement(By.id("submitAccount")).click();
		String actualTxt = driver.findElement(By.className("account")).getText();
		String expectedTxt = "Mateusz Kowalski";

		driver.manage().deleteAllCookies();
		assertEquals("strona sprawdza poprawna rejestracje", expectedTxt, actualTxt);

	}

	@Test
	public void unsuccesfulRegistration() throws Exception {
		driver.get(baseUrl + "/index.php?controller=authentication&back=my-account");
		WebElement emailInput = driver.findElement(By.id("email_create"));
		emailInput.clear();
		emailInput.sendKeys("m.kowalski@gmail.com");
		driver.findElement(By.id("SubmitCreate")).click();
		driver.findElement(By.id("submitAccount")).click();

		boolean errorFound;

		try {
			driver.findElement(By.className("alert-danger"));
			errorFound = true;
		} catch (NoSuchElementException e) {
			errorFound = false;

		}

		assertEquals("strona z nieudana rejestracja", true, errorFound);

	}

	//@Test
	public void responsiveTestMobileResolution() throws Exception {

		Dimension d = new Dimension(480, 800);
		validationHelper(d);
		Point p1 = driver.findElement(By.id("create-account_form")).getLocation();
		Point p2 = driver.findElement(By.id("login_form")).getLocation();

		assertEquals("porownanie polozenia", true, p2.getY() > p1.getY());

	}

	//@Test
	public void responsiveTestDesktopResolution() throws Exception {

		Dimension d = new Dimension(1200, 600);
		validationHelper(d);
		Point p1 = driver.findElement(By.id("create-account_form")).getLocation();
		Point p2 = driver.findElement(By.id("login_form")).getLocation();

		assertEquals("porownanie polozenia", true, Math.abs(p2.getY() - p1.getY()) < 5);

	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
