package tests.hybridFramework;

import org.openqa.selenium.WebDriver;

import data.Constants;
import pageFlows.demoaut.BookAFlightValidatePricePage;
import pageFlows.demoaut.FlightConfirmationPage;
import pageFlows.demoaut.FlightFinderPage;
import pageFlows.demoaut.RegisterPage;
import pageFlows.demoaut.SelectFlightPage;
import setup.Setup;
import utils.PropertyUtils;
import utils.ReportUtils;
/**
 * Hybrid Framework Example
 * @author Sangam
 */
public class DemoautTest {

	public static void main(String[] args) {
		
		//Variable Declarations
		WebDriver driver = null;
		//new TestDataPool();
		
		try {
			//Setup
			String url = Constants.url_Demoaut;
			String browser = PropertyUtils.propertyFile_Read(Constants.configPath, "browser");
			
			//Setup the WebDriver
			driver = Setup.launch(url, "chrome", Constants.CHROMEDRIVER_PATH);
			
			//Registration
			RegisterPage register = new RegisterPage();
			register.registration(driver);
			
			//Flight Finder
			FlightFinderPage flights = new FlightFinderPage();
			flights.flightFinder(driver);
			
			//Select Flight
			new SelectFlightPage().departFlight(driver);
			new SelectFlightPage().returnFlight(driver);
			new SelectFlightPage().continueFlight(driver);
			
			//Book A Flight
			BookAFlightValidatePricePage book = new BookAFlightValidatePricePage();
			book.validatePrice(driver);
			book.passengersInfo(driver);
			book.creditCardInfo(driver);
		
			//Flight Confirmation
			new FlightConfirmationPage().validateFlightConfirmation(driver);
			
			//System.out.println("Test case verdict :: "+ "PASS");
			ReportUtils.reportResult("Pass", "Verdict", "Test case is successful!");
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			driver.close();
		}

		
		
	}

}
