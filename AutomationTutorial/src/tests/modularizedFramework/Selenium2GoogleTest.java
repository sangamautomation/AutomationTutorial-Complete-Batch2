/**
 * Test Case: Linear Framework 
 * Launch Chrome Browser, 
 * Open URL: Google.com, 
 * Search for something, 
 * Capture Page Title, 
 * Validation, 
 * Close browser
 */
package tests.modularizedFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import data.Constants;
import infrastructure.Operations;
import setup.Setup;

/**
 * @author sangam
 * @date 12/16/2018
 *
 */
public class Selenium2GoogleTest {

	public static void main(String[] args) {

		//Instantiations
		Operations op = new Operations();
		WebDriver driver = null;
				
		//Variable Declarations
		String searchTerm = "New York Times";
		String expectedTitle = searchTerm + " - Google Search";// Requirement
		String actualTitle = null; // Runtime
		String url = "http://www.google.com";
		String browser = "chrome";
		
		// Browser Setup for Chrome
			//System.setProperty("webdriver.chrome.driver", "E:/AutomationProjects\\drivers\\chromedriver.exe"); //Change Driver letter accordingly. Use in path: escape char \\ or /
			//WebDriver driver = new ChromeDriver(); // Instantiate ChromeDriver
			// driver = op.launchBrowser_Chrome(Constants.CHROMEDRIVER_PATH);
		try {
			System.out.println("*** Automation with Selenium WebDriver ***");

			
			// Launch Chrome Browser
			//driver.get("http://www.google.com");
			
			// Maximize browser window
			///driver.manage().window().maximize();
			
			driver = Setup.launch(url, browser, Constants.CHROMEDRIVER_PATH);	

			// Type search term in Google search box (by identifying object with xPath)
			op.setText(driver, "//input[@title='Search']", searchTerm);
			
			//Click on Google Search button
			op.clickLink(driver, "//div[@jsname='VlcLAe']/center/input[contains(@value,'Search')]");
			
			// Get the page title
			
			 actualTitle = driver.getTitle();
			
			// Validation
			 System.out.println("Validation - Page Title");
			 if(expectedTitle.equals(actualTitle))
				 System.out.println("Pass - Expected and Actual Page titles are equal");
			 else
				 System.out.println("Fail - Expected and Actual Page titles are NOT equal");

			

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		finally {
			driver.close();
			//driver.quit();
		}
		

	}

}
