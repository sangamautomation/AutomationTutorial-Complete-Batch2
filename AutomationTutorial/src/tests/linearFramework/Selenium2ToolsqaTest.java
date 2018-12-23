package tests.linearFramework;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import infrastructure.Operations;
import utils.KeyboardUtils;

public class Selenium2ToolsqaTest {

	public static void main(String[] args) throws Exception {

		Operations op = new Operations();

		System.out.println("*** Practice Automation Form ***");

		//WebDriver instantiation
		System.setProperty("webdriver.chrome.driver", "E:/AutomationProjects\\drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		// Chrome browser launch & open URL
		driver.get("http://toolsqa.com/automation-practice-form/");
		System.out.println("Launched the Chrome browser & opened the URL");

		//Synchronization (Implicit wait)
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		System.out.println("Waited ...");

		// Maximize browser
		driver.manage().window().maximize();

		// Web Interactions
		//Partial Link (Reloads the same page)
		driver.findElement(By.partialLinkText("Partial")).click();

		//Synchronization
		Thread.sleep(10000); //Static wait

		//Link (Navigates to Web Table page)
		driver.findElement(By.linkText("Link Test")).click();


		//Synchronization
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);


		// Web Table
		
		System.out.println("Automation Practice Table");

 	//	op.clickLink(driver, "//a[contains(@title,'Automation Practice Table')]");

	//	op.wait(10);
				
		String tableHeader1 = op.getWidgetText(driver, "//table/tbody/tr[1]/th");
		String tableData11 =	op.getWidgetText(driver, "//div[@id='content']//table//tbody/tr[1]/td[1]"); 
		String tableData12 =	op.getWidgetText(driver, "//div[@id='content']//table//tbody/tr[1]/td[2]"); 
		String tableData13 =	op.getWidgetText(driver, "//div[@id='content']//table//tbody/tr[1]/td[3]"); 
		String tableData14 =	op.getWidgetText(driver, "//div[@id='content']//table//tbody/tr[1]/td[4]"); 
		String tableData15 =	op.getWidgetText(driver, "//div[@id='content']//table//tbody/tr[1]/td[5]"); 
		String tableData16 =	op.getWidgetText(driver, "//div[@id='content']//table//tbody/tr[1]/td[6]"); 

		String tableHeader2 = op.getWidgetText(driver, "//table/tbody/tr[2]/th");
		String tableData21 =	op.getWidgetText(driver, "//div[@id='content']//table//tbody/tr[2]/td[1]"); 
		String tableData22 =	op.getWidgetText(driver, "//div[@id='content']//table//tbody/tr[2]/td[2]"); 
		String tableData23 =	op.getWidgetText(driver, "//div[@id='content']//table//tbody/tr[2]/td[3]"); 
		String tableData24 =	op.getWidgetText(driver, "//div[@id='content']//table//tbody/tr[2]/td[4]"); 
		String tableData25 =	op.getWidgetText(driver, "//div[@id='content']//table//tbody/tr[2]/td[5]"); 
		String tableData26 =	op.getWidgetText(driver, "//div[@id='content']//table//tbody/tr[2]/td[6]"); 

		String tableHeader3 = op.getWidgetText(driver, "//table/tbody/tr[3]/th");
		String tableData31 =	op.getWidgetText(driver, "//div[@id='content']//table//tbody/tr[3]/td[1]"); 
		String tableData32 =	op.getWidgetText(driver, "//div[@id='content']//table//tbody/tr[3]/td[2]"); 
		String tableData33 =	op.getWidgetText(driver, "//div[@id='content']//table//tbody/tr[3]/td[3]"); 
		String tableData34 =	op.getWidgetText(driver, "//div[@id='content']//table//tbody/tr[3]/td[4]"); 
		String tableData35 =	op.getWidgetText(driver, "//div[@id='content']//table//tbody/tr[3]/td[5]"); 
		String tableData36 =	op.getWidgetText(driver, "//div[@id='content']//table//tbody/tr[3]/td[6]"); 

		String tableHeader4 = op.getWidgetText(driver, "//table/tbody/tr[4]/th");
		String tableData41 =	op.getWidgetText(driver, "//div[@id='content']//table//tbody/tr[4]/td[1]"); 
		String tableData42 =	op.getWidgetText(driver, "//div[@id='content']//table//tbody/tr[4]/td[2]"); 
		String tableData43 =	op.getWidgetText(driver, "//div[@id='content']//table//tbody/tr[4]/td[3]"); 
		String tableData44 =	op.getWidgetText(driver, "//div[@id='content']//table//tbody/tr[4]/td[4]"); 
		String tableData45 =	op.getWidgetText(driver, "//div[@id='content']//table//tbody/tr[4]/td[5]"); 
		String tableData46 =	op.getWidgetText(driver, "//div[@id='content']//table//tbody/tr[4]/td[6]"); 

		//driver.findElement(By.xpath("//div[@id='content']//table//tbody/tr[4]/td[6]")).click();
		
		//String tableText=	op.getWidgetText(driver, "//div[@id='content']//table//tbody/tr[1]/td[1]"); 
 		String[][] tableData = new String[10][10]; //max rows = 10, max cols = 10
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 6; j++) {
				//	 	tableData[i][j] =  	op.getWidgetText(driver, PO_ToolsQA.text_Table(i, j)); 
				tableData[i][j] = op.getText(driver, "//div[@id='content']//table//tbody/tr["+i+"]/td["+j+"]"); 
				//	op.clickLink(driver, "//div[@id='content']//table//tbody/tr[1]/td[6]");
			//	System.out.print(tableData[i][j] +"	");
			}
			//System.out.println();
		}

		
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 6; j++) {
 				System.out.print(tableData[i][j] +", ");
			}
			System.out.println();
		}
	
 
		//Navigate back
		driver.navigate().back();//Back
		/*driver.navigate().to("URL");//To like get
		driver.navigate().forward(); //Forward
		driver.navigate().refresh(); //Reload page
		 */		
		
		System.out.println("Navigated back..");

		//Synchronization
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

		// Actions
	 /*	Actions actions = new Actions(driver);
		WebElement wel = driver.findElement(By.xpath(""));
		actions.moveToElement(wel).contextClick().click().doubleClick().build().perform();
*/ 
		//Text boxes
		driver.findElement(By.xpath("//input[contains(@name,'firstname')]")).sendKeys("Selenium");
		driver.findElement(By.xpath("//input[contains(@name,'lastname')]")).sendKeys("Automation");
		System.out.println("Entered firstname &  lastname");

		// Radio button
		driver.findElement(By.xpath("//input[contains(@name,'sex') and contains(@value,'Male')]")).click();
		driver.findElement(By.xpath("//input[contains(@name,'exp') and contains(@value,'7')]")).click();
		System.out.println("Clicked on Radio buttons sex &  exp");

		//Text box
		driver.findElement(By.id("datepicker")).sendKeys("12/20/2018");
		System.out.println("Entered Date 12/20/2018");

		// Checkboxes
		driver.findElement(By.xpath("//input[contains(@value,'Manual Tester')]")).click();
		driver.findElement(By.xpath("//input[contains(@value,'Automation Tester')]")).click();
		System.out.println("Clicked on checkboxes  Profession");

		//Upload & Browse File (Robot / Actions / WGet / AutoIT / ExecRuntime )
		driver.findElement(By.xpath("//input[@id='photo' and @name='photo']")).click();
		//	driver.findElement(By.xpath("//input[@id='photo' and @name='photo']")).sendKeys("C:\Users\sanga\Pictures\go2meeting.png");

		String filePath1 = "C:\\Users\\sanga\\Pictures\\go2meeting.png";
		String filePath = "\\Users\\sanga\\Pictures\\go2meeting.png";

		new KeyboardUtils().Key_Any(KeyEvent.VK_C); // Typing Driver letter C
		new KeyboardUtils().Key_Colon(); //Typing colon separately as we need to press SHIFT
		KeyboardUtils.typeString(filePath);

		//Robot is to interact with non-browser objects (Upload dialog/popup)
		Robot robot = new Robot();
		 		
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		System.out.println("Uploaded the file.");

		//Download File
		driver.findElement(By.xpath("//a[text()='Test File to Download']")).click();
		System.out.println("Downloaded the file.");

		// Checkbox
		driver.findElement(By.xpath("//input[contains(@value,'Selenium Webdriver')]")).click();

		//Dropdown
		WebElement we = driver.findElement(By.xpath("//select[contains(@id,'continents')]"));
		Select sel = new Select(we);
		//sel.selectByIndex(5);//North America
		//sel.selectByValue("North America");
		sel.selectByVisibleText("North America");
		System.out.println("Selected dropdown value");

		//Combobox (Multiple selection)
		WebElement we1 = driver.findElement(By.xpath("//select[contains(@id,'selenium_commands')]"));
		Select sel1 = new Select(we1);
		sel1.selectByVisibleText("Browser Commands");
		sel1.selectByVisibleText("Navigation Commands");
		sel1.selectByVisibleText("WebElement Commands");

		System.out.println("Selected combobox value");

		//Button (Reloads the same page)
		driver.findElement(By.xpath("//button[contains(@id,'submit')]")).click();
		System.out.println("Clicked on button");

		//Synchronization (Explicit Wait)
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='branding']/a/img[contains(@src,'http://20tvni1sjxyh352kld2lslvc.wpengine.netdna-cdn.com/wp-content/uploads/2014/08/Toolsqa.jpg')]"))); 
		wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath("")), "Expected text"));
		
		//Label (Capture Text)
		String capturedText = driver.findElement(By.xpath("//label/span[contains(@class,'bcd')]")).getText();
		System.out.println("Captured Text : "+capturedText);

		//Wait
		Thread.sleep(10000);

		//Close Browser-Driver Instance
		driver.close(); //single instance
		//driver.quit(); //all instances


	}

}
