package tumblr;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;

/*
 * might not go through with this since it's a hassle
 * */
class Register {
	public static WebDriver webDriver;
	public static String baseUrl;
	public static JavascriptExecutor js;
	public static WebDriverWait wait;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.chrome.driver", "/home/rani/V Semester/SVVT/chromedriver_linux64/chromedriver");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--no-sandbox");
		options.addArguments("--start-maximized");
		webDriver = new ChromeDriver(options);
		baseUrl = "https://www.tumblr.com/";
		js = (JavascriptExecutor) webDriver;
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
	void testRegistration() throws InterruptedException{
		webDriver.findElement(By.linkText("Sign up")).click();
		WebElement emailButton = wait.until(
				ExpectedConditions.visibilityOfElementLocated(
						By.cssSelector("#glass-container > div > div > div > div.vVe9A > div > form > div:nth-child(1) > div > button")
						));
		emailButton.click();
		WebElement enterEmail = webDriver.findElement(By.name("email"));
		enterEmail.sendKeys("rania.weiss@stu.ibu.edu.ba");
		webDriver.findElement(By.cssSelector("#glass-container > div > div > div > div.vVe9A > div > form > div.ehOK3 > div.Kz53t > button")).click();
		/*
		 * continue with entering password
		 * */
		Thread.sleep(3000);
	}

}
