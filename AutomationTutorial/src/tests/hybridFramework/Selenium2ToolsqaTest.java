package tests.hybridFramework;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import data.Constants;
import infrastructure.Operations;
import pageObjects.toolsqa.Toolsqa;
import setup.Setup;
import utils.DateUtils;
import utils.KeyboardUtils;
import utils.ReportUtils;

public class Selenium2ToolsqaTest {


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
			driver = Setup.launch(baseURL, browserType, Constants.CHROMEDRIVER_PATH);

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

			String tableHeader1 =   op.getWidgetText(driver, Toolsqa.table_TableHeader1 );
			String tableData11 =	op.getWidgetText(driver, Toolsqa.table_TableData11); 
			String tableData12 =	op.getWidgetText(driver, Toolsqa.table_TableData12); 
			String tableData13 =	op.getWidgetText(driver, Toolsqa.table_TableData13); 
			String tableData14 =	op.getWidgetText(driver, Toolsqa.table_TableData14); 
			String tableData15 =	op.getWidgetText(driver, Toolsqa.table_TableData15); 
			String tableData16 =	op.getWidgetText(driver, Toolsqa.table_TableData16); 

			String tableHeader2 =   op.getWidgetText(driver, Toolsqa.table_TableHeader2);
			String tableData21 =	op.getWidgetText(driver, Toolsqa.table_TableData21); 
			String tableData22 =	op.getWidgetText(driver, Toolsqa.table_TableData22); 
			String tableData23 =	op.getWidgetText(driver, Toolsqa.table_TableData23); 
			String tableData24 =	op.getWidgetText(driver, Toolsqa.table_TableData24); 
			String tableData25 =	op.getWidgetText(driver, Toolsqa.table_TableData25); 
			String tableData26 =	op.getWidgetText(driver, Toolsqa.table_TableData26); 

			String tableHeader3 = op.getWidgetText(driver, Toolsqa.table_TableHeader3);
			String tableData31 =	op.getWidgetText(driver, Toolsqa.table_TableData31); 
			String tableData32 =	op.getWidgetText(driver, Toolsqa.table_TableData32); 
			String tableData33 =	op.getWidgetText(driver, Toolsqa.table_TableData33); 
			String tableData34 =	op.getWidgetText(driver, Toolsqa.table_TableData34); 
			String tableData35 =	op.getWidgetText(driver, Toolsqa.table_TableData35); 
			String tableData36 =	op.getWidgetText(driver, Toolsqa.table_TableData36); 

			String tableHeader4 = op.getWidgetText(driver, Toolsqa.table_TableHeader4);
			String tableData41 =	op.getWidgetText(driver, Toolsqa.table_TableData41); 
			String tableData42 =	op.getWidgetText(driver, Toolsqa.table_TableData42); 
			String tableData43 =	op.getWidgetText(driver, Toolsqa.table_TableData43); 
			String tableData44 =	op.getWidgetText(driver, Toolsqa.table_TableData44); 
			String tableData45 =	op.getWidgetText(driver, Toolsqa.table_TableData45); 
			String tableData46 =	op.getWidgetText(driver, Toolsqa.table_TableData46); 

			//driver.findElement(By.xpath("//div[@id='content']//table//tbody/tr[4]/td[6]")).click();

			//String tableText=	op.getWidgetText(driver, "//div[@id='content']//table//tbody/tr[1]/td[1]"); 
			String[][] tableData = new String[10][10]; //max rows = 10, max cols = 10
			for (int i = 1; i <= 4; i++) {
				for (int j = 1; j <= 6; j++) {
					tableData[i][j] = op.getText(driver, Toolsqa.table_TableAll(i, j)); 
				}
			}


			for (int i = 1; i <= 4; i++) {
				for (int j = 1; j <= 6; j++) {
					System.out.print(tableData[i][j] +", ");
				}
				System.out.println();
			}

			//Validation
			ReportUtils.compare("UAE", tableData11);
			ReportUtils.compare("Dubai", tableData12);

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
			op.setText(driver, Toolsqa.textbox_FirstName, "Selenium");
			op.setText(driver, Toolsqa.textbox_LastName, "Automation");

			//Radio button
			op.clickRadiobutton(driver, Toolsqa.radiobutton_SexMale);
			op.clickRadiobutton(driver, Toolsqa.radiobutton_Experience(6));

			//Text box
			op.setTextById(driver, "datepicker", DateUtils.timestamp("MM/dd/yyyy"));

			//Checkboxes
			op.clickCheckbox(driver, Toolsqa.checkbox_ManualTester);
			op.clickCheckbox(driver, Toolsqa.checkbox_AutomationTester);

			//Upload & Browse File (Robot / Actions / WGet / AutoIT / ExecRuntime )
			//Robot is to operate upon non-browser objects
			op.clickLink(driver, Toolsqa.link_Photo);

			//Browse File
			String filePath1 = "C:\\Users\\sanga\\Pictures\\go2meeting.png";

			new KeyboardUtils().Key_Any(KeyEvent.VK_C); // Typing Driver letter C
			new KeyboardUtils().Key_Colon(); //Typing colon separately as we need to press SHIFT
			KeyboardUtils.typeString(Constants.filePath_Toolsqa);
			//new  KeyboardUtils().Key_Enter();
			//Robot is to interact with non-browser objects (Upload dialog/popup)
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

			System.out.println("Uploaded the file.");

			//Download File
			op.clickLink(driver,Toolsqa.link_Download );

			//Checkbox
			op.clickCheckbox(driver, Toolsqa.checkbox_Tool);

			//Dropdown
			op.selectDropdown(driver, Toolsqa.dropdown_Continents, "North America");

			//Combobox (Multiple selection)
			op.selectDropdown(driver, Toolsqa.dropdown_SeleniumCommands, "Browser Commands");
			op.selectDropdown(driver, Toolsqa.dropdown_SeleniumCommands, "Navigation Commands");
			op.selectDropdown(driver, Toolsqa.dropdown_SeleniumCommands, "WebElement Commands");

			//Button (Reloads the same page)
			//op.clickLink(driver, "//button[contains(@id,'submit')]");

			//Synchronization (Explicit Wait)
			op.waitExplicitly(driver, 50, Toolsqa.image_Logo);
			//WebDriverWait wait = new WebDriverWait(driver, 50);
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='branding']/a/img[contains(@src,'http://20tvni1sjxyh352kld2lslvc.wpengine.netdna-cdn.com/wp-content/uploads/2014/08/Toolsqa.jpg')]"))); 
			//wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath("")), "Expected text"));

			//Label (Capture Text)
			String capturedText = op.getText(driver, Toolsqa.textlabel_Text1);

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			//Close Browser-Driver Instance
			Setup.teardown(driver);
			System.out.println();
		}

	}

}
