package pageFlows.phptravels;

import org.openqa.selenium.WebDriver;

import infrastructure.Operations;
import pageObjects.phptravels.Quickbookings;
import utils.DateUtils;
import utils.ReportUtils;

public class QuickBookingsPage {
	Operations op = new Operations();

	public void quickbookings(WebDriver driver){
		new Operations().clickLink(driver, Quickbookings.button_QuickBookings);
		new Operations().waitImplicitly(driver, 5);
	}

	public void popupService(WebDriver driver){
		try {
			new Operations().selectDropdown(driver, Quickbookings.dropdown_Service, "Hotels");
			new Operations().clickLink(driver, Quickbookings.button_Next);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void formBooking(WebDriver driver) throws Exception{
		
		op.setText(driver, Quickbookings.textbox_Checkin, DateUtils.date_Now("M/dd/yyyy"));
		op.setText(driver, Quickbookings.textbox_Checkout, DateUtils.dateTodayPlus(2));

	String totalNights_Actual =	op.getText(driver, Quickbookings.textLabel_TotalNights);
	String totalNights_Expected = "1";
	
	//Validation
	
	ReportUtils.compare(totalNights_Expected, totalNights_Actual);
	
	
	op.setText(driver, Quickbookings.textbox_PerNightPrice, "80");
	
	//Validation
	//TODO Add validaiton for Booking Summary
	
	op.clickLink(driver, Quickbookings.button_BookNow);
	
	//Logout
	//TODO add function for logout
	}

}
