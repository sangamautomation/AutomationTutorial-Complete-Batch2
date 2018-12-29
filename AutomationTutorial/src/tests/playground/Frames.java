package tests.playground;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import data.Constants;
import setup.TestRunSetup;
import utils.ReportUtils;

public class Frames {

	public static void main(String[] args) throws InterruptedException{ //String[] = String...
		
 		WebDriver driver = TestRunSetup.launch("http://way2automation.com/way2auto_jquery/frames-windows/frameset.html", "chrome", Constants.CHROMEDRIVER_PATH);

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		String expectedText = "Demo text. Demo text. Demo text. Demo text. Demo text. Demo text. Demo text. Demo text. Demo text. Demo text. Demo text. Demo text. Demo text. Demo text. Demo text. Demo text. Demo text. Demo text. Demo text.";

		//Switch to Frame:

		WebElement we = driver.findElement(By.xpath("//frame[@name='topFrame']"));
		driver.switchTo().frame(we);
		//	new Operations().switchToFrame(driver, "//frame[@name='topFrame']");

		//Operation
		String text =  driver.findElement(By.xpath("//p[contains(text(),'Demo')]")).getText();
		System.out.println("Text captured inside a frame: "+text);

		//Switch out of frame
		driver.switchTo().defaultContent();

		if(expectedText.equals(text))
			ReportUtils.reportResult("Pass", "Captured text is correct!", text);
		else
			ReportUtils.reportResult("Fail", "Captured text is NOT correct!", text);

		//	Thread.sleep(5000);

		driver.quit();


	}




}
