package tumblr;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class Follow {
	public static String baseUrl;
	public static WebDriver webDriver;
	public static WebDriverWait wait;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		baseUrl = "https://www.tumblr.com";
		System.setProperty("webdriver.chrome.driver", "/home/rani/V Semester/SVVT/chromedriver_linux64/chromedriver");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--no-sandbox");
		options.addArguments("--start-maximized");
		options.addArguments("--user-data-dir=/home/rani");
		webDriver = new ChromeDriver(options);
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

	@ParameterizedTest
	@CsvSource({ "neil,neil-gaiman", "blockchain-official,blockchain-official" })
	void follow(String search, String blogName) {
		webDriver.get(baseUrl);
		// start search
		webDriver.findElement(By.name("q")).sendKeys(search);
		// wait until the searched blog appears
		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath("/html/body/div/div/div[2]/div[1]/header/div[1]/div[2]/span/div/div/div/div[2]/div[1]/div/a")));
		// click on the searched blog
		webDriver
				.findElement(By.xpath(
						"/html/body/div/div/div[2]/div[1]/header/div[1]/div[2]/span/div/div/div/div[2]/div[1]/div/a"))
				.click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(
				"/html/body/div/div/div[4]/div/div[2]/div[2]/div/div/div[1]/header/div/div/div[2]/button[2]/span")));
		// find follow button
		WebElement followButton = webDriver.findElement(By.xpath(
				"/html/body/div/div/div[4]/div/div[2]/div[2]/div/div/div[1]/header/div/div/div[2]/button[2]/span"));
		if (followButton.getText().equals("Follow")) {
			followButton.click();
		}
		// check whether newly followed blog appears on followed page
		webDriver.get("https://www.tumblr.com/following");
		assertTrue(webDriver.getPageSource().contains(blogName));
						

	}
	@Test
	void unfollow() {
		webDriver.get(baseUrl + "/following");
		String followXpath = "/html/body/div/div/div[2]/div[2]/div[1]/main/section/div[5]/div[2]/div/button";
		webDriver.findElement(By.xpath(followXpath)).click();
		assertEquals("Follow", webDriver.findElement(By.xpath(followXpath)).getText());
		webDriver.navigate().refresh();

	}

}
