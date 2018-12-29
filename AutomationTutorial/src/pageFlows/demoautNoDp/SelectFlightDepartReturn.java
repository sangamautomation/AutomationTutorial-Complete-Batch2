package pageFlows.demoautNoDp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pageObjects.demoaut.SelectFlightObjects;

public class SelectFlightDepartReturn {

	public void departFlight(WebDriver driver){
		
		driver.findElement(By.xpath(SelectFlightObjects.radiobutton_DepartUnitedAirlines)).click();
		
	}
	
	public void returnFlight(WebDriver driver){
		
		driver.findElement(By.xpath(SelectFlightObjects.radiobutton_ReturnBlueSkies)).click();
		
	}
	
	public void continueFlight(WebDriver driver){
		driver.findElement(By.xpath(SelectFlightObjects.button_Continue)).click();

	}
	
}
