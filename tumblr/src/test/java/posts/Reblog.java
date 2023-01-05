package posts;

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
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class Reblog {
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
		baseUrl = "https://www.tumblr.com/reblog/one-time-i-dreamt/705063213940801536/2EbpUxdF";
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
	void test() throws InterruptedException {
		
		webDriver.get(baseUrl);
		String reblogText = "I'm adding a nice lil' reblog";
		//getting the text of the original post
		String theText =
		webDriver.findElement(By.xpath("/html/body/div[1]/div/div[4]/div/div/div/div/div[2]/div[2]/div/div[1]/div[2]/div/div/div[2]/div/div/p")).getText();
		//reblog
		Actions builder = new Actions(webDriver);
		//add some text to reblog
		Action addReblog = builder
				.sendKeys(reblogText)
				.build();
		addReblog.perform();
		//reblog the post
		webDriver.findElement(By.xpath("/html/body/div[1]/div/div[4]/div/div/div/div/div[2]/div[2]/div/div[3]/div/div/div/button/span")).click();
		//check whether the post is on user's blog
		webDriver.get("https://tumblr.com/reallycoolblogsblog");
		//checking original post
		assertEquals(theText, 
				webDriver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div/div[1]/main/div/div/div/div[2]/div[1]/div/div/article/div[1]/div/span/div[1]/div[2]/div/div/p")).getText()
		);
		//checking added text
		assertEquals(reblogText,
				webDriver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div/div[1]/main/div/div/div/div[2]/div[1]/div/div/article/div[1]/div/span/div[2]/div[2]/div/div/p")).getText()
				);
	}

}
