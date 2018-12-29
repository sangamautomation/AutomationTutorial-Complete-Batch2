package pageFlows.demoaut;

import org.openqa.selenium.WebDriver;

import infrastructure.Operations;
import pageObjects.demoaut.SelectFlightObjects;
import utils.ReportUtils;

public class SelectFlightPage {

	Operations op = new Operations();

	public void departFlight(WebDriver driver){
		System.out.println("\n******************** departFlight ********************\n");	
		op.clickRadiobutton(driver, SelectFlightObjects.radiobutton_DepartUnitedAirlines);
	}

	public void returnFlight(WebDriver driver){
		System.out.println("\n******************** returnFlight ********************\n");	
		op.clickRadiobutton(driver, SelectFlightObjects.radiobutton_ReturnBlueSkies);
	}

	public void continueFlight(WebDriver driver){
		System.out.println("\n******************** continueFlight ********************\n");	
		
		ReportUtils.reportResult("Done", "Select Flightr", "Select Flight is successful!");

		op.clickLink(driver, SelectFlightObjects.button_Continue);
	}

}
