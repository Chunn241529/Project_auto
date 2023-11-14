package develop.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import develop.ultils.ExcelUltils;

public class TestLoginExcelData {
	private WebDriver driver;
	List<String> listName = new ArrayList<String>();
//	private final String SRC = ExcelUltils.DATA_SRC + "LOGIN_TEST.xlsx";

	@BeforeClass
	public void init() throws IOException {
		System.setProperty("webdriver.chrome.driver", ExcelUltils.CHORME_DRIVER_SRC);
		// khoi tao danh sach log
	}

	@BeforeMethod
	public void setUp() {
		driver = new ChromeDriver();
//		driver.get("https://develop.com.vn/admin/edit-country/74");
//		driver.manage().window().maximize();
	}

	private void login() {
		WebElement userInput = driver.findElement(By.xpath("//*[@id=\"authenticate_form_username\"]"));
		userInput.sendKeys("trungvuong528@gmail.com");
		WebElement passInput = driver.findElement(By.xpath("//*[@id=\"authenticate_form_password\"]"));
		passInput.sendKeys("123");
		WebElement ClickBtn = driver.findElement(By.xpath("//*[@id=\"authenticate_form\"]/div[2]/div/button"));
		ClickBtn.click();
	}

	private void Test_homepage() throws IOException {

		driver.get("https://app.buzanmindmap.net/");

		login();

		driver.get("https://app.buzanmindmap.net/");
//		WebElement changePage = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[2]/div/div[3]/div[2]/div/ul/li[4]/a"));
//		changePage.click();

		WebElement tablElement = driver.findElement(By.tagName("body"));

		String text_body = tablElement.getText();
		List<String> textParts = new ArrayList<>();

		// Sử dụng mẫu regex để tách văn bản bằng dấu gạch ngang
		String pattern = "---+"; // Mẫu dấu gạch ngang

		Pattern regexPattern = Pattern.compile(pattern, Pattern.MULTILINE);
		Matcher matcher = regexPattern.matcher(text_body);

		int startIndex = 0;
		while (matcher.find()) {
			String part = text_body.substring(startIndex, matcher.start());
			part = part.trim(); // Xóa khoảng trắng thừa nếu có
			if (!part.isEmpty()) {
				textParts.add(part);
			}
			startIndex = matcher.end();
		}

		// Xử lý phần cuối cùng (phần sau dấu gạch ngang cuối cùng)
		String lastPart = text_body.substring(startIndex);
		lastPart = lastPart.trim(); // Xóa khoảng trắng thừa nếu có
		if (!lastPart.isEmpty()) {
			textParts.add(lastPart);
		}

		// In ra danh sách các phần
		for (String part : textParts) {
			System.out.println(part);
		}

//		listName.add();
//		System.out.println(tablElement.getText());

//		try (FileInputStream fileInputStream = new FileInputStream(
//				"D:\\trung\\Learn\\Project_Java\\Project_AutoTest\\test-resources\\Data\\LOGIN_TEST.xlsx");
//				XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream)) {
//
//			// Lấy trang tính đã tồn tại bằng tên hoặc index
//			XSSFSheet sheet = workbook.getSheet("LOGIN-DATA"); // Thay "Sheet1" bằng tên trang tính bạn muốn sử dụng
//			// Sheet sheet = workbook.getSheetAt(0); // Thay 0 bằng index trang tính bạn
//			// muốn sử dụng (0-based index)
//
//			// Vòng lặp để ghi dữ liệu từ danh sách vào cột C
//			for (int j = 0; j < listName.size(); j++) {
//				String chuoi = listName.get(j);
//				XSSFRow row = sheet.createRow(j + 1); // Hoặc sử dụng sheet.getRow(i) để lấy dòng đã tồn tại
//
//				Cell cellA = row.createCell(0);
//				cellA.setCellValue("admin");
//
//				// Đặt giá trị cho cột A (index 0)
//				Cell cellB = row.createCell(1); // Cột A (0-based index)
//				cellB.setCellValue("admin");
//
//				// Đặt giá trị cho cột C (index 2)
//				Cell cellD = row.createCell(2); // Cột C (0-based index)
//				cellD.setCellValue(chuoi);
//			}
//
//			// Lưu lại tệp Excel với dữ liệu mới
//			try (FileOutputStream outputStream = new FileOutputStream(
//					"D:\\trung\\Learn\\Project_Java\\Project_AutoTest\\test-resources\\Data\\LOGIN_TEST.xlsx")) {
//				workbook.write(outputStream);
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	@Test
	public void test1() {
//		Upload_Flags();
		try {
			Test_homepage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

//	@Test
//	public void test2() {
//		edit_flags();
//	}

	@AfterTest
	public void teardown() {
		try {
			Thread.sleep(2000);
			driver.quit();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
