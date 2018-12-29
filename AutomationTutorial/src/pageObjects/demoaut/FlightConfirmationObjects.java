package pageObjects.demoaut;

public class FlightConfirmationObjects {
	
	public static final String text_FlightConfirmation = "//font[contains(text(),'itinerary has been booked!')]";
	public static final String text_DepartFlightPrice = "//table/tbody/tr[3]/td/font";
	public static final String text_ReturnFlightPrice = "//table/tbody/tr[5]/td/font";
	public static final String text_NoOfPassenger = "//table/tbody/tr[7]/td/font";
	public static final String text_Tax = "//table/tbody/tr[1]/td[2]/font/font/font/b/font";
	public static final String text_TotalPrice = "//table/tbody/tr[2]/td[2]/font/b/font/font/b/font";
	public static final String button_Logout = "//img[contains(@src,'Logout')]";

}
