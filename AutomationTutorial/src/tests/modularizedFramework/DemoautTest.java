package tests.modularizedFramework;

import org.openqa.selenium.WebDriver;

import data.Constants;
import pageFlows.demoautNoDp.BookAFlightValidatePrice;
import pageFlows.demoautNoDp.FlightConfirmationValidation;
import pageFlows.demoautNoDp.Flights;
import pageFlows.demoautNoDp.Register;
import pageFlows.demoautNoDp.SelectFlightDepartReturn;
import setup.TestRunSetup;

/**
 * Modularized Framework Example
 * @author Sangam
 */
public class DemoautTest {

	public static void main(String[] args) {
		
		//Variable Declarations
		WebDriver driver = null;
		
		try {
			//Setup
			String url = "http://www.newtours.demoaut.com/";
			
			//Setup the WebDriver
			driver = TestRunSetup.launch(url, "chrome", Constants.CHROMEDRIVER_PATH);

			//Registration
			Register register = new Register();
			register.registration(driver);
			
			/*//SignIn
			SignOn signin = new SignOn();
			signin.signin(driver);
			*/
			
			//Flight Finder
			Flights flights = new Flights();
			flights.flightFinder(driver);
			
			//Select Flight
			new SelectFlightDepartReturn().departFlight(driver);
			new SelectFlightDepartReturn().continueFlight(driver);
			
			//Book A Flight
			BookAFlightValidatePrice.validatePrice(driver);
			BookAFlightValidatePrice.passengersInfo(driver);
			BookAFlightValidatePrice.creditCardInfo(driver);
			
			//Flight Confirmation
			FlightConfirmationValidation.validateFlightConfirmation(driver);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			driver.close();
		}

		
		
	}

}
