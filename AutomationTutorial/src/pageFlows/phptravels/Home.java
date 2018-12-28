package pageFlows.phptravels;

import org.openqa.selenium.WebDriver;

import infrastructure.Operations;
import pageObjects.phptravels.SupplierLogin;

public class Home {
	Operations op = new Operations();

	public void supplier(WebDriver driver){
		op.clickLink(driver, SupplierLogin.link_SupplierBackend);

		
	}

}
