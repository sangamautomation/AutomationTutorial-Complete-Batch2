package pageFlows.demoautNoDp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import pageObjects.demoaut.BookAFlightObjects;

public class BookAFlightValidatePrice {
	
	static int totalPriceInt=0;
	//Price Validation
	public static void validatePrice(WebDriver driver){
		//Capturing text
		String departFlightPrice = driver.findElement(By.xpath(BookAFlightObjects.text_DepartFlightPrice)).getText();
		String returnFlightPrice = driver.findElement(By.xpath(BookAFlightObjects.text_ReturnFlightPrice)).getText();
		String noOfPassengers = driver.findElement(By.xpath(BookAFlightObjects.text_NoOfPassengers)).getText();
		String tax = driver.findElement(By.xpath(BookAFlightObjects.text_Tax)).getText();
		String totalPrice = driver.findElement(By.xpath(BookAFlightObjects.text_TotalPrice)).getText();

		//Converting String to Integer
		int departFlightPriceInt = Integer.parseInt(departFlightPrice);
		int returnFlightPriceInt = Integer.parseInt(returnFlightPrice);
		int noOfPassengersInt = Integer.parseInt(noOfPassengers);
		
		//Getting rid of any special character
		tax = tax.substring(1);
		int taxInt = Integer.parseInt(tax);
		
		totalPrice = totalPrice.substring(1);
	   totalPriceInt = Integer.parseInt(totalPrice);
		
		//Validation
		
		int departReturnFlightPrice = departFlightPriceInt+returnFlightPriceInt;
		if((departReturnFlightPrice*noOfPassengersInt)+taxInt == totalPriceInt)
			System.out.println("PASS : Total price displayed is correct!");
		else
			System.out.println("FAIL : Total price displayed is NOT correct!");
	
	}
	
	
	//Passenger
	public static void passengersInfo(WebDriver driver){
		driver.findElement(By.xpath(BookAFlightObjects.textBox_FirstName1)).sendKeys("FirstName1");
		driver.findElement(By.xpath(BookAFlightObjects.textBox_LastName1)).sendKeys("LastName1");
		driver.findElement(By.xpath(BookAFlightObjects.textBox_FirstName2)).sendKeys("FirstName2");
		driver.findElement(By.xpath(BookAFlightObjects.textBox_LastName2)).sendKeys("LastName2");

	}
	
	//Credit Card
	public static void creditCardInfo(WebDriver driver){
		WebElement we = driver.findElement(By.xpath(BookAFlightObjects.dropdown_CreditCardType));
		Select sel1 = new Select(we);
		sel1.selectByVisibleText("Discover");
		
		driver.findElement(By.xpath(BookAFlightObjects.textBox_CreditCardNumber)).sendKeys("1234567890123456");
		
		Select sel2 = new Select(driver.findElement(By.xpath(BookAFlightObjects.dropdown_CreditCardExpirationMonth)));
		sel2.selectByVisibleText("12");
		
		new Select(driver.findElement(By.xpath(BookAFlightObjects.dropdown_CreditCardExpirationYear))).selectByVisibleText("2010");
		
		//Checkbox Operation
		driver.findElement(By.xpath(BookAFlightObjects.checkbox_TicketlessTravel)).click();
		
		//Submit
		driver.findElement(By.xpath(BookAFlightObjects.button_SecurePurchase)).click();
		
		
		
	}

}
