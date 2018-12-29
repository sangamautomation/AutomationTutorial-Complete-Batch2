package pageFlows.demoaut;

import org.openqa.selenium.WebDriver;

import infrastructure.Operations;
import pageObjects.demoaut.FlightConfirmationObjects;
import utils.ReportUtils;

public class FlightConfirmationPage {
	
	Operations op = new Operations();
	
	public void validateFlightConfirmation(WebDriver driver){
	System.out.println("\n********************** validateFlightConfirmation **********************\n");
	// Capturing the text
	String departFlightPrice = op.getText(driver, FlightConfirmationObjects.text_DepartFlightPrice);
	String returnFlightPrice = op.getText(driver, FlightConfirmationObjects.text_ReturnFlightPrice);
	String noOfPassengers = op.getText(driver, FlightConfirmationObjects.text_NoOfPassenger);
	String tax = op.getText(driver, FlightConfirmationObjects.text_Tax);
	String totalPrice = op.getText(driver, FlightConfirmationObjects.text_TotalPrice);

	
	System.out.println("departFlightPrice = "+departFlightPrice);
	System.out.println("returnFlightPrice = "+returnFlightPrice);
	System.out.println("noOfPassengers = "+noOfPassengers);
	System.out.println("tax = "+tax);
	System.out.println("totalPrice = "+totalPrice);

	//Extract the required text using regular expressions
	
	//Assert Non-Null - Depart Flight Price
	boolean departFlightPriceBoolean = departFlightPrice.matches("[$][0-9]+"); // Use Regular Expression like ^$[0-9]*$
	
	if(departFlightPriceBoolean)
		System.out.println("Pass : The depart flight price is displayed!");
	
	// Assert equals (expected = actual)
	int departBeginIndex = departFlightPrice.indexOf('$')+1; System.out.println("departBeginIndex="+departBeginIndex);
	int departEndIndex = departFlightPrice.lastIndexOf(' '); System.out.println("departEndIndex="+departEndIndex);
	//int departEndIndex2 = departFlightPrice.indexOf("each")-1;

	departFlightPrice = departFlightPrice.substring(departBeginIndex, departEndIndex);
	System.out.println("departFlightPrice="+departFlightPrice);
	
	//Assert Non-Null - Return Flight Price - Validation
	boolean returnFlightPriceBoolean = returnFlightPrice.matches("[$][0-9]+"); // Use Regular Expression like ^$[0-9]*$
	
	
	if(returnFlightPriceBoolean)
		ReportUtils.reportResult("Pass", "Flight Confirmation", "The return flight price is displayed!");
	else
		ReportUtils.reportResult("Fail", "Flight Confirmation", "The return flight price is not displayed!");

	
	// Assert equals (expected = actual)
	int returnBeginIndex = returnFlightPrice.indexOf('$')+1; System.out.println("returnBeginIndex="+returnBeginIndex);
	int returnEndIndex = returnFlightPrice.lastIndexOf(' '); System.out.println("returnEndIndex="+returnEndIndex);
	//int returnEndIndex2 = returnFlightPrice.indexOf("each")-1;

	returnFlightPrice = returnFlightPrice.substring(returnBeginIndex, returnEndIndex);
	System.out.println("returnFlightPrice="+returnFlightPrice);

	
	
	int noOfPassengersIndex = noOfPassengers.indexOf(' ');
	noOfPassengers = noOfPassengers.substring(0, noOfPassengersIndex); //2 passengers
	
	
	int taxIndex = totalPrice.indexOf("USD")-3; System.out.println("taxIndex="+taxIndex);
	tax = tax.substring(1, taxIndex); 	//$90 USD = 90
	  
	
	int totalPriceLastIndex = totalPrice.indexOf(' ');
	totalPrice = totalPrice.substring(1, totalPriceLastIndex); // $11243 USD = 11243
 
	//Converting from String integer
	int departFlightPriceInt = Integer.parseInt(departFlightPrice);
	int returnFlightPriceInt = Integer.parseInt(returnFlightPrice);
	int noOfPassengersInt = Integer.parseInt(noOfPassengers);
	int taxInt = Integer.parseInt(tax);;
	int totalPriceInt = Integer.parseInt(totalPrice);;
	
	System.out.println("departFlightPriceInt = "+ departFlightPriceInt);
	System.out.println("returnFlightPriceInt = "+ returnFlightPriceInt);
	System.out.println("noOfPassengersInt = "+ noOfPassengersInt);
	System.out.println("taxInt = "+ taxInt);
	System.out.println("totalPriceInt = "+ totalPriceInt);

	//ReportUtils.compare(ExpectedResult, ActualResult)

	
	//Calculations
	if(totalPriceInt==(departFlightPriceInt*noOfPassengersInt + returnFlightPriceInt*noOfPassengersInt + taxInt)){
		ReportUtils.reportResult("Pass", "Flight Confirmation Price", "Total Price = "+ totalPriceInt );
	}
	else{
		ReportUtils.reportResult("Fail", "Flight Confirmation Price", "Expected Total Price = "+ BookAFlightValidatePricePage.totalPriceInt +"; Actual Total Price = "+ totalPriceInt );

	}
	
	op.clickLink(driver, FlightConfirmationObjects.button_Logout);
	
	}
}
