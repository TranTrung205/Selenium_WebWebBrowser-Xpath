import java.util.Currency;
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
	String EMAIL_INVALID = "txtEmail@yopmail";
	String PWD_VALID = "Abc@123!";
	String PHONE_NUM_VALID = "0913123123";
	String PHONE_NUM_INVALID = "0xx@443123";
	// xpath
	String DANG_KY_BTN = "//div[@class='field_btn']/button";
	String HO_VA_TEN_ID = "txtFirstname";
	String HO_VA_TEN_ERROR_MSG_ID = "txtFirstname-error";
	String HO_VA_TEN_VALID = "First Name";
	String HO_VA_TEN_ERROR_MSN = "Vui lòng nhập họ tên";
	String NHAP_EMAIL_ID = "txtEmail";
	String NHAP_EMAIL_ERROR_ID = "txtEmail-error";
	String NHAP_EMAIL_ERROR_MSN = "Vui lòng nhập email";
	String NHAP_LAI_EMAIL_ID = "txtCEmail";
	String NHAP_LAI_CEMAIL_ERROR_ID = "txtCEmail-error";
	String NHAP_LAI_EMAIL_ERROR_MSN = "Vui lòng nhập lại địa chỉ email";
	String NHAP_INVALID_EMAIL_ERROR_MSN = "Vui lòng nhập email hợp lệ";
	String CEMAIL_VALID = "txtEmail@yopmail.com";
	String CEMAIL_INVALID = "txtEmail@yopmail";
	String NHAP_LAI_CEMAIL_ERROR_MSN = "Vui lòng nhập lại địa chỉ email";
	String NHAP_LAI_INVALID_CEMAIL_ERROR_MSN = "Email nhập lại không đúng";
	String NHAP_PWD_ID = "txtPassword";
	String NHAP_PWD_ERROR_ID = "txtPassword-error";
	String NHAP_PWD_ERROR_MSN = "Vui lòng nhập mật khẩu";
	String NHAP_LAI_PWD_ID = "txtCPassword";
	String NHAP_LAI_PWD_ERROR_ID = "txtCPassword-error";
	String NHAP_LAI_PWD_ERROR_MSN = "Vui lòng nhập lại mật khẩu";
	String NHAP_PHONE_NUM_ID = "txtPhone";
	String NHAP_PHONE_NUM_ERROR_ID = "txtPhone-error";
	String NHAP_PHONE_NUM_ERROR_MSN = "Vui lòng nhập số điện thoại. ";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(link);

	}

	@Test
	public void TC01_Register_with_empty_data() {
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

//	@Test
	public void TC03_Register_with_invalid_confirm_email() {
		driver.get(link);
		driver.findElement(By.xpath(DANG_KY_BTN)).click();
		driver.findElement(By.id(HO_VA_TEN_ID)).sendKeys(HO_VA_TEN_VALID);
		driver.findElement(By.id(NHAP_PWD_ID)).sendKeys(PWD_VALID);
		driver.findElement(By.id(NHAP_LAI_PWD_ID)).sendKeys(PWD_VALID);
		driver.findElement(By.id(NHAP_PHONE_NUM_ID)).sendKeys(PHONE_NUM_VALID);
		driver.findElement(By.xpath(DANG_KY_BTN)).click();
		Assert.assertTrue(driver.findElement(By.id(NHAP_EMAIL_ID)).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id(NHAP_LAI_EMAIL_ID)).isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
