package pageFlows.demoautNoDp;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import pageObjects.demoaut.RegistrationObjects;

public class Register {

	String expectedNote="",actualVal="";
	String expectedUserName ="Username2";
	public void registration(WebDriver driver) throws Exception{

		//Click Register link
		driver.findElement(By.xpath(RegistrationObjects.link_Register)).click();

		// Contact Information
		driver.findElement(By.xpath(RegistrationObjects.textBox_FirstName)).sendKeys("John");
		driver.findElement(By.xpath(RegistrationObjects.textBox_LastName)).sendKeys("Doe");
		driver.findElement(By.xpath(RegistrationObjects.textBox_Phone)).sendKeys("122223333");
		driver.findElement(By.xpath(RegistrationObjects.textBox_Email)).sendKeys("user@domain.com");

		//Mailing Information
		driver.findElement(By.xpath(RegistrationObjects.textBox_Address1)).sendKeys("123 Main St");
		driver.findElement(By.xpath(RegistrationObjects.textBox_Address2)).sendKeys("Suite# A");
		driver.findElement(By.xpath(RegistrationObjects.textBox_City)).sendKeys("New York City");
		driver.findElement(By.xpath(RegistrationObjects.textBox_StateProvince)).sendKeys("New York");
		driver.findElement(By.xpath(RegistrationObjects.textBox_PostalCode)).sendKeys("11370");

		//Selecting Dropdown value
		WebElement ele = driver.findElement(By.xpath(RegistrationObjects.dropdown_Country));
		Select sel = new Select(ele);
		sel.selectByVisibleText("UNITED STATES");

		//User Information
		WebElement we = driver.findElement(By.xpath(RegistrationObjects.textBox_UserName));
		we.sendKeys(expectedUserName);

		driver.findElement(By.xpath(RegistrationObjects.textBox_Password)).sendKeys("password123");
		driver.findElement(By.xpath(RegistrationObjects.textBox_ConfirmPassword)).sendKeys("password123");
		driver.findElement(By.xpath(RegistrationObjects.button_Submit)).click();

		//Synchronization methods
		//	Thread.sleep(5000);//Static wait for 5 seconds
		//	driver.manage().wait(5000);//Static wait
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);//Implicit wait
		//	WebDriverWait webwait = new WebDriverWait(driver, 10); // Explicit Wait/Dynamic Wait (Waits for a particular element for a conditions) 
		//		webwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@name,'firstName')]")));


		//Confirmation
		String expectedNote = "Note: Your user name is Username2.";

		String actualNote = driver.findElement(By.xpath(RegistrationObjects.text_Note)).getText();
		String actualUserName = actualNote.substring(24, 33);
		System.out.println("Actual Note: "+ actualNote);
		System.out.println("Actual UserName: "+ actualUserName);


		//Note Validation
		if(expectedNote.equals(actualNote))
			System.out.println("PASS - "+"The Note : "+ actualNote +" is matching!");
		else
			System.out.println("FAIL - Not Matching \n"+ "Expected Note: "+expectedNote + "\n Actual Note: "+actualNote);

		System.out.println("#################################################");

		//UserName validation
		if(expectedUserName.equals(actualUserName))
			System.out.println("PASS - "+"The expected Username : \n"+ expectedUserName +" is matching!");
		else
			System.out.println("FAIL - Not Matching \n"+ "Expected Username: "+expectedUserName +"\n Actual Username: "+actualUserName);

	}

}
