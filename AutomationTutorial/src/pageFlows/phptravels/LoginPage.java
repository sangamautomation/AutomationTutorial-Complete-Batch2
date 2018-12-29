package pageFlows.phptravels;

import org.openqa.selenium.WebDriver;

import data.Constants;
import infrastructure.Operations;
import pageObjects.phptravels.SupplierLoginObjects;
import utils.PropertyUtils;

public class LoginPage {

	Operations op = new Operations();
	public void loginPanel(WebDriver driver){
		
		try {
			String username = PropertyUtils.propertyFile_Read(Constants.configPath, "phptravelSupplierUserName");
			String password = PropertyUtils.propertyFile_Read(Constants.configPath, "phptravelSupplierPassword");

			op.setText(driver, SupplierLoginObjects.textbox_UserName, username);
			op.setText(driver, SupplierLoginObjects.textbox_Password, password);
			
			op.clickLink(driver, SupplierLoginObjects.button_Login);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
