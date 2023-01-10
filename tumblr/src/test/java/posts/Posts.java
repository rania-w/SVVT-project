package posts;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class Posts {
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
		baseUrl = "https://tumblr.com/";
		wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		webDriver.quit();
	}

	@BeforeEach
	void setUp() throws Exception {

	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void postText() throws InterruptedException {
		webDriver.get(baseUrl);
		// button for creating posts
		webDriver.findElement(By.xpath("/html/body/div/div/div[2]/div[1]/header/div[2]/a")).click();
		String title = "A title";
		String bold = "This text is bold";
		String italic = "This text should be italic";
		String tags = "absolutely anything can go in a tag";
		Actions builder = new Actions(webDriver);

		// waiting for popup menu
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("HT4SV")));
		// creating text post
		webDriver.findElement(By.xpath("/html/body/div[1]/div/div[4]/div/div/div[1]/div/a")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("RuIGO")));
		Thread.sleep(2000);

		// typing the title and text post
		Action type = builder.sendKeys(Keys.ARROW_UP).sendKeys(title).sendKeys(Keys.ARROW_DOWN).keyDown(Keys.CONTROL)
				.sendKeys("b").keyUp(Keys.CONTROL).sendKeys(bold).keyDown(Keys.CONTROL).sendKeys("b")
				.keyUp(Keys.CONTROL).sendKeys(Keys.SPACE).keyDown(Keys.CONTROL).sendKeys("i").keyUp(Keys.CONTROL)
				.sendKeys(italic).build();
		type.perform();

		// add tags
		webDriver.findElement(By.className("mbROR")).sendKeys(tags);
		// post the text post
		webDriver.findElement(By
				.xpath("/html/body/div[1]/div/div[4]/div/div/div/div/div[2]/div[2]/div/div[3]/div/div/div/button/span"))
				.click();

		// checking whether the post can be found on the blog, and if all the text is
		// edited how it should be
		webDriver.get("https://www.tumblr.com/reallycoolblogsblog");
		assertEquals(title, webDriver.findElement(By.xpath(
				"/html/body/div/div/div[2]/div[2]/div/div/div[1]/main/div/div/div/div[2]/div[1]/div/div/article/div[1]/div/span/div/div[1]/h1"))
				.getText());
		assertEquals(bold, webDriver.findElement(By.tagName("strong")).getText());
		assertEquals(italic, webDriver.findElement(By.tagName("em")).getText());
	}

}
