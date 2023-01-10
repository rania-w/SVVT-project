package tumblr;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class CustomizeBlog {
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
		baseUrl = "https://tumblr.com";
		wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		webDriver.quit();
	}

	//clicking the menu button which is needed for every test
	@BeforeEach
	void setUp() throws Exception {
		webDriver.get(baseUrl);
		webDriver.findElement(By.xpath("/html/body/div/div/div[2]/div[1]/header/div[2]/div[7]/span/span/button")).click();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testTitle() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[2]/div[1]/header/div[2]/div[7]/span/div/div/div/ul[2]/div/li/div/ul/li[8]/a")));
		webDriver.findElement(By.xpath("/html/body/div/div/div[2]/div[1]/header/div[2]/div[7]/span/div/div/div/ul[2]/div/li/div/ul/li[8]/a")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[2]/div[2]/div[1]/main/div/div[1]/div[2]/button")));
		webDriver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div[1]/main/div/div[1]/div[2]/button")).click();
		webDriver.findElement(By.className("c1JQY")).clear();
		String newName = "sad qa";
		webDriver.findElement(By.className("c1JQY")).sendKeys(newName);
		
		//save
		webDriver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div[1]/main/div/div[1]/div[2]/div[1]/button[2]")).click();
		webDriver.get("https://tumblr.com/reallycoolblogsblog");
		String blogTitle = webDriver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div/div/div[1]/header/div/div/h1")).getText();
		assertEquals(blogTitle, newName);
	}
	
	@Test
	void testDescription() {
		webDriver.findElement(By.xpath("/html/body/div/div/div[2]/div[1]/header/div[2]/div[7]/span/div/div/div/ul[2]/div/li/div/ul/li[8]/a")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[2]/div[2]/div[1]/main/div/div[1]/div[2]/button")));
		webDriver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div[1]/main/div/div[1]/div[2]/button")).click();
		webDriver.findElement(By.className("BL18W")).clear();
		String newDesc = "im not a bot i promise im testing this hellsite for a school project";
		webDriver.findElement(By.className("BL18W")).sendKeys(newDesc);
		
		//save
		webDriver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div[1]/main/div/div[1]/div[2]/div[1]/button[2]")).click();
		webDriver.get("https://tumblr.com/reallycoolblogsblog");
		String blogDesc = webDriver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div/div/div[1]/header/div/div/div[1]/div/div")).getText();
		assertEquals(blogDesc, newDesc);
		
		
	}
	@ParameterizedTest
	@CsvSource({"True Blue,palette--trueBlue", "Dark Mode,palette--darkMode", "Low-Contrast Classic,palette--lowContrastClassic", "Cement,palette--cement", "Cybernetic,palette--cybernetic", "Canary,palette--canary", "Ghost,palette--ghost", "Vampire,palette--vampire", "Pumpkin,palette--pumpkin", "Snow Bright,palette--snowBright", "Goth Rave,palette--gothRave", "Pride,palette--pride"})
	void palette(String given, String expected) throws InterruptedException {
		WebElement paletteButton = webDriver.findElement(By.xpath("/html/body/div/div/div[2]/div[1]/header/div[2]/div[7]/span/div/div/div/ul[1]/li[9]/button"));
		paletteButton.click();
		WebElement body = webDriver.findElement(By.xpath("/html/body"));
		String s = webDriver.findElement(By.xpath("/html/body/div/div/div[2]/div[1]/header/div[2]/div[7]/span/div/div/div/ul[1]/li[9]/button/span/div[2]/span[1]/span")).getText();
		int i=0;
		while(!s.equals(given) && i<12) {
			paletteButton.click();
			s = webDriver.findElement(By.xpath("/html/body/div/div/div[2]/div[1]/header/div[2]/div[7]/span/div/div/div/ul[1]/li[9]/button/span/div[2]/span[1]/span")).getText();
			i++;
			wait.wait(1500);
		}
		assertEquals(expected, body.getAttribute("class"));
		
		
	}

}
