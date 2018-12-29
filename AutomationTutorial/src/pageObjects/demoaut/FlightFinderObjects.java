package pageObjects.demoaut;

public class FlightFinderObjects {

	//Flight link after successful registration
	public static final String link_Flights = "//a[contains(text(),'Flights')]";

	//Flight Details
	public static final String radiobutton_FlightTypeOneway = "//input[contains(@name,'tripType') and contains(@value,'oneway')]";
	public static final String radiobutton_FlightTypeRoundtrip = "//input[contains(@value,'roundtrip')]";
	public static final String dropdown_Passengers = "//select[contains(@name,'passCount')]";
	public static final String dropdown_DepartFrom = "//select[contains(@name,'fromPort')]";
	public static final String dropdown_OnMonth = "//select[contains(@name,'fromMonth')]";
	public static final String dropdown_OnDay = "//select[contains(@name,'fromDay')]";
	public static final String dropdown_ArrivingIn = "//select[contains(@name,'toPort')]";
	public static final String dropdown_ReturningMonth = "//select[contains(@name,'toMonth')]";
	public static final String dropdown_ReturningDay = "//select[contains(@name,'toDay')]";

	//Preferences
	public static final String radiobutton_ServiceClassEconomyClass = "//input[contains(@value,'Coach')]";
	public static final String radiobutton_ServiceClassBusinessClass = "//input[contains(@value,'Business')]";
	public static final String radiobutton_ServiceClassFirstClass = "//input[contains(@value,'First')]";
	public static final String dropdown_Airline = "//select[contains(@name,'airline')]";
	public static final String button_Continue = "//input[contains(@name,'findFlights')]";

	public static final String radiobutton_FlightType(String input){
		return "//input[contains(@name,'tripType') and contains(@value,'"+input+"')]";
	}
}
