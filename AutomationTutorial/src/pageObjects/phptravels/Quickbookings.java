package pageObjects.phptravels;

public class Quickbookings {

	public static final String button_QuickBookings = "//button[contains(@data-target,'quickbook')]";
	public static final String dropdown_Service = "//select[contains(@id,'servicetype')]";
	
	public static final String button_Next = "//button[text()='Next']";

	public static final String textbox_Checkin = "//input[@name='checkin' and @id='Hotels']";
	public static final String textbox_Checkout = "//input[@name='checkout' and @placeholder = 'Date']";
	public static final String textLabel_TotalNights = "//input[@name='stay']";
	public static final String textbox_PerNightPrice = "//input[@id='roomtotal']";
	public static final String textbox_TotalDeposit = "//input[@id='totaltopay']";
	
	public static final String textLabel_BookingSummaryTotalRoomAmount = "//span[@id='summaryroomtotal']";
	public static final String textLabel_BookingSummaryDeposit = "//td[@id='topaytotal']";
	public static final String textLabel_BookingSummaryGrandTotal = "//td[@id='grandtotal']";
	
	public static final String button_BookNow = "//input[@value='Book Now']";



	

	

}
