package posts;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class Reblog {
	public static WebDriver webDriver;
	public static String firstBlog;
	public static String secondBlog;
	public static WebDriverWait wait;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.chrome.driver", "/home/rani/V Semester/SVVT/chromedriver_linux64/chromedriver");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--no-sandbox");
		options.addArguments("--start-maximized");
		options.addArguments("--user-data-dir=/home/rani");
		webDriver = new ChromeDriver(options);
		firstBlog = "https://www.tumblr.com/one-time-i-dreamt";
		secondBlog = "https://www.tumblr.com/reallycoolblogsblog";
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
	void reblogWithContent() throws InterruptedException {
		webDriver.get(firstBlog);
		String reblogText = "I'm adding a reblog";
		String tagText = "Gotta add a tag";
		// find reblog button and click on it
		webDriver.findElement(By.xpath(
				"/html/body/div/div/div[2]/div[2]/div/div/div[1]/main/div/div/div/div[2]/div[1]/div/div/article/div[3]/footer/div[1]/div[2]/div[3]/span/span/span/span/a"))
				.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("RuIGO")));

		// get original text
		String originalText = webDriver.findElement(By.xpath(
				"/html/body/div[1]/div/div[4]/div/div/div/div/div[2]/div[2]/div/div[1]/div[2]/div/div/div[2]/div/div/p"))
				.getText();
		// add text and tags
		webDriver.findElement(By.xpath(
				"/html/body/div[1]/div/div[4]/div/div/div/div/div[2]/div[2]/div/div[1]/div[3]/div/div[5]/div/div/div[1]/p"))
				.sendKeys(reblogText);
		webDriver
				.findElement(By.xpath(
						"/html/body/div[1]/div/div[4]/div/div/div/div/div[2]/div[2]/div/div[2]/div/span/span/textarea"))
				.sendKeys(tagText);

		// confirm reblog
		webDriver
				.findElement(By.xpath(
						"/html/body/div[1]/div/div[4]/div/div/div/div/div[2]/div[2]/div/div[3]/div/div/div/button"))
				.click();

		// check on user's blog if the post was reblogged properly
		webDriver.get(secondBlog);
		Thread.sleep(3000);
		assertEquals(originalText, webDriver.findElement(By.xpath(
				"/html/body/div/div/div[2]/div[2]/div/div/div[1]/main/div/div/div/div[2]/div[1]/div/div/article/div[1]/div/span/div[1]/div[2]/div/div/p"))
				.getText());
		assertEquals(reblogText, webDriver.findElement(By.xpath(
				"/html/body/div/div/div[2]/div[2]/div/div/div[1]/main/div/div/div/div[2]/div[1]/div/div/article/div[1]/div/span/div[2]/div[2]/div/div/p"))
				.getText());
		assertEquals("#".concat(tagText), webDriver.findElement(By.xpath(
				"/html/body/div/div/div[2]/div[2]/div/div/div[1]/main/div/div/div/div[2]/div[1]/div/div/article/div[2]/div/div/a"))
				.getText());
	}

}
