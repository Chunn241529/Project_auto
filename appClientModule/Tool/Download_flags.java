package Tool;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import develop.ultils.ExcelUltils;

public class Download_flags {
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
		WebElement userInput = driver.findElement(By.xpath("//*[@id=\"login_form\"]/div[1]/input"));
		userInput.sendKeys("admin");
		WebElement passInput = driver.findElement(By.xpath("//*[@id=\"login_form\"]/div[2]/div[2]/input"));
		passInput.sendKeys("admin");
		WebElement ClickBtn = driver.findElement(By.xpath("//*[@id=\"login_form\"]/div[4]/button"));
		ClickBtn.click();
	}

	private void download_flags() throws IOException {

		driver.get("https://develop.com.vn/admin");

		String countValue = "-1";

		login();

		driver.get("https://develop.com.vn/admin/country-settings");
//		WebElement changePage = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[2]/div/div[3]/div[2]/div/ul/li[4]/a"));
//		changePage.click();

		WebElement tablElement = driver.findElement(By.xpath("//*[@id=\"bootstrap-data-table-export\"]/tbody"));

		WebElement selectElement = driver
				.findElement(By.xpath("//*[@id=\"bootstrap-data-table-export_length\"]/label/select"));
		Select select = new Select(selectElement);
		select.selectByValue(countValue);

		List<WebElement> rows_table = tablElement.findElements(By.tagName("tr"));
		int rows_count = rows_table.size();

		for (int row = 0; row < rows_count; row++) {
			List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));

			for (int column = 1; column < 2; column++) {
				String cell_text = Columns_row.get(column).getText();
//				System.out.println(cell_text);				

				listName.add(cell_text);

			}
		}

		for (int i = 176; i < listName.size(); i++) {

//			driver.get("https://flagdownload.com/");
//
//			driver.findElement(By.xpath("/html/body/header/nav[1]/div/div/div[2]/form/div/input"))
//					.sendKeys(listName.get(i));
//			driver.findElement(By.xpath("/html/body/header/nav[1]/div/div/div[2]/form/div/input")).submit();
//			String nameCountry = driver.findElement(By.xpath("/html/body/main/article[1]/a")).getText();
////			String nameCountry3 = driver.findElement(By.xpath("/html/body/main/article[3]/a")).getText();
//
//			if (nameCountry.equals(listName.get(i))) {
//				driver.findElement(By.xpath("/html/body/main/article[1]/a")).click();
//			} else if (!nameCountry.equals(listName.get(i))) {
//				String nameCountry2 = driver.findElement(By.xpath("/html/body/main/article[2]/a")).getText();
//				if (nameCountry2.equals(listName.get(i))) {
//					driver.findElement(By.xpath("/html/body/main/article[2]/a")).click();
//				} else {
//					driver.findElement(By.xpath("/html/body/main/article[3]/a")).click();
//				}
//			}
//
//			WebElement downloadFileElement = driver
//					.findElement(By.xpath("/html/body/section/div/div[2]/div[1]/ul/li[1]/div/a[7]"));
//
//			String imageUrl = downloadFileElement.getAttribute("href");
//			String fileName = listName.get(i) + ".png";
//			String savePath = "D:\\trung\\Data\\develop\\flag\\flg\\flg\\" + fileName;
//
//			try {
//				URL url = new URL(imageUrl);
//				try (InputStream in = new BufferedInputStream(url.openStream());
//						FileOutputStream fileOutputStream = new FileOutputStream(savePath)) {
//
//					byte[] buffer = new byte[1024];
//					int bytesRead;
//					while ((bytesRead = in.read(buffer, 0, 1024)) != -1) {
//						fileOutputStream.write(buffer, 0, bytesRead);
//					}
//
//					System.out.println("Ảnh đã được tải và lưu tại: " + savePath);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}

//			System.out.println(listName.get(i) + imageUrl);

			try (FileInputStream fileInputStream = new FileInputStream(
					"D:\\trung\\Learn\\Project_Java\\Project_AutoTest\\test-resources\\Data\\EDIT_FLAGS.xlsx");
					XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream)) {

				// Lấy trang tính đã tồn tại bằng tên hoặc index
				XSSFSheet sheet = workbook.getSheet("DATA"); // Thay "Sheet1" bằng tên trang tính bạn muốn sử dụng
				// Sheet sheet = workbook.getSheetAt(0); // Thay 0 bằng index trang tính bạn
				// muốn sử dụng (0-based index)

				// Vòng lặp để ghi dữ liệu từ danh sách vào cột C
				for (int j = 0; j < listName.size(); j++) {
					String chuoi = listName.get(j) + ".png";
					XSSFRow row = sheet.createRow(j + 1); // Hoặc sử dụng sheet.getRow(i) để lấy dòng đã tồn tại

					Cell cellA = row.createCell(0); // Cột A (0-based index)
					cellA.setCellValue(j+8);

					// Đặt giá trị cho cột A (index 0)
					Cell cellB = row.createCell(1); // Cột A (0-based index)
					cellB.setCellValue("admin");

					// Đặt giá trị cho cột B (index 1)
					Cell cellC = row.createCell(2); // Cột B (0-based index)
					cellC.setCellValue("admin");

					// Đặt giá trị cho cột C (index 2)
					Cell cellD = row.createCell(3); // Cột C (0-based index)
					cellD.setCellValue(chuoi);
				}

				// Lưu lại tệp Excel với dữ liệu mới
				try (FileOutputStream outputStream = new FileOutputStream(
						"D:\\trung\\Learn\\Project_Java\\Project_AutoTest\\test-resources\\Data\\EDIT_FLAGS.xlsx")) {
					workbook.write(outputStream);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

//	public void edit_flags() {
//
//	}

	@Test
	public void test1() {
//		Upload_Flags();
		try {
			download_flags();
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
