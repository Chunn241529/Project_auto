package develop.test;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Date;
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

import develop.log.ChucNangData;
import develop.ultils.ExcelUltils;

public class TestInsertUser {
	private WebDriver driver;
	private final String SRC = ExcelUltils.DATA_SRC + "TEST_CHUCNANG.xlsx";
	private Set<ChucNangData> logs;
	private ChucNangData data;

	@BeforeClass
	public void init() throws IOException {
		System.setProperty("webdriver.chrome.driver", ExcelUltils.CHORME_DRIVER_SRC);
		// khoi tao danh sach log
		logs = new LinkedHashSet<>();
	}

	@BeforeMethod
	public void setUp() {
		driver = new ChromeDriver();
		driver.get("https://app.buzanmindmap.net/");
//		driver.manage().window().maximize();

		data = new ChucNangData();
	}

	private void login() {
		WebElement userInput = driver.findElement(By.xpath("//*[@id=\"authenticate_form_username\"]"));
		userInput.sendKeys("trungvuong528@gmail.com");
		WebElement passInput = driver.findElement(By.xpath("//*[@id=\"authenticate_form_password\"]"));
		passInput.sendKeys("123");
		WebElement ClickBtn = driver.findElement(By.xpath("//*[@id=\"authenticate_form\"]/div[2]/div/button/span"));
		ClickBtn.click();
		callapi();
	}

	public void callapi() {
		// Replace the following code with your API call logic
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://apiv2.vmindmap.com.vn/api/MstMindmap/listFilter?page=1&limit=12")).build();

		// Execute the request and get the response
		HttpResponse<String> response = null;
		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Print the response body
		System.out.println(response.body());
	}

	@Test
	public void multiInsert() throws InterruptedException {

		for (int i = 0; i < 100; i++) {
			login();
			
		}
	}

	@AfterMethod
	public void teadDown(ITestResult result) throws IOException {
		data.setTestMethod(result.getName());
		switch (result.getStatus()) {
		case ITestResult.SUCCESS:
			data.setStatus("Success");
			break;
		case ITestResult.FAILURE:
			data.setStatus("Failure");
			data.setException(result.getThrowable().getMessage());

			// chi dinh huong dan file luu anh
			String path = ExcelUltils.IMG_SRC + "failure-" + System.currentTimeMillis() + ".png";
			// goi ham chup anh man hinh tu class tien ich
			ExcelUltils.takescreenShot(driver, path);
			// ghi du lieu hinh anh vao workbook
			data.setImage(path);
			break;
		case ITestResult.SKIP:
			data.setStatus("Skip");
			break;
		default:
			break;
		}
		logs.add(data);
		driver.close();
		driver.quit();
	}

	@AfterClass
	public void destroy() throws IOException {
		data.writeLog(SRC, "RESULT-TEST", logs);
	}

	@DataProvider(name = "InsertData")
	public Object[][] data() throws IOException {
		// mo file excell de lay du lieu
		XSSFWorkbook workbook = ExcelUltils.getworkbook(SRC);
		// thay doi ten sheet cho phu hop
		XSSFSheet sheet = workbook.getSheet("INSERT-DATA");
		// doc du lieu test
		Object[][] data = ExcelUltils.readSheetData(sheet);

		return data;
	}
}
