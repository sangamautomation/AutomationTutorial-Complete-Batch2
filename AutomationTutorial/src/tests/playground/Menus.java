package tests.playground;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import infrastructure.Operations;

 
public class Menus {

	public static void main(String[] args) throws InterruptedException{ //String[] = String...
		Operations sd = new Operations();

			System.setProperty("webdriver.gecko.driver", "D:/Selenium_Drivers/drivers/geckodriver.exe");
	//	System.setProperty("webdriver.chrome.driver", "D:/Selenium_Drivers/drivers/chromedriver.exe");

		WebDriver driver = null;
			driver = new FirefoxDriver();
	//	driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get("http://way2automation.com/way2auto_jquery/menu.php");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		sd.clickLink(driver, "//a[text()='Signin']");
		Thread.sleep(25000);

		//Dynamic wait
	//	WebDriverWait webwait = new WebDriverWait(driver, 10); // Explicit Wait/Dynamic Wait (Waits for a particular element for a conditions) 
 	//	webwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='username']")));

		sd.setText(driver, "//input[@name='username']", "selenium"); 
		sd.setText(driver, "//input[@name='password']", "Automation123"); 
		sd.clickLink(driver, "//input[@value='Submit']");


		sd.clickLink(driver, "//img[contains(@src,'menu.jpg')]");
		Thread.sleep(5000);
		sd.clickLink(driver, "//a[text()='Simple Menu']");


		//Actions
		Actions act = new Actions(driver);
		WebElement we = driver.findElement(By.xpath("//a[text()='Interaction']")); //li[text()='Salzburg']
		act.moveToElement(we).build().perform(); 

	//	act.dragAndDrop(source, target)
		//act.moveToElement(we).click().contextClick().build().perform(); 

		Thread.sleep(5000);

		driver.quit();

	}
}
 