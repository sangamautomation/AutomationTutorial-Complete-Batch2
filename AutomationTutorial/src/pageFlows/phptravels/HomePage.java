package pageFlows.phptravels;

import org.openqa.selenium.WebDriver;

import infrastructure.Operations;
import pageObjects.phptravels.SupplierLoginObjects;

public class HomePage {
	Operations op = new Operations();

	public void supplier(WebDriver driver){
		op.clickLink(driver, SupplierLoginObjects.link_SupplierBackend);

		
	}

}
