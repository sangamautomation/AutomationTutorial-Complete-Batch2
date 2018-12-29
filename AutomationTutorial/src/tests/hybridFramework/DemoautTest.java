package tests.hybridFramework;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;

import data.Constants;
import data.TestDataPool;
import pageFlows.demoaut.BookAFlightValidatePricePage;
import pageFlows.demoaut.FlightConfirmationPage;
import pageFlows.demoaut.FlightFinderPage;
import pageFlows.demoaut.RegisterPage;
import pageFlows.demoaut.SelectFlightPage;
import reports.SendReportEmail;
import reports.TestReportTools;
import setup.TestRunSetup;
import utils.DateUtils;
import utils.ExcelUtils;
import utils.PropertyUtils;
import utils.ReportUtils;
/**
 * Hybrid Framework Test (Keyword-driven, Reusability, Infrastructure-driven, Modularized, Utility-driven, Data-driven, Report-driven approach
 * @author Sangam
 */
public class DemoautTest {

	public static void main(String[] args) throws Exception {

		//Declarations
		String url = Constants.url_Demoaut;
		String browser = PropertyUtils.propertyFile_Read(Constants.configPath, "browser");
		WebDriver driver = null;

		//new TestDataPool();

		try {

			//Setup
			driver =  TestRunSetup.prerequisites(driver, url, browser, Constants.CHROMEDRIVER_PATH);

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

			//Reporting
			ReportUtils.reportResult("Pass", "Verdict", "Test case is successful!");

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			TestRunSetup.postrequisites(driver);
		}
	}

}
