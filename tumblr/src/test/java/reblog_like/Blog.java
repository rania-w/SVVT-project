package reblog_like;

import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;

import org.junit.Ignore;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;

/*
 * rewrite this
 * */
class Blog {
	public static WebDriver webDriver;
	public static String baseUrl;
	public static WebDriverWait wait;
	public String postNumberBefore;
	public String postNumberAfter;
	public String likeNumberBefore;
	public String likeNumberAfter;

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

	/*
	 * @BeforeEach void setUp() throws Exception { webDriver.get(baseUrl); // click
	 * on menu that shows number of posts and likes webDriver.findElement(By.xpath(
	 * "/html/body/div/div/div[2]/div[1]/header/div[2]/div[7]/span/span/button"))
	 * .click(); // the number of posts postNumberBefore =
	 * webDriver.findElement(By.xpath(
	 * "/html/body/div/div/div[2]/div[1]/header/div[2]/div[7]/span/div/div/div/ul[2]/div/li/div/ul/li[1]/a/span[2]"
	 * )) .getText(); }
	 * 
	 * @AfterEach void tearDown() throws Exception { webDriver.get(baseUrl); //
	 * click on menu that shows number of posts and likes
	 * webDriver.findElement(By.xpath(
	 * "/html/body/div/div/div[2]/div[1]/header/div[2]/div[7]/span/span/button"))
	 * .click(); // the number of posts postNumberAfter =
	 * webDriver.findElement(By.xpath(
	 * "/html/body/div/div/div[2]/div[1]/header/div[2]/div[7]/span/div/div/div/ul[2]/div/li/div/ul/li[1]/a/span[2]"
	 * )) .getText(); assertNotEquals(postNumberBefore, postNumberAfter);
	 * assertNotEquals(likeNumberBefore, likeNumberAfter); }
	 */
	@Test
	@Disabled
	void reblogBlog() {
		webDriver.get("https://www.tumblr.com/doggosource/704577297320165376/doggosource-a-n-g-e-l");
		webDriver.findElement(By.xpath(
				"/html/body/div/div/div[2]/div[2]/div/div/div[1]/main/div/div/div/div[2]/div/div/div/article/div[2]/footer/div[1]/div[2]/div[3]/span/span/span/span/a"))
				.click();
	}

	@Test
	@Disabled
	void likeBlog() throws InterruptedException {
		webDriver.get("https://www.tumblr.com/doggosource/704577297320165376/doggosource-a-n-g-e-l");
		webDriver.findElement(By.xpath(
				"/html/body/div/div/div[2]/div[2]/div/div/div[1]/main/div/div/div/div[2]/div/div/div/article/div[2]/footer/div[1]/div[2]/div[4]/span/span/span/span/button"))
				.click();

	}

	/*
	 * IT WORKS
	 * make it prty
	 * */
	@Test
	void general() throws InterruptedException{
		webDriver.get(baseUrl);
		// click on menu that shows number of posts and likes
		WebElement menuButton = webDriver.findElement(By.xpath("/html/body/div/div/div[2]/div[1]/header/div[2]/div[7]/span/span/button"));
		menuButton.click();
		// the number of posts
		WebElement postNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"/html/body/div/div/div[2]/div[1]/header/div[2]/div[7]/span/div/div/div/ul[2]/div/li/div/ul/li[1]/a/span[2]")));
		
		postNumberBefore = postNumber.getText();
		System.out.println(postNumberBefore);
		likeNumberBefore = webDriver.findElement(By.xpath(
				"/html/body/div/div/div[2]/div[1]/header/div[2]/div[7]/span/div/div/div/ul[1]/li[1]/a/div[2]/span[2]"))
				.getText();
		System.out.println(likeNumberBefore);
		
		//click like button
		webDriver.findElement(By.xpath(
				"/html/body/div/div/div[2]/div[2]/div/div/div[1]/main/div/div/div/div[2]/div/div/div/article/div[2]/footer/div[1]/div[2]/div[4]/span/span/span/span/button"))
				.click();
		Thread.sleep(8000);
		menuButton.click();
		likeNumberAfter = webDriver.findElement(By.xpath(
				"/html/body/div/div/div[2]/div[1]/header/div[2]/div[7]/span/div/div/div/ul[1]/li[1]/a/div[2]/span[2]"))
				.getText();
		assertNotSame(likeNumberBefore, likeNumberAfter);
		
	}

}
