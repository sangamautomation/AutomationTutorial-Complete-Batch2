/**
 * Test Case: Linear Framework 
 * Launch Chrome Browser, 
 * Open URL: Google.com, 
 * Search for something, 
 * Capture Page Title, 
 * Validation, 
 * Close browser
 */
package tests.linearFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author sangam
 * @date 12/16/2018
 *
 */
public class GoogleTest {

	public static void main(String[] args) {

		String searchTerm = "New York Times";
		String expectedTitle = searchTerm + " - Google Search";// Requirement
		String actualTitle = null; // Runtime
		
		// Browser Setup for Chrome
			System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe"); //Change Driver letter accordingly. Use in path: escape char \\ or /
			WebDriver driver = new ChromeDriver(); // Instantiate ChromeDriver

					
		try {
			System.out.println("*** Automation with Selenium WebDriver ***");

			
			// Launch Chrome Browser
			driver.get("http://www.google.com");
			
			// Maximize browser window
			driver.manage().window().maximize();
			
			// Type search term in Google search box (by identifying object with xPath)
			//input[@title='Search'] // Search box Xpath
			driver.findElement(By.xpath("//input[@title='Search']")).sendKeys(searchTerm);
			
			//Click on Google Search button
			//div[@jsname='VlcLAe']/center/input[contains(@value,'Search')]
			//div[@class='VlcLAe']/center/input[contains(@value,'Google Search')]
			
			driver.findElement(By.xpath("//div[@jsname='VlcLAe']/center/input[contains(@value,'Search')]")).click();
			
			//Different ways of locating elements or objects
			/*driver.findElement(By.className("className"));
			driver.findElement(By.cssSelector("cssSelector"));
			driver.findElement(By.id("id"));
			driver.findElement(By.linkText("linkText"));
			driver.findElement(By.name("name"));
			driver.findElement(By.partialLinkText("partialLinkText"));
			driver.findElement(By.tagName("tagName"));*/
			
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
