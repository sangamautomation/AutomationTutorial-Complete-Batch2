package setup;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
/**
 * Setup for WebDriver
 * @author Sangam
 */
public class Setup {


	public static WebDriver launchBrowser(String url, String browser){

		WebDriver driver = null;
		try {

			switch (browser) {
			case "chrome":
				System.setProperty("webdriver.chrome.driver","C:\\drivers\\chromedriver.exe");
				driver = new ChromeDriver();
				break;
			case "firefox":
				System.setProperty("webdriver.gecko.driver","C:\\drivers\\geckodriver.exe");
				driver = new FirefoxDriver();
				break;
			case "edge":
				System.setProperty("webdriver.edge.driver","C:\\drivers\\MicrosoftWebDriver.exe");
				driver = new EdgeDriver();
				break;
			case "ie":
				System.setProperty("webdriver.ie.driver","C:\\drivers\\IEWebDriver.exe");
				driver = new InternetExplorerDriver();
				break;
			case "opera":
				System.setProperty("webdriver.opera.driver","C:\\drivers\\operadriver.exe");
				driver = new OperaDriver();
			default:
				System.out.println("The mentioned browser type is not defined!");
				break;
			}
			driver.get(url);
			driver.manage().window().maximize();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return driver;

	}

	public static WebDriver launch(String url, String browser, String driverPath){
		System.out.println("Launched the browser & opened the URL");

		WebDriver driver = null;
		try {

			switch (browser) {
			case "chrome":
				System.setProperty("webdriver.chrome.driver",driverPath);
				driver = new ChromeDriver();
				break;
			case "firefox":
				System.setProperty("webdriver.gecko.driver",driverPath);
				driver = new FirefoxDriver();
				break;
			case "edge":
				System.setProperty("webdriver.edge.driver",driverPath);
				driver = new EdgeDriver();
				break;
			case "ie":
				System.setProperty("webdriver.ie.driver",driverPath);
				driver = new InternetExplorerDriver();
				break;
			case "opera":
				System.setProperty("webdriver.opera.driver",driverPath);
				driver = new OperaDriver();
			default:
				System.out.println("The mentioned browser type is not defined!");
				break;
			}
			driver.get(url);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.manage().window().maximize();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return driver;

	}

	public static void teardown(WebDriver driver){
		driver.close();
		System.out.println("Closed the browser!");
	}

	public static void teardownAll(WebDriver driver){
		driver.quit();
		System.out.println("Closed the browser!");
	}
}
