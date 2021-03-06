package pageFlows.phptravels;

import org.openqa.selenium.WebDriver;

import infrastructure.Operations;
import pageObjects.phptravels.QuickbookingsObjects;
import utils.DateUtils;
import utils.ReportUtils;

public class QuickBookingsPage {
	Operations op = new Operations();

	public void quickbookings(WebDriver driver){
		new Operations().clickLink(driver, QuickbookingsObjects.button_QuickBookings);
		new Operations().waitImplicitly(driver, 5);
	}

	public void popupService(WebDriver driver){
		try {
			new Operations().selectDropdown(driver, QuickbookingsObjects.dropdown_Service, "Hotels");
			new Operations().clickLink(driver, QuickbookingsObjects.button_Next);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void formBooking(WebDriver driver) throws Exception{
		
		op.setText(driver, QuickbookingsObjects.textbox_Checkin, DateUtils.date_Now("M/dd/yyyy"));
		op.setText(driver, QuickbookingsObjects.textbox_Checkout, DateUtils.dateTodayPlus(2));

	String totalNights_Actual =	op.getText(driver, QuickbookingsObjects.textLabel_TotalNights);
	String totalNights_Expected = "1";
	
	//Validation
	
	ReportUtils.compare(totalNights_Expected, totalNights_Actual);
	
	
	op.setText(driver, QuickbookingsObjects.textbox_PerNightPrice, "80");
	
	//Validation
	//TODO Add validaiton for Booking Summary
	
	op.clickLink(driver, QuickbookingsObjects.button_BookNow);
	
	//Logout
	//TODO add function for logout
	}

}
