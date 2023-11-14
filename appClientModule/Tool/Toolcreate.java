package Tool;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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

import develop.ultils.ExcelUltils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

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
		driver.get("https://app.buzanmindmap.net/");
	}

	private void createUserAPI() {
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

	private void login() {
		WebElement userInput = driver.findElement(By.xpath("//*[@id=\"authenticate_form_username\"]"));
		userInput.sendKeys("trungvuong528@gmail.com");
		WebElement passInput = driver.findElement(By.xpath("//*[@id=\"authenticate_form_password\"]"));
		passInput.sendKeys("123");
		WebElement ClickBtn = driver.findElement(By.xpath("//*[@id=\"authenticate_form\"]/div[2]/div/button"));
		ClickBtn.click();
		
	}

	private void Create_User(int numberOfUsers, String userToken) {

//		login(userToken);

//		List<String> firstNames = new ArrayList<>();
//		firstNames.add("Trung");
//		firstNames.add("Linh");
//		firstNames.add("Thuan");
//		firstNames.add("Vu");
//		firstNames.add("Quan");
//		firstNames.add("Sang");
//		firstNames.add("Long");
//		firstNames.add("Ngan");
//		firstNames.add("Bang");
//		firstNames.add("Ba");
//		firstNames.add("Vien");
//		firstNames.add("Quang");
//		firstNames.add("Duc");
//		firstNames.add("Trinh");
//		firstNames.add("Nhi");
//		// Thêm các tên người dùng khác vào danh sách
//
//		List<String> lastNames = new ArrayList<>();
//		lastNames.add("Vuong");
//		lastNames.add("Nguyen");
//		lastNames.add("Tran");
//		lastNames.add("dev");
//		lastNames.add("tester");
//		lastNames.add("develop");
//		lastNames.add("editor");
//		lastNames.add("hoasi");
//		lastNames.add("Smith");
//		lastNames.add("ne");
//		lastNames.add("Sunshine");
//		lastNames.add("QA");
//		// Thêm các họ khác vào danh sách

//		Random random1 = new Random();
//		for (int i = 0; i < numberOfUsers; i++) {
//			String randomFirstName = firstNames.get(random1.nextInt(firstNames.size()));
//			String randomLastName = lastNames.get(random1.nextInt(lastNames.size()));
//			String username = randomFirstName.toLowerCase() + randomLastName.toLowerCase() + i;
//
//			System.out.println("User " + (i + 1) + ": " + username);
//		}

		for (int i = 0; i < numberOfUsers; i++) {
			createUserAPI();
			// Đóng trang sau khi tạo user xong
//			driver.close();
//			driver.quit();
//			// Mở trình duyệt mới cho lần lặp tiếp theo
			driver = new ChromeDriver();
			driver.get("https://app.buzanmindmap.net/");
//			login(userToken); // Đăng nhập lại cho lần lặp tiếp theo

		}
		// Đóng trình duyệt sau khi hoàn thành vòng lặp
		driver.close();
		driver.quit();
	}

	@Test
	public void Multi_Create() {
		String tokenString = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9lbWFpbGFkZHJlc3MiOiJtaW5oLmhvYW5nbmdhQGdtYWlsLmNvbSIsImh0dHA6Ly9zY2hlbWFzLnhtbHNvYXAub3JnL3dzLzIwMDUvMDUvaWRlbnRpdHkvY2xhaW1zL2dpdmVubmFtZSI6Ik1pbmggxJDhu6ljIiwiaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvc2lkIjoiMTkxNTEiLCJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1lIjoiTWluaGR1YzEyMyIsImh0dHA6Ly9zY2hlbWFzLnhtbHNvYXAub3JnL3dzLzIwMDUvMDUvaWRlbnRpdHkvY2xhaW1zL21vYmlsZXBob25lIjoiMDgzMzQ1NjcyMiIsImV4cCI6MTc2MzA4OTYxNSwiaXNzIjoiaHR0cHM6Ly9zaWV1dHJpbmhvaG9jZHVvbmcuY29tIiwiYXVkIjoiaHR0cHM6Ly9zaWV1dHJpbmhvaG9jZHVvbmcuY29tIn0.iwayE0D0cVT-FO6Sg_n8FMv7XQiJ92cLKsF6ne-kF_Q";
		Create_User(100, tokenString);
	}

}
