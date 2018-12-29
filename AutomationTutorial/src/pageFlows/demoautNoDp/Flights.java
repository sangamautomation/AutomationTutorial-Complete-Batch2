package pageFlows.demoautNoDp;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import pageObjects.demoaut.FlightFinderObjects;

public class Flights {
	
public void flightFinder(WebDriver driver){
	// Clicking on Flights link
	driver.findElement(By.xpath(FlightFinderObjects.link_Flights)).click();
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	
	//Flight Finder
	//driver.findElement(By.xpath(FlightFinder.radiobutton_FlightTypeOneway)).click();
	driver.findElement(By.xpath(FlightFinderObjects.radiobutton_FlightTypeRoundtrip)).click();

	Select sel1 = new Select(driver.findElement(By.xpath(FlightFinderObjects.dropdown_Passengers)));
	sel1.selectByVisibleText("2");
	
	Select sel2 = new Select(driver.findElement(By.xpath(FlightFinderObjects.dropdown_DepartFrom)));
	sel2.selectByVisibleText("New York");
	
	Select sel3 = new Select(driver.findElement(By.xpath(FlightFinderObjects.dropdown_OnMonth)));
	sel3.selectByVisibleText("November");
	
	Select sel4 = new Select(driver.findElement(By.xpath(FlightFinderObjects.dropdown_OnDay)));
	sel4.selectByValue("25");
	
	Select sel5 = new Select(driver.findElement(By.xpath(FlightFinderObjects.dropdown_ArrivingIn)));
	sel5.selectByVisibleText("Paris");
	
// Preferences
	
	driver.findElement(By.xpath(FlightFinderObjects.radiobutton_ServiceClassFirstClass)).click();
	
	driver.findElement(By.xpath(FlightFinderObjects.button_Continue)).click();
	
}

}
