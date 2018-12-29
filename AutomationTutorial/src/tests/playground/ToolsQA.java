package tests.playground;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import data.Constants;
import infrastructure.Operations;



public class ToolsQA {

	public static void main(String[] args) throws InterruptedException{ 

		System.out.println(Constants.CHROMEDRIVER_PATH);

		System.setProperty("webdriver.chrome.driver", Constants.CHROMEDRIVER_PATH);
		
		WebDriver driver = null;

		try {
			//String[] = String...

			Operations sd = new Operations();
			System.out.println(Constants.CHROMEDRIVER_PATH);


			//	System.setProperty("webdriver.gecko.driver", "D:/Selenium_Drivers/drivers/geckodriver.exe");
			//System.setProperty("webdriver.chrome.driver", "D:/Selenium_Drivers/drivers/chromedriver.exe");
			
			//	driver = new FirefoxDriver();
			driver = new ChromeDriver();

			driver.get("http://toolsqa.com/automation-practice-form/");

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();


			// Actions::

	/*		Actions actions = new Actions(driver);
			WebElement we = driver.findElement(By.xpath(""));
			actions.moveToElement(we).contextClick().click().doubleClick().build().perform();
*/



			sd.setText(driver, "//input[@name='firstname']", "selenium");
			sd.setText(driver, "//input[@name='lastname']", "automation");

			sd.clickRadiobutton(driver, "//input[@value='Male']");
			sd.clickRadiobutton(driver, "//input[@value='3']");


			sd.clickCheckbox(driver, "//input[@value='Automation Tester']");


			sd.clickLink(driver, "//a[text()='Test File to Download']");

			sd.selectDropdown(driver, "//select[@id='continents']", "North America");

			sd.selectDropdown(driver, "//select[@id='selenium_commands']", "Wait Commands");

			sd.clickLink(driver, "//button[text()='Button']");

			String text = sd.getWidgetText(driver, "//span[@class='bcd']");

			System.out.println(text);

			Thread.sleep(5000);


			// Web Table


			sd.clickLink(driver, "//a[contains(@title,'Automation Practice Table')]");

			sd.wait(10);

			//String tableText=	sd.getWidgetText(driver, "//div[@id='content']//table//tbody/tr[1]/td[1]"); 

			String[][] tableData = new String[10][10]; //max rows = 10, max cols = 10
			for (int i = 1; i <= 4; i++) {
				for (int j = 1; j <= 6; j++) {
					//	 	tableData[i][j] =  	sd.getWidgetText(driver, PO_ToolsQA.text_Table(i, j)); 
					//	sd.getWidgetText(driver, "//div[@id='content']//table//tbody/tr["+i+"]/td["+j+"]"); 
					//	sd.clickLink(driver, "//div[@id='content']//table//tbody/tr[1]/td[6]");
					System.out.print(tableData[i][j] +"	");
				}
				System.out.println();
			}

			String tableData1 =	sd.getWidgetText(driver, "//div[@id='content']//table//tbody/tr[1]/td[1]"); 
			String tableData2 =	sd.getWidgetText(driver, "//div[@id='content']//table//tbody/tr[1]/td[2]"); 
			String tableData3 =	sd.getWidgetText(driver, "//div[@id='content']//table//tbody/tr[1]/td[3]"); 
			String tableData4 =	sd.getWidgetText(driver, "//div[@id='content']//table//tbody/tr[2]/td[1]"); 
			String tableData5 =	sd.getWidgetText(driver, "//div[@id='content']//table//tbody/tr[2]/td[2]"); 
			String tableData6 =	sd.getWidgetText(driver, "//div[@id='content']//table//tbody/tr[3]/td[1]"); 
			String tableData7 =	sd.getWidgetText(driver, "//div[@id='content']//table//tbody/tr[3]/td[3]"); 
			String tableData8 =	sd.getWidgetText(driver, "//div[@id='content']//table//tbody/tr[4]/td[4]"); 



		} catch (Exception e) {

			e.printStackTrace();
		}

		finally{

			driver.quit();	
		}

	}
}
