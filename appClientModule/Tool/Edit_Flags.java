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
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import develop.log.flags;
import develop.ultils.ExcelUltils;

public class Edit_Flags {
	private WebDriver driver;
	private final String SRC = ExcelUltils.DATA_SRC + "EDIT_FLAGS.xlsx";
	private Set<flags> logs;
	private flags data;

	

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

		data = new flags();
	}

	private void processLogin(String username, String password) {
		WebElement userInput = driver.findElement(By.xpath("//*[@id=\"login_form\"]/div[1]/input"));
		userInput.sendKeys(username);
		WebElement passInput = driver.findElement(By.xpath("//*[@id=\"login_form\"]/div[2]/div[2]/input"));
		passInput.sendKeys(password);
		WebElement ClickBtn = driver.findElement(By.xpath("//*[@id=\"login_form\"]/div[4]/button"));
		ClickBtn.click();
	}

	@Test(dataProvider = "flags")
	public void multiLogin(String stt, String username, String password, String country) {
		processLogin(username, password);

		driver.get("https://develop.com.vn/admin/edit-country/" + stt);

		driver.findElement(By.xpath("//*[@id=\"country_badges\"]"))
				.sendKeys("D:\\trung\\Data\\develop\\flag\\flg\\flg\\" + country);

		driver.findElement(By.xpath("//*[@id=\"setting_form\"]/div/div[3]/button[1]")).click();

		System.out.println("Đã edit hình của Quốc gia: " + country);

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

	@DataProvider(name = "flags")
	public Object[][] data() throws IOException {
		// mo file excell de lay du lieu
		XSSFWorkbook workbook = ExcelUltils.getworkbook(SRC);
		// thay doi ten sheet cho phu hop
		XSSFSheet sheet = workbook.getSheet("DATA");
		// doc du lieu test
		Object[][] data = ExcelUltils.readSheetData(sheet);

		return data;
	}
}
