package notes;

import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;

import org.junit.Ignore;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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
 * tests which should pass have passed, which should fail had failed
 * i should change these xpaths to class names, could possibly make life easier and better
 * */
@TestMethodOrder(OrderAnnotation.class)
class NotesPost {
	public static WebDriver webDriver;
	public static String baseUrl;
	public static JavascriptExecutor js;
	public static WebDriverWait wait;
	public String postNumberBefore;
	public String postNumberAfter;
	public String noteNumberBefore;
	public String noteNumberAfter;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.chrome.driver", "/home/rani/V Semester/SVVT/chromedriver_linux64/chromedriver");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--no-sandbox");
		options.addArguments("--start-maximized");
		options.addArguments("--user-data-dir=/home/rani");//nece da loada profil
		webDriver = new ChromeDriver(options);
		webDriver.manage().getCookies();
		baseUrl = "https://tumblr.com/one-time-i-dreamt/";
		js = (JavascriptExecutor) webDriver;
		wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		webDriver.quit();
	}

	@BeforeEach
	void setUp() throws Exception {
		webDriver.get("https://tumblr.com/blog/reallycoolblogsblog");
		postNumberBefore= webDriver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div[2]/aside/div[1]/aside/ul/li[2]/a")).getText();
		webDriver.get("https://tumblr.com/one-time-i-dreamt/");
		noteNumberBefore = webDriver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div/div/div[1]/main/div/div/div/div[2]/div[1]/div/div/article/div[3]/footer/div[1]/div[1]/span/span/button/span/span/span/span")).getText();
		
	}

	@AfterEach
	void tearDown() throws Exception {
		webDriver.get("https://tumblr.com/blog/reallycoolblogsblog");
		postNumberBefore= webDriver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div[2]/aside/div[1]/aside/ul/li[2]")).getText();
		webDriver.get("https://tumblr.com/one-time-i-dreamt/");
		noteNumberAfter = webDriver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div/div/div[1]/main/div/div/div/div[2]/div[1]/div/div/article/div[3]/footer/div[1]/div[1]/span/span/button/span/span/span/span")).getText();
		assertNotEquals(noteNumberBefore, noteNumberAfter);
		assertNotEquals(postNumberBefore, postNumberAfter);
	}
	
	
	/*
	@Test
	void reblogCustomURL() throws InterruptedException{
		//shouldve passed, cant find xpath
		//when reblog button clicked a class called RuIGO should show
		//different ways
		

		webDriver.get("https://heritageposts.tumblr.com/post/623202863013822464/caluummhood-holy-shit-it-was-the-original-one");	
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[3]/div/a[1]")));
		webDriver.findElement(By.xpath("/html/body/div[2]/div[3]/div/a[1]")).click();
		
	}*/
	
	@Test
	void reblogBlog() {
		webDriver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div/div/div[1]/main/div/div/div/div[2]/div[1]/div/div/article/div[3]/footer/div[1]/div[2]/div[3]/span/span/span/span/a")).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("RuIGO")));
		webDriver.findElement(By.xpath("/html/body/div[1]/div/div[4]/div/div/div/div/div[2]/div[2]/div/div[3]/div/div/div/button")).click();
		
	}
	
	
	@Test
	void like() throws InterruptedException{
		webDriver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div/div/div[1]/main/div/div/div/div[2]/div[1]/div/div/article/div[3]/footer/div[1]/div[2]/div[4]/span/span/span/span/button")).click();
	}
	
	

}
