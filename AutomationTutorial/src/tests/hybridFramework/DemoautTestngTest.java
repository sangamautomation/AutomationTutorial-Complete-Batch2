package tests.hybridFramework;

import java.text.ParseException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import data.Constants;
import infrastructure.Operations;
import pageFlows.demoaut.BookAFlightValidatePricePage;
import pageFlows.demoaut.FlightConfirmationPage;
import pageFlows.demoaut.FlightFinderPage;
import pageFlows.demoaut.RegisterPage;
import pageFlows.demoaut.SelectFlightPage;
import setup.Setup;
import utils.DateUtils;
import utils.PropertyUtils;
import utils.ReportUtils;
// Hybrid framework (Keyword-driven, Reusability, Infrastructure-driven, Modularized, Utility-driven, Data-driven, Report-driven with TestNG approach

public class DemoautTestngTest {

	//Instantiations
	WebDriver driver = null;
	Operations op = new Operations();

	//Variable Declarations
	String url = Constants.url_Demoaut;
	String browser = PropertyUtils.propertyFile_Read(Constants.configPath, "browser");
	String startTime = DateUtils.getCurrentTimestamp("MM/dd/yyyy HHmmss");

	@BeforeTest
	public void prerequisites(){
		driver = Setup.launch(url, browser, Constants.CHROMEDRIVER_PATH);

	}

	@Test
	public void testDemoaut() {
		//new TestDataPool();

		try {

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

	}

	@AfterTest
	public void postrequisites() throws ParseException{
		Setup.teardown(driver);
		String endTime = DateUtils.getCurrentTimestamp("MM/dd/yyyy HHmmss");

		String testDuration = DateUtils.getDateTimeDifference(startTime, endTime, "MM/dd/yyyy HHmmss");
	}

}
