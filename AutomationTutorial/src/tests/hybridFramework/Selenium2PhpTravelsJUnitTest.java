package tests.hybridFramework;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import data.Constants;
import infrastructure.Operations;
import pageFlows.phptravels.Home;
import pageFlows.phptravels.Login;
import pageObjects.phptravels.Phptravels;
import setup.Setup;

// JUnit Test case

public class Selenium2PhpTravelsJUnitTest {
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
		Setup.teardown(driver);
	}

	@Test
	public void testPhpTravels(){

		//Home
	//	new Home().supplier(driver);
		
		op.waitImplicitly(driver, 10);

		// Login Panel
		new Login().loginPanel(driver);

	}




}
