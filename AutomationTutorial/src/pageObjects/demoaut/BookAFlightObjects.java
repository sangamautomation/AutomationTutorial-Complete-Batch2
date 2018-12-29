package pageObjects.demoaut;

public class BookAFlightObjects {
	
	//Summary
	public static final String text_DepartFlightPrice = "//table/tbody/tr[3]/td[3]";
	public static final String text_ReturnFlightPrice = "//table/tbody/tr[6]/td[3]";
	public static final String text_NoOfPassengers = "//table/tbody/tr[5]/td/form/table/tbody/tr[2]/td/table/tbody/tr[7]/td[2]";
	public static final String text_Tax = "//table/tbody/tr[5]/td/form/table/tbody/tr[2]/td/table/tbody/tr[8]/td[2]";
	public static final String text_TotalPrice = "//table/tbody/tr[5]/td/form/table/tbody/tr[2]/td/table/tbody/tr[9]/td[2]";

	//Passengers
	public static final String textBox_FirstName1 = "//input[contains(@name,'passFirst0')]";
	public static final String textBox_LastName1 = "//input[contains(@name,'passLast0')]";
	public static final String textBox_FirstName2 = "//input[contains(@name,'passFirst1')]";
	public static final String textBox_LastName2 = "//input[contains(@name,'passLast1')]";
	
	//Credit Card
	
	public static final String dropdown_CreditCardType = "//select[contains(@name,'creditCard')]";
	public static final String dropdown_CreditCardExpirationMonth = "//select[contains(@name,'cc_exp_dt_mn')]";
	public static final String dropdown_CreditCardExpirationYear = "//select[contains(@name,'cc_exp_dt_yr')]";
	public static final String textBox_CreditCardNumber = "//input[contains(@name,'creditnumber')]";
	public static final String checkbox_TicketlessTravel = "//table/tbody/tr[9]/td[2]//input[contains(@name,'ticketLess') and contains(@value,'checkbox')]";
	public static final String button_SecurePurchase = "//input[@name='buyFlights']";

}
