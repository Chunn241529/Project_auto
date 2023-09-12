package Tool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestASM2.ultils.ExcelUltils;

public class Toolcreate {
	private WebDriver driver;
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

	private void login() {
		WebElement userInput = driver.findElement(By.xpath("//*[@id=\"login_form\"]/div[1]/input"));
		userInput.sendKeys("admin");
		WebElement passInput = driver.findElement(By.xpath("//*[@id=\"login_form\"]/div[2]/div[2]/input"));
		passInput.sendKeys("admin");
		WebElement ClickBtn = driver.findElement(By.xpath("//*[@id=\"login_form\"]/div[4]/button"));
		ClickBtn.click();
	}

	private void Create_User(int numberOfUsers) {
		

		login();
		
		List<String> firstNames = new ArrayList<>();
		firstNames.add("Trung");
		firstNames.add("Linh");
		firstNames.add("Thuan");
		firstNames.add("Vu");
		firstNames.add("Quan");
		firstNames.add("Sang");
		firstNames.add("Long");
		firstNames.add("Ngan");
		firstNames.add("Bang");
		firstNames.add("Ba");
		firstNames.add("Vien");
		firstNames.add("Quang");
		firstNames.add("Duc");
		firstNames.add("Trinh");
		firstNames.add("Nhi");
		// Thêm các tên người dùng khác vào danh sách

		List<String> lastNames = new ArrayList<>();
		lastNames.add("Vuong");
		lastNames.add("Nguyen");
		lastNames.add("Tran");
		lastNames.add("dev");
		lastNames.add("tester");
		lastNames.add("develop");
		lastNames.add("editor");
		lastNames.add("hoasi");
		lastNames.add("Smith");
		lastNames.add("ne");
		lastNames.add("Sunshine");
		lastNames.add("QA");
		// Thêm các họ khác vào danh sách


		Random random1 = new Random();
//		for (int i = 0; i < numberOfUsers; i++) {
//			String randomFirstName = firstNames.get(random1.nextInt(firstNames.size()));
//			String randomLastName = lastNames.get(random1.nextInt(lastNames.size()));
//			String username = randomFirstName.toLowerCase() + randomLastName.toLowerCase() + i;
//
//			System.out.println("User " + (i + 1) + ": " + username);
//		}

		for (int i = 0; i < numberOfUsers; i++) {
			String randomFirstName = firstNames.get(random1.nextInt(firstNames.size()));
			String randomLastName = lastNames.get(random1.nextInt(lastNames.size()));
			String username = randomFirstName.toLowerCase() + randomLastName.toLowerCase() + i;

			Random random = new Random();
			int randomNumber = random.nextInt(2); // Tạo số ngẫu nhiên 0 hoặc 1

			if (randomNumber == 0) {
				driver.get("https://develop.com.vn/admin/add-customer");
				WebElement nameInput = driver.findElement(
						By.xpath("/html/body/div[1]/div[2]/div/div/div/form/div/div[1]/div/div/div/div[1]/input"));
				nameInput.sendKeys(username);
				WebElement user_nameInput = driver.findElement(
						By.xpath("/html/body/div[1]/div[2]/div/div/div/form/div/div[1]/div/div/div/div[2]/input"));
				user_nameInput.sendKeys(username);
				WebElement mailInput = driver.findElement(
						By.xpath("/html/body/div[1]/div[2]/div/div/div/form/div/div[1]/div/div/div/div[3]/input"));
				mailInput.sendKeys(username + "@gmail.com");
				WebElement passwordInput = driver.findElement(
						By.xpath("/html/body/div[1]/div[2]/div/div/div/form/div/div[1]/div/div/div/div[4]/input"));
				passwordInput.sendKeys("123456");

				WebElement submitButton = driver
						.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/div/div[3]/button[1]"));
				submitButton.click();
				System.out.println("\nĐã tạo user " + username + "\n");

			} else {
				driver.get("https://develop.com.vn/admin/add-vendor");
				WebElement nameInput = driver.findElement(
						By.xpath("/html/body/div[1]/div[2]/div/div/div/form/div/div[1]/div/div/div/div[1]/input"));
				nameInput.sendKeys(username);
				WebElement user_nameInput = driver.findElement(
						By.xpath("/html/body/div[1]/div[2]/div/div/div/form/div/div[1]/div/div/div/div[2]/input"));
				user_nameInput.sendKeys(username);
				WebElement mailInput = driver.findElement(
						By.xpath("/html/body/div[1]/div[2]/div/div/div/form/div/div[1]/div/div/div/div[3]/input"));
				mailInput.sendKeys(username + "@gmail.com");
				WebElement passwordInput = driver.findElement(
						By.xpath("/html/body/div[1]/div[2]/div/div/div/form/div/div[1]/div/div/div/div[4]/input"));
				passwordInput.sendKeys("123456");

//				WebElement Subscription_Type = driver.findElement(
//						By.xpath("/html/body/div[1]/div[2]/div/div/div/form/div/div[1]/div/div/div/div[7]/select"));
//				Select select_Subscription_Type = new Select(Subscription_Type);
//				// Chọn tùy chọn bằng giá trị
//				select_Subscription_Type.selectByValue("free");
//
//				WebElement Account_Verification = driver.findElement(
//						By.xpath("/html/body/div[1]/div[2]/div/div/div/form/div/div[1]/div/div/div/div[8]/select"));
//				Select select_Account_Verification = new Select(Account_Verification);
//				// Chọn tùy chọn bằng giá trị
//				select_Account_Verification.selectByValue("1");
//
//				WebElement Status_payment = driver.findElement(
//						By.xpath("/html/body/div[1]/div[2]/div/div/div/form/div/div[1]/div/div/div/div[9]/select"));
//				Select select_Status_payment = new Select(Status_payment);
//				// Chọn tùy chọn bằng giá trị
//				select_Status_payment.selectByValue("completed");

				WebElement submitButton = driver
						.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/div/div[3]/button[1]"));
				submitButton.click();
				System.out.println("\nĐã tạo vendor " + username + "\n");

			}

			// Đóng trang sau khi tạo user xong
			driver.close();
			driver.quit();
			// Mở trình duyệt mới cho lần lặp tiếp theo
			driver = new ChromeDriver();
			driver.get("https://develop.com.vn/admin");
			login(); // Đăng nhập lại cho lần lặp tiếp theo

		}
		// Đóng trình duyệt sau khi hoàn thành vòng lặp
		driver.close();
		driver.quit();
	}

	@Test
	public void Multi_Create() {
		Create_User(200);
	}

}
