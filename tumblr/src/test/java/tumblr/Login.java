package tumblr;

import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Login {
	public static WebDriver webDriver;
	public static String baseUrl;
	public static WebDriverWait wait;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.chrome.driver", "/home/rani/V Semester/SVVT/chromedriver_linux64/chromedriver");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--no-sandbox");
		options.addArguments("--start-maximized");
		options.addArguments("--incognito");
		webDriver = new ChromeDriver(options);
		baseUrl = "https://www.tumblr.com/";
		wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		webDriver.quit();
	}

	@BeforeEach
	void setUp() throws Exception {
		webDriver.get(baseUrl);
		webDriver.findElement(By.linkText("Log in")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("EaAON")));
		webDriver.findElement(By.xpath("/html/body/div[1]/div/div[4]/div/div/div/div[2]/div/form/div[1]/div/button"))
				.click();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@Order(3)
	void login() throws InterruptedException {
		WebElement toPass = webDriver
				.findElement(By.xpath("/html/body/div[1]/div/div[4]/div/div/div/div[2]/div/form/div[1]/div[1]/button"));
		// valid email
		webDriver.findElement(By.name("email")).sendKeys("rpbjfgnumsfqwglmkk@tmmwj.net");
		// waiting until button becomes enabled
		wait.until(ExpectedConditions.elementToBeClickable(toPass));
		toPass.click();
		// waiting until password field can be modified
		wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
		webDriver.findElement(By.name("password")).sendKeys("ThisIsAPassword!!12");
		// waiting until log in button is enabled
		WebElement logInButton = webDriver.findElement(
				By.xpath("/html/body/div[1]/div/div[4]/div/div/div/div[2]/div/form/div[1]/div[1]/div/button"));
		wait.until(ExpectedConditions.elementToBeClickable(logInButton));

		logInButton.click();
		Thread.sleep(2500);
		// after log in should go to dashboard
		String currUrl = webDriver.getCurrentUrl();
		assertEquals("https://www.tumblr.com/dashboard", currUrl);
	}

	@Test
	@Order(1)
	void loginNoAcc() {
		WebElement toPass = webDriver
				.findElement(By.xpath("/html/body/div[1]/div/div[4]/div/div/div/div[2]/div/form/div[1]/div[1]/button"));
		webDriver.findElement(By.name("email")).sendKeys("rania.weiss@stu.ibu.edu.ba");
		toPass.click();
		assertTrue(wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("/html/body/div[1]/div/div[4]/div/div/div/div[2]/div/form/div[1]/div[1]/p[2]/button")))));
	}

	@Test
	@Order(2)
	void loginBadMail() {
		WebElement toPass = webDriver
				.findElement(By.xpath("/html/body/div[1]/div/div[4]/div/div/div/div[2]/div/form/div[1]/div[1]/button"));
		webDriver.findElement(By.name("email")).sendKeys("this is not an email");
		toPass.click();
		// error claiming the entered value is not a valid email
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]")));
	}

}
