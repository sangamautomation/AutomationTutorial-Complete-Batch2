package pageFlows.demoaut;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;

import data.Constants;
import infrastructure.Operations;
import pageObjects.demoaut.BookAFlightObjects;
import infrastructure.Operations;
import utils.ExcelUtils;
import utils.ReportUtils;

public class BookAFlightValidatePricePage {
	Operations op = new Operations();
	static int totalPriceInt=0;

	
	
	//Price Validation
	public void validatePrice(WebDriver driver) throws Exception{
		System.out.println("\n********************* validatePrice *********************\n");

		
		//Capturing text
		String departFlightPrice = op.getText(driver, BookAFlightObjects.text_DepartFlightPrice);
		//String departFlightPrice = driver.findElement(By.xpath(BookAFlight.text_DepartFlightPrice)).getText();
		String returnFlightPrice = op.getText(driver, BookAFlightObjects.text_ReturnFlightPrice);
		String noOfPassengers = op.getText(driver, BookAFlightObjects.text_NoOfPassengers);
		String tax = op.getText(driver, BookAFlightObjects.text_Tax);
		String totalPrice = op.getText(driver, BookAFlightObjects.text_TotalPrice);

		//Converting String to Integer
		int departFlightPriceInt = Integer.parseInt(departFlightPrice);
		int returnFlightPriceInt = Integer.parseInt(returnFlightPrice);
		int noOfPassengersInt = Integer.parseInt(noOfPassengers);

		//Getting rid of any special character
		tax = tax.substring(1);
		int taxInt = Integer.parseInt(tax);

		totalPrice = totalPrice.substring(1);
		totalPriceInt = Integer.parseInt(totalPrice);

		//Book Flight - Validation

		int departReturnFlightPrice = departFlightPriceInt+returnFlightPriceInt;

		if((departReturnFlightPrice*noOfPassengersInt)+taxInt == totalPriceInt)
			ReportUtils.reportResult("Pass", "Book A Flight - Total Price", "Total price displayed is correct!");

		else
			ReportUtils.reportResult("Fail", "Book A Flight - Total Price", "Total price displayed is NOT correct!");

	}


	//Passenger
	public void passengersInfo(WebDriver driver) throws Exception{
		System.out.println("\n********************* passengersInfo *********************\n");
		String filePath = Constants.automationDatapoolPath;

		String sheetName = "Automation";
		int headerRowNum = 0;
		int tcRowNum = 1;
		
		HashMap<String,String> rowData = ExcelUtils.getTestDataXls(filePath, sheetName, headerRowNum, tcRowNum);
		
	//	op.switchToFrame(driver, "Xpath of the frame"); //if the objects are inside frame
		op.setText(driver, BookAFlightObjects.textBox_FirstName1, rowData.get("firstName1"));
		op.setText(driver, BookAFlightObjects.textBox_LastName1, rowData.get("lastName1"));
		op.setText(driver, BookAFlightObjects.textBox_FirstName2, rowData.get("firstName2"));
		op.setText(driver, BookAFlightObjects.textBox_LastName2, rowData.get("lastName2"));
	//	op.switchToDefault(driver); //if the objects are inside frame and to switch back
	}

	//Credit Card
	public void creditCardInfo(WebDriver driver) throws Exception{
		
		String filePath = Constants.automationDatapoolPath;
 		String sheetName = "Automation";
		int headerRowNum = 0;
		int tcRowNum = 1;
		
		HashMap<String,String> rowData = ExcelUtils.getTestDataXls(filePath, sheetName, headerRowNum, tcRowNum);
		
		System.out.println("\n********************* creditCardInfo *********************\n");

		op.selectDropdown(driver, BookAFlightObjects.dropdown_CreditCardType, rowData.get("creditCardType"));
		op.setText(driver, BookAFlightObjects.textBox_CreditCardNumber, rowData.get("creditCardNumber"));

		op.selectDropdown(driver, BookAFlightObjects.dropdown_CreditCardExpirationMonth, rowData.get("CreditCardExpirationMonth"));
		op.selectDropdown(driver, BookAFlightObjects.dropdown_CreditCardExpirationYear, rowData.get("CreditCardExpirationYear"));


		//Checkbox Operation
		op.clickCheckbox(driver, BookAFlightObjects.checkbox_TicketlessTravel);

		ReportUtils.reportResult("Done", "Book A Flight", "Book A Flight is successful!");

	
		//Submit
		op.clickLink(driver, BookAFlightObjects.button_SecurePurchase);

	}

}
