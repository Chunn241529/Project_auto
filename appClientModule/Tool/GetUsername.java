package Tool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

public class GetUsername {
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
		driver.get("https://develop.com.vn/admin");
//		driver.manage().window().maximize();
	}

	private void login_admin() {
		WebElement userInput = driver.findElement(By.xpath("//*[@id=\"login_form\"]/div[1]/input"));
		userInput.sendKeys("admin");
		WebElement passInput = driver.findElement(By.xpath("//*[@id=\"login_form\"]/div[2]/div[2]/input"));
		passInput.sendKeys("admin");
		WebElement ClickBtn = driver.findElement(By.xpath("//*[@id=\"login_form\"]/div[4]/button"));
		ClickBtn.click();
	}

	


	private void get_user_table() {

		String countValue = "-1";

		login_admin();

		driver.get("https://develop.com.vn/admin/customer");
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
				System.out.println(cell_text);
				
			}
		}
	}



	@Test
	public void Multi_B() {
		get_user_table();
	}

	@AfterTest
	public void after() {
		driver.close();
		driver.quit();
	}

}
