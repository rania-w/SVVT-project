package notes;

import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;

class Blog {
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
		baseUrl = "https://www.tumblr.com/doggosource/704577297320165376/doggosource-a-n-g-e-l";
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
	void reblogBlog() {
		String postNumberBefore;
		String postNumberAfter;
		// click on menu that shows number of posts and likes
		WebElement menuButton = webDriver
				.findElement(By.xpath("/html/body/div/div/div[2]/div[1]/header/div[2]/div[7]/span/span/button"));
		menuButton.click();
		// the number of posts
		WebElement postNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"/html/body/div/div/div[2]/div[1]/header/div[2]/div[7]/span/div/div/div/ul[2]/div/li/div/ul/li[1]/a/span[2]")));
		postNumberBefore = postNumber.getText();

		// click reblog button
		webDriver.findElement(By.xpath(
				"/html/body/div/div/div[2]/div[2]/div/div/div[1]/main/div/div/div/div[2]/div/div/div/article/div[2]/footer/div[1]/div[2]/div[3]/span/span/span/span/a"))
				.click();
		// waiting until the reblog modal shows
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("RuIGO")));

		// confirm simple reblog
		webDriver
				.findElement(By.xpath(
						"/html/body/div[1]/div/div[4]/div/div/div/div/div[2]/div[2]/div/div[3]/div/div/div/button"))
				.click();
		//check the menu for number of posts after reblog
		wait.until(ExpectedConditions.visibilityOf(menuButton));
		menuButton.click();
		WebElement postNumberA = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"/html/body/div/div/div[2]/div[1]/header/div[2]/div[7]/span/div/div/div/ul[2]/div/li/div/ul/li[1]/a/span[2]")));
		postNumberAfter = postNumberA.getText();
		assertNotSame(postNumberBefore, postNumberAfter);
	}

	@Test
	void likeBlog() throws InterruptedException {
		String likeNumberBefore;
		String likeNumberAfter;
		// click on menu that shows number of posts and likes
		WebElement menuButton = webDriver
				.findElement(By.xpath("/html/body/div/div/div[2]/div[1]/header/div[2]/div[7]/span/span/button"));
		menuButton.click();
		// getting the number of likes
		WebElement likeNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"/html/body/div/div/div[2]/div[1]/header/div[2]/div[7]/span/div/div/div/ul[2]/div/li/div/ul/li[1]/a/span[2]")));
		likeNumberBefore = likeNumber.getText();

		// click like button
		webDriver.findElement(By.xpath(
				"/html/body/div/div/div[2]/div[2]/div/div/div[1]/main/div/div/div/div[2]/div/div/div/article/div[2]/footer/div[1]/div[2]/div[4]/span/span/span/span/button"))
				.click();
		//check the number of likes after liking post
		menuButton.click();
		likeNumberAfter = likeNumber.getText();
		assertNotSame(likeNumberBefore, likeNumberAfter);
	}

}
