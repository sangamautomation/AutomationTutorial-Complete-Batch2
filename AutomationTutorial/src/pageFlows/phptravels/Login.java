package pageFlows.phptravels;

import org.openqa.selenium.WebDriver;

import data.Constants;
import infrastructure.Operations;
import pageObjects.phptravels.Phptravels;
import utils.PropertyUtils;

public class Login {

	Operations op = new Operations();
	public void loginPanel(WebDriver driver){
		
		try {
			String username = PropertyUtils.propertyFile_Read(Constants.configPath, "phptravelSupplierUserName");
			String password = PropertyUtils.propertyFile_Read(Constants.configPath, "phptravelSupplierPassword");

			op.setText(driver, Phptravels.textbox_UserName, username);
			op.setText(driver, Phptravels.textbox_Password, password);
			
			op.clickLink(driver, Phptravels.button_Login);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
