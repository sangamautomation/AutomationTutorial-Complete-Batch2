package tests.playground;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import data.Constants;
import infrastructure.Operations;
import setup.TestRunSetup;
import utils.PropertyUtils;
import utils.ReportUtils;

public class Alert {

	public static void main(String[] args) throws InterruptedException{ //String[] = String...
		//String URL = args[0];
		//String URL = args[0].toString().toUpperCase();
		String url = "http://output.jsbin.com/usidix/1";
		WebDriver driver = TestRunSetup.launch(url, "chrome", Constants.CHROMEDRIVER_PATH);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//input[contains(@value,'Go')]")).click();

		Thread.sleep(5000);
		WebDriverWait myWait = new WebDriverWait(driver, 10);
		if(myWait.until(ExpectedConditions.alertIsPresent()) != null )
			System.out.println("Alert is present.");


		//	WebElement txt= myWait.until(ExpectedConditions.elementToBeClickable(By.id("username")));
		//	myWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("viewFrame"));



		//Screenshot
		//ScreenshotUtils.screenshot("D:\\Selenium_Logs\\Screenshots", 1);

		String alertMessage_Expected= "This is an alert box.";

		//Alert / Popup - Capture text

		String alertMessage = driver.switchTo().alert().getText();
		System.out.println("Alert message is = "+alertMessage);


		if (alertMessage.equals(alertMessage_Expected))  
			ReportUtils.reportResult("Pass", "Alert Message is correct", alertMessage);
		else
			ReportUtils.reportResult("Fail", "Alert Message is wrong", alertMessage);

		PropertyUtils.propertyFile_Write("C:/Selenium_Logs/Screenshots/Captions.properties", "Caption0001", "Alert Txt : "+alertMessage);

		// ALert - OK
		Thread.sleep(5000);
		new Operations().switchToAlert(driver);
		//driver.switchTo().alert().accept();// Click OK
		//	driver.switchTo().alert().dismiss();// Click on Red X or Cancel

		//	driver.switchTo().alert().dismiss();

		//Waits
		Thread.sleep(5000);
		new Operations().waitImplicitly(driver, 10);
		new Operations().waitExplicitly(driver, 10, "//input[contains(@value,'Go')]");
		
		//	driver.close();//closing the single instance of browser
		driver.quit();//close all instances of browser





	}


}
