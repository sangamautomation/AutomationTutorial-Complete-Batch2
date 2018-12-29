package tests.hybridFramework;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import data.Constants;
import infrastructure.Operations;
import pageFlows.phptravels.HomePage;
import pageFlows.phptravels.LoginPage;
import pageFlows.phptravels.QuickBookingsPage;
import pageObjects.phptravels.SupplierLoginObjects;
import setup.Setup;
import utils.LogUtils;

// JUnit Test case

public class PhptravelsJunitTest {
	//Instantiations
	WebDriver driver = null;
	Operations op = new Operations();

	//Variable Declarations
	String url = "https://www.phptravels.net/supplier";
	String browser = "chrome";

	@Before
	public void prerequisites(){
		driver = Setup.launch(url, browser, Constants.CHROMEDRIVER_PATH);
	}

	@After
	public void postrequisites(){
		//Setup.teardown(driver);
	}

	@Test
	public void testPhpTravels() throws Exception{
		LogUtils.log("Php Travels Test");

		//Home
	//	new Home().supplier(driver);
		
		op.waitImplicitly(driver, 10);

		// Login Panel
		new LoginPage().loginPanel(driver);
		
		//Quick Bookings
		new QuickBookingsPage().quickbookings(driver);
		new QuickBookingsPage().popupService(driver);
		new QuickBookingsPage().formBooking(driver);

	}




}
