package pageFlows.demoautNoDp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pageObjects.demoaut.FlightConfirmationObjects;

public class FlightConfirmationValidation {
	
	
	public static void validateFlightConfirmation(WebDriver driver){
	
	// Capturing the text
	String departFlightPrice = driver.findElement(By.xpath(FlightConfirmationObjects.text_DepartFlightPrice)).getText();
	String returnFlightPrice = driver.findElement(By.xpath(FlightConfirmationObjects.text_ReturnFlightPrice)).getText();
	String noOfPassengers = driver.findElement(By.xpath(FlightConfirmationObjects.text_NoOfPassenger)).getText();
	String tax = driver.findElement(By.xpath(FlightConfirmationObjects.text_Tax)).getText();
	String totalPrice = driver.findElement(By.xpath(FlightConfirmationObjects.text_TotalPrice)).getText();
	
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
	
	//Assert Non-Null - Return Flight Price
	boolean returnFlightPriceBoolean = returnFlightPrice.matches("[$][0-9]+"); // Use Regular Expression like ^$[0-9]*$
	if(returnFlightPriceBoolean)
		System.out.println("Pass : The return flight price is displayed!");
	
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

	//TODO Calculations
	if(totalPriceInt==(departFlightPriceInt*noOfPassengersInt + returnFlightPriceInt*noOfPassengersInt + taxInt)){
		System.out.println("Validation Pass :: "+ "Total Price = "+ totalPriceInt );
	}
	else{
		System.out.println("Validation Fail :: "+ "Expected Total Price = "+ BookAFlightValidatePrice.totalPriceInt +"; Actual Total Price = "+ totalPriceInt);
	}
	
	
	driver.findElement(By.xpath(FlightConfirmationObjects.button_Logout)).click();
	
	
	}
}
