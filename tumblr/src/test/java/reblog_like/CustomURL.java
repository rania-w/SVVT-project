package reblog_like;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class CustomURL {
	public static WebDriver webDriver;
	public static String baseUrl;
	public static WebDriverWait wait;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.chrome.driver", "/home/rani/V Semester/SVVT/chromedriver_linux64/chromedriver");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--no-sandbox");
		options.addArguments("--start-maximized");
		options.addArguments("--user-data-dir=/home/rani");
		webDriver = new ChromeDriver(options);
		webDriver.manage().getCookies();
		baseUrl = "https://doggosource.tumblr.com/post/704577297320165376/doggosource-a-n-g-e-l";
		wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		webDriver.quit();
	}

	@BeforeEach
	void setUp() throws Exception {
		webDriver.get(baseUrl);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void reblogCustomURL() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[3]/div/a[1]")));
		webDriver.findElement(By.xpath("/html/body/div[2]/div[3]/div/a[1]")).click();
	}

	@Test
	void likeCustomUrl() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[3]/div/button[1]")));
		webDriver.findElement(By.xpath("/html/body/div[2]/div[3]/div/button[1]")).click();
	}

}
