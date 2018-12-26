package pageFlows.phptravels;

import org.openqa.selenium.WebDriver;

import infrastructure.Operations;
import pageObjects.phptravels.Phptravels;

public class Home {
	Operations op = new Operations();

	public void supplier(WebDriver driver){
		op.clickLink(driver, Phptravels.link_SupplierBackend);

		
	}

}
