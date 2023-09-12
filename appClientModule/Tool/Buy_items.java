package Tool;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TestASM2.Log.LoginData;
import TestASM2.ultils.ExcelUltils;

public class Buy_items {
	private WebDriver driver;
	private final String SRC = ExcelUltils.DATA_SRC + "LOGIN_TEST.xlsx";
	private Set<LoginData> logs;
	private LoginData data;

	String App_running = "https://develop.com.vn/add-to-cart/running-apps-mobile--ng-d-ng-di-ng-h-tr-ch-y-b-gia-t-ng-s-c-kho-";
	String source_taomanguon = "https://develop.com.vn/add-to-cart/millennials---tr-nh-t-o-m-ngu-n-t-y-bi-n-t-i-u";
	String source_linecounter = "https://develop.com.vn/add-to-cart/source-code-line-counter-m-d-ng-m-ngu-n-nhanh-ch-ng";

	@BeforeClass
	public void init() throws IOException {
		System.setProperty("webdriver.chrome.driver", ExcelUltils.CHORME_DRIVER_SRC);
		// khoi tao danh sach log
		logs = new LinkedHashSet<>();
	}

	@BeforeMethod
	public void setUp() {
		driver = new ChromeDriver();
		driver.get("https://develop.com.vn/login");
//		driver.manage().window().maximize();

		data = new LoginData();
	}

	private void processLogin(String username, String password) {
		WebElement userInput = driver.findElement(By.xpath("//*[@id=\"login_form\"]/div[1]/input"));
		userInput.sendKeys(username);
		WebElement passInput = driver.findElement(By.xpath("//*[@id=\"login_form\"]/div[2]/div[2]/input"));
		passInput.sendKeys(password);
		WebElement ClickBtn = driver.findElement(By.xpath("//*[@id=\"login_form\"]/div[4]/button"));
		ClickBtn.click();
	}

	@Test(dataProvider = "loginData")
	public void multiLogin(String username, String password, String expected) throws InterruptedException {
		processLogin(username, password);

		double probability = Math.random(); // Sinh ra một số ngẫu nhiên từ 0.0 đến 1.0

		if (probability < 0.3) { // Điều kiện 30% xuất hiện
			driver.get(App_running);
		} else if (probability < 0.6) { // Điều kiện 30% tiếp theo (tổng 60%)
			driver.get(source_linecounter);
		} else { // Các trường hợp còn lại (40%)
			driver.get(source_taomanguon);
		}

		WebElement btnmuaElement = driver.findElement(By.xpath("//*[@id=\"contact_form\"]/div[2]/button"));
		btnmuaElement.click();
		WebElement btnVisaElement = driver.findElement(By.xpath("//*[@id=\"onepay_visa\"]/div/button"));
		btnVisaElement.click();
		Thread.sleep(2000);

		WebElement inputSotheElement = driver.findElement(By.xpath("//*[@id=\"card_number\"]"));
		inputSotheElement.sendKeys("4242 4242 4242 4242");
		WebElement inputNgay = driver.findElement(By.xpath("//*[@id=\"exp_date\"]"));
		inputNgay.sendKeys("1234");
		WebElement inputotp = driver.findElement(
				By.xpath("//*[@id=\"SelectInternational\"]/div/intercard-main-form/form/div[2]/div[4]/div/input"));
		inputotp.sendKeys("567");
		WebElement btnthanhtoanElement = driver
				.findElement(By.xpath("//*[@id=\"SelectInternational\"]/div/intercard-main-form/form/div[4]/button"));
		btnthanhtoanElement.click();

		Thread.sleep(11000);
	}

	@AfterMethod
	public void teadDown(ITestResult result) throws IOException {
//		data.setTestMethod(result.getName());
//		switch (result.getStatus()) {
//		case ITestResult.SUCCESS:
//			data.setStatus("PASS");
//			break;
//		case ITestResult.FAILURE:
//			data.setStatus("FAILURE");
//			data.setException(result.getThrowable().getMessage());
//
//			// chi dinh huong dan file luu anh
//			String path = ExcelUltils.IMG_SRC + "failure-" + System.currentTimeMillis() + ".png";
//			// goi ham chup anh man hinh tu class tien ich
//			ExcelUltils.takescreenShot(driver, path);
//			// ghi du lieu hinh anh vao workbook
//			data.setImage(path);
//			break;
//		case ITestResult.SKIP:
//			data.setStatus("SKIP");
//			break;
//		default:
//			break;
//		}
		logs.add(data);
		driver.close();
		driver.quit();
	}

	@AfterClass
	public void destroy() throws IOException {
		data.writeLog(SRC, "RESULT-TEST", logs);
	}

	@DataProvider(name = "loginData")
	public Object[][] data() throws IOException {
		// mo file excell de lay du lieu
		XSSFWorkbook workbook = ExcelUltils.getworkbook(SRC);
		// thay doi ten sheet cho phu hop
		XSSFSheet sheet = workbook.getSheet("LOGIN-DATA");
		// doc du lieu test
		Object[][] data = ExcelUltils.readSheetData(sheet);

		return data;
	}
}
