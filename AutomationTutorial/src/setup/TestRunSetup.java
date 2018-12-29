package setup;

import java.text.ParseException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.xml.LaunchSuite;

import data.Constants;
import utils.DateUtils;
import utils.ExcelUtils;
/**
 * Test Run Setup
 * @author Sangam
 */
public class TestRunSetup {
	static String startTestCase = null;
	static String endTestCase = null;
	static String testDuration = null;
	public static WebDriver prerequisites(WebDriver driver, String url, String browser, String driverPath) throws Exception{
		//Banner : print test case names with spl chars *

		//new TestDataPool();
		/*String filePath = Constants.automationDatapoolPath;
		String sheetName = "Automation";
		int headerRowNum = 0;
		int tcRowNum = 1;
		HashMap<String,String> h = ExcelUtils.getTestDataXls(filePath, sheetName, headerRowNum, tcRowNum);
		 */
		
		// Calculating Start Timestamps
		startTestCase = DateUtils.getCurrentTimestamp("MMddyyyy HHmmss");
		System.out.println("Test Started @ "+ startTestCase);	

		driver = launch(url, browser, driverPath);

		return driver;
	}

	public static void postrequisites(WebDriver driver) throws ParseException{
		teardown(driver);

		//Calculating End Timestamp
		endTestCase = DateUtils.getCurrentTimestamp("MMddyyyy HHmmss");
		System.out.println("Test Ended @ "+ endTestCase);

		testDuration = DateUtils.getDateTimeDifference(startTestCase, endTestCase, "MMddyyyy HHmmss");
		System.out.println("Test Duration = "+testDuration);

		//Reporting
		//TestReportTools.runTestReportTools(h);
		//SendReportEmail.runSendReportEmail(h);
		System.out.println("_______________________________ TEST END_________________________");

	}

	public static WebDriver getDriver(){
		WebDriver driver = null;
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
				//driver = new MarionetteDriver();
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

	public static void teardown(WebDriver driver) throws ParseException{
		driver.close();
		//driver.quit();
		System.out.println("Closed the browser!");
	}

}
