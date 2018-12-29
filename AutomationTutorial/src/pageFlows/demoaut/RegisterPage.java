package pageFlows.demoaut;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;

import data.Constants;
import infrastructure.Operations;
import pageObjects.demoaut.RegistrationObjects;
import utils.ExcelUtils;
import utils.ReportUtils;
import utils.StringUtils;

public class RegisterPage {
	Operations op = new Operations();



	public void registration(WebDriver driver) throws Exception{
		String datapoolPath = Constants.automationDatapoolPath;

 		String sheetName = "Automation";
		int header=0;//Excel first row is 0
		int tc=1;

		//Datapool Reading
		HashMap<String, String> rowData = ExcelUtils.getTestDataXls(datapoolPath, sheetName, header, tc);

		String expectedNote="",actualVal="";
		String expectedUserName =rowData.get("userName");

		System.out.println("\n******************** Registration ********************\n");	
		//Click Register link
		op.clickLink(driver, RegistrationObjects.link_Register);

		// Contact Information

		op.waitImplicitly(driver, 10);

		//Conditional Wait

		if(op.webElement(driver, RegistrationObjects.textBox_FirstName).isEnabled()){
			op.setText(driver, RegistrationObjects.textBox_FirstName, rowData.get("firstName1"));
			op.setText(driver, RegistrationObjects.textBox_LastName, rowData.get("lastName1"));
			op.setText(driver, RegistrationObjects.textBox_Phone, rowData.get("phoneNumber"));
			//driver.findElement(By.xpath(Registration.textBox_Phone)).sendKeys("122223333");
			op.setText(driver, RegistrationObjects.textBox_Email, rowData.get("email"));
		}

		//Mailing Information
		if(op.webElement(driver, RegistrationObjects.textBox_Address1).isDisplayed()){
			op.setText(driver, RegistrationObjects.textBox_Address1, rowData.get("address1"));
			op.setText(driver, RegistrationObjects.textBox_Address2, rowData.get("address2"));
			op.setText(driver, RegistrationObjects.textBox_City, rowData.get("city"));
			op.setText(driver, RegistrationObjects.textBox_StateProvince, rowData.get("state"));
			op.setText(driver, RegistrationObjects.textBox_PostalCode, rowData.get("zipcode"));
		}

		//Selecting Dropdown value
		if(op.webElement(driver, RegistrationObjects.dropdown_Country).isSelected()){
			op.selectDropdown(driver, RegistrationObjects.dropdown_Country, "UNITED STATES");
		}
			//User Information
			op.setText(driver, RegistrationObjects.textBox_UserName, expectedUserName);
			op.setText(driver, RegistrationObjects.textBox_Password, rowData.get("password"));
			op.setText(driver, RegistrationObjects.textBox_ConfirmPassword, rowData.get("password"));

			ReportUtils.reportResult("Done", "Registration", "Registration is successful!");

			op.clickLink(driver, RegistrationObjects.button_Submit);

			//Synchronization methods
			op.waitImplicitly(driver, 20);
			op.waitExplicitly(driver, 10, RegistrationObjects.text_Note);

			//Confirmation
			expectedNote = "Note: Your user name is Username2.";

			String actualNote = op.getText(driver, RegistrationObjects.text_Note);
			String actualUserName = StringUtils.subString(actualNote, 24, 33);
			//String actualUserName = actualNote.substring(24, 33);
			System.out.println("Actual Note: "+ actualNote);
			System.out.println("Actual UserName: "+ actualUserName);

		//	ReportUtils.compare(expectedNote, actualNote);

			//Note Validation
			boolean noteValidation = expectedNote.equals(actualNote);

			if(noteValidation)
				ReportUtils.reportResult("Pass", "Note Validation", "The Note : "+ actualNote +" is matching!");

			else
				ReportUtils.reportResult("Fail", "Note Validation", "Note is Not Matching \n"+ "Expected Note: "+expectedNote + "\n Actual Note: "+actualNote);



			//UserName validation
			if(expectedUserName.equals(actualUserName))
				ReportUtils.reportResult("Pass", "UserName Validation", "The expected Username : \n"+ expectedUserName +" is matching!");
			else
				ReportUtils.reportResult("Fail", "UserName Validation", "User name is Not Matching \n"+ "Expected Username: "+expectedUserName +"\n Actual Username: "+actualUserName);

		}

	}

