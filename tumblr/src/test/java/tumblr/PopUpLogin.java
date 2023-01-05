package tumblr;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;


class PopUpLogin {
	public static WebDriver webDriver;
	public static String baseUrl;
	public static JavascriptExecutor js;
	public static String frogXpath = "/html/body/div[1]/div/div[4]/div/div[2]/div";
	public static WebDriverWait wait;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.chrome.driver", "/home/rani/V Semester/SVVT/chromedriver_linux64/chromedriver");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--no-sandbox");
		options.addArguments("--incognito");
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
	
	@ParameterizedTest
	@ValueSource(ints = {2525, 1254, 6989, 100})
	void testFrog(int number) throws InterruptedException{
		js.executeScript("window.scrollBy(0, " + number + " )", "");
		Boolean b=false;;
		WebElement frog;
		if(number > 2000) {
			frog = wait.until(
					ExpectedConditions.visibilityOfElementLocated(
							By.xpath(frogXpath)));
		} else if (number <= 2000) {
			/*finish this shit*/
			b = wait.until(
					ExpectedConditions.not(
							ExpectedConditions.visibilityOfElementLocated(
									By.xpath(frogXpath))));
			assertTrue(b);
		}
	}

}
