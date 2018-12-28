package pageFlows.phptravels;

import org.openqa.selenium.WebDriver;

import data.Constants;
import infrastructure.Operations;
import pageObjects.phptravels.SupplierLogin;
import utils.PropertyUtils;

public class Login {

	Operations op = new Operations();
	public void loginPanel(WebDriver driver){
		
		try {
			String username = PropertyUtils.propertyFile_Read(Constants.configPath, "phptravelSupplierUserName");
			String password = PropertyUtils.propertyFile_Read(Constants.configPath, "phptravelSupplierPassword");

			op.setText(driver, SupplierLogin.textbox_UserName, username);
			op.setText(driver, SupplierLogin.textbox_Password, password);
			
			op.clickLink(driver, SupplierLogin.button_Login);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
