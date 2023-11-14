package Tool;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import develop.log.Action;
import develop.ultils.ExcelUltils;

public class Create_action_fake {
	private WebDriver driver;
	private final String SRC = ExcelUltils.DATA_SRC + "ACTION.xlsx";
	private Set<Action> logs;
	private Action data;

	String item1 = "https://develop.com.vn/item/combo-3-mau-flyer-chat-luong-cao-de-tao-n-tuong-manh-me";
//	String source_taomanguon = "https://develop.com.vn/add-to-cart/millennials---tr-nh-t-o-m-ngu-n-t-y-bi-n-t-i-u";
//	String source_linecounter = "https://develop.com.vn/add-to-cart/source-code-line-counter-m-d-ng-m-ngu-n-nhanh-ch-ng";

	public void buy_item() throws InterruptedException {
//		double probability = Math.random(); // Sinh ra một số ngẫu nhiên từ 0.0 đến 1.0

		driver.get(item1);
		
//		if (probability < 0.3) { // Điều kiện 30% xuất hiện
//			driver.get(App_running);
//		} else if (probability < 0.6) { // Điều kiện 30% tiếp theo (tổng 60%)
//			driver.get(source_linecounter);
//		} else { // Các trường hợp còn lại (40%)
//			driver.get(source_taomanguon);
//		}

		WebElement btnmuaElement = driver.findElement(By.xpath("//*[@id=\"contact_form\"]/div/button"));
		btnmuaElement.click();
		driver.get("https://develop.com.vn/checkout");
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

	public void feedback(String star, String feedback) throws InterruptedException {
		driver.get("https://develop.com.vn/purchases");
		
		WebElement modalFeedback = driver.findElement(By.xpath("/html/body/div[2]/div/div/section/div[1]/div[2]/div[1]/div[2]/a"));
		String modalString = modalFeedback.getAttribute("href");
		modalFeedback.click();
		
		WebElement select_star = driver
				.findElement(By.xpath("//*[@id=\"profile_form\"]/div[1]/div[1]/select"));
		Select select1 = new Select(select_star);
		select1.selectByValue(star);
		
		WebElement Select_tag = driver
				.findElement(By.xpath("//*[@id=\"profile_form\"]/div[1]/div[2]/select"));
		Select select2 = new Select(Select_tag);
		select2.selectByValue("Other");
		
		driver.findElement(By.xpath("//*[@id=\"rating_comment\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"rating_comment\"]")).sendKeys(feedback);
		driver.findElement(By.xpath("//*[@id=\"profile_form\"]/div[2]/button[2]")).click();
		
	}
	
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
		driver.manage().window().maximize();

		data = new Action();
	}

	private void processLogin(String username, String password) {
		WebElement userInput = driver.findElement(By.xpath("//*[@id=\"login_form\"]/div[1]/input"));
		userInput.sendKeys(username);
		WebElement passInput = driver.findElement(By.xpath("//*[@id=\"login_form\"]/div[2]/div[2]/input"));
		passInput.sendKeys(password);
		WebElement ClickBtn = driver.findElement(By.xpath("//*[@id=\"login_form\"]/div[4]/button"));
		ClickBtn.click();
	}

	@Test(dataProvider = "actionData")
	public void multiLogin(String star, String username, String password, String expected) throws InterruptedException {
		processLogin(username, password);
//		buy_item();
		feedback(star,expected);
		
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

	@DataProvider(name = "actionData")
	public Object[][] data() throws IOException {
		// mo file excell de lay du lieu
		XSSFWorkbook workbook = ExcelUltils.getworkbook(SRC);
		// thay doi ten sheet cho phu hop
		XSSFSheet sheet = workbook.getSheet("ACTION-DATA");
		// doc du lieu test
		Object[][] data = ExcelUltils.readSheetData(sheet);

		return data;
	}
}
