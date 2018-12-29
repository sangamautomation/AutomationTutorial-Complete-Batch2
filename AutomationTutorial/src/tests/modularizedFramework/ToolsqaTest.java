package tests.modularizedFramework;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import data.Constants;
import infrastructure.Operations;
import setup.TestRunSetup;
import utils.DateUtils;
import utils.KeyboardUtils;
import utils.ReportUtils;

public class ToolsqaTest {

	@Test
	public void testToolsqa(){
		System.out.println("### Practice Automation Form ###");

	}

	public static void main(String[] args) throws Exception {
		
		System.out.println("*** Automation with Selenium WebDriver ***");

		//Instantiations
		WebDriver driver = null;
		Operations op = new Operations();

		//Variable Declarations
		String browserType = "chrome";
		String baseURL = "http://toolsqa.com/automation-practice-form/";


		try {
			System.out.println("*** Tools QA Practice Automation Form ***");

			//Prerequisites
			driver = TestRunSetup.launch(baseURL, browserType, Constants.CHROMEDRIVER_PATH);

			//Web Interactions
			//Partial Link (Reloads the same page)
			driver.findElement(By.partialLinkText("Partial")).click();

			//Synchronization
			op.waitImplicitly(driver, 40);

			//Link (Navigates to Web Table page)
			driver.findElement(By.linkText("Link Test")).click();
			//op.clickLinkByText(driver, "Link Test");

			//Synchronization
			//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			op.waitImplicitly(driver, 40);

			//Web Table

			System.out.println("Automation Practice Table");

			String tableHeader1 =   op.getWidgetText(driver, "//table/tbody/tr[1]/th");
			String tableData11 =	op.getWidgetText(driver, "//div[@id='content']//table//tbody/tr[1]/td[1]"); 
			String tableData12 =	op.getWidgetText(driver, "//div[@id='content']//table//tbody/tr[1]/td[2]"); 
			String tableData13 =	op.getWidgetText(driver, "//div[@id='content']//table//tbody/tr[1]/td[3]"); 
			String tableData14 =	op.getWidgetText(driver, "//div[@id='content']//table//tbody/tr[1]/td[4]"); 
			String tableData15 =	op.getWidgetText(driver, "//div[@id='content']//table//tbody/tr[1]/td[5]"); 
			String tableData16 =	op.getWidgetText(driver, "//div[@id='content']//table//tbody/tr[1]/td[6]"); 

			String tableHeader2 =   op.getWidgetText(driver, "//table/tbody/tr[2]/th");
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

			//Validation
			ReportUtils.compare("UAE", tableData11);

			//Navigate back
			driver.navigate().back();//Back

			System.out.println("Navigated back..");

			//Synchronization
			op.waitImplicitly(driver, 30);

			//Actions
			/*	Actions actions = new Actions(driver);
			WebElement wel = driver.findElement(By.xpath(""));
			actions.moveToElement(wel).contextClick().click().doubleClick().build().perform();
			 */ 

			//Text boxes
			op.setText(driver, "//input[contains(@name,'firstname')]", "Selenium");
			op.setText(driver, "//input[contains(@name,'lastname')]", "Automation");

			//Radio button
			op.clickRadiobutton(driver, "//input[contains(@name,'sex') and contains(@value,'Male')]");
			op.clickRadiobutton(driver, "//input[contains(@name,'exp') and contains(@value,'7')]");

			//Text box
			op.setTextById(driver, "datepicker", DateUtils.timestamp("MM/dd/yyyy"));

			//Checkboxes
			op.clickCheckbox(driver, "//input[contains(@value,'Manual Tester')]");
			op.clickCheckbox(driver, "//input[contains(@value,'Automation Tester')]");

			//Upload & Browse File (Robot / Actions / WGet / AutoIT / ExecRuntime )
			op.clickLink(driver, "//input[@id='photo' and @name='photo']");

			//Browse File
			String filePath1 = "C:\\Users\\sanga\\Pictures\\go2meeting.png";
			String filePath = "\\Users\\sanga\\Pictures\\go2meeting.png";

			new KeyboardUtils().Key_Any(KeyEvent.VK_C); // Typing Driver letter C
			new KeyboardUtils().Key_Colon(); //Typing colon separately as we need to press SHIFT
			KeyboardUtils.typeString(filePath);
			//new  KeyboardUtils().Key_Enter();
			//Robot is to interact with non-browser objects (Upload dialog/popup)
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

			System.out.println("Uploaded the file.");

			//Download File
			op.clickLink(driver, "//a[text()='Test File to Download']");

			//Checkbox
			op.clickCheckbox(driver, "//input[contains(@value,'Selenium Webdriver')]");

			//Dropdown
			op.selectDropdown(driver, "//select[contains(@id,'continents')]", "North America");

			//Combobox (Multiple selection)
			op.selectDropdown(driver, "//select[contains(@id,'selenium_commands')]", "Browser Commands");
			op.selectDropdown(driver, "//select[contains(@id,'selenium_commands')]", "Navigation Commands");
			op.selectDropdown(driver, "//select[contains(@id,'selenium_commands')]", "WebElement Commands");

			//Button (Reloads the same page)
			//op.clickLink(driver, "//button[contains(@id,'submit')]");

			//Synchronization (Explicit Wait)
			op.waitExplicitly(driver, 50, "//div[@class='branding']/a/img[contains(@src,'http://20tvni1sjxyh352kld2lslvc.wpengine.netdna-cdn.com/wp-content/uploads/2014/08/Toolsqa.jpg')]");
			//WebDriverWait wait = new WebDriverWait(driver, 50);
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='branding']/a/img[contains(@src,'http://20tvni1sjxyh352kld2lslvc.wpengine.netdna-cdn.com/wp-content/uploads/2014/08/Toolsqa.jpg')]"))); 
			//wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath("")), "Expected text"));

			//Label (Capture Text)
			String capturedText = op.getText(driver, "//label/span[contains(@class,'bcd')]");

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			//Close Browser-Driver Instance
			TestRunSetup.teardown(driver);
			System.out.println();
		}

	}

}
