package tests.playground;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import data.Constants;
import setup.TestRunSetup;

public class MultipleActions {

	public static void main(String[] args) {
		WebDriver driver = TestRunSetup.launch("http://www.demoaut.com", "firefox", Constants.CHROMEDRIVER_PATH);

		WebElement userName = driver.findElement(By.xpath("//input[contains(@name,'userName')]"));

		Actions builder = new Actions(driver);
		Action seriesOfActions = builder
				.moveToElement(userName)
				.click()
				.keyDown(userName, Keys.SHIFT)
				.sendKeys(userName, "hello")
				.doubleClick(userName)
				.contextClick(userName)
				.build();
		seriesOfActions.perform();


	}

}
