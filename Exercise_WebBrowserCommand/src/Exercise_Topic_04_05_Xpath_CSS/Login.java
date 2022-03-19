package Exercise_Topic_04_05_Xpath_CSS;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Login {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String link = "http://live.techpanda.org/";

	// data
	String EMAIL_VALID = "trung9999@yopmail.com";
	String EMAIL_INVALID = "123412345@12312.123123";
	String PWD_VALID = "123456";
	String PWD_INVALID = "123";
	// xpath
	String MY_ACCOUNT_FOOTER = "//div[@class='footer']//a[@title='My Account']";
	String LOGIN_BTN_ID = "send2";
	String EMAIL_ID = "email";
	String PWD_ID = "pass";
	String EMAIL_NULL_ERROR_ID = "advice-required-entry-email";
	String EMAIL_INVALID_ERROR_ID = "advice-validate-email-email";
	String PWD_NULL_ERROR_ID = "advice-required-entry-pass";
	String PWD_INVALID_ERROR_ID = "advice-validate-password-pass";
	String INVALID_LOGIN_PWD = "//ul[@class='messages']//span";
	String CREATE_A_ACCOUNT_BTN = "//a[@title='Create an Account']";
	String CREATE_ACCOUNT_FIRST_NAME_ID = "firstname";
	String CREATE_ACCOUNT_LAST_NAME_ID = "lastname";
	String CREATE_ACCOUNT_EMAIL_ADDRESS_ID = "email_address";
	String CREATE_ACCOUNT_PWD_ID = "password";
	String CREATE_ACCOUNT_CONFIRM_PWD_ID = "confirmation";
	String REGISTER_BTN = "//button[@title='Register']";

	// message
	String EMAIL_NULL_ERROR_MSN = "This is a required field.";
	String EMAIL_INVALID_ERROR_MSN = "Please enter a valid email address. For example johndoe@domain.com.";
	String PWD_NULL_ERROR_MSN = "This is a required field.";
	String PWD_LOWER_THAN_6_CHARACTER_ERROR_MSN = "Please enter 6 or more characters without leading or trailing spaces.";
	String INVALID_LOGIN_PWD_MSN = "Invalid login or password.";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// @Test
	public void TC01_Login_with_empty_email_and_password() throws InterruptedException {
		driver.get(link);
		driver.findElement(By.xpath(MY_ACCOUNT_FOOTER)).click();
		driver.findElement(By.id(LOGIN_BTN_ID)).click();
		Thread.sleep(2000);
		Assert.assertEquals(driver.findElement(By.id(EMAIL_NULL_ERROR_ID)).getText(), EMAIL_NULL_ERROR_MSN);
		Assert.assertEquals(driver.findElement(By.id(PWD_NULL_ERROR_ID)).getText(), PWD_NULL_ERROR_MSN);
	}

	// @Test
	public void TC02_Login_with_invalid_email() throws InterruptedException {
		driver.get(link);
		driver.findElement(By.xpath(MY_ACCOUNT_FOOTER)).click();
		driver.findElement(By.id(EMAIL_ID)).sendKeys(EMAIL_INVALID);
		driver.findElement(By.id(PWD_ID)).sendKeys(PWD_VALID);
		driver.findElement(By.id(LOGIN_BTN_ID)).click();
		Thread.sleep(2000);
		Assert.assertEquals(driver.findElement(By.id(EMAIL_INVALID_ERROR_ID)).getText(), EMAIL_INVALID_ERROR_MSN);
	}

	// @Test
	public void TC03_Login_with_password_lower_than_6_character() throws InterruptedException {
		driver.get(link);
		driver.findElement(By.xpath(MY_ACCOUNT_FOOTER)).click();
		driver.findElement(By.id(EMAIL_ID)).sendKeys(EMAIL_VALID);
		driver.findElement(By.id(PWD_ID)).sendKeys(PWD_INVALID);
		driver.findElement(By.id(LOGIN_BTN_ID)).click();
		Thread.sleep(2000);
		Assert.assertEquals(driver.findElement(By.id(PWD_INVALID_ERROR_ID)).getText(),
				PWD_LOWER_THAN_6_CHARACTER_ERROR_MSN);
	}

	@Test
	public void TC04_Login_with_incorrect_email_and_incorrect_password() throws InterruptedException {
		driver.get(link);
		driver.findElement(By.xpath(MY_ACCOUNT_FOOTER)).click();
		driver.findElement(By.id(EMAIL_ID)).sendKeys(EMAIL_VALID);
		driver.findElement(By.id(PWD_ID)).sendKeys(PWD_VALID);
		driver.findElement(By.id(LOGIN_BTN_ID)).click();
		Thread.sleep(2000);
		Assert.assertEquals(driver.findElement(By.xpath(INVALID_LOGIN_PWD)).getText(), INVALID_LOGIN_PWD_MSN);
	}

	@Test
	public void TC05_Create_a_new_account() {
		driver.get(link);
		driver.findElement(By.xpath(MY_ACCOUNT_FOOTER)).click();
		driver.findElement(By.xpath(CREATE_A_ACCOUNT_BTN)).click();

		Assert.assertEquals(driver.findElement(By.xpath(INVALID_LOGIN_PWD)).getText(), INVALID_LOGIN_PWD_MSN);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private int random() {
		Random random = new Random();
		return random.nextInt(50);
	}

}
