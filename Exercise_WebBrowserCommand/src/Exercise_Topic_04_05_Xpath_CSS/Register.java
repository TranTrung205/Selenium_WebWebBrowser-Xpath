package Exercise_Topic_04_05_Xpath_CSS;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Register {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	// data
	String link = "https://alada.vn/tai-khoan/dang-ky.html";
	String EMAIL_VALID = "txtEmail@yopmail.com";
	String EMAIL_INVALID = "txtEmail";
	String CEMAIL_VALID = "txtEmail@yopmail.com";
	String CEMAIL_INVALID = "txtEmaill";
	String PWD_VALID = "Abc@123!";
	String PWD_INVALID_LOWER_THAN_6_CHARACTERS = "123!";
	String PHONE_NUM_VALID = "0913123123";
	String PHONE_NUM_INVALID = "09999";
	// xpath
	String DANG_KY_BTN = "//div[@class='field_btn']/button";
	String HO_VA_TEN_ID = "txtFirstname";
	String HO_VA_TEN_ERROR_MSG_ID = "txtFirstname-error";
	String HO_VA_TEN_VALID = "First Name";
	String NHAP_EMAIL_ID = "txtEmail";
	String NHAP_LAI_EMAIL_ID = "txtCEmail";
	String NHAP_LAI_CEMAIL_ERROR_ID = "txtCEmail-error";
	String NHAP_PWD_ID = "txtPassword";
	String NHAP_PWD_ERROR_ID = "txtPassword-error";
	String NHAP_LAI_PWD_ID = "txtCPassword";
	String NHAP_LAI_PWD_ERROR_ID = "txtCPassword-error";
	String NHAP_PHONE_NUM_ID = "txtPhone";
	String NHAP_PHONE_NUM_ERROR_ID = "txtPhone-error";
	// Error Message
	String NHAP_EMAIL_ERROR_ID = "txtEmail-error";
	String HO_VA_TEN_ERROR_MSN = "Vui lòng nhập họ tên";
	String NHAP_EMAIL_ERROR_MSN = "Vui lòng nhập email";
	String NHAP_LAI_EMAIL_ERROR_MSN = "Vui lòng nhập lại địa chỉ email";
	String NHAP_INVALID_EMAIL_ERROR_MSN = "Vui lòng nhập email hợp lệ";
	String NHAP_LAI_CEMAIL_ERROR_MSN = "Vui lòng nhập lại địa chỉ email";
	String NHAP_LAI_INVALID_CEMAIL_ERROR_MSN = "Email nhập lại không đúng";
	String NHAP_PWD_ERROR_MSN = "Vui lòng nhập mật khẩu";
	String NHAP_PWD_ERROR_LOWER_THAN_6_CHARACTER_MSN = "Mật khẩu phải có ít nhất 6 ký tự";
	String NHAP_LAI_PWD_ERROR_MSN = "Vui lòng nhập lại mật khẩu";
	String NHAP_LAI_PWD_ERROR_LOWER_THAN_6_CHARACTER_MSN = "Mật khẩu phải có ít nhất 6 ký tự";
	String NHAP_LAI_PWD_ERROR_KHONG_KHOP = "Mật khẩu bạn nhập không khớp";

	String NHAP_PHONE_NUM_ERROR_MSN = "Vui lòng nhập số điện thoại.";
	String NHAP_PHONE_NUM_ERROR_10_11_So_MSN = "Số điện thoại phải từ 10-11 số.";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC01_Register_with_empty_data() {
		driver.get(link);
		driver.findElement(By.xpath(DANG_KY_BTN)).click();
		Assert.assertEquals(driver.findElement(By.id(HO_VA_TEN_ERROR_MSG_ID)).getText(), HO_VA_TEN_ERROR_MSN);
		Assert.assertEquals(driver.findElement(By.id(NHAP_EMAIL_ERROR_ID)).getText(), NHAP_EMAIL_ERROR_MSN);
		Assert.assertEquals(driver.findElement(By.id(NHAP_LAI_CEMAIL_ERROR_ID)).getText(), NHAP_LAI_CEMAIL_ERROR_MSN);
		Assert.assertEquals(driver.findElement(By.id(NHAP_PWD_ERROR_ID)).getText(), NHAP_PWD_ERROR_MSN);
		Assert.assertEquals(driver.findElement(By.id(NHAP_LAI_PWD_ERROR_ID)).getText(), NHAP_LAI_PWD_ERROR_MSN);
		Assert.assertEquals(driver.findElement(By.id(NHAP_PHONE_NUM_ERROR_ID)).getText(), NHAP_PHONE_NUM_ERROR_MSN);
	}

	@Test
	public void TC02_Register_with_invalid_email() {
		driver.get(link);
		driver.findElement(By.xpath(DANG_KY_BTN)).click();
		driver.findElement(By.id(HO_VA_TEN_ID)).sendKeys(HO_VA_TEN_VALID);
		driver.findElement(By.id(NHAP_PWD_ID)).sendKeys(PWD_VALID);
		driver.findElement(By.id(NHAP_LAI_PWD_ID)).sendKeys(PWD_VALID);
		driver.findElement(By.id(NHAP_EMAIL_ID)).sendKeys(EMAIL_INVALID);
		driver.findElement(By.id(NHAP_LAI_EMAIL_ID)).sendKeys(EMAIL_INVALID);
		driver.findElement(By.id(NHAP_PHONE_NUM_ID)).sendKeys(PHONE_NUM_VALID);
		driver.findElement(By.xpath(DANG_KY_BTN)).click();
		Assert.assertEquals(driver.findElement(By.id(NHAP_EMAIL_ERROR_ID)).getText(), NHAP_INVALID_EMAIL_ERROR_MSN);
		Assert.assertEquals(driver.findElement(By.id(NHAP_LAI_CEMAIL_ERROR_ID)).getText(),
				NHAP_LAI_INVALID_CEMAIL_ERROR_MSN);
	}

	@Test
	public void TC03_Register_with_invalid_confirm_email() {
		driver.get(link);
		driver.findElement(By.xpath(DANG_KY_BTN)).click();
		driver.findElement(By.id(HO_VA_TEN_ID)).sendKeys(HO_VA_TEN_VALID);
		driver.findElement(By.id(NHAP_PWD_ID)).sendKeys(PWD_VALID);
		driver.findElement(By.id(NHAP_LAI_PWD_ID)).sendKeys(PWD_VALID);
		driver.findElement(By.id(NHAP_EMAIL_ID)).sendKeys(EMAIL_VALID);
		driver.findElement(By.id(NHAP_LAI_EMAIL_ID)).sendKeys(EMAIL_INVALID);
		driver.findElement(By.id(NHAP_PHONE_NUM_ID)).sendKeys(PHONE_NUM_VALID);
		driver.findElement(By.xpath(DANG_KY_BTN)).click();
		Assert.assertEquals(driver.findElement(By.id(NHAP_LAI_CEMAIL_ERROR_ID)).getText(),
				NHAP_LAI_INVALID_CEMAIL_ERROR_MSN);
	}

	@Test
	public void TC04_Register_with_password_lower_than_6_characters() {
		driver.get(link);
		driver.findElement(By.xpath(DANG_KY_BTN)).click();
		driver.findElement(By.id(HO_VA_TEN_ID)).sendKeys(HO_VA_TEN_VALID);
		driver.findElement(By.id(NHAP_PWD_ID)).sendKeys(PWD_INVALID_LOWER_THAN_6_CHARACTERS);
		driver.findElement(By.id(NHAP_LAI_PWD_ID)).sendKeys(PWD_INVALID_LOWER_THAN_6_CHARACTERS);
		driver.findElement(By.id(NHAP_EMAIL_ID)).sendKeys(EMAIL_VALID);
		driver.findElement(By.id(NHAP_LAI_EMAIL_ID)).sendKeys(EMAIL_VALID);
		driver.findElement(By.id(NHAP_PHONE_NUM_ID)).sendKeys(PHONE_NUM_VALID);
		driver.findElement(By.xpath(DANG_KY_BTN)).click();
		Assert.assertEquals(driver.findElement(By.id(NHAP_PWD_ERROR_ID)).getText(),
				NHAP_PWD_ERROR_LOWER_THAN_6_CHARACTER_MSN);
		Assert.assertEquals(driver.findElement(By.id(NHAP_LAI_PWD_ERROR_ID)).getText(),
				NHAP_PWD_ERROR_LOWER_THAN_6_CHARACTER_MSN);
	}

	@Test
	public void TC05_Register_with_password_do_not_match() {
		driver.get(link);
		driver.findElement(By.xpath(DANG_KY_BTN)).click();
		driver.findElement(By.id(HO_VA_TEN_ID)).sendKeys(HO_VA_TEN_VALID);
		driver.findElement(By.id(NHAP_PWD_ID)).sendKeys(PWD_INVALID_LOWER_THAN_6_CHARACTERS);
		driver.findElement(By.id(NHAP_LAI_PWD_ID)).sendKeys(PWD_VALID);
		driver.findElement(By.id(NHAP_EMAIL_ID)).sendKeys(EMAIL_VALID);
		driver.findElement(By.id(NHAP_LAI_EMAIL_ID)).sendKeys(EMAIL_VALID);
		driver.findElement(By.id(NHAP_PHONE_NUM_ID)).sendKeys(PHONE_NUM_VALID);
		driver.findElement(By.xpath(DANG_KY_BTN)).click();
		Assert.assertEquals(driver.findElement(By.id(NHAP_LAI_PWD_ERROR_ID)).getText(), NHAP_LAI_PWD_ERROR_KHONG_KHOP);
	}

	@Test
	public void TC06_Register_with_invalid_phone_number() {
		driver.get(link);
		driver.findElement(By.xpath(DANG_KY_BTN)).click();
		driver.findElement(By.id(HO_VA_TEN_ID)).sendKeys(HO_VA_TEN_VALID);
		driver.findElement(By.id(NHAP_PWD_ID)).sendKeys(PWD_VALID);
		driver.findElement(By.id(NHAP_LAI_PWD_ID)).sendKeys(PWD_VALID);
		driver.findElement(By.id(NHAP_EMAIL_ID)).sendKeys(EMAIL_VALID);
		driver.findElement(By.id(NHAP_LAI_EMAIL_ID)).sendKeys(EMAIL_VALID);
		driver.findElement(By.id(NHAP_PHONE_NUM_ID)).sendKeys(PHONE_NUM_INVALID);
		driver.findElement(By.xpath(DANG_KY_BTN)).click();
		Assert.assertEquals(driver.findElement(By.id(NHAP_PHONE_NUM_ERROR_ID)).getText(),
				NHAP_PHONE_NUM_ERROR_10_11_So_MSN);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
