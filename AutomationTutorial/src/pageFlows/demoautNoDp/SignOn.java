package pageFlows.demoautNoDp;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pageObjects.demoaut.SigninObjects;

public class SignOn {
	
	public void signin(WebDriver driver){
		driver.findElement(By.xpath(SigninObjects.link_Signin)).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath(SigninObjects.textBox_UserName)).sendKeys("Username2");
		driver.findElement(By.xpath(SigninObjects.textBox_Password)).sendKeys("Password123");
		driver.findElement(By.xpath(SigninObjects.button_Signin)).click();


	}

}
