package pageFlows.demoaut;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;

import data.Constants;
import infrastructure.Operations;
import pageObjects.demoaut.FlightFinderObjects;
import utils.ExcelUtils;
import utils.ReportUtils;

public class FlightFinderPage {

	Operations op = new Operations();



	public void flightFinder(WebDriver driver) throws Exception{
		System.out.println("\n******************** Flight Finder ********************\n");	

		String filePath = Constants.automationDatapoolPath;
		String sheetName = "Automation";
		int headerRowNum = 0;
		int tcRowNum = 1;

		HashMap<String,String> rowData = ExcelUtils.getTestDataXls(filePath, sheetName, headerRowNum, tcRowNum);


		// Clicking on Flights link
		op.clickLink(driver, FlightFinderObjects.link_Flights);
		//driver.findElement(By.xpath(FlightFinder.link_Flights)).click();
		op.waitImplicitly(driver, 10);
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		//Flight Finder
		if(op.webElement(driver, FlightFinderObjects.radiobutton_FlightTypeOneway).isSelected())
			op.clickRadiobutton(driver, FlightFinderObjects.radiobutton_FlightType("roundtrip"));

		op.selectDropdown(driver, FlightFinderObjects.dropdown_Passengers, rowData.get("noOfPassengers"));
		op.selectDropdown(driver, FlightFinderObjects.dropdown_DepartFrom, rowData.get("departFrom"));
		op.selectDropdown(driver, FlightFinderObjects.dropdown_OnMonth, rowData.get("departMonth"));
		op.selectDropdown(driver, FlightFinderObjects.dropdown_OnDay, rowData.get("departDay"));
		op.selectDropdown(driver, FlightFinderObjects.dropdown_ArrivingIn, rowData.get("arrivingIn"));


		// Preferences
		op.clickRadiobutton(driver, FlightFinderObjects.radiobutton_ServiceClassFirstClass);

		ReportUtils.reportResult("Done", "Flight Finder", "Flight Finder is successful!");

		op.clickLink(driver, FlightFinderObjects.button_Continue);

	}

}
